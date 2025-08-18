package com.stripe.system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.system.dto.InitiatePaymentDTO;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.pojo.CreatePaymentRequest;
import com.stripe.system.pojo.CreatePaymentResponse;
import com.stripe.system.pojo.InitPaymentResponse;
import com.stripe.system.pojo.InitiatePaymentReq;
import com.stripe.system.service.interfaces.PaymentService;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {
	private ModelMapper modelMapper;
	private PaymentService paymentService;
	
	
	
	public PaymentController(ModelMapper modelMapper,PaymentService paymentService) {
		super();
		this.modelMapper = modelMapper;
		this.paymentService=paymentService;
	}



	@PostMapping
	public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest createPaymentRequest) {
		System.out.println("Create Payment | CreatePaymentRequest:"+createPaymentRequest);
		TranactionDTO txnDto=modelMapper.map(createPaymentRequest,TranactionDTO.class);
		  
		  
		  System.out.println("Convert the createPaymentRequest to TranactionDto:"+txnDto);
		  
		TranactionDTO tranactionDTO=paymentService.createPayment(txnDto);
		CreatePaymentResponse cr=new CreatePaymentResponse();
		  cr.setTxnzReference(tranactionDTO.getTxnReference());
		  cr.setTxnStatus(tranactionDTO.getTxnStatus());
		  
		  
		return new ResponseEntity<>(cr,HttpStatus.CREATED);
		
	}
	
	
	
	@PostMapping("{txnreference}/initiate")
	public ResponseEntity<InitPaymentResponse> initiatePayment(@PathVariable String txnreference,@RequestBody InitiatePaymentReq initiatePaymentReq) {
		System.out.println("initiate Payment");
		
		InitiatePaymentDTO reqDto=modelMapper.map(initiatePaymentReq, InitiatePaymentDTO.class);
		
		
		TranactionDTO resDTO=paymentService.initiatePayment(txnreference,reqDto);
		System.out.println("response from service TranactionDTO:"+resDTO);
		InitPaymentResponse response= new InitPaymentResponse();
		response.setTxnReference(resDTO.getTxnReference());
		response.setTxnStatus(resDTO.getTxnStatus());
		response.setUrl(resDTO.getUrl());
		
		System.out.println("Returning Response:"+response);
		
		
		return ResponseEntity.ok(response);
		
	}
	
}
