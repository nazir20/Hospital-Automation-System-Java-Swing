package View;

/**
 * 
 * @imports
 * 
 */
import Helper.*;
import Model.*;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel wrapperPane;
	private JTextField fld_hastaTC;
	private JTextField fld_hastaPassword;
	private JTextField fld_doctorTC;
	private JPasswordField fld_doctorPassword;
	private DBconnection con = new DBconnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * @create the frame.
	 * 
	 */
	public LoginGUI() {
		
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		wrapperPane = new JPanel();
		wrapperPane.setBackground(Color.WHITE);
		wrapperPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wrapperPane);
		wrapperPane.setLayout(null);
		Image img = new ImageIcon("hospital-logo.png").getImage() ;  
		Image newImg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
		JLabel lbl_logo = new JLabel(new ImageIcon(newImg));
		lbl_logo.setBounds(216, 24, 84, 78);
		wrapperPane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane  Yönetim  Sistemi'ne  Hoşgeldiniz!");
		lblNewLabel.setFont(new Font("Zapf Dingbats", Font.BOLD, 17));
		lblNewLabel.setBounds(72, 98, 358, 23);
		wrapperPane.add(lblNewLabel);
		
		JTabbedPane WrappertabbedPane = new JTabbedPane(JTabbedPane.TOP);
		WrappertabbedPane.setBounds(6, 133, 488, 239);
		wrapperPane.add(WrappertabbedPane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		WrappertabbedPane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaranız:");
		lblTcNumaranz.setFont(new Font("Zapf Dingbats", Font.BOLD, 15));
		lblTcNumaranz.setBounds(45, 31, 124, 23);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Zapf Dingbats", Font.BOLD, 15));
		lblifre.setBounds(45, 66, 124, 23);
		w_hastaLogin.add(lblifre);
		
		fld_hastaTC = new JTextField();
		fld_hastaTC.setHorizontalAlignment(SwingConstants.CENTER);
		fld_hastaTC.setFont(new Font("Zapf Dingbats", Font.PLAIN, 13));
		fld_hastaTC.setBounds(182, 26, 225, 34);
		w_hastaLogin.add(fld_hastaTC);
		fld_hastaTC.setColumns(10);
		
		fld_hastaPassword = new JTextField();
		fld_hastaPassword.setHorizontalAlignment(SwingConstants.CENTER);
		fld_hastaPassword.setFont(new Font("Zapf Dingbats", Font.PLAIN, 13));
		fld_hastaPassword.setColumns(10);
		fld_hastaPassword.setBounds(181, 64, 225, 34);
		w_hastaLogin.add(fld_hastaPassword);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_hastaLogin.setBounds(243, 141, 159, 39);
		w_hastaLogin.add(btn_hastaLogin);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.setForeground(Color.BLACK);
		btn_register.setBackground(Color.WHITE);
		btn_register.setBounds(45, 141, 159, 39);
		w_hastaLogin.add(btn_register);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		WrappertabbedPane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaranız:");
		lblTcNumaranz_1.setFont(new Font("Zapf Dingbats", Font.BOLD, 15));
		lblTcNumaranz_1.setBounds(51, 30, 124, 23);
		w_doctorLogin.add(lblTcNumaranz_1);
		
		fld_doctorTC = new JTextField();
		fld_doctorTC.setHorizontalAlignment(SwingConstants.CENTER);
		fld_doctorTC.setFont(new Font("Zapf Dingbats", Font.PLAIN, 13));
		fld_doctorTC.setColumns(10);
		fld_doctorTC.setBounds(187, 28, 225, 34);
		w_doctorLogin.add(fld_doctorTC);
		
		JLabel lblTcNumaranz_1_1 = new JLabel("Şifre:");
		lblTcNumaranz_1_1.setFont(new Font("Zapf Dingbats", Font.BOLD, 15));
		lblTcNumaranz_1_1.setBounds(51, 73, 124, 23);
		w_doctorLogin.add(lblTcNumaranz_1_1);
		/**
		 * 
		 * 
		 * 
		 * @control the login of doctors 
		 * 
		 * 
		 */
		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * 
				 * @control if fields are not empty while clicking the login button
				 * 
				 */
				if(fld_doctorTC.getText().length() == 0 || fld_doctorPassword.getText().length() ==0) {
					
					Helper.showMessage("fill");
					
				}else {
					
					Connection connect = null;
					try {
						connect = con.connectDB();
						Statement stmt = connect.createStatement();
						String query = "SELECT * FROM users";
						ResultSet result = stmt.executeQuery(query);
						while(result.next()) {
							if(fld_doctorTC.getText().equals(result.getString("tcno")) && fld_doctorPassword.getText().equals(result.getString("password"))) {
								BasHekim basHekim = new BasHekim();
								basHekim.setId(result.getInt("id"));
								basHekim.setTcno(result.getString("tcno"));
								basHekim.setName(result.getString("name"));
								basHekim.setType(result.getString("type"));
								basHekim.setPassword("password");
								
								
								/**
								 * 
								 * 
								 * if provided TCNO & password is right:
								 * then, the BasHekim GUI is going to be called and replaced with the login GUI
								 * 
								 * 
								 */
								BasHekimGUI bhGUI = new BasHekimGUI(basHekim);
								bhGUI.setVisible(true);
								dispose(); // causes the JFrame window to be destroyed...
								
							}
						}
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					}
					
					
				}
				
			}
		});
		btn_doctorLogin.setBounds(51, 130, 361, 39);
		w_doctorLogin.add(btn_doctorLogin);
		
		fld_doctorPassword = new JPasswordField();
		fld_doctorPassword.setBounds(187, 71, 225, 34);
		w_doctorLogin.add(fld_doctorPassword);
	}
}
