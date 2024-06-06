package com.chrisjhkim.sweethome.member.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
	GUEST("ROLE_GUEST", "손님"),
	USER("ROLE_USER", "일반 사용자"),
	MASTER("ROLE_MASTER", "관리자"),
	STOP("ROLE_STOPPED", "중지된 사용자");
	private final String key;
	private final String title;

}
