public class CGFiveTilesX extends CGSubmatrix {

    @Override
    protected boolean controlSubmatrix(Color[][] matrix) {
        //Essendo il controllo su una sottomatrice fissa 3x3 uso le posizioni fisse
        Color beginMatrix= matrix[0][0];
        Color centralMatrix = matrix[2][2];
        Color topRightMatrix = matrix[0][3];
        Color bottomLeftMatrix = matrix[3][0];
        Color bottomRightMatrix = matrix[3][3];

        if(beginMatrix.equals(centralMatrix) && beginMatrix.equals(topRightMatrix) && beginMatrix.equals(bottomRightMatrix) &&
        beginMatrix.equals(bottomLeftMatrix)){ return true;}

        else{return false;}


    }
}
