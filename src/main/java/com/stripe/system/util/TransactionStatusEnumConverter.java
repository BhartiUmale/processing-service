package com.stripe.system.util;

import org.modelmapper.AbstractConverter;

import com.stripe.system.constant.TransactionStatusEnum;

public class TransactionStatusEnumConverter extends AbstractConverter<String,Integer>{

	@Override
	protected Integer convert(String source) {
		
		return TransactionStatusEnum.getByName(source).getId();
	}

}
