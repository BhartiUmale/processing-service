package com.stripe.system.pojo.stripe;

public class StripeEvent {

	private String id;
	private String type;
	private StripeData data;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public StripeData getData() {
		return data;
	}
	public void setData(StripeData data) {
		this.data = data;
	}
	
	
}
