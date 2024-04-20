package com.example.salle.service;

import com.example.salle.model.RequestResponseModel;

public interface ISearchService {

    public RequestResponseModel searchHorandvertch(char[][] matrix, String word);
    public RequestResponseModel search(char[][] matrix, String word);
    
}
