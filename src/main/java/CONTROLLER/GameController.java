package CONTROLLER;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.*;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.RemoteException;

/**
 * Class that represents the controller of the game, it checks the input from the user interface and modifies the model of the game.
 */
public class GameController{

    /**
     * The model of the game associated to the controller.
     */
    private final Game game;

    /**
     * The slots selected by the player in the current turn.
     */
    private Slot[] selectedSlots;

    /**
     * The array that keeps memorized the selected slots.
     */
    private Target[] coordinatesSaver;

    /**
     * The value that marks the selection as valid.
     */
    private boolean didSelection = false;

    /**
     * Constructor for Controller class.
     *
     * @param game The model of the game associated to the controller.
     */
    public GameController(Game game){
        this.game=game;
    }

    /**
     * Starts the game in the model.
     *
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    public void startGame () throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
            game.setGameOn(true);
            game.startGame();
    }

    /**
     * Skips the turn of the player who should be playing.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    public void skipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        if(didSelection){
            undoSelection();
        }
        game.nextPlayerInGame();
    }

    /**
     * Disallows the selection of the current player and sets the model on his previous state.
     *
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    private void undoSelection() throws NotEnoughSpaceChoiceException, RemoteException {
        game.undoTurn(selectedSlots, coordinatesSaver);
        didSelection = false;
    }

    /**
     * Retrieves the nickname of the current player.
     *
     * @return The nickname of the current player.
     */
    public String getOnStage(){
        return game.playerOnStage().getNickname();
    }

    /**
     * Checks if the nickname chosen by a player is already in the enrolled nickname list.
     *
     * @param name The nickname to check.
     * @return True if the nickname is already picked, False otherwise.
     */
    public boolean checkNick(String name){
        for(int i = 0; i < game.getPlayerInGame(); i++){
            if(game.getPlayer()[i].nickname.equals(name)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the slot corresponding to the input coordinates is catchable.
     *
     * @param x The index of the dashboard's slot's row to check.
     * @param y The index of the dashboard's slot's column to check.
     * @return True if the slot is catchable, False otherwise.
     */
    public boolean checkCoordinates(int x, int y){
        return game.getTable().getSingleSlot(x,y).isCatchable();
    }

    /**
     * Checks if two slot corresponding to the input coordinates are nearby.
     *
     * @param x The index of the first dashboard's slot's row to check.
     * @param y The index of the first dashboard's slot's column to check.
     * @param x1 The index of the second dashboard's slot's row to check.
     * @param y1 The index of the second dashboard's slot's column to check.
     * @return True if the slots are adjacent, false otherwise.
     */
    public boolean checkAdjacent(int x, int y, int x1, int y1){
        if((x == x1 && (y == y1 + 1 || y == y1-1)) || (y == y1 && (x == x1+1 || x == x1-1))){
            return true;
        }
        return false;
    }

    /**
     * Checks if the selection of the player is correct according to the rules, then proceeds to the selection in the model of the game.
     *
     * @param selectedCards The slots selected by the player in the current turn.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    public void checkSelect(SlotChoice[] selectedCards) throws NotCatchableException, NotAdjacentSlotsException, RemoteException {
        selectedSlots = new Slot[selectedCards.length];
        coordinatesSaver = new Target[selectedCards.length];
        //Manages the selection according to the number of selected slots
        switch (selectedCards.length){
            //Single slot selected
            case 1 -> {
                int x = selectedCards[0].getX();
                int y = selectedCards[0].getY();
                //Checks if the only selected slot is catchable
                if(checkCoordinates(x, y)){
                    didSelection = true;
                    coordinatesSaver[0] = new Target(game.getTable().getSingleSlot(x,y).getColor(), x, y);
                    selectedSlots[0]=game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y);
                } else {
                    game.setLastError(GameError.SELECT_ERROR_NOT_CATCHABLE);
                    throw new NotCatchableException("La tessera selezionata non può essere presa!");
                }
            }
            //Double slot selection
            case 2 -> {
                int x = selectedCards[0].getX();
                int y = selectedCards[0].getY();
                int x1 = selectedCards[1].getX();
                int y1 = selectedCards[1].getY();
                //Checks if the two selected slots are catchable
                if(checkCoordinates(x,y) && checkCoordinates(x1,y1)){
                    //Checks if the two selected slots are adjacent
                    if(checkAdjacent(x, y, x1, y1)){
                        selectedSlots[0]=(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y));
                        selectedSlots[1]=(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x1, y1));
                        coordinatesSaver[0] = new Target(selectedSlots[0].getColor(), x, y);
                        coordinatesSaver[1] = new Target(selectedSlots[1].getColor(), x1, y1);
                        didSelection = true;
                    } else {
                        game.setLastError(GameError.SELECT_ERROR_NOT_ADJACENT);
                        throw new NotAdjacentSlotsException("Le tessere selezionate non sono adiacenti!");
                    }
                } else {
                    game.setLastError(GameError.SELECT_ERROR_ONE_NOT_CATCHABLE);
                    throw new NotCatchableException("Una delle tessere selezionate non può essere presa!");
                }
            }
            //Triple slot selection
            case 3 -> {
                int x = selectedCards[0].getX();
                int y = selectedCards[0].getY();
                int x1 = selectedCards[1].getX();
                int y1 =  selectedCards[1].getY();
                int x2 = selectedCards[2].getX();
                int y2 =  selectedCards[2].getY();
                if(checkCoordinates(x,y) && checkCoordinates(x1, y1) && checkCoordinates(x2, y2)){
                    /*Controls the fact that the tiles are adjacent and that they form a straight line;
                    the first term of the expression is true if the first pair of coordinates (x,y) is adjacent to at least one between (x1,y1) and (x2,y2)
                    the second term tell us, combined with the first one, if the three tiles are nearby.
                    the last one is used to accept only group of tiles that form a straight line*/
                    if((checkAdjacent(x,y,x1,y1) || checkAdjacent(x,y,x2,y2)) && checkAdjacent(x1,y1,x2,y2) && ((x == x1 && x1 == x2) || (y == y1 && y1 == y2))){
                        selectedSlots[0]=(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y));
                        selectedSlots[1]=(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x1, y1));
                        selectedSlots[2]=(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x2, y2));
                        coordinatesSaver[0] = new Target(selectedSlots[0].getColor(), x, y);
                        coordinatesSaver[1] = new Target(selectedSlots[1].getColor(), x1, y1);
                        coordinatesSaver[2] = new Target(selectedSlots[2].getColor(), x2, y2);
                        didSelection = true;
                    } else {
                        game.setLastError(GameError.SELECT_ERROR_NOT_ADJACENT);
                        throw new NotAdjacentSlotsException("Le tessere selezionate non sono adiacenti!");
                    }
                } else {
                    game.setLastError(GameError.SELECT_ERROR_ONE_NOT_CATCHABLE);
                    throw new NotCatchableException("Una delle tessere selezionate non può essere presa!");
                }
            }
        }
    }

    /**
     * Perform the ordering of the selected slots in the model of the game.
     *
     * @param o The order decided by the player in the current turn, to manage a switch of the two slots in a double slot selection the parameter is set on a default 1,1,1 configuration.
     */
    public void checkOrder(OrderChoice o){
        if(o.getT() == 1 && o.getP() == 1 && o.getS() == 1){
            game.getPlayer()[game.getPlayerInGame()].orderCards(selectedSlots);
        } else {
            game.getPlayer()[game.getPlayerInGame()].orderCards(selectedSlots, o.getP(), o.getS(), o.getT());
        }
    }

    /**
     * Checks if the column chosen by the player has enough free slots to perform the insertion.
     *
     * @param column The shelf's column index chosen by the player.
     * @throws NotEnoughSpaceChoiceException If a player wants to insert in a column
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    public void checkInsert(int column) throws NotEnoughSpaceChoiceException, RemoteException {
        int countSpaces = 0;

        if(column==-1){
            didSelection=false;
            return;
        }

        for(int i = 0; i < PersonalShelf.N_ROWS; i++){
            if(game.getPlayer()[game.getPlayerInGame()].getShelf().getSingleSlot(i, column).getColor().Equals(Color.GREY)){
                countSpaces++;
            }
        }

        if(countSpaces >= selectedSlots.length) {
            game.getPlayer()[game.getPlayerInGame()].getShelf().insert(selectedSlots, column);
            didSelection = false;
        } else {
            game.setLastError(GameError.INSERT_ERROR);
            throw new NotEnoughSpaceChoiceException("La colonna scelta non può contenere così tante tessere!");
        }
    }

    /**
     * Adds an extra point to the first player who completed his personal shelf.
     */
    public void completeShelf() {
       game.getPlayer()[game.getPlayerInGame()].sumPoints(1);
    }

    /**
     * Retrieves the ranking and the string that announces the winner of the game with his score.
     *
     * @return The ranking and the string that announces the winner of the game.
     */
    public String endGame(){

        Player winner;
        for(int i = 0; i < game.getPlayer().length; i++){
            game.getPlayer()[i].checkScore();
        }
        winner = game.finalScore();
        StringBuilder endGameString = new StringBuilder();
        game.setGameOn(false);

        for(int i=0; i < game.getNplayers(); i++){
            endGameString.append("\n").append(game.getPlayer()[i].getNickname()).append(" ha totalizzato: ").append(game.getPlayer()[i].getScore()).append(" punti ");
        }
        endGameString.append("\n Il vincitore è: ").append(winner.getNickname()).append(" Congratulazioni!");

        return endGameString.toString();
    }

    /**
     * Updates the turn in the game.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    public void turnUpdate() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        System.out.println("Aggioramento del turno in corso..");
        game.updateTurn();
    }

    /**
     * Checks if the number of slots to select insert to the player can actually be selected according to the catchable slots positioning in the dashboard and the available spaces in the shelf.
     *
     * @param number The number of slots the player would like to select.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    public void checkSpaceChoices(int number) throws NotEnoughSpaceChoiceException, RemoteException {
        int rows = PersonalShelf.N_ROWS;
        int column = PersonalShelf.N_COLUMN;
        int freeDashboardSpace1 = 0;
        int freeDashboardSpace2 = 0;
        int freeColumnSpace = 0;
        boolean spaceDashboard = false;
        boolean spaceShelf = false;

        //Checking the dashboard row by row
        for (int i = 0; i < Dashboard.getSide() && !spaceDashboard; i ++) {
            int counter = 0;
            for(int j = 0; j < Dashboard.getSide() && !spaceDashboard; j ++) {
                if (counter == 0) {
                    if (game.getTable().getSingleSlot(i,j).isCatchable())
                        counter ++;
                } else if (game.getTable().getSingleSlot(i,j).isCatchable() && game.getTable().getSingleSlot(i,j-1).isCatchable())
                    counter ++;

                if (!game.getTable().getSingleSlot(i,j).isCatchable())
                    counter = 0;

                if (counter >= freeDashboardSpace1)
                    freeDashboardSpace1 = counter;
                if (freeDashboardSpace1 >= number)
                    spaceDashboard = true;
            }
        }

        //Checking the dashboard column by column
        for (int j = 0; j < Dashboard.getSide() && !spaceDashboard; j ++) {
            int counter = 0;
            for(int i = 0; i < Dashboard.getSide() && !spaceDashboard; i ++) {
                if (counter == 0) {
                    if (game.getTable().getSingleSlot(i,j).isCatchable())
                        counter ++;
                } else if (game.getTable().getSingleSlot(i,j).isCatchable() && game.getTable().getSingleSlot(i-1,j).isCatchable())
                    counter ++;

                if (!game.getTable().getSingleSlot(i,j).isCatchable())
                    counter = 0;

                if (counter >= freeDashboardSpace1)
                    freeDashboardSpace2 = counter;
                if (freeDashboardSpace2 >= number)
                    spaceDashboard = true;
            }
        }

        //Checking the shelf
        for (int j=0; j < column && !spaceShelf ; j++){
            for(int i=0; i < rows && !spaceShelf ; i++){
                if(game.getPlayer()[game.getPlayerInGame()].getShelf().getSingleSlot(i,j).getColor().Equals(Color.GREY)){
                    freeColumnSpace++;
                }
                if(freeColumnSpace>=number){
                    System.out.println("Colonna libera trovata ");
                    spaceShelf = true;
                }
            }
            freeColumnSpace = 0;
        }
        if (spaceDashboard && spaceShelf)
            return;

        System.err.println("Nessuna posizione libera trovata ");
        game.setLastError(GameError.SPACE_CHOICES_ERROR);
        throw new NotEnoughSpaceChoiceException("Non c'è abbastanza spazio per prendere il numero desiderato ");
    }

    /**
     * Switches the current state of the game.
     */
    public void switchGameState(){
        game.setGameOn(!game.isGameOn());
    }


    /**
     * Retrieves the slots selected by the player in the current turn.
     *
     * @return The slots selected by the player in the current turn.
     */
    public Slot[] getSelectedSlots() {
        return selectedSlots;
    }

}













