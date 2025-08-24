package com.stripe.system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stripe.net.Webhook;
import com.stripe.system.dto.stripe.StripeEventDTO;
import com.stripe.system.service.interfaces.StripeWebHookService;

@RestController
@RequestMapping("/stripe/webhook")
public class StripeWebhookController {
	
	@Autowired
	private  Gson gson;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StripeWebHookService hookService;
	
	@Value("${stripe.signature}")
	private String endpointSecret;

	



	@PostMapping
	public ResponseEntity<String>  processStripeEvent(@RequestBody String eventBody,
			@RequestHeader("Stripe-Signature")String signatureHeader)
	{
		System.out.println("\n \n \n\n Received event:"+" event body"+eventBody);
		System.out.println("\n \n \n\n Received signature:"+" signatureHeader"+signatureHeader);

		try {
			Webhook.constructEvent(eventBody, signatureHeader, endpointSecret);

			System.out.println("Successfully verify webhook signature");
		} catch (Exception e) {
			System.out.println("Invaild signature");
			return ResponseEntity.badRequest().build();
		}
		
		StripeEventDTO event=gson.fromJson(eventBody, StripeEventDTO.class);
		System.out.println("Received event type:"+event.getType());
		StripeEventDTO eventDto=modelMapper.map(event,StripeEventDTO.class);
		
		hookService.processEvent(eventDto);
		return ResponseEntity.ok().build();
	}
}
