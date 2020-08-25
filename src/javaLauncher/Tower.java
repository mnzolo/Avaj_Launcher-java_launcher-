package javaLauncher;
import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>(1);
    private int aircraftCounter;

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        aircraftCounter = 0;
        if(observers.size() == 0){
            System.exit(0);
        }
        while(aircraftCounter < observers.size()){
            observers.get(aircraftCounter).updateCondition();
            aircraftCounter++;
        }
    }
}