package com.example.salle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salle.model.RequestResponseModel;
import com.example.salle.service.ISearchService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SearchController {

    @Autowired
    private ISearchService iSearchService;

    @PostMapping("search-horandvert/")
    public ResponseEntity<RequestResponseModel> searchHorandvert(@RequestBody RequestResponseModel request) {
        if (request.getRows() <= 0 || !StringUtils.isNotBlank(request.getSearchword())
                || !StringUtils.isNotBlank(request.getWord())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        RequestResponseModel response = iSearchService.searchHorandvertch(request.getMatrix(), request.getWord());
        response.setSearchword(request.getSearchword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("search/")
    public ResponseEntity<RequestResponseModel> search(@RequestBody RequestResponseModel request) {
        if (request.getRows() <= 0 || !StringUtils.isNotBlank(request.getSearchword())
                || !StringUtils.isNotBlank(request.getWord())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        RequestResponseModel response = iSearchService.search(request.getMatrix(), request.getWord());
        response.setSearchword(request.getSearchword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
