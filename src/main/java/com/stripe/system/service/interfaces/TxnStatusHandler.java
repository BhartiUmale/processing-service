package com.stripe.system.service.interfaces;

import com.stripe.system.dto.TranactionDTO;

public interface TxnStatusHandler {
	public TranactionDTO processStatus(TranactionDTO tranactionDTO);

}
