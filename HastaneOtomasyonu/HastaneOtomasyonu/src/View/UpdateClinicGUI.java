package View;

/**
 * 
 * @imports
 * 
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Helper.Helper;
import Model.Clinic;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static Clinic clinic; 

	/**
	 * 
	 * @Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * @Create the frame.
	 * 
	 */
	public UpdateClinicGUI(Clinic clinic) {
		
		setTitle("Güncelle");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 230);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Polikilinik Adı");
		lblNewLabel_1_3.setBounds(118, 15, 137, 22);
		contentPane.add(lblNewLabel_1_3);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(71, 49, 200, 36);
		fld_clinicName.setText(clinic.getName());
		contentPane.add(fld_clinicName);
		
		JButton btn_updateClinic = new JButton("Düzenle");
		/**
		 * 
		 * @event listener
		 * 
		 */
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confrim("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fld_clinicName.getText());
						Helper.showMessage("success");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateClinic.setForeground(new Color(0, 100, 0));
		btn_updateClinic.setBounds(71, 87, 200, 36);
		contentPane.add(btn_updateClinic);
	}

}
