import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private GUI gof;
	private Report rep;

	public Program() throws IOException {
		setTitle("Sound simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gof = new GUI(this);
		gof.initialize(this.getContentPane());
		this.setSize(1024, 768);
		this.setVisible(true);
//		System.out.println("iteracja: " + gof.getIterNum());
//		rep = new Report(this.gof.getBoard());
//		if (gof.getIterNum()==50 || gof.getIterNum()==100 || gof.getIterNum()==150 || gof.getIterNum()==200) {
//			rep.generateReport(gof.getIterNum());
////			System.out.println("iteracja: " + gof.getIterNum());
//		}

	}

	public static void main(String[] args) throws IOException {
		new Program();
	}
}
