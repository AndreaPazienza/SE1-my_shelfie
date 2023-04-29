package CONTROLLER;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.*;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.util.ArrayList;


public class GameController{
    private final Game game;

    private ArrayList <Slot> selectedSlots;

    public GameController(Game game){
        this.game=game;
    }


    public void startGame () {
        if(game.isGameOn()) {
            game.startGame();
        }
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

    public void checkSelect(SlotChoice[] selectedCards) throws NotCatchableException, NotAdjacentSlotsException {
        switch (selectedCards.length){
           case 1 -> {
                int x = selectedCards[0].getX();
                int y = selectedCards[0].getY();
                if(checkCoordinates(x, y)){
                    selectedSlots.add(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y));
                } else {
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
                        selectedSlots.add(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y));
                        selectedSlots.add(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x1, y1));
                    } else {
                        throw new NotAdjacentSlotsException("Le tessere selezionate non sono adiacenti!");
                    }
                } else {
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
                        selectedSlots.add(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y));
                        selectedSlots.add(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x1, y1));
                        selectedSlots.add(game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x2, y2));
                    } else {
                        throw new NotAdjacentSlotsException("Le tessere selezionate non sono adiacenti!");
                    }
                } else {
                    throw new NotCatchableException("Una delle tessere selezionate non può essere presa!");
                }
            }

            /*case Type4 -> {
                game.getPlayer()[game.getPlayerInGame()].orderCards((Slot[]) selectedSlots.toArray());
            }
            case Type5 -> {
                game.getPlayer()[game.getPlayerInGame()].orderCards((Slot[]) selectedSlots.toArray(), c.getP(), c.getS(), c.getT());
            }*/
        }
    }

    public void checkOrder(OrderChoice o){
        if(o.getT() == 1 && o.getP() == 1 && o.getS() == 1){
            Slot[] arrayToBeSorted = (Slot[]) selectedSlots.toArray();
            game.getPlayer()[game.getPlayerInGame()].orderCards(arrayToBeSorted);
            selectedSlots.clear();
            for(int i = 0; i < arrayToBeSorted.length; i++){
                selectedSlots.add(arrayToBeSorted[i]);
            }
        } else {
            Slot[] arrayToBeSorted = (Slot[]) selectedSlots.toArray();
            game.getPlayer()[game.getPlayerInGame()].orderCards(arrayToBeSorted, o.getP(), o.getS(), o.getT());
            selectedSlots.clear();
            for(int i = 0; i < arrayToBeSorted.length; i++){
                selectedSlots.add(arrayToBeSorted[i]);
            }
        }
    }
    public void checkInsert(int column) throws NotEnoughSpaceChoiceException {
        int countSpaces = 0;
        for(int i = 0; i < PersonalShelf.N_ROWS; i++){
            if(game.getPlayer()[game.getPlayerInGame()].getShelf().getSingleSlot(i, column).getColor().Equals(Color.GREY)){
                countSpaces++;
            }
        }
        if(countSpaces >= selectedSlots.size()) {
            game.getPlayer()[game.getPlayerInGame()].getShelf().insert((Slot[])selectedSlots.toArray(), column);
        } else {
            throw new NotEnoughSpaceChoiceException("La colonna scelta non può contenere così tante tessere!");
        }
    }

    public void completeRound(){
        //int countdown = game.getNplayers()-game.getPlayerInGame();
        while(game.getPlayerInGame()!= 0){
            //turnManagement();
        }
    }
    public void endGame(){
        Player winner;
        for(int i = 0; i < game.getPlayer().length; i++){
            game.getPlayer()[i].checkScore();
        }
        winner = game.finalScore();
        //System.out.println("Il vincitore è :"+winner.getNickname()+" Complimenti!");
        game.setGameOn(false);
    }


}













