 package com.stripe.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stripe.system.constant.TransactionStatusEnum;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.impl.PaymentServiceImpl;
import com.stripe.system.service.interfaces.PaymentStatusService;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
	
	@InjectMocks
	private PaymentServiceImpl paymentServiceImpl;
	
	@Mock
	private PaymentStatusService paymentStatusService;
	
	
	@Test
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

}
