package com.stripe.system.impl;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stripe.system.constant.TransactionStatusEnum;
import com.stripe.system.dao.TransactionDao;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.dto.stripe.CheckoutSessionCompletedData;
import com.stripe.system.dto.stripe.StripeEventDTO;
import com.stripe.system.service.interfaces.PaymentStatusService;
import com.stripe.system.service.interfaces.StripeWebHookService;

import lombok.extern.java.Log;

@Service
public class StripeWebHookServiceImpl implements StripeWebHookService {

	private final Gson gson;
	private final PaymentStatusService paymentStatusService;
	private final TransactionDao dao;
	private static final String CHECKOUT_SESSION_ASYNC_PAYMENT_FAILED = "checkout.session.async_payment_failed";
	private static final String CHECKOUT_SESSION_ASYNC_PAYMENT_SUCCEEDED = "checkout.session.async_payment_succeeded";
	private static final String CHECKOUT_SESSION_COMPLETED = "checkout.session.completed";

	
	public StripeWebHookServiceImpl(Gson gson,PaymentStatusService paymentStatusService,TransactionDao dao) {
		super();
		this.gson = gson;
		this.paymentStatusService = paymentStatusService;
		this.dao =dao;
	}
	

	@Override
	public void processEvent(StripeEventDTO eventDto) {
		
		if(CHECKOUT_SESSION_COMPLETED.equals(eventDto.getType())) {
			System.out.println("checkout Session completed event received");
			
		CheckoutSessionCompletedData objData=gson.fromJson(eventDto.getData().getObject(),CheckoutSessionCompletedData.class );
		
		System.out.println("ObjData, convert StripeEventDTO to CheckoutSessionCompletedData"+objData);
		if("complete".equals(objData.getStatus())&& "paid".equals(objData.getPaymentStatus())) {
			System.out.println("Payment success");

			TranactionDTO txnDto=dao.getTransactionByProviderReference(objData.getId());
			if(txnDto==null) {
				System.err.println("no Transaction found for provider reference || providerReference:"+objData.getId());
			}
			txnDto.setTxnStatus(TransactionStatusEnum.SUCCESS.getName());
			paymentStatusService.processStatus(txnDto);

		}
			return;
		}
		
		
		if(CHECKOUT_SESSION_ASYNC_PAYMENT_SUCCEEDED.equals(eventDto.getType())) {
			System.out.println("checkout session async payment succeeded event received");
			return;
		}
		
		if(CHECKOUT_SESSION_ASYNC_PAYMENT_FAILED.equals(eventDto.getType())) {
			System.out.println("checkout session async payment failed event received");
			return;
		}
	}

	

}
