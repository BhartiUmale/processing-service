package com.stripe.system.entity;

import java.time.LocalDate;

public class TranactionEntity {
	 private Integer id;
	    private Integer userId;
	    private Integer paymentMethodId;
	    private Integer providerId;
	    private Integer paymentTypeId;
	    private Integer txnStatusId;
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
		public Integer getPaymentMethodId() {
			return paymentMethodId;
		}
		public void setPaymentMethodId(Integer paymentMethodId) {
			this.paymentMethodId = paymentMethodId;
		}
		public Integer getProviderId() {
			return providerId;
		}
		public void setProviderId(Integer providerId) {
			this.providerId = providerId;
		}
		public Integer getPaymentTypeId() {
			return paymentTypeId;
		}
		public void setPaymentTypeId(Integer paymentTypeId) {
			this.paymentTypeId = paymentTypeId;
		}
		public Integer getTxnStatusId() {
			return txnStatusId;
		}
		public void setTxnStatusId(Integer txnStatusId) {
			this.txnStatusId = txnStatusId;
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
			return "TranactionEntity [id=" + id + ", userId=" + userId + ", paymentMethodId=" + paymentMethodId
					+ ", providerId=" + providerId + ", paymentTypeId=" + paymentTypeId + ", txnStatusId=" + txnStatusId
					+ ", amount=" + amount + ", currency=" + currency + ", merchantTxnReference=" + merchantTxnReference
					+ ", txnReference=" + txnReference + ", providerReference=" + providerReference + ", errorCode="
					+ errorCode + ", errorMessage=" + errorMessage + ", creationDate=" + creationDate + ", updatedDate="
					+ updatedDate + ", retryCount=" + retryCount + "]";
		}

	    
	    
}
