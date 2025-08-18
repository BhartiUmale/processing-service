package com.stripe.system.dto;

import java.time.LocalDate;

public class TranactionDTO {
	 private Integer id;
	    private Integer userId;
	    
	    private String paymentMethod;
	    private String provider;
	    private String paymentType;
	    
	    private String txnStatus;
	    private Long amount;
	    private String currency;
	    private String merchantTxnReference;
	    private String txnReference;
	    private String providerReference;
	    private String errorCode;
	    private String errorMessage;
	    private LocalDate creationDate;
	    private LocalDate updatedDate;
	    private Integer retryCount;
	    
	    private String url;
	    
	    
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
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
		
		public String getTxnStatus() {
			return txnStatus;
		}
		public void setTxnStatus(String txnStatus) {
			this.txnStatus = txnStatus;
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
		public String getTxnReference() {
			return txnReference;
		}
		public void setTxnReference(String txnReference) {
			this.txnReference = txnReference;
		}
		public String getProviderReference() {
			return providerReference;
		}
		public void setProviderReference(String providerReference) {
			this.providerReference = providerReference;
		}
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		public LocalDate getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(LocalDate creationDate) {
			this.creationDate = creationDate;
		}
		public LocalDate getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(LocalDate updatedDate) {
			this.updatedDate = updatedDate;
		}
		public Integer getRetryCount() {
			return retryCount;
		}
		public void setRetryCount(Integer retryCount) {
			this.retryCount = retryCount;
		}
		
		@Override
		public String toString() {
			return "TranactionDTO [id=" + id + ", userId=" + userId + ", paymentMethod=" + paymentMethod + ", provider="
					+ provider + ", paymentType=" + paymentType + ", txnStatus=" + txnStatus + ", amount=" + amount
					+ ", currency=" + currency + ", merchantTxnReference=" + merchantTxnReference + ", txnReference="
					+ txnReference + ", providerReference=" + providerReference + ", errorCode=" + errorCode
					+ ", errorMessage=" + errorMessage + ", creationDate=" + creationDate + ", updatedDate="
					+ updatedDate + ", retryCount=" + retryCount + ", url=" + url + "]";
		}
		
		
		
		
		

}
