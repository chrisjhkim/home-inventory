package com.chrisjhkim.sweethome.item.validation;

import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//@Component
public class ItemDTOValidator
//		implements Validator
{
//	@Override
	public boolean supports(Class<?> clazz) {
		return ItemDTO.class.isAssignableFrom(clazz);
	}

//	@Override
	public void validate(Object target, Errors errors) {
		ItemDTO item = (ItemDTO) target;
		// 검증 오류 결과 보관
		if (!StringUtils.hasText(item.getItemName())){
//			errors.addError(new FieldError("item","itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
			errors.rejectValue("itemName", "required");
		}
		if ( item.getQuantity() == null || item.getQuantity() > 9999 || item.getQuantity() < 0 ){
//			errors.addError(new FieldError("item","quantity", item.getQuantity(), false, new String[]{"range.item.quantity"}, new Object[]{0,9999}, null));
			errors.rejectValue("quantity", "range", new Object[]{0,9999}, null);
		}

		if (StringUtils.hasText(item.getItemName()) && item.getQuantity() != null) {
			if ( item.getItemName().equals("test") && item.getQuantity() == 123 ){
//				errors.addError(new ObjectError("item", new String[]{"testErrorCase"}, new Object[]{"test","123"}, null )); // TODO
				errors.reject("testErrorCase", new Object[]{"test","123"}, null);
			}
		}


	}
}
