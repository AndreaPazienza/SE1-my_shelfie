package MODEL;

public interface GameEventListener {

    public void addGameEventListener(GameEventListener listener);
    public void gameStateChanged();
    public void turnIsOver(); //Manda le nuove game view a fine turno
    public void notifyTurnIsOver(GameView view);
}
