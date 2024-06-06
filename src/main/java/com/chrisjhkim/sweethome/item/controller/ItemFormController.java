package com.chrisjhkim.sweethome.item.controller;

import com.chrisjhkim.sweethome.item.dto.ItemSearchCond;
import com.chrisjhkim.sweethome.item.service.ItemService;
import com.chrisjhkim.sweethome.item.web.dto.SearchType;
import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import com.chrisjhkim.sweethome.item.web.form.ItemType;
import com.chrisjhkim.sweethome.location.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class ItemFormController {
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


	@ModelAttribute("searchTypes")
	public SearchType[] searchTypes(){return SearchType.values();}

	@GetMapping
	public String items(Model model,
	                    @ModelAttribute("itemSearch")ItemSearchCond itemSearchCond){
		log.info("itemSearchCond = {}", itemSearchCond);

		List<ItemDTO> itemList = itemService.findItems(itemSearchCond).stream()
				.map(ItemDTO::from)
				.collect(Collectors.toList());


		model.addAttribute("itemList", itemList);
		return "item/form/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable(name = "itemId") Long itemId,
	                   Model model){
		System.out.println("itemId = " + itemId);
		ItemDTO item = ItemDTO.from(itemService.findById(itemId));
		model.addAttribute("item", item);
		model.addAttribute("placeCodes", placeService.getPlaceCodes());
		return "item/form/item";
	}


	@ModelAttribute("itemTypes")
	public ItemType[] itemTypes() {
		return ItemType.values();
	}



	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("item", new ItemDTO() );
//		model.addAttribute("itemTypes", ItemType.values() );

		model.addAttribute("placeCodes", placeService.getPlaceCodes());
		return "item/form/addForm";
	}





	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable(name = "itemId") Long itemId,
	                       Model model) {
		ItemDTO item = ItemDTO.from(itemService.findById(itemId));
		model.addAttribute("item", item);
		return "item/form/editForm";
	}


}
