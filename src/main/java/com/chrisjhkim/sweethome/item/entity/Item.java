package com.chrisjhkim.sweethome.item.entity;

import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import com.chrisjhkim.sweethome.item.web.form.ItemType;
import com.chrisjhkim.sweethome.location.entity.Place;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String itemName;
	private int quantity;

	private Boolean isNewItem;   // 미개봉 새 물품



	@OneToMany(mappedBy = "item")
	@Builder.Default
	private List<ItemTag> itemTags = new ArrayList<>();

	private ItemType itemType;
	private String placeCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="place_id")
	private Place place;


	public void update(ItemDTO itemUpdateRequest) {
		this.itemName = itemUpdateRequest.getItemName();
		this.quantity = itemUpdateRequest.getQuantity();

		this.isNewItem = itemUpdateRequest.getOpen();
		if ( itemUpdateRequest.getTags() != null ) {
//			this.tags = String.join(",", itemUpdateRequest.getTags());
			// TODO : if 문 없어질 수 있을듯
		}
		this.itemType = itemUpdateRequest.getItemType();
		this.placeCode = itemUpdateRequest.getPlaceCode();
	}

	public void setLocation(Place place){
		if ( this.place != null ) {
			this.place.getItems().remove(this);
		}
		this.place = place;
		place.addItem(this);
	}

//	public Item(Long id, String itemName, int quantity, Boolean isNewItem, List<ItemTag> itemTags, ItemType itemType, String placeCode, Place place) {
//		this.id = id;
//		this.itemName = itemName;
//		this.quantity = quantity;
//		this.isNewItem = isNewItem;
//		this.itemTags = itemTags;
//		this.itemType = itemType;
//		this.placeCode = placeCode;
//		if ( place != null ) {
//			setLocation(place);
//		}
//	}
}
