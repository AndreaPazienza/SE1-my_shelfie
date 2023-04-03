public abstract class CGOnLines extends CommonGoalAbs{

    public CGOnLines(int players){
        super(players);
    }
    public void control(Player player) {

        if (!commonGoalAchived()) {

            boolean validRows =true, lastRow=false;
            Color[] rows = new Color[PersonalShelf.N_COLUMN ];
            int rowsLines = 0;

            //Ciclo per controllare
            for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
                validRows = true;
                rows[0] = Color.GREY;
                if(i == PersonalShelf.N_ROWS-1) lastRow=true;
                //Acquisizione dell'array della riga
                for (int j = 0; j < PersonalShelf.N_COLUMN && validRows; j++) {
                    Color colorOfSlot = player.getShelf().getSingleSlot(i, j).getColor();
                    if (colorOfSlot.equals(Color.GREY)) {
                        validRows = false;
                    }
                    rows[j] = colorOfSlot;

                }
                if (controlRows(player, rows,rowsLines)) {
                    rowsLines++;
                }

            }
            if(lastRow && !commonGoalAchived()){
                controlRows(player, rows, rowsLines);
            }
        }
    }

    protected abstract boolean controlRows(Player player, Color[] rows, int alreadyFound);

}
