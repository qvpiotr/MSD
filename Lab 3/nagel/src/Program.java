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
	}

	public static void main(String[] args) throws IOException {
		new Program();
	}
}
