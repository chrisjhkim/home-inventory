package com.chrisjhkim.sweethome.item.web.dto;

import lombok.Getter;

@Getter
public enum SearchType {
	ALL("전체"), ITEM_NAME("물품명"), PLACE("장소");

	private final String displayName;

	SearchType(String displayName) {
		this.displayName = displayName;
	}
}
