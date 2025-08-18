package com.stripe.system.service.interfaces;

import com.stripe.system.dto.InitiatePaymentDTO;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.pojo.InitiatePaymentReq;

public interface PaymentService {
	
	public TranactionDTO createPayment(TranactionDTO tranactionDTO);
	public TranactionDTO initiatePayment(String txnreference, InitiatePaymentDTO reqDto);

}
