package com.stripe.system.constant;

public enum ProviderEnum {
	
	STRIPE(1,"STRIPE");
	private int id;
	private String name;
	private ProviderEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static ProviderEnum getById(int id) {
		for(ProviderEnum status:values()) {
			if(status.id==id) {
				return status;
			}
		}
		return null;
	}
	
	public static ProviderEnum getByName(String name) {
		for(ProviderEnum status:values()) {
			if(status.name.equalsIgnoreCase(name)) {
				return status;
			}
		}
		return null;
	}
	

}
