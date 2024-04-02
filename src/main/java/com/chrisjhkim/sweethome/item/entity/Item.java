package com.chrisjhkim.sweethome.item.entity;

import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import com.chrisjhkim.sweethome.item.web.form.ItemType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString // TODO : REMOVE
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String itemName;
	private int quantity;

	private Boolean open;   // 미개봉 새 물품
	private String tags; // tag 들
	private ItemType itemType;
	private String placeCode;


	public String getPlace(){
		return null;
	}

	public void update(ItemDTO itemUpdateRequest) {
		this.itemName = itemUpdateRequest.getItemName();
		this.quantity = itemUpdateRequest.getQuantity();

		this.open = itemUpdateRequest.getOpen();
		if ( itemUpdateRequest.getTags() != null ) {
			this.tags = String.join(",", itemUpdateRequest.getTags());
			// TODO : if 문 없어질 수 있을듯
		}
		this.itemType = itemUpdateRequest.getItemType();
		this.placeCode = itemUpdateRequest.getPlaceCode();
	}
}
