package javaLauncher;

abstract class Aircraft {
    protected long id = nextId();
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.coordinates = coordinates;
        this.name = name;
    }

    private long nextId(){
        return idCounter++;
    }
}
