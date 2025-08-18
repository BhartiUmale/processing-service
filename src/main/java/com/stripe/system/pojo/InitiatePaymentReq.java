package com.stripe.system.pojo;

import java.util.List;

public class InitiatePaymentReq {
	private String successUrl;
	private String cancelUrl;
	private List<LineItem> lineItem;
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public String getCancelUrl() {
		return cancelUrl;
	}
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}
	public List<LineItem> getLineItem() {
		return lineItem;
	}
	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}
	@Override
	public String toString() {
		return "InitiatePaymentReq [successUrl=" + successUrl + ", cancelUrl=" + cancelUrl + ", lineItem=" + lineItem
				+ "]";
	}
	
	
	

}
