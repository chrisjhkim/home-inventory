package com.chrisjhkim.sweethome.item.web.form;

import lombok.Getter;

/**
 * TODO : package 여기가 맞나 싶음
 */
@Getter
public enum ItemType {
	EXPENDABLES("소모품"),
	ELECTRONICS("가전제품"),
	BOOK("도서");

	private final String description;

	ItemType(String description) {
		this.description = description;
	}


}
