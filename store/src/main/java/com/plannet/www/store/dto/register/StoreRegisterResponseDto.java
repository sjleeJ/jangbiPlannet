package com.plannet.www.store.dto.register;

public record StoreRegisterResponseDto(
	String name,
	String businessNumber,
	String address,
	String phone
) {
	public static StoreRegisterResponseDto of(String name, String businessNumber, String address, String phone) {
		return new StoreRegisterResponseDto(name, businessNumber, address, phone);
	}
}
