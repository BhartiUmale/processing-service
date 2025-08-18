package com.stripe.system.constant;

public enum PaymentMethodEnum {
	
	APM(1,"APM");
	private int id;
	private String name;
	private PaymentMethodEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static PaymentMethodEnum getById(int id) {
		for(PaymentMethodEnum status:values()) {
			if(status.id==id) {
				return status;
			}
		}
		return null;
	}
	
	public static PaymentMethodEnum getByName(String name) {
		for(PaymentMethodEnum status:values()) {
			if(status.name.equalsIgnoreCase(name)) {
				return status;
			}
		}
		return null;
	}
	

}
