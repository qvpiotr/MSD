import java.util.Random;

import static java.lang.Math.abs;

public class Point {

    public boolean isCar;
    public boolean newCar;
    public int velocity;

    public Board map;
    public int x;
    public int y;


    // TODO


    public Point(int x,int y, Board map, int velocity) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.isCar = false;
        this.newCar = true;
        this.velocity = velocity;
    }

    public void clicked() {
        this.isCar = true;
    }

    public void clear() {
        this.isCar = false;
    }

    public Point closestNeighbor() {
        for (int i = 1; i < this.map.getMaxVelocity(); i++) {
            if (this.map.getPoints()[(x+i)%this.map.getPoints().length][y].isCar &&
                    !this.map.getPoints()[(x+i)%this.map.getPoints().length][y].newCar) {
                return this.map.getPoints()[(x+i)%this.map.getPoints().length][y];
            }
        }
        return null;
    }

    public void carMotion() {
        this.x = (this.x + this.velocity)%this.map.getPoints().length;
    }

    public void updateVelocity() {
        this.acceleration();
        this.slowingDown();
        this.randomization();
    }

    public void acceleration() {
        if (this.velocity < this.map.getMaxVelocity()) this.velocity+=1;
    }

    public void slowingDown() {
        if (this.closestNeighbor() != null && this.velocity > 0) {
            if (this.closestNeighbor().x - this.x  < this.velocity) {
                this.velocity = this.closestNeighbor().x - this.x - 1;
            }
        }
    }

    public void randomization() {
        boolean val = new Random().nextInt(2) == 0;

        if (val && this.velocity >= 1) this.velocity -= 1;
    }

}