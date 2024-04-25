package com.lani.common;

public enum KeyEnum {

	MAP_DATA_KEY("main_data");

	private String value;

	KeyEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}


}
