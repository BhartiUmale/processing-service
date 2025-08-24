package com.stripe.system.util.mmConveter.idToString;

import org.modelmapper.AbstractConverter;

import com.stripe.system.constant.PaymentTypeEum;

public class PaymentTypeEnumIdToStringConverter extends AbstractConverter<Integer,String>{

	@Override
	protected String convert(Integer source) {
		return PaymentTypeEum.getById(source).getName();
	}

}
