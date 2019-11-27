import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class DeclineCurvesApp {

	private JFrame frmDeclineCurves;
	private JTextField declineRateValue;
	private JTextField initialFlowRate;
	private JTextField qoLimit;
	private JTextField timeYears;
	private JTextField accumulatedProduction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeclineCurvesApp window = new DeclineCurvesApp();
					window.frmDeclineCurves.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeclineCurvesApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDeclineCurves = new JFrame();
		frmDeclineCurves.setTitle("Decline Curves");
		frmDeclineCurves.setBounds(100, 100, 629, 443);
		frmDeclineCurves.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDeclineCurves.getContentPane().setLayout(null);

		JLabel lblDeclineType = new JLabel("Decline Type");
		lblDeclineType.setBounds(10, 11, 84, 37);
		frmDeclineCurves.getContentPane().add(lblDeclineType);

		JRadioButton ExponentialDecline = new JRadioButton("Exponential");
		ExponentialDecline.setBounds(10, 46, 111, 23);
		frmDeclineCurves.getContentPane().add(ExponentialDecline);

		JRadioButton HarmonicDecline = new JRadioButton("Harmonic");
		HarmonicDecline.setBounds(10, 76, 111, 23);
		frmDeclineCurves.getContentPane().add(HarmonicDecline);

		JRadioButton HyperbolicDecline = new JRadioButton("Hyperbolic");
		HyperbolicDecline.setBounds(10, 104, 111, 23);
		frmDeclineCurves.getContentPane().add(HyperbolicDecline);

		JLabel lblDe = new JLabel("Decline Rate");
		lblDe.setBounds(175, 39, 84, 14);
		frmDeclineCurves.getContentPane().add(lblDe);

		declineRateValue = new JTextField();
		declineRateValue.setHorizontalAlignment(SwingConstants.CENTER);
		declineRateValue.setBounds(277, 36, 96, 20);
		frmDeclineCurves.getContentPane().add(declineRateValue);
		declineRateValue.setColumns(10);

		JLabel lblQoi = new JLabel("Qoi");
		lblQoi.setBounds(175, 91, 49, 14);
		frmDeclineCurves.getContentPane().add(lblQoi);

		initialFlowRate = new JTextField();
		initialFlowRate.setHorizontalAlignment(SwingConstants.CENTER);
		initialFlowRate.setBounds(277, 88, 96, 20);
		frmDeclineCurves.getContentPane().add(initialFlowRate);
		initialFlowRate.setColumns(10);

		JLabel lblBopd = new JLabel("BOPD");
		lblBopd.setBounds(402, 91, 49, 14);
		frmDeclineCurves.getContentPane().add(lblBopd);

		JLabel lblNewLabel = new JLabel("QoL");
		lblNewLabel.setBounds(175, 147, 49, 14);
		frmDeclineCurves.getContentPane().add(lblNewLabel);

		qoLimit = new JTextField();
		qoLimit.setHorizontalAlignment(SwingConstants.CENTER);
		qoLimit.setBounds(277, 144, 96, 20);
		frmDeclineCurves.getContentPane().add(qoLimit);
		qoLimit.setColumns(10);

		JLabel label = new JLabel("BOPD");
		label.setBounds(402, 147, 49, 14);
		frmDeclineCurves.getContentPane().add(label);

		JLabel lblTimeWhenThe = new JLabel("Time when the flow rate limit is reached");
		lblTimeWhenThe.setBounds(129, 254, 244, 14);
		frmDeclineCurves.getContentPane().add(lblTimeWhenThe);

		timeYears = new JTextField();
		timeYears.setHorizontalAlignment(SwingConstants.CENTER);
		timeYears.setEditable(false);
		timeYears.setBounds(385, 254, 96, 20);
		frmDeclineCurves.getContentPane().add(timeYears);
		timeYears.setColumns(10);

		JLabel lblYears = new JLabel("years");
		lblYears.setBounds(520, 254, 49, 14);
		frmDeclineCurves.getContentPane().add(lblYears);

		JLabel lblNpWhenThe = new JLabel("Np when the flow rate limit is reached");
		lblNpWhenThe.setBounds(129, 294, 244, 14);
		frmDeclineCurves.getContentPane().add(lblNpWhenThe);

		accumulatedProduction = new JTextField();
		accumulatedProduction.setHorizontalAlignment(SwingConstants.CENTER);
		accumulatedProduction.setEditable(false);
		accumulatedProduction.setColumns(10);
		accumulatedProduction.setBounds(385, 294, 96, 20);
		frmDeclineCurves.getContentPane().add(accumulatedProduction);

		JLabel lblBopd_1 = new JLabel("BOPD");
		lblBopd_1.setBounds(520, 294, 49, 14);
		frmDeclineCurves.getContentPane().add(lblBopd_1);

		JTextPane txtpnQoiInitial = new JTextPane();
		txtpnQoiInitial.setText(
				"Qoi = initial flow rate, QoL = economical flow rate limit,  Np = Accumulated oil production, BOPD = barrels of oil per day");
		txtpnQoiInitial.setBounds(94, 346, 448, 37);
		frmDeclineCurves.getContentPane().add(txtpnQoiInitial);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ExponentialDecline.isSelected()) {
					float declineRateInput = Float.valueOf(declineRateValue.getText());
					float qoi = Float.valueOf(initialFlowRate.getText());
					float qoL = Float.valueOf(qoLimit.getText());

					float exponentialDeclineRate = (float) Math.pow(1 - ((float) declineRateInput / 100), 12);
					float monthlyDeclineRate = (float) Math.log(exponentialDeclineRate) * (float) (-1);

					int year = 0;

					float np = 0;

					float tempQo = 0;

					year++;

					float qo = exponentialDeclineRate * qoi;
					float deltaNp = ((qoi - qo) / monthlyDeclineRate) * 365;
					np = np + deltaNp;

					tempQo = qo;

					while (qo > qoL) {
						qo = exponentialDeclineRate * tempQo;
						deltaNp = ((tempQo - qo) / monthlyDeclineRate) * 365;
						np = np + deltaNp;
						year++;
						tempQo = qo;
					}

					timeYears.setText(year + "");
					accumulatedProduction.setText(np + "");
				} else if (HarmonicDecline.isSelected()) {
					float declineRateInput = Float.valueOf(declineRateValue.getText());
					float qoi = Float.valueOf(initialFlowRate.getText());
					float qoL = Float.valueOf(qoLimit.getText());

					float exponentialDeclineRate = (float) Math.pow(1 - ((float) declineRateInput / 100), 12);
					float monthlyDeclineRate = (float) Math.log(exponentialDeclineRate) * (float) (-1);
					
					float deltaNp = 0;
					float tempNp = 0;
					int year = 0;

					year++;

					float qo = qoi / (1 + monthlyDeclineRate * year);
					float np = (float) (((qoi / monthlyDeclineRate) * Math.log(qoi / qo)) * 365);
					deltaNp = np - deltaNp;
					tempNp = np;
					year++;

					while (qo > qoL) {
						qo = qoi / (1 + monthlyDeclineRate * year);
						np = (float) (((qoi / monthlyDeclineRate) * Math.log(qoi / qo)) * 365);
						deltaNp = np - tempNp;
						tempNp = np;						
						year++;
					}
					
					timeYears.setText((year - 1) + "");
					accumulatedProduction.setText(np + "");

				} else if (HyperbolicDecline.isSelected()) {
					float declineRateInput = Float.valueOf(declineRateValue.getText());
					float qoi = Float.valueOf(initialFlowRate.getText());
					float qoL = Float.valueOf(qoLimit.getText());

					float exponentialDeclineRate = (float) Math.pow(1 - ((float) declineRateInput / 100), 12);
					float monthlyDeclineRate = (float) Math.log(exponentialDeclineRate) * (float) (-1);
					
					float deltaNp = 0;
					float tempNp = 0;
					int year = 0;
					final float n = 0.5f;
					
					year++;
					float qo = (float) (qoi * Math.pow((1 + n * monthlyDeclineRate*year), -1/n));
					float np = (float) (((Math.pow(qoi, n) / ((1 - n) * monthlyDeclineRate))) * ((Math.pow(qoi, n) - Math.pow(qo, n))) * 365);
					deltaNp = np - deltaNp;
					tempNp = np;
					year++;
					
					while(qo > qoL) {
						qo = (float) (qoi * Math.pow((1 + n * monthlyDeclineRate*year), -1/n));
						np = (float) (((Math.pow(qoi, n) / ((1 - n) * monthlyDeclineRate))) * ((Math.pow(qoi, n) - Math.pow(qo, n))) * 365);
						deltaNp = np - tempNp;
						tempNp = np;
						year++;
						
					}
					
					timeYears.setText((year - 1) + "");
					accumulatedProduction.setText(np + "");					
				}

			}
		});
		btnCalculate.setBounds(284, 197, 89, 23);
		frmDeclineCurves.getContentPane().add(btnCalculate);

		JLabel label_1 = new JLabel("%");
		label_1.setBounds(402, 35, 49, 14);
		frmDeclineCurves.getContentPane().add(label_1);

	}
}
