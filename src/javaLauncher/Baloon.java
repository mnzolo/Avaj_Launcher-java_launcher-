package javaLauncher;

class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    String currentWeather;
    int latitude = super.coordinates.getLatitude();
    int longitude = super.coordinates.getLongitude();
    int height = super.coordinates.getHeight();

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        currentWeather = weatherTower.getWeather(super.coordinates);
        if(this.height > 100){
            this.height = 100;
        }
        if(this.height < 0){
            this.height = 0;
        }
        switch (currentWeather) {
            case "SUN":
                this.height = this.height + 4;
                this.longitude = this.longitude + 2;
                if (this.height <= 0){
                    ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Baloon#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": It is time to fly to the beach.");
                break;
            case "FOG":
                this.height = this.height - 3;
                if (this.height <= 0){
                    ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Baloon#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": I am struggling to see up ahead.");
                break;
            case "RAIN":
                this.height = this.height - 5;
                if (this.height <= 0){
                    ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Baloon#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": These rain drops are getting in our way.");
                break;
            case "SNOW":
                this.height = this.height - 15;
                if (this.height <= 0){
                    ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Baloon#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Baloon#" + super.name + "(" + super.id + ")" + ": The air is smelling like christmas.");
                break;
            default:
               ReadingWriting.fileContents.add("The is an error on the weather provided!!!");
               System.exit(0);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        ReadingWriting.fileContents.add("Tower says: Baloon#" + super.name + "(" + super.id + ")" + " was registered to weather tower.");
        weatherTower.register(this);
    }
}