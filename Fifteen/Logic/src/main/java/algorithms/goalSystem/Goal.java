package algorithms.goalSystem;

public class Goal {
    private final int[] coordinates;
    private final int value;
    private final boolean isValueDesired;

    Goal(int x, int y, int value, boolean isValueDesired) {
        this.isValueDesired = isValueDesired;
        coordinates = new int[]{x, y};
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isValueDesired() {
        return isValueDesired;
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
