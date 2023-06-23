package MODEL;

/**
 * Enum that represents the errors occurred during the game.
 */
public enum GameError {
    SELECT_ERROR_NOT_CATCHABLE,                 //The selected slot isn't catchable
    SELECT_ERROR_NOT_ADJACENT,                  //The selected slots aren't adjacent
    SELECT_ERROR_ONE_NOT_CATCHABLE,             //One (or more than one) selected slot isn't catchable
    INSERT_ERROR,                               //The selected column hasn't enough space to insert the number of selected slots
    SPACE_CHOICES_ERROR,                        //The player selected a number of slots that can't take from the dashboard or insert in the shelf
}

