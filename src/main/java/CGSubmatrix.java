public abstract class CGSubmatrix extends CommonGoalAbs {

    @Override
    public void control(PersonalShelf shelf) {

        boolean valid = true;
        Color[][] submatrix = new Color[3][3];

        for (int i = 0; i < PersonalShelf.N_ROWS - 3; i++) {
                for (int j = 0; j < PersonalShelf.N_COLUMN - 3; j++) {
                    valid=true;
                    for(int k=0; k < 3; k++){
                        for(int w=0; w<3; w++){

                            if(shelf.getSingleSlot(i+k, j+w).getColor().equals(Color.GREY))

                            {valid = false;}

                            else{ submatrix[k][w]  = shelf.getSingleSlot(i+k, j+w).getColor();}
                        }
                    }
                    if(valid)
                          {controlSubmatrix(submatrix);}
                }
         }
     }
        protected abstract boolean controlSubmatrix(Color[][] matrix);
}
