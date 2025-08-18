package com.stripe.system.pojo;

public class CreatePaymentRequest {
	
	
	    private Integer userId;
	    private String paymentMethod;
	    private String provider;
	    private String paymentType;
	   
	    private Long amount;
	    private String currency;
	    private String merchantTxnReference;
	
	    
	    
	    
	    
		
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
		public String getProvider() {
			return provider;
		}
		public void setProvider(String provider) {
			this.provider = provider;
		}
		public String getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
		public Long getAmount() {
			return amount;
		}
		public void setAmount(Long amount) {
			this.amount = amount;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getMerchantTxnReference() {
			return merchantTxnReference;
		}
		public void setMerchantTxnReference(String merchantTxnReference) {
			this.merchantTxnReference = merchantTxnReference;
		}
		@Override
		public String toString() {
			return "CreatePaymentRequest [userId=" + userId + ", paymentMethod=" + paymentMethod + ", provider="
					+ provider + ", paymentType=" + paymentType + ", amount=" + amount + ", currency=" + currency
					+ ", merchantTxnReference=" + merchantTxnReference + "]";
		}
		
		
	    

}
