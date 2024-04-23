package com.chrisjhkim.sweethome.item.web.form;

import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.validation.SaveCheck;
import com.chrisjhkim.sweethome.item.validation.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
//@ScriptAssert(lang = "javascript" , script = "_this.quantity != 123 && _this.itemName!='test'")
public class ItemDTO {

	@NotNull(groups = UpdateCheck.class)
	private Long id;
	@NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
	private String itemName;
	@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
	@Range(min = 0,max = 9999, groups = {SaveCheck.class, UpdateCheck.class})
	private Integer quantity;

	private Boolean open;   // 미개봉 새 물품
	private List<String> tags; // tag 들
	private ItemType itemType;
	private String placeCode;




	public String getPlace(){
		return null;
	}
	public Item toItem() {
		return Item.builder()
				.itemName(this.itemName)
				.quantity(this.quantity)
				.isNewItem(this.open)
//				.tags(String.join(",", this.tags)) // TODO
				.itemType(this.itemType)
				.placeCode(this.placeCode)
				.build();
	}


	public static ItemDTO from(Item item) {
		ItemDTO result = new ItemDTO();
		result.id = item.getId();
		result.itemName = item.getItemName();
		result.quantity = item.getQuantity();
		result.open = item.getIsNewItem();
		if ( item.getItemTags() != null ) {
//			result.tags = Arrays.stream(item.getTags().split(",")).collect(Collectors.toList()); // TODO

		}
		result.itemType = item.getItemType();
		result.placeCode = item.getPlaceCode();
		return result;
	}

}
