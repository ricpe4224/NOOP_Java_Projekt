/*
 * 
 */
package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

// TODO: Auto-generated Javadoc
/**
 * The Class MainframeClient.
 */
/*
 * Klasa za glavno sucelje aplikacije, prikazuje temperaturu pritiskom na gumb
 */
public class MainframeClient {
	
	/** The lbl temperatura. */
	private JLabel lblTemperatura;
	
	/** The lbl vlaga. */
	private JLabel lblVlaga;
	
	/** The lbl vjetar. */
	private JLabel lblVjetar;
	
	/** The lbl tlak. */
	private JLabel lblTlak;
	
	/** The lbl vidljivost. */
	private JLabel lblVidljivost;
	
	/** The graf btn. */
	//////////
	private JButton grafBtn;
	
	/** The btn trenutno. */
	private JButton btnTrenutno;
	
	/** The frame. */
	private JFrame frame;
	
	/** The text field unesi ime grada. */
	private JTextField textFieldUnesiImeGrada;
	
	/** The btn zatrazi. */
	private JButton btnZatrazi;
	
	/** The panel pocetni. */
	private JPanel panelPocetni;
	
	/** The panel 3 dana. */
	// panel 3dana
	private JPanel panel3dana;

	/** The lbl vremenska prognoza. */
	private JLabel lblVremenskaPrognoza;
	
	/** The lbl lat. */
	private JLabel lblLat;
	
	/** The lbl lon. */
	private JLabel lblLon;
	
	/** The lbl max temp. */
	private JLabel lblMaxTemp;
	
	/** The lbl vrsta. */
	private JLabel lblVrsta;
	
	
	
	/**
	 * Instantiates a new mainframe client.
	 */
	public MainframeClient() {
		initialize();
		panelTrenutna();
		pozicija();
		addActList();
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 925, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		lblVremenskaPrognoza = new JLabel("Vremenska Prognoza");
		textFieldUnesiImeGrada = new JTextField();

		btnZatrazi = new JButton("Zatrazi prognozu");
		panelPocetni = new JPanel();
		initPanel3DaysComps();
		lblVidljivost = new JLabel();
		grafBtn = new JButton("Prikazi Graf");
		grafBtn.setVisible(false);
		btnTrenutno = new JButton("Trenutno");
		btnTrenutno.setVisible(false);

	}

	/**
	 * Pozicija.
	 */
	public void pozicija() {
		// top
		lblVremenskaPrognoza.setFont(new Font("Century", Font.BOLD, 50));
		lblVremenskaPrognoza.setHorizontalAlignment(SwingConstants.CENTER);
		lblVremenskaPrognoza.setBounds(12, 13, 883, 86);
		frame.getContentPane().add(lblVremenskaPrognoza);

		textFieldUnesiImeGrada.setBounds(284, 126, 181, 35);
		frame.getContentPane().add(textFieldUnesiImeGrada);
		textFieldUnesiImeGrada.setColumns(10);

		btnZatrazi.setBounds(480, 116, 130, 55);
		frame.getContentPane().add(btnZatrazi);
		// trenutni panel
		panelPocetni.setBounds(12, 209, 897, 317);
		frame.getContentPane().add(panelPocetni);
		panelPocetni.setVisible(false);
		panelPocetni.setLayout(null);
		// panel 3 dana
		panel3dana = new JPanel();
		panel3dana.setBounds(12, 209, 897, 317);
		panel3dana.setVisible(false);
		frame.getContentPane().add(panel3dana);
		panel3dana.setLayout(null);
		panel3Days();

		// lbl

		lblTemperatura = new JLabel();
		lblTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTemperatura.setBounds(126, 8, 264, 41);
		panelPocetni.add(lblTemperatura);
		lblVlaga = new JLabel("Vlaga:");
		lblVlaga.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblVlaga.setBounds(126, 56, 264, 35);
		panelPocetni.add(lblVlaga);
		lblVjetar = new JLabel("Vjetar:");
		lblVjetar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblVjetar.setBounds(126, 102, 264, 35);
		panelPocetni.add(lblVjetar);

		lblTlak = new JLabel("Tlak:");
		lblTlak.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTlak.setBounds(126, 148, 264, 35);
		panelPocetni.add(lblTlak);

		lblLat = new JLabel();
		lblLat.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLat.setBounds(479, 11, 264, 35);
		panelPocetni.add(lblLat);

		lblLon = new JLabel();
		lblLon.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLon.setBounds(479, 56, 264, 35);
		panelPocetni.add(lblLon);

		lblVrsta = new JLabel();
		lblVrsta.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblVrsta.setBounds(332, 235, 302, 41);
		panelPocetni.add(lblVrsta);

		lblVidljivost.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblVidljivost.setBounds(479, 102, 366, 35);
		panelPocetni.add(lblVidljivost);

		btnTrenutno.setBounds(12, 163, 90, 35);
		frame.getContentPane().add(btnTrenutno);

		grafBtn.setBounds(112, 163, 130, 35);
		frame.getContentPane().add(grafBtn);

	}
	/*
	 * Metoda za postavljanje actionlistenera radi button za "Zatrazi prognozu"
	 * prikaz za 3 dana neradi
	 */

	/**
	 * Adds the act list.
	 */
	public void addActList() {

		btnZatrazi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				AppClient client = new AppClient();
				System.out.println("RADIMMFC " + "GRAD " + textFieldUnesiImeGrada.getText());
				client.setGrad(textFieldUnesiImeGrada.getText() + ":1");
				client.takeControl();
				Prognoza prognoza = Prognoza.getInstance();

				if (prognoza.getPrognoza() != null) {
					grafBtn.setVisible(true);

					btnTrenutno.setVisible(true);

					double totalC = prognoza.getPrognoza().getMain().getTemp() - 273.1;

					DecimalFormat df = new DecimalFormat("####0.00");
					panelPocetni.setVisible(true);

					// System.out.println("Value of temp in C: " + df.format(totalC));
					lblTemperatura.setText("Temperatura je: " + df.format(totalC)+"C");
					////////////////////// vlaga///////////
					// System.out.println("VLAGA: " +
					////////////////////// prognoza.getPrognoza().getMain().getHumidity());
					lblVlaga.setText("Vlaga je: " + prognoza.getPrognoza().getMain().getHumidity() + "%");
					/// vjetar//
					// System.out.println("VJETAR: " + prognoza.getPrognoza().getWind().getSpeed());
					lblVjetar.setText("Vjetar je: " + prognoza.getPrognoza().getWind().getSpeed() + " cvorova");
					// vrijeme
					// System.out.println("Tlak: " +
					// prognoza.getPrognoza().getMain().getPressure());

					lblTlak.setText("Tlak je: " + prognoza.getPrognoza().getMain().getPressure() + "hpa");
					// lonlat
					lblLat.setText("Zem.duzina je: " + prognoza.getPrognoza().getCoord().getLat());
					lblLon.setText("Zem.sirina je: " + prognoza.getPrognoza().getCoord().getLon());
					/// vidljivost
					lblVidljivost.setText("Vidljivost je: " + prognoza.getPrognoza().getVisibility() + "m");
				}

			}
		});
		btnTrenutno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelPocetni.setVisible(true);

			}
		});
		grafBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelPocetni.setVisible(true);
				Prognoza prognoza = Prognoza.getInstance();
				double totalC = prognoza.getPrognoza().getMain().getTemp() - 273.1;
				int totalCint = (int) Math.round(totalC);
				// DecimalFormat df = new DecimalFormat("####0");

				int kiloPascali = (prognoza.getPrognoza().getMain().getPressure())/10;
				int vidljivostKM = (prognoza.getPrognoza().getVisibility())/1000;
				int vlaznost = prognoza.getPrognoza().getMain().getHumidity();
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				dataset.setValue(totalCint, "Stupnjevi Celzijusi", "Temperatura u C");
				dataset.setValue(kiloPascali, "KiloPascali", "Tlak*10");
				dataset.setValue(vidljivostKM, "KiloMetara", "Vidljivost u km");
				dataset.setValue(vlaznost, "%", "Vlaga");
				
				/**
				 * kreacija grafa 
				 * 
				 * 
				 */
				JFreeChart chart = ChartFactory.createBarChart("Grafikon", "Vremenske parametri", "Skala", dataset,
						PlotOrientation.VERTICAL, false, true, false);
				CategoryPlot p = chart.getCategoryPlot();
				p.setRangeGridlinePaint(Color.black);
				ChartFrame frame = new ChartFrame("Prognzoa Grafikon", chart);
				frame.setVisible(true);
				frame.setSize(700, 500);
				centreWindow(frame);

			}
		});

	}
	
	/**
	 * Centre window.
	 *
	 * @param frame the frame
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

	/**
	 * Panel trenutna.
	 */
	public void panelTrenutna() {

		panelPocetni.setBounds(12, 209, 897, 317);

		frame.getContentPane().add(panelPocetni);
		panelPocetni.setVisible(false);
		panelPocetni.setLayout(null);

		lblTemperatura = new JLabel("");
		lblTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTemperatura.setBounds(126, 8, 264, 41);
		panelPocetni.add(lblTemperatura);
		lblVlaga = new JLabel("");
		lblVlaga.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblVlaga.setBounds(126, 56, 264, 35);
		panelPocetni.add(lblVlaga);
		lblVjetar = new JLabel("");
		lblVjetar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblVjetar.setBounds(126, 102, 264, 35);
		panelPocetni.add(lblVjetar);

		lblTlak = new JLabel("");
		lblTlak.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTlak.setBounds(126, 148, 264, 35);
		panelPocetni.add(lblTlak);

		lblLat = new JLabel("");
		lblLat.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLat.setBounds(479, 11, 366, 35);
		panelPocetni.add(lblLat);

		lblLon = new JLabel("");
		lblLon.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLon.setBounds(479, 56, 366, 35);
		panelPocetni.add(lblLon);

		lblVrsta = new JLabel("TRENUTNA TEMPERATURA");
		lblVrsta.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblVrsta.setBounds(332, 235, 302, 41);
		panelPocetni.add(lblVrsta);

	}

	/**
	 * Panel 3 days.
	 */
	public void panel3Days() {

	}

	/**
	 * Inits the panel 3 days comps.
	 */
	public void initPanel3DaysComps() {

	}
}
