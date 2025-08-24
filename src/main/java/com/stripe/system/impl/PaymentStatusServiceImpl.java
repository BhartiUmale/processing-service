package com.stripe.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.system.constant.TransactionStatusEnum;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.service.interfaces.PaymentStatusService;
import com.stripe.system.service.interfaces.TxnStatusHandler;


@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

	@Autowired
	private PaymentStatusFactory paymentStatusFactory;

	@Override
	public TranactionDTO processStatus(TranactionDTO tranactionDTO) {
		System.out.println("In PaymentStatusServiceImpl reviced TranactionDTO:"+tranactionDTO);

		TransactionStatusEnum statusEnum=TransactionStatusEnum.getByName(tranactionDTO.getTxnStatus());
		if(statusEnum == null) {
			System.out.println("Invalid status received status:"+tranactionDTO.getTxnStatus());
			//throw custom exception TODO
		}
		TxnStatusHandler statusHandler=paymentStatusFactory.getHandler(statusEnum);
		if(statusHandler==null) {
			//TODO custom exception handling
		}
		TranactionDTO txnDto=statusHandler.processStatus(tranactionDTO);


		return txnDto;
	}

}
