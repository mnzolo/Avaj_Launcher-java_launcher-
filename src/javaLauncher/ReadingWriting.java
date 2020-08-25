package javaLauncher;

import java.io.*;
import java.util.ArrayList;

class ReadingWriting {
    ArrayList<Flyable> aircrafts = new ArrayList<>();
    static ArrayList<String> fileContents = new ArrayList<>();
    String absolutePath;
    String Simulation = "simulation.txt";
    int numberOfSimulations = 0;

    ReadingWriting(String scenario) {
        this.absolutePath = scenario;
    }

    ArrayList<Flyable> AircraftArray() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.absolutePath))) {
            String line = bufferedReader.readLine();
            int lineNumber = 1;
            int lineCheck;
            numberOfSimulations = checkNumber(line, lineNumber);
            if(numberOfSimulations == 0){
                System.exit(0);
            }
            while (line != null) {
                if (lineNumber >= 2) {
                    lineCheck = checkLine(line.split(" "), lineNumber);
                    if (lineCheck == 1) {
                        String type = line.split(" ")[0];
                        String name = line.split(" ")[1];
                        int longitude = checkNumber(line.split(" ")[2], lineNumber);
                        int lat = checkNumber(line.split(" ")[3], lineNumber);
                        int height = checkNumber(line.split(" ")[4], lineNumber);
                        aircrafts.add(AircraftFactory.newAircraft(type,name,longitude,lat,height));
                    } else if (lineCheck == 0) {
                        numberOfSimulations = lineCheck;
                        System.exit(0);
                    }
                }
                line = bufferedReader.readLine();
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file specified cannot be found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
           System.exit(0);
        }
        return aircrafts;
    }

    int checkNumber(String line, int lineNumber) {
        try {
            int number = Integer.valueOf(line);
	    if(number < 0){
		    number *= -1;
	    }
            return number;
        } catch (Exception e) {
            System.out.println("You have entered an invalid number in line " + lineNumber + ".");
            return 0;
        }
    }

    int checkLine(String[] line, int lineNumber) {
        int counter = 0;
        ArrayList<String> type = new ArrayList<>();
        type.add("Baloon");
        type.add("Helicopter");
        type.add("JetPlane");
        if (line.length < 5) {
            System.out.println("The is a missing input in line " + lineNumber + ".");
            return 0;
        }
        if (line.length > 5) {
            System.out.println("The input's in line " + lineNumber + " are not placed accordingly.");
            return 0;
        }
        if (!type.contains(line[0])) {
            System.out.println("You entered an invalid type in line " + lineNumber + ".");
            return 0;
        }
        while (counter < line.length) {
            if (counter == 2 || counter == 3 || counter == 4) {
                if(checkNumber(line[counter], lineNumber) == 0){
                    return 0;
                }
            }
            counter++;
        }
        return 1;
    }
    void writeSimulation(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.Simulation) )){
            for (String content:this.fileContents) {
                bufferedWriter.write(content+"\n");
            }
        } catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }
    }
}
