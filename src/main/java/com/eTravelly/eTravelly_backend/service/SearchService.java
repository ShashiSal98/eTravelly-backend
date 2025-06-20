package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.SearchRequest;
import com.eTravelly.eTravelly_backend.dto.SearchResult;

import java.util.List;

public interface SearchService {
    List<SearchResult> searchAvailableRooms(SearchRequest request);
}
