package com.stripe.system.dto.stripe;

import com.google.gson.annotations.SerializedName;

public class CheckoutSessionCompletedData {
	
	private String id;
	private String status;
	
	@SerializedName("payment_status")
	private String paymentStatus;

	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "CheckoutSessionCompletedData [id=" + id + ", status=" + status + ", paymentStatus=" + paymentStatus
				+ "]";
	}
	
	
	

}
