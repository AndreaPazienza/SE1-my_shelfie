package MODEL;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents the deck that contains the common goal cards.
 */
public class CommonGoalDeck{

    /**
     * The list of common goal still in the deck.
     */
    private static final ArrayList<CommonGoalAbs> deck = new ArrayList<>();

    /**
     * Constructor of CommonGoalsDeck class.
     *
     * @param players The number of players in the game.
     */
    public CommonGoalDeck(int players){
        deck.add(new CGFourCorners(players));
        deck.add(new CGDecreaseTiles(players));
        deck.add(new CGEightTilesSameType(players));
        deck.add(new CGFiveTilesDiagonal(players));
        deck.add(new CGFiveTilesX(players));
        deck.add(new CGSixGroupsTwoTiles(players));
        deck.add(new CGFourGroupsFourTiles(players));
        deck.add(new CGSameTypeSquare(players));
        //Column definition common goals
        deck.add(new CGThreeColumnsSixTiles(players));
        deck.add(new CGTwoColumnsSixTiles(players));
        //Lines definition common goals
        deck.add(new CGTwoLinesFiveTiles(players));
        deck.add(new CGFourLinesFiveTiles(players));
    }

    /**
     * Retrieves a randomly chosen common goal from the deck.
     *
     * @return The extracted common goal.
     */
    public CommonGoalAbs getACommonGoal(){
        int Pos = shuffle();
        CommonGoalAbs CG = deck.get(Pos);
        deck.remove(Pos);
        return CG;
    }

    /**
     * Retrieves a random index.
     *
     * @return The index randomly extracted according to the cards still in the deck.
     */
    public static int shuffle(){
        int randIndex = new Random().nextInt(deck.size());
        return randIndex;
    }
}
