public abstract class CGOnLines extends CommonGoalAbs{

    public void control(PersonalShelf shelf) {


        Color[] rows = new Color[PersonalShelf.N_COLUMN - 1];
        int columnLines=0;

        //Ciclo per controllare tutte le
        for (int i = 0; i < PersonalShelf.N_ROWS - 1; i++) {

            //Acquisizione dell'array della colonna
            for (int j=0; j < PersonalShelf.N_COLUMN -1; j++){

                rows[j] = shelf.getSingleSlot(i, j).getColor();

            }
            if(controlRows(rows)) {columnLines++;}

        }

        if(columnLines>2) {givePoints(playerPlying);}

    }

    protected abstract boolean controlRows(Color[] rows);

}
