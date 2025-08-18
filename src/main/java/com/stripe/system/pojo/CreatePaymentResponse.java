package com.stripe.system.pojo;

public class CreatePaymentResponse {

	private String txnzReference;
	private String txnStatus;
	public String getTxnzReference() {
		return txnzReference;
	}
	public void setTxnzReference(String txnzReference) {
		this.txnzReference = txnzReference;
	}
	@Override
	public String toString() {
		return "CreatePaymentResponse [txnzReference=" + txnzReference + ", txnStatus=" + txnStatus + "]";
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}
	
	

	
}
