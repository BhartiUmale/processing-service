package com.stripe.system.impl.statusHandler;

import org.springframework.stereotype.Service;

import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.service.interfaces.TxnStatusHandler;
@Service
public class SuccessStatusHandlerImpl implements TxnStatusHandler {

	@Override
	public TranactionDTO processStatus(TranactionDTO tranactionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
