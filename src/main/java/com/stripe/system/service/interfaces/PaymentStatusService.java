package com.stripe.system.service.interfaces;

import com.stripe.system.dto.TranactionDTO;

public interface PaymentStatusService {
	
	public TranactionDTO processStatus(TranactionDTO tranactionDTO);

}
