package com.stripe.system.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stripe.system.dto.TranactionDTO;
import com.stripe.system.entity.TranactionEntity;
import com.stripe.system.util.PaymentMethodEnumConverter;
import com.stripe.system.util.PaymentTypeEnumConverter;
import com.stripe.system.util.ProviderEnumConverter;
import com.stripe.system.util.TransactionStatusEnumConverter;
import com.stripe.system.util.mmConveter.idToString.PaymentMethodEnumIdToStringConverter;
import com.stripe.system.util.mmConveter.idToString.PaymentTypeEnumIdToStringConverter;
import com.stripe.system.util.mmConveter.idToString.ProviderEnumIdToStringConverter;
import com.stripe.system.util.mmConveter.idToString.TranactionStatusEnumIdToStringConverter;
@Configuration
public class AppConfig {

	@Bean
	ModelMapper modelMapper() {
		System.out.println("****Inside Appcofig model mappper***");

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Converter<String, Integer> paymentMethodEnumConverter = new PaymentMethodEnumConverter();
		Converter<String, Integer> providerEnumConverter = new ProviderEnumConverter();
		Converter<String, Integer> paymentTypeEnumConverter = new PaymentTypeEnumConverter();
		Converter<String, Integer> transactionStatusEnumConverter = new TransactionStatusEnumConverter();

		mapper.addMappings(new PropertyMap<TranactionDTO, TranactionEntity>() {
			@Override
			protected void configure() {
				using(paymentMethodEnumConverter)
				.map(source.getPaymentMethod(), destination.getPaymentMethodId());
				using(paymentTypeEnumConverter)
				.map(source.getPaymentType(), destination.getPaymentTypeId());
				using(providerEnumConverter)
				.map(source.getProvider(), destination.getProviderId());
				using(transactionStatusEnumConverter)
				.map(source.getTxnStatus(), destination.getTxnStatusId());
			}
		});


		mapper.addMappings(new PropertyMap<TranactionEntity,TranactionDTO>() {
			@Override
			protected void configure() {
				using(new PaymentMethodEnumIdToStringConverter())
				.map(source.getPaymentMethodId(), destination.getPaymentMethod());

				using(new PaymentTypeEnumIdToStringConverter())
				.map(source.getPaymentTypeId(), destination.getPaymentType());

				using(new ProviderEnumIdToStringConverter())
				.map(source.getProviderId(), destination.getProvider());

				using(new TranactionStatusEnumIdToStringConverter())
				.map(source.getTxnStatusId(), destination.getTxnStatus());
			}
		});
		return mapper;
	}
}
