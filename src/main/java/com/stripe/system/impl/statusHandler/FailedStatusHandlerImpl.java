package com.stripe.system.impl.statusHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.system.dao.TransactionDao;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.service.interfaces.TxnStatusHandler;
@Service
public class FailedStatusHandlerImpl implements TxnStatusHandler {
	@Autowired
	private TransactionDao dao;
	@Override
	public TranactionDTO processStatus(TranactionDTO tranactionDTO) {
		System.out.println("*** Processing FAILED Status || txnDTO: "+tranactionDTO);
		dao.updateTransactionStatusDetails(tranactionDTO);
		System.out.println("*** In FailedStatusHandlerImpl update txn status in DB:"+tranactionDTO);
		return tranactionDTO;
	}

}
