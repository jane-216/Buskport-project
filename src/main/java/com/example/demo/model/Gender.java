package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
	WOMAN("W"),
	MAN("M");
	
	private String code;
	
	Gender(String code) {
		this.code = code;
	}
	
	String getCode() {
		return this.code;
	}
	
	@JsonCreator
	public static Gender findByCode(String code) {
		for(Gender gender : Gender.values()) {
			if (gender.code.equals(code)) {
				return gender;
			}
		}
		return null;
	}

}
