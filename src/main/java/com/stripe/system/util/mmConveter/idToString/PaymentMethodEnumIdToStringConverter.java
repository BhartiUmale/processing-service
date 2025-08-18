package com.stripe.system.util.mmConveter.idToString;

import org.modelmapper.AbstractConverter;

import com.stripe.system.constant.PaymentMethodEnum;

public class PaymentMethodEnumIdToStringConverter extends AbstractConverter<Integer,String>{

	@Override
	protected String convert(Integer source) {
		return PaymentMethodEnum.getById(source).getName();
	}

}
