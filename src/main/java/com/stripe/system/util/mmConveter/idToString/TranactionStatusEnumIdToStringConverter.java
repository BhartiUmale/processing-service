package com.stripe.system.util.mmConveter.idToString;

import org.modelmapper.AbstractConverter;

import com.stripe.system.constant.PaymentMethodEnum;
import com.stripe.system.constant.TransactionStatusEnum;

public class TranactionStatusEnumIdToStringConverter extends AbstractConverter<Integer,String>{

	@Override
	protected String convert(Integer source) {
		return TransactionStatusEnum.getById(source).getName();
	}

}
