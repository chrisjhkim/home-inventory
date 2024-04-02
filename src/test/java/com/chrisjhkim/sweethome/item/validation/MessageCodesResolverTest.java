package com.chrisjhkim.sweethome.item.validation;

import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {

	MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

	@Test
	@DisplayName("")
	void messageCodesResolverObject(){
	    // Given
		String errorCode = "required";
		String objectName = "item";

		// When
		String[] messageCodes = codesResolver.resolveMessageCodes(errorCode, objectName);

	    // Then
		assertThat(messageCodes).containsExactly("required.item", "required");
	}

	@Test
	@DisplayName("")
	void messageCodesResolverField(){
		// Given
		String errorCode = "required";
		String objectName = "item";
		String field = "itemName";
		Class<?> fieldType = String.class;

		// When
		String[] messageCodes = codesResolver.resolveMessageCodes(errorCode, objectName, field, fieldType);

		// Then
		assertThat(messageCodes).containsExactly("required.item.itemName", "required.itemName", "required.java.lang.String", "required");
	}
}
