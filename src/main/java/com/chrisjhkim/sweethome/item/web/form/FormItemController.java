package com.chrisjhkim.sweethome.item.web.form;

import com.chrisjhkim.sweethome.item.dto.ItemSearchCond;
import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.service.ItemService;
import com.chrisjhkim.sweethome.item.validation.SaveCheck;
import com.chrisjhkim.sweethome.item.validation.UpdateCheck;
import com.chrisjhkim.sweethome.item.web.dto.SearchType;
import com.chrisjhkim.sweethome.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {
	private final ItemService itemService;
	private final PlaceService placeService;
//	private final ItemDTOValidator itemDTOValidator;
//
//	@InitBinder //  기본으로는 Controller 에 만 적용
//	public void init(WebDataBinder dataBinder){
//		log.info("init binder {}",dataBinder);
//		dataBinder.addValidators(itemDTOValidator);
//	}

	@ModelAttribute("tags")
	public Map<String, String> tags() {
		Map<String, String> tags = new LinkedHashMap<>();
		tags.put("CLEANING_SUPPLIES", "청소용품");
		tags.put("BABY_SUPPLIES", "아기용품");
		tags.put("EXPENDABLES", "소모품");
		return tags;
	}
	@ModelAttribute("itemTypes")
	public ItemType[] itemTypes() {
		return ItemType.values();
	}
	@ModelAttribute("placeCodes")
	public List<PlaceCode> deliveryCodes() {
		List<PlaceCode> placeCodes = new ArrayList<>();
		placeCodes.add(new PlaceCode("BEDROOM_VERANDA_STORAGE", "안방 베란다 창고"));
		placeCodes.add(new PlaceCode("BEDROOM_CLOSET", "안방 옷장"));
		placeCodes.add(new PlaceCode("DRESS_ROOM", "옷방"));
		placeCodes.add(new PlaceCode("COMPUTER_ROOM_SHELF", "컴퓨터방 선반"));
		placeCodes.add(new PlaceCode("COMPUTER_ROOM_CLOSET", "컴퓨터방 옷장"));
		return placeCodes;
	}
	@ModelAttribute("searchTypes")
	public SearchType[] searchTypes(){return SearchType.values();}

	@GetMapping
	public String items(Model model,
	                    @ModelAttribute("itemSearch")ItemSearchCond itemSearchCond){
		log.info("itemSearchCond = {}", itemSearchCond);

		List<ItemDTO> itemList = itemService.findItems(itemSearchCond).stream()
				.map(ItemDTO::from)
				.collect(Collectors.toList());

//		itemList.forEach(itemDTO -> {
//			placeService.getPlaceByPlaceCode(itemDTO.getPlaceCode())
//					.ifPresent(place -> itemDTO.setPlaceName(place.getName()))
//		});


		model.addAttribute("itemList", itemList);
		return "item/form/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable(name = "itemId") Long itemId,
	                   Model model){
		System.out.println("itemId = " + itemId);
		ItemDTO item = ItemDTO.from(itemService.findById(itemId));
		model.addAttribute("item", item);
		return "item/form/item";
	}



	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("item", new ItemDTO() );
		return "item/form/addForm";
	}



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



	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable(name = "itemId") Long itemId,
	                       Model model) {
		ItemDTO item = ItemDTO.from(itemService.findById(itemId));
		model.addAttribute("item", item);
		return "item/form/editForm";
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
