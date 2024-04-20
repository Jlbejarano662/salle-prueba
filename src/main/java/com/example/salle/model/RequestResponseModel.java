package com.example.salle.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestResponseModel {
    @NotNull
    @NotEmpty
    @NotBlank
    private String searchword;
    @NotNull
    @Min(1)
    private int rows;
    @NotNull
    @NotEmpty
    @NotBlank
    private String word;
    private Boolean contains;

    public RequestResponseModel() {
        super();
    }

    public RequestResponseModel(String searchword, int rows, String word, Boolean contains) {
        this.searchword = searchword;
        this.rows = rows;
        this.word = word;
        this.contains = contains;
    }

    public char[][] getMatrix() {
        int cols = this.searchword.length() / this.rows;
        char[][] matrix = new char[this.rows][cols];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = this.searchword.charAt(index);
                index++;
            }
        }
        return matrix;
    }

}