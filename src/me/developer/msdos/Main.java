package me.developer.msdos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {
	static Runtime rt;
	static Process pr;
	
	public static void main(String args[]) {
		try {
			rt = Runtime.getRuntime();
			pr = rt.exec("cmd -c");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		JFrame frame = new JFrame();
		frame.setSize(500, 700);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frame.add(panel);
		final JTextField txt = new JTextField(40);
		panel.add(txt);
		JButton button = new JButton("Enviar");
		
		panel.add(button);
		
		final JTextArea area = new JTextArea(35, 40);
		
		area.setEditable(false);
		panel.add(area);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String comando = txt.getText();
				comando = "cmd /c " + comando;
				try {
					pr = rt.exec(comando);
					BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
					String line = null;
					while ((line = input.readLine()) != null) {
						System.out.println(line);
						area.setText(area.getText() + line);
						area.setText(area.getText() + "\n");
					}
				} catch (Exception e) {
					System.out.println(e.toString());
					e.printStackTrace();
				}
			}
		});
		frame.setVisible(true);
	}
}
