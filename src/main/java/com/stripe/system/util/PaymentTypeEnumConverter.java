package com.stripe.system.util;

import org.modelmapper.AbstractConverter;

import com.stripe.system.constant.PaymentTypeEum;

public class PaymentTypeEnumConverter extends AbstractConverter<String,Integer>{

	@Override
	protected Integer convert(String source) {
		
		return PaymentTypeEum.getByName(source).getId();
	}

}
