
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.MainframeClient;

public class LogInFrame {

	private JFrame frame;
	private JTextField textFieldLogUser;
	private JTextField textFieldLogPass;
	private Login lg;

	/*
	 * Klasa za pokretanje Log in forme
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame window = new LogInFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogInFrame() throws InstantiationException, IllegalAccessException {
		lg = new Login();
		initialize();
	}

	/*
	 * postavljanje izgleda sucelja te provjera postoji li klijent u bazi te
	 * kreiranje novog
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 484, 307);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUserName = new JLabel("User name:");
		lblUserName.setBounds(114, 61, 91, 26);
		frame.getContentPane().add(lblUserName);

		JLabel lblLogiranje = new JLabel("REGISTRACIJA/LOGIRANJE");
		lblLogiranje.setBounds(153, 13, 198, 45);
		frame.getContentPane().add(lblLogiranje);

		textFieldLogUser = new JTextField();
		textFieldLogUser.setBounds(201, 63, 116, 22);
		frame.getContentPane().add(textFieldLogUser);
		textFieldLogUser.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(114, 101, 91, 26);
		frame.getContentPane().add(lblPassword);

		textFieldLogPass = new JTextField();
		textFieldLogPass.setBounds(201, 103, 116, 22);
		frame.getContentPane().add(textFieldLogPass);
		textFieldLogPass.setColumns(10);

		JButton btnRegistracija = new JButton("Registracija");
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lg.createNewUser( textFieldLogUser.getText(), textFieldLogPass.getText());

				//lg.closeConnection();

			}
		});
		btnRegistracija.setBounds(64, 159, 141, 45);
		frame.getContentPane().add(btnRegistracija);

		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (lg.userExists(textFieldLogUser.getText(), textFieldLogPass.getText())) {
					System.out.println("radilog");
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainframeClient window = new MainframeClient();
								lg.closeConnection();

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				}
				//lg.closeConnection();

			}
		});
		btnLogIn.setBounds(267, 159, 123, 45);
		frame.getContentPane().add(btnLogIn);
	}
}
