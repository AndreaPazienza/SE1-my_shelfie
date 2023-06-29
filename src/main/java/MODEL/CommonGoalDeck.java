package MODEL;

import java.util.ArrayList;
import java.util.Random;

public class CommonGoalDeck{
    private static final ArrayList<CommonGoalAbs> deck = new ArrayList<>();

    public CommonGoalDeck(int players){
        deck.add(new CGFourCorners(players));
        deck.add(new CGDecreaseTiles(players));
        deck.add(new CGEightTilesSameType(players));
        deck.add(new CGFiveTilesDiagonal(players));
        deck.add(new CGFiveTilesX(players));
        deck.add(new CGSixGroupsTwoTiles(players));
        deck.add(new CGFourGroupsFourTiles(players));
        deck.add(new CGSameTypeSquare(players));
        //column definition
        deck.add(new CGThreeColumnsSixTiles(players));
        deck.add(new CGTwoColumnsSixTiles(players));
        //lines definition
        deck.add(new CGTwoLinesFiveTiles(players));
        deck.add(new CGFourLinesFiveTiles(players));
    }

    public CommonGoalAbs getACommonGoal(){
        int Pos = shuffle();
        CommonGoalAbs CG = deck.get(Pos);
        deck.remove(Pos);
        return CG;
    }
    public static int shuffle(){
        int randIndex = new Random().nextInt(deck.size());
        return  randIndex;
    }

}
