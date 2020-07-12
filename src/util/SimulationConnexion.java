package util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimulationConnexion {
	
	JDialog dialog;
	JButton button;
	JLabel labelIp, labelPort;
	JPanel panel, panelIp, panelPort, panelButton;
	JTextField inputIp, inputPort;
	Container c;
	
	String ip;
	int port;
	
	public SimulationConnexion(String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.initializeConnexionInterface();
	}
	
	public SimulationConnexion() {
		this.ip = "";
		this.port = 0;
		this.initializeConnexionInterface();
	}
	
	private void initializeConnexionInterface() {
		this.dialog = new JDialog(new JFrame(), "Simulation - Connexion", true);
		this.panel = new JPanel();
		this.panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.panel.setLayout(new GridLayout(3, 1));
		
		this.c = this.dialog.getContentPane();
		this.c.setLayout(new BorderLayout());
		this.c.add(this.panel);

		this.labelIp = new JLabel("Adresse IP");
		this.labelPort = new JLabel("Port distant");
		this.labelIp.setHorizontalAlignment(JLabel.CENTER);
		this.labelPort.setHorizontalAlignment(JLabel.CENTER);
		
		this.inputIp = new JTextField(this.ip);
		this.inputPort = new JTextField(Integer.toString(this.port));
		
		this.button = new JButton("Connexion");
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkAddress();
			}
		});
		
		this.panelIp = new JPanel(new GridLayout(1, 2));
		this.panel.add(this.panelIp, BorderLayout.CENTER);
		this.panelPort = new JPanel(new GridLayout(1, 2));
		this.panel.add(this.panelPort, BorderLayout.CENTER);
		this.panelButton = new JPanel();
		this.panel.add(this.panelButton, BorderLayout.SOUTH);

		this.panelIp.add(this.labelIp);
		this.panelIp.add(this.inputIp);

		this.panelPort.add(this.labelPort);
		this.panelPort.add(this.inputPort);
		
		this.panelButton.add(this.button);
		
		this.dialog.setSize(320, 170);
		this.dialog.setLocationRelativeTo(null);
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	
	public void show() {
		this.dialog.setVisible(true);
	}
	
	public boolean isAddressValid() {
		return this.isValidIp(this.ip) && this.isValidPort(this.port);
	}
	
	private void checkAddress() {
		ip = inputIp.getText();
		try {
			port = Integer.parseInt(inputPort.getText());
		} catch (NumberFormatException e) {
			port = 0;
		}
		if(isValidIp(ip) && isValidPort(port))
			this.close();
		else {
			SimulationError error = new SimulationError("Adresse IP ou port invalide(s) !");
			error.show();
		}
	}
	
	public void close() {
		this.dialog.dispose();
	}
    
    private boolean isValidIp (String ip) {
        try {
        	
            if (ip == null || ip.isEmpty()) {
                return false;
            }

            String[] parts = ip.split("\\.");
            
            if (parts.length != 4) {
                return false;
            }

            for (String s : parts) {
                int i = Integer.parseInt(s);
                if ((i < 0) || (i > 255)) {
                    return false;
                }
            }
            
            if (ip.endsWith(".")) {
                return false;
            }

            return true;
            
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private boolean isValidPort(int port) {
    	return port >= 0 && port <= 65535;
    }

}
