package MODEL;
import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers (Object arg){
        for (Observer observer: observers){
            observer.update(this, arg);
        }
    }
}
