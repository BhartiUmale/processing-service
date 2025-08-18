package com.stripe.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.stripe.system.constant.TransactionStatusEnum;
import com.stripe.system.impl.statusHandler.CreateStatusHandlerImpl;
import com.stripe.system.impl.statusHandler.FailedStatusHandlerImpl;
import com.stripe.system.impl.statusHandler.InitiatedStatusHandlerImpl;
import com.stripe.system.impl.statusHandler.PendingStatusHandlerImpl;
import com.stripe.system.impl.statusHandler.SuccessStatusHandlerImpl;
import com.stripe.system.service.interfaces.TxnStatusHandler;

@Component
public class PaymentStatusFactory {
	
	@Autowired
	private ApplicationContext context;

	public TxnStatusHandler getHandler(TransactionStatusEnum statusEnum) {
		
		switch(statusEnum) {
		case CREATED: return context.getBean(CreateStatusHandlerImpl.class);
				
		
		case INITIATED:return context.getBean(InitiatedStatusHandlerImpl.class);
		
		case PENDING:return context.getBean(PendingStatusHandlerImpl.class); 
		
		case SUCCESS:return context.getBean(SuccessStatusHandlerImpl.class); 
		
		case FAILED:return context.getBean(FailedStatusHandlerImpl.class); 
		}
		return null;
	}
}
