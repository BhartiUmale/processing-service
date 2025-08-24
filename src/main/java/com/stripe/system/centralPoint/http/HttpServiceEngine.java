package com.stripe.system.centralPoint.http;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import com.stripe.system.exception.ProcessingException;
import com.stripe.system.exception.exEnum.ErrorCodeEnum;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Component
public class HttpServiceEngine {
	private RestClient restClient;

	public HttpServiceEngine(RestClient.Builder restClientBuilder) {
		super();
		this.restClient = restClientBuilder.build();
	}




	@CircuitBreaker(name = "processing-service", fallbackMethod = "fallbackProcessPayment")
	public ResponseEntity<String> makeHttpCall(HttpRequest httpRequest) {

		System.out.println("enter in makeHttpCall");

		try {
			System.out.println("enter in try");

			ResponseEntity<String> res = restClient
					.method(httpRequest.getMethod())
					.uri(httpRequest.getUrl())
					.headers(header -> header.addAll(httpRequest.getHeaders()))
					.body(httpRequest.getRequestBody())
					.retrieve()
					.toEntity(String.class);

			System.out.println("Response from Stripe: " + res);

			return res;

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			System.out.println(e.getStatusCode() + " in catch");

			if (e.getStatusCode().equals(HttpStatus.GATEWAY_TIMEOUT) || 
					e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
				System.out.println(e.getStatusCode() + " got error");
				throw new ProcessingException(ErrorCodeEnum.UNABLE_TO_CONNECT_TO_STRIPE_PS.getErrorCode(),ErrorCodeEnum.UNABLE_TO_CONNECT_TO_STRIPE_PS.getErrorMessage(),HttpStatus.valueOf(e.getStatusCode().value()));
			}

			System.out.println(e.getResponseBodyAsString() + " after if");

			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		} catch (Exception e2) {
			System.out.println("General exception occurred"+e2);
			throw new ProcessingException(ErrorCodeEnum.UNABLE_TO_CONNECT_TO_STRIPE_PS.getErrorCode(),
					ErrorCodeEnum.UNABLE_TO_CONNECT_TO_STRIPE_PS.getErrorMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	public ResponseEntity<String> fallbackProcessPayment(HttpRequest httpRequest, Throwable t) {
		// Handle fallback logic here
		System.out.println("Fallback method invoked due to exception:" + t.getMessage());
		throw new ProcessingException(
				ErrorCodeEnum.UNABLE_TO_CONNECT_TO_STRIPE_PS.getErrorCode(),
				ErrorCodeEnum.UNABLE_TO_CONNECT_TO_STRIPE_PS.getErrorMessage(),
				HttpStatus.SERVICE_UNAVAILABLE);
	}


}
