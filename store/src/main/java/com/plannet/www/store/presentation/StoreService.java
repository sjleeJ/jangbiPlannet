package com.plannet.www.store.presentation;

import com.plannet.www.store.dto.register.StoreRegisterRequestDto;
import com.plannet.www.store.dto.register.StoreRegisterResponseDto;

public interface StoreService {
	StoreRegisterResponseDto registerStore(StoreRegisterRequestDto storeRegisterRequestDto);
}
