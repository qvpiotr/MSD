public class Stats {

    private Board map;

    public Board getMap() {
        return map;
    }

    public Stats(Board map) {
        this.map = map;
    }

    public int carsFlow(){
        int counter = 0;
        for (int y = 0; y < this.map.getPoints()[99].length; y++) {
            if (this.map.getPoints()[99][y].isCar) counter += 1;
        }
        return counter;
    }
}
