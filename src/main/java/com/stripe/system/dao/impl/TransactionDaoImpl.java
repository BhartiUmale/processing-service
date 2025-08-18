package com.stripe.system.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.stripe.system.dao.TransactionDao;
import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.entity.TranactionEntity;

@Repository
public class TransactionDaoImpl implements TransactionDao{

	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public TranactionDTO createTransaction(TranactionDTO txnDto) {
		System.out.println("**TranactionDTO Recevied in TransactionDaoImpl:"+txnDto);
		TranactionEntity txnEntity = modelMapper.map(txnDto, TranactionEntity.class);
		System.out.println("Converted to Entity txnEntity:" + txnEntity);

		String sql = "INSERT INTO payments.`Transaction` (userId, paymentMethodId, providerId, paymentTypeId, txnStatusId, " +
				"amount, currency, merchantTxnReference, txnReference, providerReference, errorCode, errorMessage, retryCount) " +
				"VALUES (:userId, :paymentMethodId, :providerId, :paymentTypeId, :txnStatusId, :amount, :currency, " +
				":merchantTxnReference, :txnReference, :providerReference, :errorCode, :errorMessage, :retryCount)";

		SqlParameterSource params = new BeanPropertySqlParameterSource(txnEntity);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
		
		int id = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
		txnDto.setId(id);
		
		System.out.println("Transaction created in DB||txn id:"+txnDto.getId()+" |txnReference:{}"+txnDto.getTxnReference());
		
		return txnDto;
	}
	@Override
	public TranactionDTO getTransactionByRef(String txnReference) {
		String sql = "SELECT * FROM payments.`Transaction` WHERE txnReference = :txnReference";
		
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("txnReference",txnReference);
		System.out.println("**Recevied param:"+params);
		try {
			TranactionEntity transaction=namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(TranactionEntity.class));
			
			TranactionDTO txnDto=modelMapper.map(transaction,TranactionDTO.class);
			System.out.println("**received data from DB TranactionDTO:"+txnDto);
			return txnDto;
		}catch(Exception e) {
			System.out.println("Error occur in catch block"+e);
			return null;
		}
		
	}

	public TranactionDTO updateTransactionStatusDetails(TranactionDTO dto) {
		
		
		String sql="UPDATE payments.`Transaction` SET txnStatusId=:txnStatusId,providerReference=:providerReference,errorCode=:errorCode,errorMessage=:errorMessage WHERE txnReference=:txnReference";
		TranactionEntity transaction=modelMapper.map(dto, TranactionEntity.class);
		SqlParameterSource params=new BeanPropertySqlParameterSource(transaction);
		
		namedParameterJdbcTemplate.update(sql, params);
		System.out.println("***Transaction Status updated in DB:"+dto);
		return dto;
	}
	
}
