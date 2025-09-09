 package com.stripe.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.stripe.system.centralPoint.http.HttpServiceEngine;
import com.stripe.system.constant.TransactionStatusEnum;
import com.stripe.system.dao.TransactionDao;
import com.stripe.system.dto.InitiatePaymentDTO;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.impl.PaymentServiceImpl;
import com.stripe.system.service.interfaces.PaymentStatusService;
import com.stripe.system.stripeProvider.CreatePaymentReq;
import com.stripe.system.stripeProvider.PaymentRes;
import com.stripe.system.stripeProvider.PaymentResDto;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
	
	@InjectMocks
	private PaymentServiceImpl paymentServiceImpl;
	
	@Mock
	private PaymentStatusService paymentStatusService;
	
	@Mock
	private TransactionDao transactionDao;
	
	@Mock
	private Gson gson;

	@Mock
	private HttpServiceEngine httpServiceEngine;
	
	@Mock
	private ModelMapper mapper;

	
	//@Test
	public void testCreatePayment() {
		System.out.println("Test create payment");
		
		//arrange
		TranactionDTO txnDto=new TranactionDTO();
		
		//act
		paymentServiceImpl.createPayment(txnDto);
		
		//assert
		//in function code logic is to status should be create
		assertEquals(TransactionStatusEnum.CREATED.getName(), txnDto.getTxnStatus());
		
		//and txnReference should be generated 
		assertNotNull(txnDto.getTxnReference());
	}
	
	@Test
	public void testinitiatePayment() {
		System.out.println("Test Initiate payment");
		
		//arrange
		TranactionDTO txnDto=new TranactionDTO();
		
		InitiatePaymentDTO reqDto=new InitiatePaymentDTO();
		String txnRef="txnRef1";
		
		//for TranactionDTO txnDto=transactionDao.getTransactionByRef(txnRef); below logic
		
		when(transactionDao.getTransactionByRef(txnRef)).thenReturn(txnDto);
		
		//for==>ResponseEntity<String> httpRes=httpServiceEngine.makeHttpCall(httpRequest);

		ResponseEntity<String> httpRes=new ResponseEntity<String>("",HttpStatus.CREATED);
		when(httpServiceEngine.makeHttpCall(any())).thenReturn(httpRes);
		
		PaymentRes paymentRes=new PaymentRes();
		paymentRes.setUrl("http://test.com");
		when(gson.fromJson(anyString(),eq( PaymentRes.class))).thenReturn(paymentRes);
		
		
		when(mapper.map(any(), eq(CreatePaymentReq.class))).thenReturn(new CreatePaymentReq());
		
		PaymentResDto paymentResDto=new PaymentResDto();
		paymentResDto.setId("provider-ref-id1");
		paymentResDto.setUrl("http://test.com");
		when(mapper.map(any(), eq(PaymentResDto.class))).thenReturn(paymentResDto);
		//act
		TranactionDTO txnDtoRes=paymentServiceImpl.initiatePayment(txnRef,reqDto);
		
	
		//assert
		assertNotNull(txnDtoRes);
		assertEquals(TransactionStatusEnum.PENDING.getName(), txnDtoRes.getTxnStatus());
		
		assertEquals("provider-ref-id1", txnDtoRes.getProviderReference());
		assertEquals("http://test.com", txnDtoRes.getUrl());
	}

}
