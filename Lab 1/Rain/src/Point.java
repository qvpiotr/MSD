import java.util.ArrayList;
import java.util.Random;

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

		if (this.getState() > 0) {
			nextState = currentState -1;
		}
		if (this.getState() == 0 && aliveNeighbors() > 0) {
			nextState = 6;
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
			if (neighbor.getState() > 0) {
				counter ++;
			}
		}
		return counter;
	}

	public void drop(){
		boolean val = new Random().nextInt(20) == 0;

		if (val) setState(6);
	}
}
