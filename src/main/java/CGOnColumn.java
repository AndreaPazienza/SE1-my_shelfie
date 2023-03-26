public abstract class CGOnColumn extends CommonGoalAbs{

    @Override
    public void control(PersonalShelf shelf) {


        Color[] column = new Color[PersonalShelf.N_ROWS];
        int columnLines=0;

        //Ciclo per controllare tutte le
        for (int j = 0; j < PersonalShelf.N_COLUMN ; j++) {
            //Acquisizione dell'array della colonna
            for (int i=0; i < PersonalShelf.N_ROWS ; i++){
                Color colorOfSlot = shelf.getSingleSlot(i, j).getColor();
                if( colorOfSlot.equals(Color.GREY)){
                    return;
                }
                column[i] = colorOfSlot;

            }
            if(controlColumn(column)) {columnLines++;}

        }

        if(columnLines>2) {givePoints(playerPlying);}

    }

    protected abstract boolean controlColumn(Color[] column);
}
