package com.stripe.system.impl.statusHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.system.dao.TransactionDao;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.service.interfaces.TxnStatusHandler;
@Service
public class CreateStatusHandlerImpl implements TxnStatusHandler {

	@Autowired
	private TransactionDao transactionDao;
	@Override
	public TranactionDTO processStatus(TranactionDTO tranactionDTO) {
		System.out.println("****In CreateStatusHandlerImpl recevied:"+tranactionDTO);
		tranactionDTO=transactionDao.createTransaction(tranactionDTO);
	
		return tranactionDTO;
	}

}
