package Model;

public class Planet {

    private final int id;
    private final String name;
    private final double gravity;          // m/s^2
    private final String groundImagePath;  // path to the background image

    public Planet(int id, String name, double gravity, String groundImagePath) {
        this.id = id;
        this.name = name;
        this.gravity = gravity;
        this.groundImagePath = groundImagePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGravity() {
        return gravity;
    }

    public String getGroundImagePath() {
        return groundImagePath;
    }
}
