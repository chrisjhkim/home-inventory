package com.chrisjhkim.sweethome.location.service;

import com.chrisjhkim.sweethome.location.PlaceCode;
import com.chrisjhkim.sweethome.location.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

	@Override
	public List<PlaceCode> getPlaceCodes() {
		List<PlaceCode> placeCodes = new ArrayList<>();
		placeCodes.add(new PlaceCode("BEDROOM_VERANDA_STORAGE", "안방 베란다 창고"));
		placeCodes.add(new PlaceCode("BEDROOM_CLOSET", "안방 옷장"));
		placeCodes.add(new PlaceCode("DRESS_ROOM", "옷방"));
		placeCodes.add(new PlaceCode("COMPUTER_ROOM_SHELF", "컴퓨터방 선반"));
		placeCodes.add(new PlaceCode("COMPUTER_ROOM_CLOSET", "컴퓨터방 옷장"));
		return placeCodes;
	}

	public Optional<Place> findByPlaceId(Long placeId) {
		return null;
	}
}
