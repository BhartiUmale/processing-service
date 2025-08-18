package com.stripe.system.constant;

public enum PaymentTypeEum {
	
	SALE(1,"SALE");
	private int id;
	private String name;
	private PaymentTypeEum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static PaymentTypeEum getById(int id) {
		for(PaymentTypeEum status:values()) {
			if(status.id==id) {
				return status;
			}
		}
		return null;
	}
	
	public static PaymentTypeEum getByName(String name) {
		for(PaymentTypeEum status:values()) {
			if(status.name.equalsIgnoreCase(name)) {
				return status;
			}
		}
		return null;
	}
	

}
