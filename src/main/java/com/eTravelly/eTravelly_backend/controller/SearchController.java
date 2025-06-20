package com.eTravelly.eTravelly_backend.controller;

import com.eTravelly.eTravelly_backend.dto.SearchRequest;
import com.eTravelly.eTravelly_backend.dto.SearchResult;
import com.eTravelly.eTravelly_backend.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<?> searchAvailableRooms(@RequestBody SearchRequest request) {
        List<SearchResult> results = searchService.searchAvailableRooms(request);

        if (results.isEmpty()) {
            return ResponseEntity.ok("No available rooms found for the given criteria.");
        }

        return ResponseEntity.ok(results);
    }

}
