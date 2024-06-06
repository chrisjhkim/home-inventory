package com.chrisjhkim.sweethome.item.controller;

import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.service.ItemService;
import com.chrisjhkim.sweethome.item.validation.SaveCheck;
import com.chrisjhkim.sweethome.item.validation.UpdateCheck;
import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/form/items/")
@Slf4j
@RequiredArgsConstructor
public class ItemRestController {

	private final ItemService itemService;

	@PostMapping("/add")
	public String addItem(
//			@Validated // itemDTOValidator.validate(item, bindingResult); 대신
			@Validated(SaveCheck.class)
			@ModelAttribute("item")
			ItemDTO item,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {
		log.info("input = {}" , item);


		if (StringUtils.hasText(item.getItemName()) && item.getQuantity() != null) {
			if ( item.getItemName().equals("test") && item.getQuantity() == 123 ){
//				errors.addError(new ObjectError("item", new String[]{"testErrorCase"}, new Object[]{"test","123"}, null )); // TODO
				bindingResult.reject("testErrorCase", new Object[]{"test","123"}, null);
			}
		}



		if ( bindingResult.hasErrors() ){
			log.error("errors = {}", bindingResult);
//			model.addAttribute("errors", errors);
			return "item/form/addForm";
		}

		System.out.println("item = " + item);
		Item savedItem = itemService.createItem(item.toItem());
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/form/items/{itemId}";
	}

	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable(name = "itemId") Long itemId,
//					   @Validated
                       @Validated(UpdateCheck.class)
	                   @ModelAttribute("item") ItemDTO item,
                       BindingResult bindingResult) {
		log.info("item = {}", item);;
		if (StringUtils.hasText(item.getItemName()) && item.getQuantity() != null) {
			if ( item.getItemName().equals("test") && item.getQuantity() == 123 ){
//				errors.addError(new ObjectError("item", new String[]{"testErrorCase"}, new Object[]{"test","123"}, null )); // TODO
				bindingResult.reject("testErrorCase", new Object[]{"test","123"}, null);
			}
		}
		if ( bindingResult.hasErrors() ){
			log.error("errors = {}", bindingResult);
//			model.addAttribute("errors", errors);
			return "item/form/editForm";
		}


		System.out.println("itemId = " + itemId);
		System.out.println("item = " + item);
		itemService.update(itemId, item);
		return "redirect:/form/items/{itemId}";
	}

}
