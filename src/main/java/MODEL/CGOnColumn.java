package MODEL;

public abstract class CGOnColumn extends CommonGoalAbs{

    public CGOnColumn(int players){
        super(players);
    }

    /**
     * {@inheritDoc}
     *
     * @param player The player whose shelf has to be checked.
     */
    @Override
    public void control(Player player) {
        if (!commonGoalAchived()) {
            boolean validColumn =true, lastColumn=false;
            Color[] column = new Color[PersonalShelf.N_ROWS];
            int columnLines = 0;

            //Ciclo per controllare tutte le
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                validColumn=true;
                column[0] = Color.GREY;
                if(j == PersonalShelf.N_COLUMN-1) lastColumn=true;
                //Acquisizione dell'array della colonna
                for (int i = 0; i < PersonalShelf.N_ROWS && validColumn; i++) {
                    Color colorOfSlot = player.getShelf().getSingleSlot(i, j).getColor();
                    if (colorOfSlot.equals(Color.GREY)) {
                        validColumn = false;
                    }
                    column[i] = colorOfSlot;

                }
                if (controlColumn(player, column, columnLines) && !commonGoalAchived()) {
                    columnLines++;
                }

            }
            if(lastColumn && !commonGoalAchived()){
                controlColumn(player, column, columnLines);
            }
        }
    }

    protected abstract boolean controlColumn(Player player, Color[] column, int columnFound);
}
