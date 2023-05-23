package CONTROLLER;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.*;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.RemoteException;


public class GameController{
    private final Game game;
    private Slot[] selectedSlots;
    private Target[] coordinatesSaver;
    private boolean didSelection = false;

    public GameController(Game game){
        this.game=game;
    }


    public void startGame () throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
            game.setGameOn(true);
            game.startGame();
    }
    public void skipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        System.err.println("ciao");
        System.err.println("\n"+didSelection);
        if(didSelection){
            System.err.println("Sto per fare l'undo!");
            undoSelection();
        }
        game.nextPlayerInGame();
    }

    private void undoSelection() throws NotEnoughSpaceChoiceException, RemoteException {
        game.undoTurn(selectedSlots, coordinatesSaver);
        didSelection = false;
    }

    public String getOnStage(){
        return game.playerOnStage().getNickname();
    }

    //controllo se il nickname è stato già preso
    public boolean checkNick(String name){
        for(int i = 0; i < game.getPlayerInGame(); i++){
            if(game.getPlayer()[i].nickname.equals(name)){
                return false; //genera un eccezione SAMENICK
            }
        }
        return true;
    }

    //method which controls if a tile can be caught
    public boolean checkCoordinates(int x, int y){
        return game.getTable().getSingleSlot(x,y).isCatchable();
    }

    //method which controls if two tiles are nearby
    public boolean checkAdjacent(int x, int y, int x1, int y1){
        if((x == x1 && (y == y1 + 1 || y == y1-1)) || (y == y1 && (x == x1+1 || x == x1-1))){
            return true;
        }
        return false;
    }

    public void checkSelect(SlotChoice[] selectedCards) throws NotCatchableException, NotAdjacentSlotsException, RemoteException {
        selectedSlots = new Slot[selectedCards.length];
        coordinatesSaver = new Target[selectedCards.length];
        switch (selectedCards.length){
            case 1 -> {
                int x = selectedCards[0].getX();
                int y = selectedCards[0].getY();
                if(checkCoordinates(x, y)){
                    didSelection = true;
                    //System.out.println("\n"+didSelection);
                    coordinatesSaver[0] = new Target(game.getTable().getSingleSlot(x,y).getColor(), x, y);
                    selectedSlots[0]=game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y);
                } else {
                    game.setLastError(GameError.SELECT_ERROR_NOT_CATCHABLE);
                    throw new NotCatchableException("La tessera selezionata non può essere presa!");
                }
            }
            case 2 -> {
                int x = selectedCards[0].getX();
                int y = selectedCards[0].getY();
                int x1 = selectedCards[1].getX();
                int y1 = selectedCards[1].getY();
                if(checkCoordinates(x,y) && checkCoordinates(x1,y1)){

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
            case 3 -> {
                int x = selectedCards[0].getX();
                int y = selectedCards[0].getY();
                int x1 = selectedCards[1].getX();
                int y1 =  selectedCards[1].getY();
                int x2 = selectedCards[2].getX();
                int y2 =  selectedCards[2].getY();
                if(checkCoordinates(x,y) && checkCoordinates(x1, y1) && checkCoordinates(x2, y2)){
                    /*this if is used to control the fact that the tiles are adjacent and that they form a straight line;
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

    public void checkOrder(OrderChoice o){
        if(o.getT() == 1 && o.getP() == 1 && o.getS() == 1){
            game.getPlayer()[game.getPlayerInGame()].orderCards(selectedSlots);
        } else {
            game.getPlayer()[game.getPlayerInGame()].orderCards(selectedSlots, o.getP(), o.getS(), o.getT());
        }
    }
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
            System.err.println("Mando eccezione di non abbstanza spazio ");
            throw new NotEnoughSpaceChoiceException("La colonna scelta non può contenere così tante tessere!");
        }
    }

    public void completeShelf() {
       game.getPlayer()[game.getPlayerInGame()].sumPoints(1);
    }

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

    //Si occupa dell'effettivo cambio turno nel gioco del modello scegliendo il nuovo gicatore.
    public void turnUpdate() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        System.out.println("Aggioramento del turno in corso.. \n");
        game.updateTurn();
    }

    //Problemi in questa funzione quando crasha un player con 3 giocatori
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


        //Entra comunque in questo IF, non va bene
        System.err.println("Nessuna posizione libera trovata ");
        game.setLastError(GameError.SPACE_CHOICES_ERROR);
        throw new NotEnoughSpaceChoiceException("Non c'è abbastanza spazio per prendere il numero desiderato ");
    }

    public void switchGameState(){
        game.setGameOn(!game.isGameOn());
    }


    public Slot[] getSelectedSlots() {
        return selectedSlots;
    }
}













