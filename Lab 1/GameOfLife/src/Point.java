import java.util.ArrayList;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public ArrayList<Point> getNeighbors() {
		return neighbors;
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and 
		//number of active neighbors

		if (this.getState() == 0 && aliveNeighbors() == 3) {
			nextState = 1;
		}
		else if (this.getState() == 1) {
			if (aliveNeighbors() != 2 && aliveNeighbors() != 3) {
				nextState = 0;
			}
			else{
				nextState = 1;
			}
		}
	}

	public void calculateNewStateCities() {
		//TODO: insert logic which updates according to currentState and
		//number of active neighbors

		if (this.getState() == 0 && (aliveNeighbors() == 4 || aliveNeighbors() == 5 || aliveNeighbors() == 6
				|| aliveNeighbors() == 7 || aliveNeighbors() == 8)) {
			nextState = 1;
		}
		else if (this.getState() == 1) {
			if (aliveNeighbors() != 2 && aliveNeighbors() != 3 && aliveNeighbors() != 4	&& aliveNeighbors() != 5) {
				nextState = 0;
			}
			else{
				nextState = 1;
			}
		}
	}

	public void calculateNewStateCoral() {
		//TODO: insert logic which updates according to currentState and
		//number of active neighbors

		if (this.getState() == 0 && aliveNeighbors() == 3) {
			nextState = 1;
		}
		else if (this.getState() == 1) {
			if (aliveNeighbors() != 4 && aliveNeighbors() != 5 && aliveNeighbors() != 6	&& aliveNeighbors() != 7
					&& aliveNeighbors() != 8) {
				nextState = 0;
			}
			else{
				nextState = 1;
			}
		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}

	//TODO: write method counting all active neighbors of THIS point
	public int aliveNeighbors(){

		int counter = 0;
		for (Point neighbor : this.neighbors){
			if (neighbor.getState() == 1) {
				counter ++;
			}
		}
		return counter;
	}
}
