package com.stripe.system.constant;

public enum TransactionStatusEnum {
	
	CREATED(1,"CREATED"),
	INITIATED(2,"INITIATED"),
	PENDING(3,"PENDING"),
	SUCCESS(4,"SUCCESS"),
	FAILED(5,"FAILED");
	private int id;
	private String name;
	private TransactionStatusEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static TransactionStatusEnum getById(int id) {
		for(TransactionStatusEnum status:values()) {
			if(status.id==id) {
				return status;
			}
		}
		return null;
	}
	
	public static TransactionStatusEnum getByName(String name) {
		for(TransactionStatusEnum status:values()) {
			if(status.name.equalsIgnoreCase(name)) {
				return status;
			}
		}
		return null;
	}
	

}
