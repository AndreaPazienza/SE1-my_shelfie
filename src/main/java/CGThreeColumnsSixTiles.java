public class CGThreeColumnsSixTiles extends CGOnColumn {

    public CGThreeColumnsSixTiles(int players){
        super(players);
    }
    public boolean controlColumn(Color[] column) {

            boolean[] colorFound = new boolean[6];
            int differentColors=0, index=0;
            Color colorChecked;

        for(int i=0; i<column.length; i++){
            colorChecked = column[i];
            index = colorChecked.ordinal();

        if(!colorFound[index]){
                                colorFound[index] = true;
                                differentColors++;
                            }
             if(differentColors>3){ return false;}

        }


        return true;}
}
