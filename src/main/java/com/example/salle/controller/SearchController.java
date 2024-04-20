package com.example.salle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salle.model.RequestResponseModel;
import com.example.salle.service.ISearchService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SearchController {

    @Autowired
    private ISearchService iSearchService;

    @PostMapping("search-horandvert/")
    public ResponseEntity<RequestResponseModel> searchHorandvert(@Valid @RequestBody RequestResponseModel request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        RequestResponseModel response = iSearchService.searchHorandvertch(request.getMatrix(), request.getWord());
        response.setSearchword(request.getSearchword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("search/")
    public ResponseEntity<RequestResponseModel> search(@Valid @RequestBody RequestResponseModel request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        RequestResponseModel response = iSearchService.search(request.getMatrix(), request.getWord());
        response.setSearchword(request.getSearchword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
