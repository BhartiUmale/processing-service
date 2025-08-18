package com.stripe.system.util;

import org.modelmapper.AbstractConverter;

import com.stripe.system.constant.PaymentMethodEnum;

public class PaymentMethodEnumConverter extends AbstractConverter<String,Integer>{

	@Override
	protected Integer convert(String source) {
		
		return PaymentMethodEnum.getByName(source).getId();
	}

}
