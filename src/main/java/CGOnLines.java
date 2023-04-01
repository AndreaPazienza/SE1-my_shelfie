public abstract class CGOnLines extends CommonGoalAbs{


    public void control(Player player) {

        if (!commonGoalAchived()) {
            Color[] rows = new Color[PersonalShelf.N_COLUMN - 1];
            int rowsLines = 0;


            //Ciclo per controllare
            for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
                //Acquisizione dell'array della riga
                for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                    Color colorOfSlot = player.getShelf().getSingleSlot(i, j).getColor();
                    if (colorOfSlot.equals(Color.GREY)) {
                        return;
                    }
                    rows[j] = colorOfSlot;

                }
                if (controlRows(rows)) {
                    rowsLines++;
                }

            }
            //potrebbe presenatare un bug, siccome devo avere 4 righe con al più tre tipi
            if (rowsLines > 2) {
                givePoints(player);
            }

        }
    }

    protected abstract boolean controlRows(Color[] rows);

}
