package util;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimulationError {
	
	JDialog dialog;
	JPanel panel;
	JLabel label;
	
	public SimulationError(String errorMsg) {
		this.dialog = new JDialog(new JFrame(), "Simulation - Erreur", true);
		this.label = new JLabel(errorMsg);
		this.panel = new JPanel();
		
		this.dialog.add(panel);
		this.panel.add(label);
		
		this.label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		this.label.setFont(new Font("Open Sans", 0, 14));
		
		this.dialog.setSize(480, 70);
		this.dialog.setLocationRelativeTo(null);
	}
	
	public void show() {
		this.dialog.setVisible(true);
	}

}
