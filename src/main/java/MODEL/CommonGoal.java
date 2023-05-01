package MODEL;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CommonGoal extends Observable {

    private CommonGoalAbs goal;

    public CommonGoal(int players){
        this.goal = this.getACommonGoal(players);
    }
    public CommonGoalAbs getGoal(){
        return goal;
    }

    public CommonGoalAbs getACommonGoal(int players){

        ArrayList<CommonGoalAbs> deck = new ArrayList<>();
        deck.add(new CGFourCorners(players));
        deck.add(new CGDecreaseTiles(players));
        deck.add(new CGEightTilesSameType(players));
        deck.add(new CGFiveTilesDiagonal(players));
        deck.add(new CGFiveTilesX(players));
        deck.add(new CGSixGroupsTwoTiles(players));
        deck.add(new CGDecreaseTiles(players));
        deck.add(new CGSameTypeSquare(players));
        //definizione colonne
        deck.add(new CGThreeColumnsSixTiles(players));
        deck.add(new CGTwoColumnsSixTiles(players));
        //definizione righe
        deck.add(new CGTwoLinesFiveTiles(players));
        deck.add(new CGFourLinesFiveTiles(players));

        return deck.get(shuffle());
    }
    public static int shuffle(){
        int randIndex = new Random().nextInt(12);
        return  randIndex;
    }

}
