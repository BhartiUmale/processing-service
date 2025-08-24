package com.stripe.system.util.mmConveter.idToString;

import org.modelmapper.AbstractConverter;

import com.stripe.system.constant.ProviderEnum;

public class ProviderEnumIdToStringConverter extends AbstractConverter<Integer,String>{

	@Override
	protected String convert(Integer source) {
		return ProviderEnum.getById(source).getName();
	}

}
