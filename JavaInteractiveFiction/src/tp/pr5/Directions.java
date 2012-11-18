package tp.pr5;

/**
 * @author Grupo03 Sergio Revilla Jose Carlos Gonzalez
 * 
 */

public enum Directions {

    NORTH, SOUTH, EAST, WEST, UNKNOWN;

    public Directions opposite() {

	Directions dir = Directions.UNKNOWN;

	if (this == Directions.NORTH)
	    dir = Directions.SOUTH;
	else if (this == Directions.SOUTH)
	    dir = Directions.NORTH;
	else if (this == Directions.WEST)
	    dir = Directions.EAST;
	else if (this == Directions.EAST)
	    dir = Directions.WEST;

	return dir;
    }

    public static Directions numberDirection(int number) {
	Directions dir = Directions.UNKNOWN;
	if (number == 0) {
	    dir = Directions.NORTH;
	} else if (number == 1) {
	    dir = Directions.SOUTH;
	} else if (number == 2) {
	    dir = Directions.EAST;
	} else if (number == 3) {
	    dir = Directions.WEST;
	}

	return dir;

    }

    // TODO compare literals first
    public static Directions stringToDirection(String direction) {
	Directions dir = Directions.UNKNOWN;
	if ("NORTH".equals(direction)) {
	    dir = Directions.NORTH;
	} else if ("SOUTH".equals(direction)) {
	    dir = Directions.SOUTH;
	} else if ("WEST".equals(direction)) {
	    dir = Directions.WEST;
	} else if ("EAST".equals(direction)) {
	    dir = Directions.EAST;
	}
	return dir;
    }
}
