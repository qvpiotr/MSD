import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Point {

    public ArrayList<Point> neighbors;
    public static Integer []types ={0,1,2,3};
    public int type;
    public int staticField;
    public boolean isPedestrian;
    public boolean blocked;

    public Point() {
        type=0;
        staticField = 100000;
        neighbors= new ArrayList<Point>();
        blocked = false;
    }

    public void clear() {
        staticField = 100000;

    }

    public boolean calcStaticField() {
        if (neighbors.isEmpty() || this.type == 1) return false;

        int smallest = neighbors.get(0).staticField;
        for (Point point: neighbors){
            if(point.staticField < smallest){
                smallest = point.staticField;
            }
        }

        if(smallest+1 >= this.staticField) return false;

        this.staticField = smallest+1;
        return true;

    }

    public void move(){
        if(isPedestrian && !blocked && !neighbors.isEmpty()){
            int smallest = neighbors.get(0).staticField;
            int id = 0;
            if(neighbors.size()>1){
                for(int i=0; i< neighbors.size();i++){
                    int sField = neighbors.get(i).staticField;
                    if(smallest > sField){
                        id = i;
                        smallest = sField;
                    }
                }
            }

            this.isPedestrian = false;
            if (neighbors.get(id).type !=2) {
                neighbors.get(id).isPedestrian = true;
            }
            neighbors.get(id).blocked = true;
        }

    }

    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }
}