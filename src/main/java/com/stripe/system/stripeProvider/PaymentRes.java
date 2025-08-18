package com.stripe.system.stripeProvider;

import com.google.gson.annotations.SerializedName;

public class PaymentRes {
	private String id;
	private String url;
	private String status;
	
	@SerializedName("payment_status")
	private String paymentStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		return "PaymentRes [id=" + id + ", url=" + url + ", status=" + status + ", paymentStatus=" + paymentStatus
				+ "]";
	}

	
}
