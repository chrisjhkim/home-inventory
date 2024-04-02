package com.chrisjhkim.sweethome.item.controller;

import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ItemController.class)
class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService; // ItemServiceImpl의 MockBean

	@Test
	@DisplayName("save 호출되는지 테스트")
	public void whenCreateItemCalled_thenSaveItemIsCalled() throws Exception {
		String itemJson = "{\"id\":1,\"name\":\"Test Item\",\"count\":10}";
		mockMvc.perform(post("/item/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemJson))
						.andExpect(status().isOk());

		// 검증: ItemService의 saveItem 메소드가 호출되었는지 확인
		verify(itemService).createItem(any(Item.class));
	}



}