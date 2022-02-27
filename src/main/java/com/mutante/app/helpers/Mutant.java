package com.mutante.app.helpers;

/**
 * @author Ivan
 * <p>Esta clase se encarga de verificar las secuencias del DNA.
 */
public class Mutant {
    /**
     * @param dna
     * @return
     */
    public static boolean isMutant(String[] dna) {

        int recordsDna = 0; //Contabiliza total de combinaciones iguales
        int positionRow = -1;
        int positionCol = -1;
        int positionVertRigthRow = -1;
        int positionVertRigthCol = -1;
        int positionVertLeftRow = -1;
        int positionVertLeftCol = -1;

        //Variable i recorre los string completos ej "ATAGC"
        for (int i = 0; i < dna.length; i++) {
            //j determina la posicion dentro del string
            for (int j = 0; j < dna[i].length(); j++) {

                // Control Horizontal
                if (j < dna[i].length() - 3 && (positionRow == -1 || positionRow == j)) {
                    if (isEquals(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2), dna[i].charAt(j + 3))) {
                        recordsDna++;
                        positionRow = j + 4;
                    }
                }
                if (j >= positionRow) {
                    positionRow = -1;
                }

                // Control Vertical
                if (i < dna.length - 3 && (positionCol == -1 || positionCol == i)) {
                    // vertical check
                    if (isEquals(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j))) {
                        recordsDna++;
                        positionCol = i + 4;
                    }
                }
                if (i >= positionCol) {
                    positionCol = -1;
                }

                //Control Diagonal
                if (i < dna.length - 3 && j < dna[i].length() - 3 && ((positionVertRigthRow == -1 && positionVertRigthCol == -1) || (positionVertRigthRow == i && positionVertRigthCol == j))) {
                    if (isEquals(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                        recordsDna++;
                        positionVertRigthRow = i + 4;
                        positionVertRigthCol = j + 4;
                    }
                }
                if (i >= positionVertRigthRow && j >= positionVertRigthCol) {
                    positionVertRigthRow = -1;
                    positionVertRigthCol = -1;
                }

                //Control Diagonal Invertido
                if (i >= 3 && j < dna[i].length() - 3 && ((positionVertLeftRow == -1 && positionVertLeftCol == -1) || (positionVertLeftRow == i && positionVertLeftCol == j))) {
                    if (isEquals(dna[i].charAt(j), dna[i - 1].charAt(j + 1), dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
                        recordsDna++;
                        positionVertLeftRow = i + 4;
                        positionVertLeftCol = j - 4;
                    }
                }
                if (i >= positionVertLeftRow && j >= positionVertLeftCol) {
                    positionVertLeftRow = -1;
                    positionVertLeftCol = -1;
                }

                if (recordsDna >= 2) {
                    return true;
                }

            }

        }

        return false;
    }

    private static boolean isEquals(char w, char x, char y, char z) {
        return w == x && x == y && y == z;
    }
}
