package com.chrisjhkim.sweethome.place.service;

import com.chrisjhkim.sweethome.location.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

	public Optional<Place> findByPlaceId(Long placeId) {
		return null;
	}
}
