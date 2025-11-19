package MODEL;

/**
 * Class that represents a generic common goal card that requires a particular columns configuration to be achieved.
 */
public abstract class CGOnColumn extends CommonGoalAbs{

    /**
     * Constructor for CGOnColumn class.
     *
     * @param players The number of players in the match.
     */
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

            //Checking all the columns
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                validColumn=true;
                column[0] = Color.GREY;
                if(j == PersonalShelf.N_COLUMN-1) lastColumn=true;
                //Acquisition of the column from the shelf
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

    /**
     * Checks if there are enough columns that respect the condition to achieve the common goal.
     *
     * @param player The player whose shelf has to be checked.
     * @param column The colors of the column's slots.
     * @param columnFound The number of columns already found that respect the condition.
     * @return True if there are enough columns that respect the condition, false otherwise.
     */
    protected abstract boolean controlColumn(Player player, Color[] column, int columnFound);
}
