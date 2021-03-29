import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Report {
    private Board map;
    private FileWriter data;

    public Report(Board map) throws IOException {
        this.map = map;
        this.data = new FileWriter("car_flow.txt");
    }

    public void generateReport(int n) throws IOException {
        data = new FileWriter("car_flow.txt",true);
        data.write("Liczba iteracji: " + n + ' ' +"Flow: " + this.map.stat.carsFlow() + "\n");
        data.close();
    }

}