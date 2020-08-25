package javaLauncher;

class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower = new WeatherTower();
    String currentWeather;
    int latitude = super.coordinates.getLatitude();
    int longitude = super.coordinates.getLongitude();
    int height = super.coordinates.getHeight();

    JetPlane(String name, Coordinates coordinates) {
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
                this.latitude = this.latitude + 10;
                this.height = this.height + 2;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: JetPlane#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude, this.latitude, this.height);
                ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": Passing through these clear skies like I am iron man.");
                break;
            case "FOG":
                this.latitude = this.latitude + 1;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: JetPlane#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude, this.latitude, this.height);
                ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": My wings are getting slippery.");
                break;
            case "RAIN":
                this.latitude = this.latitude + 5;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: JetPlane#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude, this.latitude, this.height);
                ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": These rain drops want to kill my engine.");
                break;
            case "SNOW":
                this.latitude = this.latitude - 7;
                if (this.height <= 0) {
                    ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": Landing.");
                    ReadingWriting.fileContents.add("Tower says: JetPlane#" + super.name + "(" + super.id + ")" + " unregistered from weather tower.");
                    weatherTower.unregister(this);
                    return;
                }
                super.coordinates = new Coordinates(this.longitude, this.latitude, this.height);
                ReadingWriting.fileContents.add("JetPlane#" + super.name + "(" + super.id + ")" + ": It is getting cold, it is santa season.");
                break;
            default:
                ReadingWriting.fileContents.add("The is an error on the weather");
                System.exit(0);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        ReadingWriting.fileContents.add("Tower says: JetPlane#" + super.name + "(" + super.id + ")" + " was registered to weather tower.");
        this.weatherTower.register(this);
    }
}
