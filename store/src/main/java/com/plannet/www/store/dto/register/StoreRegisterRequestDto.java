package com.plannet.www.store.dto.register;

public record StoreRegisterRequestDto(
	String name,
	String businessNumber,
	String address,
	String phone
) {
	public static StoreRegisterRequestDto of(String name, String businessNumber, String address, String phone) {
		return new StoreRegisterRequestDto(name, businessNumber, address, phone);
	}
}
