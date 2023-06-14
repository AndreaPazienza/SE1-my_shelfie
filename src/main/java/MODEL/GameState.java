package MODEL;

/**
 * Enum that represents the current state of the game.
 */
public enum GameState{

    LOGIN,                          //The players are logging in the game
    PLAYING_IN_SELECTION,           //The current player is selecting the slots to pick up from the dashboard
    PLAYING_IN_ORDERING,            //The current player is ordering the selected slots
    PLAYING_IN_INSERTION,           //The current player is inserting the selected slots
}

