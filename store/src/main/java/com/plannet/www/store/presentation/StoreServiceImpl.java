package com.plannet.www.store.presentation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plannet.www.store.dto.register.StoreRegisterRequestDto;
import com.plannet.www.store.dto.register.StoreRegisterResponseDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService {

	public StoreRegisterResponseDto registerStore(StoreRegisterRequestDto storeRegisterRequestDto) {

		return StoreRegisterResponseDto.of(storeRegisterRequestDto.name(), storeRegisterRequestDto.businessNumber()
			, storeRegisterRequestDto.address(), storeRegisterRequestDto.phone());
	}
}
