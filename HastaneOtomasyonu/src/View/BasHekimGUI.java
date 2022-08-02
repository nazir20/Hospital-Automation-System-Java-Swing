package View;
import Helper.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.BasHekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class BasHekimGUI extends JFrame {
	
	static BasHekim basHekim  = new BasHekim();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPassword;
	private JTextField fld_DoctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	
	Clinic clinic = new Clinic();
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_employee;
	/**
	 * 
	 * @Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(basHekim);
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
	 * @throws SQLException
	 * 
	 */
	public BasHekimGUI(BasHekim bashekim) throws SQLException{
		
		/**
		 * 
		 * @doctor model
		 * 
		 */
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "T.C.No";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		
		try {
			for(int i = 0; i<bashekim.getDoctorList().size(); i++) {
				doctorData[0] = bashekim.getDoctorList().get(i).getId();
				doctorData[1] = bashekim.getDoctorList().get(i).getName();
				doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
				doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
				doctorModel.addRow(doctorData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + bashekim.getName() + "!");
		lblNewLabel.setFont(new Font("Zapf Dingbats", Font.PLAIN, 16));
		lblNewLabel.setBounds(68, 33, 415, 30);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setBounds(588, 36, 117, 29);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setForeground(Color.WHITE);
		w_tab.setBackground(Color.WHITE);
		w_tab.setBounds(6, 75, 738, 397);
		w_pane.add(w_tab);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setBounds(550, 7, 76, 22);
		w_doctor.add(lblNewLabel_1);
		
		/**
		 * 
		 * 
		 * @add button event Listener
		 * 
		 * 
		 */
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_dName.getText().length() == 0 || fld_dTcno.getText().length() == 0 || fld_dPassword.getText().length() ==0) {
					
					Helper.showMessage("fill");
					
				}else {
					
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPassword.getText(), fld_dName.getText());
						if(control) {
							Helper.showMessage("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPassword.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btn_addDoctor.setForeground(new Color(0, 100, 0));
		btn_addDoctor.setBounds(550, 205, 159, 36);
		w_doctor.add(btn_addDoctor);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(548, 31, 161, 36);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setBounds(550, 71, 76, 22);
		w_doctor.add(lblNewLabel_1_1);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(550, 93, 159, 36);
		w_doctor.add(fld_dTcno);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Şifre");
		lblNewLabel_1_1_1.setBounds(550, 131, 76, 22);
		w_doctor.add(lblNewLabel_1_1_1);
		
		fld_dPassword = new JTextField();
		fld_dPassword.setColumns(10);
		fld_dPassword.setBounds(550, 157, 159, 36);
		w_doctor.add(fld_dPassword);
		
		fld_DoctorID = new JTextField();
		fld_DoctorID.setForeground(new Color(211, 211, 211));
		fld_DoctorID.setColumns(10);
		fld_DoctorID.setBounds(550, 276, 159, 36);
		w_doctor.add(fld_DoctorID);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kullanıcı ID");
		lblNewLabel_1_1_1_1.setBounds(550, 253, 76, 22);
		w_doctor.add(lblNewLabel_1_1_1_1);
		
		JButton btn_deleteDoctor = new JButton("Sil");
		btn_deleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_DoctorID.getText().length() ==0) {
					Helper.showMessage("Lütfen geçerli bir doktor seçiniz!");
				}else {
					if(Helper.confrim("sure")) {
						try {
							int selectedID = Integer.parseInt(fld_DoctorID.getText());
							boolean control = bashekim.deleteDoctor(selectedID);
							if(control) {
								Helper.showMessage("success");
								updateDoctorModel();
								fld_DoctorID.setText(null);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_deleteDoctor.setForeground(new Color(255, 0, 0));
		btn_deleteDoctor.setBounds(550, 309, 159, 36);
		w_doctor.add(btn_deleteDoctor);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(6, 7, 532, 338);
		w_doctor.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				try {
					fld_DoctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				}catch(Exception ex) {
					
				}
				
			}
		} );
		
		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				
				if(e.getType() == TableModelEvent.UPDATE) {
					
					int selectedID = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectedName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectedTCNO = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectedPassword = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					try {
						boolean control = bashekim.updateDoctor(selectedID, selectedTCNO, selectedPassword,selectedName);
						if(control) {
							Helper.showMessage("success");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
			
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 * @clinics model
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		/**
		 * @creating table for clinic Model
		 */
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Polikilinik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		try {
			for(int i = 0; i<clinic.getClinicList().size(); i++) {
				clinicData[0] = clinic.getClinicList().get(i).getId();
				clinicData[1] = clinic.getClinicList().get(i).getName();
				clinicModel.addRow(clinicData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Polikilinkler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(6, 6, 272, 339);
		w_clinic.add(w_scrollClinic);
		
		/**
		 * 
		 * @Popup menu for clinic
		 * 
		 */
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedClinicID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				try {
					Clinic selectedClinic = clinic.getFetch(selectedClinicID);
					UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectedClinic);
					updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					updateGUI.setVisible(true);
					updateGUI.addWindowListener( new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							try {
								updateClinicModel();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}

					});
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		deleteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confrim("sure")) {
					int selectedClinicID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if(clinic.deleteclinic(selectedClinicID)) {
							Helper.showMessage("success");
							updateClinicModel();
						}else {
							Helper.showMessage("Error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		/*
		 * 
		 */
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener((MouseListener) new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
				
			}

		});
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lblNewLabel_1_3 = new JLabel("Polikilinik Adı");
		lblNewLabel_1_3.setBounds(290, 6, 137, 22);
		w_clinic.add(lblNewLabel_1_3);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(289, 30, 139, 36);
		w_clinic.add(fld_clinicName);
		/**
		 * 
		 * @add clinics
		 * 
		 */
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_clinicName.getText().length() == 0) {
					Helper.showMessage("fill");
				}else {
					try {
						if(clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMessage("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setForeground(new Color(0, 100, 0));
		btn_addClinic.setBounds(289, 66, 139, 36);
		w_clinic.add(btn_addClinic);
		
		JScrollPane w_scrollEmployee = new JScrollPane();
		w_scrollEmployee.setBounds(439, 6, 272, 339);
		w_clinic.add(w_scrollEmployee);
		
		table_employee = new JTable();
		w_scrollEmployee.setViewportView(table_employee);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(290, 264, 137, 36);
		w_clinic.add(select_doctor);
		
		/**
		 * @adding doctor names to the JComboBox
		 */
		for(int i = 0; i<bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(),bashekim.getDoctorList().get(i).getName()));
		}
		/**
		 * 
		 * 
		 * @employee model
		 * 
		 * 
		 * 
		 */
		DefaultTableModel employeeModel = new DefaultTableModel();
		
		Object[] colEmployee = new Object[2];
		colEmployee[0] = "ID";
		colEmployee[1] = "Ad Soyad";
		employeeModel.setColumnIdentifiers(colEmployee);
		Object[] employeeData = new Object[2];
		
		JButton btn_addEmployee = new JButton("Ekle");
		btn_addEmployee.setForeground(new Color(0, 100, 0));
		btn_addEmployee.setBounds(288, 298, 139, 36);
		w_clinic.add(btn_addEmployee);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Polikilinik Adı");
		lblNewLabel_1_3_1.setBounds(290, 152, 137, 22);
		w_clinic.add(lblNewLabel_1_3_1);
		
		/**
		 * 
		 * 
		 */
		btn_addEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_clinic.getSelectedRow();
				if(selectedRow >= 0) {
					String selectedClinic = table_clinic.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = bashekim.addEmployee(doctorItem.getKey(), selectedClinicID);
						if(control) {
							Helper.showMessage("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_employee.getModel();
							clearModel.setRowCount(0);
							for(int i = 0; i<bashekim.getClinicDoctorList(selectedClinicID).size(); i++) {
								employeeData[0] = bashekim.getClinicDoctorList(selectedClinicID).get(i).getId();
								employeeData[1] = bashekim.getClinicDoctorList(selectedClinicID).get(i).getName();
								employeeModel.addRow(employeeData);
							}
							table_employee.setModel(employeeModel);
						}else {
							Helper.showMessage("Error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					Helper.showMessage("Lütfen bir polikilinik seçiniz!");
				}
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 */
		
		JButton btn_selectEmployee = new JButton("Seç");
		btn_selectEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_clinic.getSelectedRow();
				if(selectedRow >=0) {
					String selectedClinic = table_clinic.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_employee.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i = 0; i<bashekim.getClinicDoctorList(selectedClinicID).size(); i++) {
							employeeData[0] = bashekim.getClinicDoctorList(selectedClinicID).get(i).getId();
							employeeData[1] = bashekim.getClinicDoctorList(selectedClinicID).get(i).getName();
							employeeModel.addRow(employeeData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_employee.setModel(employeeModel);
				}else {
					Helper.showMessage("Lütfen bir polikilinik seçiniz!");
				}
			}
		});
		btn_selectEmployee.setForeground(new Color(0, 100, 0));
		btn_selectEmployee.setBounds(290, 174, 139, 36);
		w_clinic.add(btn_selectEmployee);
		
		
	}
	public void updateDoctorModel() throws SQLException{
		
		DefaultTableModel clear = (DefaultTableModel) table_doctor.getModel();
		clear.setRowCount(0);
		
		try {
			for(int i = 0; i<basHekim.getDoctorList().size(); i++) {
				doctorData[0] = basHekim.getDoctorList().get(i).getId();
				doctorData[1] = basHekim.getDoctorList().get(i).getName();
				doctorData[2] = basHekim.getDoctorList().get(i).getTcno();
				doctorData[3] = basHekim.getDoctorList().get(i).getPassword();
				doctorModel.addRow(doctorData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateClinicModel() throws SQLException{
		
		DefaultTableModel clear = (DefaultTableModel) table_clinic.getModel();
		clear.setRowCount(0);
		
		try {
			for(int i = 0; i<clinic.getClinicList().size(); i++) {
				clinicData[0] = clinic.getClinicList().get(i).getId();
				clinicData[1] = clinic.getClinicList().get(i).getName();
				clinicModel.addRow(clinicData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
