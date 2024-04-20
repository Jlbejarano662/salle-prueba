package com.example.salle.service;

import org.springframework.stereotype.Service;

import com.example.salle.model.RequestResponseModel;

@Service
public class SearchService implements ISearchService {

    @Override
    public RequestResponseModel search(char[][] matrix, String word) {
        RequestResponseModel response = new RequestResponseModel();
        // Buscamos Horizontal y verticalmente primero
        response = searchHorandvertch(matrix, word);
        if (response.getContains())
            return response;

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Buscamos diagonalmente
        for (int i = 0; i <= rows - word.length(); i++) {
            for (int j = 0; j <= cols - word.length(); j++) {
                // Buscar desde esquina superior izquierda ó esquina inferior izquierda
                if (searchDiagonalFromTopLeft(matrix, word, i, j)
                        || searchDiagonalFromBottomLeft(matrix, word, i + word.length() - 1, j)) {

                    response.setStartRow(i);
                    response.setStartCol(j);
                    response.setContains(true);
                    return response;
                }
            }

            // Buscar desde esquina superior derecha
            for (int j = cols - 1; j >= word.length() - 1; j--) {
                if (searchDiagonalFromTopRight(matrix, word, i, j)) {
                    response.setStartRow(i);
                    response.setStartCol(j);
                    response.setContains(true);
                    return response;
                }
            }
        }

        // Buscar desde esquina inferior derecha
        for (int i = rows - 1; i >= word.length() - 1; i--) {
            for (int j = cols - 1; j >= word.length() - 1; j--) {
                if (searchDiagonalFromBottomRight(matrix, word, i, j)) {
                    response.setStartRow(i);
                    response.setStartCol(j);
                    response.setContains(true);
                    return response;
                }
            }
        }

        response.setContains(false);
        return response;
    }

    @Override
    public RequestResponseModel searchHorandvertch(char[][] matrix, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // Construirmos obejtso de respuesta
        RequestResponseModel response = new RequestResponseModel();
        response.setRows(rows);
        response.setWord(word);

        // Buscar horizontalmente
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - word.length(); j++) {
                if (searchHorizontal(matrix, word, i, j)) {
                    response.setStartRow(i);
                    response.setStartCol(j);
                    response.setContains(true);
                    return response;
                }
            }

            // Buscar horizontalmente invertido
            for (int j = cols - 1; j >= word.length() - 1; j--) {
                if (searchHorizontalReverse(matrix, word, i, j)) {
                    response.setStartRow(i);
                    response.setStartCol(j);
                    response.setContains(true);
                    return response;
                }
            }
        }

        // Buscar verticalmente
        for (int i = 0; i <= rows - word.length(); i++) {
            for (int j = 0; j < cols; j++) {
                if (searchVertical(matrix, word, i, j)) {
                    response.setStartRow(i);
                    response.setStartCol(j);
                    response.setContains(true);
                    return response;
                }
            }
        }

        // Buscar verticalmente invertido
        for (int i = rows - 1; i >= word.length() - 1; i--) {
            for (int j = 0; j < cols; j++) {
                if (searchVerticalReverse(matrix, word, i, j)) {
                    response.setStartRow(i);
                    response.setStartCol(j);
                    response.setContains(true);
                    return response;
                }
            }
        }

        response.setContains(false);
        return response;
    }

    // Método para buscar horizontalmente
    private static boolean searchHorizontal(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row][col + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Método para buscar verticalmente
    private static boolean searchVertical(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row + i][col] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Método para buscar horizontalmente invertido
    private static boolean searchHorizontalReverse(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row][col - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Método para buscar verticalmente invertido
    private static boolean searchVerticalReverse(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row - i][col] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Método para buscar en diagonal desde la esquina superior derecha
    private static boolean searchDiagonalFromTopRight(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row + i][col - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Método para buscar en diagonal desde la esquina superior izquierda
    private static boolean searchDiagonalFromTopLeft(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row + i][col + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Método para buscar en diagonal desde la esquina inferior izquierda
    private static boolean searchDiagonalFromBottomLeft(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row - i][col + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Método para buscar en diagonal invertida
    private static boolean searchDiagonalFromBottomRight(char[][] wordGrid, String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (wordGrid[row - i][col - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
