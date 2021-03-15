public class Point {

	public Point nNeighbor;
	public Point wNeighbor;
	public Point eNeighbor;
	public Point sNeighbor;
	public float nVel;
	public float eVel;
	public float wVel;
	public float sVel;
	public float pressure;

	public static Integer []types ={0,1,2};
	public int type;

	int sinInput;

	public Point() {
		clear();
		this.type = 0;
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		// TODO: clear velocity and pressure
		seteVel(0);
		setnVel(0);
		setsVel(0);
		setwVel(0);
		setPressure(0);
	}

	public void updateVelocity() {
		// TODO: velocity update
		float newNVel = this.nVel - this.nNeighbor.getPressure() + this.pressure;
		float newSVel = this.sVel - this.sNeighbor.getPressure() + this.pressure;
		float newEVel = this.eVel - this.eNeighbor.getPressure() + this.pressure;
		float newWVel = this.wVel - this.wNeighbor.getPressure() + this.pressure;

		if (this.type == 0) {
			setwVel(newWVel);
			setsVel(newSVel);
			setnVel(newNVel);
			seteVel(newEVel);
		}

	}

	public void updatePresure() {
		// TODO: pressure update
		float sumVel = this.eVel + this.wVel + this.sVel + this.nVel;
		float newPresure = this.pressure - sumVel/2;

		if (this.type == 0) {
			setPressure(newPresure);
		}

		if (this.type == 2) {
			double radians = Math.toRadians(sinInput);
			pressure = (float) (Math.sin(radians));
			sinInput = sinInput + 5;
		}
	}

	public float getPressure() {
		return pressure;
	}

	public void setnVel(float nVel) {
		this.nVel = nVel;
	}

	public void seteVel(float eVel) {
		this.eVel = eVel;
	}

	public void setwVel(float wVel) {
		this.wVel = wVel;
	}

	public void setsVel(float sVel) {
		this.sVel = sVel;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
}