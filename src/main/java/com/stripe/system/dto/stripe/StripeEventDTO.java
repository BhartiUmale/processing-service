package com.stripe.system.dto.stripe;

public class StripeEventDTO {

	private String id;
	private String type;
	private StripeDataDTO data;
	
	
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
	public StripeDataDTO getData() {
		return data;
	}
	public void setData(StripeDataDTO data) {
		this.data = data;
	}
	
	
}
