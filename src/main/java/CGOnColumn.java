public abstract class CGOnColumn extends CommonGoalAbs{

    @Override
    public void control(PersonalShelf shelf) {


        Color[] column = new Color[PersonalShelf.N_ROWS - 1];
        int columnCounter=0;

            //Ciclo per controllare tutte le
        for (int i = 0; i < PersonalShelf.N_COLUMN - 1; i++) {

            //Acquisizione dell'array della colonna
            for (int j=0; j < PersonalShelf.N_ROWS -1; j++){

                column[j] = shelf.getSingleSlot(j, i).getColor();

            }

            if(controlColumn(column)) {columnCounter++;}

        }

        if(columnCounter>2) {givePoints(playerPlying);}

    }

    protected abstract boolean controlColumn(Color[] column);

}
