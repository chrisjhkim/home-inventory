package com.chrisjhkim.sweethome.location.service;

import com.chrisjhkim.sweethome.location.PlaceCode;
import com.chrisjhkim.sweethome.location.entity.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {


	List<PlaceCode> getPlaceCodes();
	Optional<Place> findByPlaceId(Long placeId);

}
