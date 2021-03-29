import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Board board;
	private JButton start;
	private JButton clear;
	private JComboBox<Integer> drawType;
	private JSlider pred;
	private JFrame frame;
	private int iterNum = 0;
	private final int maxDelay = 500;
	private final int initDelay = 100;
	private boolean running = false;
	public Report rep;


	public GUI(JFrame jf) {
		frame = jf;
		timer = new Timer(initDelay, this);
		timer.stop();
	}

	public Board getBoard() {
		return board;
	}

	public int getIterNum() {
		return iterNum;
	}

	public void initialize(Container container) throws IOException {
		container.setLayout(new BorderLayout());
		container.setSize(new Dimension(1024, 768));

		JPanel buttonPanel = new JPanel();

		start = new JButton("Start");
		start.setActionCommand("Start");
		start.addActionListener(this);

		clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(this);

		pred = new JSlider();
		pred.setMinimum(0);
		pred.setMaximum(maxDelay);
		pred.addChangeListener(this);
		pred.setValue(maxDelay - timer.getDelay());

		buttonPanel.add(start);
		buttonPanel.add(clear);
		buttonPanel.add(pred);

		board = new Board(1024, 768 - buttonPanel.getHeight());
		rep = new Report(board);
		container.add(board, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			iterNum++;
			frame.setTitle("Sound simulation (" + Integer.toString(iterNum) + " iteration)");
			board.iteration();
			try {
				for (int i = 50; i <= 1000; i += 50) {
					if (i == iterNum) {
//						System.out.println("iteracja: " + iterNum);
						rep.generateReport(i);
					}
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}



		} else {
			String command = e.getActionCommand();
			if (command.equals("Start")) {
				if (!running) {
					timer.start();
					start.setText("Pause");
				} else {
					timer.stop();
					start.setText("Start");
				}
				running = !running;
				clear.setEnabled(true);

			} else if (command.equals("clear")) {
				iterNum = 0;
				timer.stop();
				start.setEnabled(true);
				board.clear();
				frame.setTitle("Cellular Automata Toolbox");
			}
			else if (command.equals("drawType")){
				int newType = (Integer)drawType.getSelectedItem();
				board.editType = newType;
			}

		}
	}

	public void stateChanged(ChangeEvent e) {
		timer.setDelay(maxDelay - pred.getValue());
	}
}