package com.stripe.system.dao;

import com.stripe.system.dto.TranactionDTO;

public interface TransactionDao {
	public TranactionDTO createTransaction(TranactionDTO dto);
	public TranactionDTO getTransactionByRef(String trnRef);
	public TranactionDTO updateTransactionStatusDetails(TranactionDTO dto);
	public TranactionDTO getTransactionByProviderReference(String providerReference);

}
