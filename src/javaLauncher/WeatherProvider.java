package javaLauncher;

class WeatherProvider {
    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"SNOW","RAIN", "FOG", "SUN"};
    int latitude = 0;
    int longitude = 0;
    int height = 0;
    int decider = 0;

    private WeatherProvider(){}

    public static WeatherProvider getProvider(){
        return weatherProvider;
    }
    public String getCurrentWeather(Coordinates coordinates){
        this.latitude = coordinates.getLatitude();
        this.longitude = coordinates.getLongitude();
        this.height  = coordinates.getHeight();
        this.decider = (this.latitude + this.longitude + this.height)/3;

        if (decider >= 75){
            return weather[2];
        } else if(decider <= 74 && decider >= 50){
            return weather[1];
        } else if(decider <= 49 && decider >= 25){
            return weather[3];
        } else {
            return weather[0];
        }
    }
}