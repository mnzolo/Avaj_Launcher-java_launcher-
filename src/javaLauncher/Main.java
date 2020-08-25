package javaLauncher;

import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList<Flyable> aircrafts;
        WeatherTower weatherTower = new WeatherTower();
        int aircraftscounter = 0;
        int numberofsimulation = 0;
        String file = null;

        if(args.length == 1){
            file = args[0];
        } else {
            System.out.println("The simulation Expects one argument input");
            System.exit(0);
        }
        ReadingWriting read = new ReadingWriting(file);
        aircrafts = read.AircraftArray();
        numberofsimulation = read.numberOfSimulations;
        if(numberofsimulation <= 0){
            System.out.println("You have entered an invalid number of simulations");
            System.exit(0);
        }
        for (Flyable flying : aircrafts) {
            flying.registerTower(weatherTower);
        }

        while(aircraftscounter < numberofsimulation) {
            weatherTower.changeWeather();
            aircraftscounter++;
        }
        read.writeSimulation();
    }
}