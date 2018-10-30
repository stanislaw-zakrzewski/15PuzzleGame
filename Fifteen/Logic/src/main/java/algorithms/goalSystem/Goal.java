package algorithms.goalSystem;

public class Goal {
    private final int[] coordinates;
    private final int value;

    Goal(int x, int y, int value) {

        coordinates = new int[]{x,y};
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    int[] getCoordinates() {
        return coordinates;
    }

    public int getX() {
        return coordinates[0];
    }

    public int getY() {
        return coordinates[1];
    }
}
