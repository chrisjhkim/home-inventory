package com.chrisjhkim.sweethome.item.dto;

import com.chrisjhkim.sweethome.item.web.dto.SearchType;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

//@Getter
//@ToString

@Data
public class ItemSearchCond {
	private SearchType searchType;
	private String searchText;



	public String getItemName(){
		if ( searchType == SearchType.ALL || searchType == SearchType.ITEM_NAME ){
			return searchText;
		}

		return null;
	}
	public String getPlaceName(){
		if ( searchType == SearchType.ALL || searchType == SearchType.PLACE ){
			return searchText;
		}
		return null;
	}
}
