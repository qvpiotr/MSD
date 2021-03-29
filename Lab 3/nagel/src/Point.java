import java.util.Random;

import static java.lang.Math.abs;

public class Point {

    public boolean isCar;
    public boolean newCar;
    public int velocity;

    public Board map;
    public int x;
    public int y;

    public boolean overtaking = false;

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
        this.overtaking = false;
    }


    public Point closestNeighbor() {
        for (int i = 1; i < this.map.getMaxVelocity(); i++) {
            if(this.map.getPoints()[(x+i+this.map.getPoints().length)%this.map.getPoints().length][y].isCar) {
//                System.out.println((x+i+this.map.getPoints().length)%this.map.getPoints().length);
                return this.map.getPoints()[(x+i+this.map.getPoints().length)%this.map.getPoints().length][y];
            }
        }
        return null;
    }

    public Point leftClosestNeighborBehind() {
        for (int i = 1; i < this.map.getMaxVelocity(); i++) {
            if(this.map.getPoints()[(x-i+this.map.getPoints().length)%this.map.getPoints().length][y-1].isCar) {
//                System.out.println((x-i+this.map.getPoints().length)%this.map.getPoints().length);
                return this.map.getPoints()[(x-i+this.map.getPoints().length)%this.map.getPoints().length][y-1];
            }
        }
        return null;
    }

    public Point leftclosestNeighborInFrontOf() {
        for (int i = 1; i < this.map.getMaxVelocity(); i++) {
            if(this.map.getPoints()[(x+i+this.map.getPoints().length)%this.map.getPoints().length][y-1].isCar) {
//                System.out.println((x+i+this.map.getPoints().length)%this.map.getPoints().length);
                return this.map.getPoints()[(x+i+this.map.getPoints().length)%this.map.getPoints().length][y-1];
            }
        }
        return null;
    }

    public Point rightClosestNeighborInFrontOf() {
        for (int i = 1; i < this.map.getMaxVelocity(); i++) {
            if(this.map.getPoints()[(x+i+this.map.getPoints().length)%this.map.getPoints().length][y+1].isCar) {
//                System.out.println((x+i+this.map.getPoints().length)%this.map.getPoints().length);
                return this.map.getPoints()[(x+i+this.map.getPoints().length)%this.map.getPoints().length][y+1];
            }
        }
        return null;
    }

    public Point rightClosestNeighborBehind() {
        for (int i = 1; i < this.map.getMaxVelocity(); i++) {
            if(this.map.getPoints()[(x-i+this.map.getPoints().length)%this.map.getPoints().length][y+1].isCar) {
//                System.out.println((x-i+this.map.getPoints().length)%this.map.getPoints().length);
                return this.map.getPoints()[(x-i+this.map.getPoints().length)%this.map.getPoints().length][y+1];
            }
        }
        return null;
    }


    public boolean canOvertake() {

        if (this.closestNeighbor() != null && this.velocity > 0) {
            if ((this.closestNeighbor().x - this.x + this.map.getPoints().length)%this.map.getPoints().length -1< this.velocity) {
                if (this.leftclosestNeighborInFrontOf() == null && this.leftClosestNeighborBehind() == null) {
                    return true;
                }
                else if (this.leftclosestNeighborInFrontOf() != null && this.leftClosestNeighborBehind() != null) {
                    if ((this.leftClosestNeighborBehind().x - this.x + this.map.getPoints().length)%this.map.getPoints().length -1 >
                            this.leftClosestNeighborBehind().velocity && (this.leftclosestNeighborInFrontOf().x - this.x + this.map.getPoints().length)%this.map.getPoints().length -1 >
                            this.velocity) {
                        return true;
                    }
                }
                else if (this.leftclosestNeighborInFrontOf() != null && this.leftClosestNeighborBehind() == null) {
                    if ((this.leftclosestNeighborInFrontOf().x - this.x + this.map.getPoints().length)%this.map.getPoints().length -1 >
                            this.velocity) {
                        return true;
                    }
                }

                else if (this.leftclosestNeighborInFrontOf() == null && this.leftClosestNeighborBehind() != null) {
                    if ((this.leftClosestNeighborBehind().x - this.x + this.map.getPoints().length)%this.map.getPoints().length -1 >
                            this.leftClosestNeighborBehind().velocity) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public boolean canReturn() {
        if (this.overtaking) {
            if (this.rightClosestNeighborInFrontOf() == null && this.rightClosestNeighborBehind() == null) {
                return true;
            }
            else if (this.rightClosestNeighborInFrontOf() != null && this.rightClosestNeighborBehind() != null) {
                if ((this.rightClosestNeighborBehind().x - this.x + this.map.getPoints().length) % this.map.getPoints().length - 1 >
                        this.rightClosestNeighborBehind().velocity && (this.rightClosestNeighborInFrontOf().x - this.x + this.map.getPoints().length) % this.map.getPoints().length - 1 >
                        this.velocity) {
                    return true;
                }
            }
            else if (this.rightClosestNeighborInFrontOf() != null && this.rightClosestNeighborBehind() == null) {
                if ((this.rightClosestNeighborInFrontOf().x - this.x + this.map.getPoints().length) % this.map.getPoints().length - 1 >
                        this.velocity) {
                    return true;
                }
            }
            else if (this.rightClosestNeighborInFrontOf() == null && this.rightClosestNeighborBehind() != null) {
                if ((this.rightClosestNeighborBehind().x - this.x + this.map.getPoints().length) % this.map.getPoints().length - 1 >
                        this.rightClosestNeighborBehind().velocity) {
                    return true;
                }
            }
        }
    return false;
    }


    public void carMotion() {
        this.x = (this.x + this.velocity+this.map.getPoints().length)%this.map.getPoints().length;
    }


    public void updateVelocity() {
        this.acceleration();
        if (this.y < this.map.getPoints().length && this.canReturn()) {
            this.y = y+1;
            System.out.println("bede wracał");
            this.overtaking = false;
        }
        if (this.y >= 1 && this.canOvertake()) {
            this.y = y-1;
            System.out.println("bede wyprzedzał");
            this.overtaking = true;
        }
        else {
            this.slowingDown();
        }
        this.randomization();
    }

    public void acceleration() {
        if (this.velocity < this.map.getMaxVelocity()) this.velocity+=1;
    }

    public void slowingDown() {
        if (this.closestNeighbor() != null && this.velocity > 0) {
            if ((this.closestNeighbor().x - this.x + this.map.getPoints().length)%this.map.getPoints().length -1< this.velocity) {
                this.velocity = this.closestNeighbor().x - this.x - 1;
            }
        }
    }

    public void randomization() {
        boolean val = new Random().nextInt(2) == 0;

        if (val && this.velocity >= 1) this.velocity -= 1;
    }

}