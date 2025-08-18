package com.stripe.system.impl;


import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stripe.system.centralPoint.http.HttpRequest;
import com.stripe.system.centralPoint.http.HttpServiceEngine;
import com.stripe.system.constant.Constants;
import com.stripe.system.constant.TransactionStatusEnum;
import com.stripe.system.dao.TransactionDao;
import com.stripe.system.dto.InitiatePaymentDTO;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.exception.ProcessingException;
import com.stripe.system.exception.SPErrorResponse;
import com.stripe.system.exception.exEnum.ErrorCodeEnum;
import com.stripe.system.service.interfaces.PaymentService;
import com.stripe.system.service.interfaces.PaymentStatusService;
import com.stripe.system.stripeProvider.CreatePaymentReq;
import com.stripe.system.stripeProvider.PaymentRes;
import com.stripe.system.stripeProvider.PaymentResDto;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentStatusService paymentStatusService;
	
	@Autowired
	private HttpServiceEngine httpServiceEngine;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Value("${stripe.create_session.url}")
	private String stripeUrl;

	@Override
	public TranactionDTO createPayment(TranactionDTO tranactionDTO) {
		System.out.println("In PaymentServiceImpl recevice Dto:"+tranactionDTO);
		
		tranactionDTO.setTxnStatus(TransactionStatusEnum.CREATED.getName());
		tranactionDTO.setTxnReference(generatedTxnRefernce());
	TranactionDTO txnDto=paymentStatusService.processStatus(tranactionDTO);
		return txnDto;
	}

	private String generatedTxnRefernce() {
		return UUID.randomUUID().toString();
	}

	@Override
	public TranactionDTO initiatePayment(String txnreference,InitiatePaymentDTO reqDto) {
		System.out.println("In PaymentServiceImpl initiatePayment invoked***");
		
		
		TranactionDTO txnDto=transactionDao.getTransactionByRef(txnreference);
		
		System.out.println(" ***IN PaymentServiceImpl initiatePayment recevice Dto from DB:"+txnDto);
		
		
		txnDto.setTxnStatus(TransactionStatusEnum.INITIATED.getName());
	
		
		
		HttpRequest httpRequest = prepareHttpReq(reqDto);
		
		try {
			ResponseEntity<String> httpRes=httpServiceEngine.makeHttpCall(httpRequest);
			PaymentResDto paymentResDto=processResponse(httpRes);
			
			System.out.println("In initiatePayment got res from processResponse:"+paymentResDto);
			
			txnDto.setTxnStatus(TransactionStatusEnum.PENDING.getName());
			txnDto.setProviderReference(paymentResDto.getId());
			txnDto.setUrl(paymentResDto.getUrl());
			paymentStatusService.processStatus(txnDto);
			
			System.out.println("Successfully got url & updated in DB:"+txnDto);
			//System.out.println("Response from Stripe Provider Service:"+httpRes);
			return txnDto;
			
		}catch(ProcessingException e){
			txnDto.setTxnStatus(TransactionStatusEnum.FAILED.getName());
			txnDto.setErrorCode(e.getErrorCode());
			txnDto.setErrorMessage(e.getErrorMessage());
			paymentStatusService.processStatus(txnDto);
			
			System.out.println("Successfully ErrorCode  & ErrorMessage updated in DB:"+txnDto);
			if(HttpStatus.BAD_REQUEST.equals(e.getHttpStatus())) {
				throw new ProcessingException(ErrorCodeEnum.ERROR_AT_STRIPE_PSP.getErrorCode(),
						ErrorCodeEnum.ERROR_AT_STRIPE_PSP.getErrorMessage(), e.getHttpStatus());
			}
			throw e;
			
		}
	}
	

	private HttpRequest prepareHttpReq(InitiatePaymentDTO reqDto) {
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE );

		CreatePaymentReq paymentReq=mapper.map(reqDto, CreatePaymentReq.class);
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setMethod(HttpMethod.POST);

		httpRequest.setUrl(stripeUrl);
		httpRequest.setHeaders(httpHeaders);
	
		httpRequest.setRequestBody(gson.toJson(paymentReq));
		return httpRequest;
	}
	
	private PaymentResDto processResponse(ResponseEntity<String> httpRes) {
		if(httpRes.getStatusCode().isSameCodeAs(HttpStatus.CREATED)) {
			PaymentRes spPaymentRes=gson.fromJson(httpRes.getBody(), PaymentRes.class);//change gson to gsonUtils
			System.out.println("In processResponse Converted httpRes to PaymentRes:"+spPaymentRes);
			
			
			if(spPaymentRes != null && spPaymentRes.getUrl() !=null) {
				PaymentResDto paymentResDto=mapper.map(spPaymentRes, PaymentResDto.class);
				System.out.println("In processResponse Converted spPaymentRes to PaymentResDto:"+paymentResDto);
				return paymentResDto;
			}
			System.out.println("Got 201 but no url in response");
		}
		
		SPErrorResponse errorRes=gson.fromJson(httpRes.getBody(),SPErrorResponse.class);
		System.out.println("Convert HttpRes Body to SPErrorResponse");
		if(errorRes!=null && errorRes.getErrorCode()!=null) {
			throw new ProcessingException(errorRes.getErrorCode(), errorRes.getErrorMessage(), 
					HttpStatus.valueOf(httpRes.getStatusCode().value()));
			
		}
		
		throw new ProcessingException(ErrorCodeEnum.GENERIC_ERROR.getErrorCode(), 
				ErrorCodeEnum.GENERIC_ERROR.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}









