package com.chrisjhkim.sweethome.item.validation;

import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ValidationTest {
	@Test
	@DisplayName("bean validation 검증 결과")
	void test(){
	    // Given
		Validator validator;
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			validator = factory.getValidator();
		}

		// When
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemName(" ");
		itemDTO.setQuantity(null);

	    // Then
		Set<ConstraintViolation<ItemDTO>> violations = validator.validate(itemDTO);
		for (ConstraintViolation<ItemDTO> violation : violations) {
			System.out.println(violation);
			System.out.println(violation.getMessage());
		}
	}
}
