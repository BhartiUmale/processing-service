package com.stripe.system.stripeProvider;

import lombok.Data;

@Data
public class LineItem {
	private int quantity;
	private String currency;
	private String productName;
	private int unitAmount;
	
	
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(int unitAmount) {
		this.unitAmount = unitAmount;
	}
	@Override
	public String toString() {
		return "LineItem [quantity=" + quantity + ", currency=" + currency + ", productName=" + productName
				+ ", unitAmount=" + unitAmount + "]";
	}
	
	
	
	
	
	

}
