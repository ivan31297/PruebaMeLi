package com.mutante.app.helpers;

import java.util.regex.Pattern;

public class Validations {

    public static boolean validateLetter(String letter) {
        Pattern pat = Pattern.compile("(.*[^ACTG])");
        return !pat.matcher(letter).matches();
    }

    public static boolean validateMatrixSize(int letterlength, int matrixSize) {
        return letterlength == matrixSize;
    }

}
