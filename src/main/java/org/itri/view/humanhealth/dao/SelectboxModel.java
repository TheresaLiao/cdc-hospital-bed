package org.itri.view.humanhealth.dao;

public class SelectboxModel {

	private String key;
	private String value;

	public SelectboxModel(String key, String value) {
		this.value = value;
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}
}
