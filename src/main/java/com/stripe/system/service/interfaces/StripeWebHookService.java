package com.stripe.system.service.interfaces;

import com.stripe.system.dto.stripe.StripeEventDTO;

public interface StripeWebHookService {
	public void processEvent(StripeEventDTO eventDto );

}
