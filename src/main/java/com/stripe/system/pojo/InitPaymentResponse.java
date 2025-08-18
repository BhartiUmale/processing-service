package com.stripe.system.pojo;

public class InitPaymentResponse {
	
	private String txnReference;
	private String txnStatus;
	private String url;
	public String getTxnReference() {
		return txnReference;
	}
	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "InitPaymentResponse [txnReference=" + txnReference + ", txnStatus=" + txnStatus + ", url=" + url + "]";
	}
	
	

}
