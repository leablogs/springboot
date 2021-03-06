package com.leablogs.enumeration;

public enum SexEnum {
	MALE(0, "男"), FEMALE(1, "女");

	private int id;
	private String name;

	private SexEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static SexEnum getEnumById(int id) {
		for (SexEnum sex : SexEnum.values()) {
			if (sex.getId() == id) {
				return sex;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
