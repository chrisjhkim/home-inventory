package com.chrisjhkim.sweethome.place.service;

import com.chrisjhkim.sweethome.location.entity.Place;

import java.util.Optional;

public interface PlaceService {
	Optional<Place> findByPlaceId(Long placeId);

}
