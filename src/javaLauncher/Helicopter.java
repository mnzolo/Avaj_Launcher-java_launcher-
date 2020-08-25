package javaLauncher;

class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    String currentWeather;
    int latitude = super.coordinates.getLatitude();
    int longitude = super.coordinates.getLongitude();
    int height = super.coordinates.getHeight();

    Helicopter(String name, Coordinates coordinates) {
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
                this.longitude = this.longitude + 10;
                this.height = this.height + 2;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Helicopter#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": My blades are free as any other metal.");
                break;
            case "FOG":
                this.longitude = this.longitude + 1;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Helicopter#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": Passing threw this steam is surely not refreshing.");
                break;
            case "RAIN":
                this.longitude = this.longitude + 5;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Helicopter#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": This rain is about to get us an early landind.");
                break;
            case "SNOW":
                this.height = this.height - 12;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: Helicopter#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude,this.latitude,this.height);
                ReadingWriting.fileContents.add("Helicopter#" + super.name + "(" + super.id + ")" + ": My blades can't handle the cold any more.");
                break;
            default:
                ReadingWriting.fileContents.add("The is an error on the weather");
                System.exit(0);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        ReadingWriting.fileContents.add("Tower says: Helicopter#"+super.name+"("+super.id+")"+" was registered to weather tower.");
        weatherTower.register(this);
    }
}
