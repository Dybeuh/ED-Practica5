package empleado;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmpleadoFromVista implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> choiceSelector = new JComboBox<String>();
	private JLabel idLabel = new JLabel("ID");
	private JLabel nombreLabel = new JLabel("Nombre");
	private JTextField idField = new JTextField();
	private JTextField nombreField = new JTextField();
	private JButton cancelButton = new JButton("Cancelar");
	private JButton aceptarButton = new JButton("Aceptar");
	public JFrame miFrame = new JFrame();
	public String estado = "";
	public EmpleadoControlador empContrl;
	

	public EmpleadoFromVista(EmpleadoControlador empContrl) {
		
		this.empContrl = empContrl;
		miFrame.setTitle("Entornos de Desarrollo 2020");
		miFrame.setBounds(100, 100, 300, 200);
		GridBagLayout miLayout = new GridBagLayout();
		GridBagConstraints miContrain = new GridBagConstraints();
		miFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		miContrain.weightx = 0.5;
		miContrain.insets = new Insets(10,10,10,10);
		miFrame.getContentPane().setLayout(miLayout);
		miContrain.weighty = 1.0;
		// ADD choice
		miContrain.anchor = GridBagConstraints.NORTH;
		miContrain.fill = GridBagConstraints.HORIZONTAL;
		miContrain.gridx = 0;
		miContrain.gridy = 0;
		miContrain.gridwidth = 2;
		choiceSelector.addActionListener(this);
		miFrame.getContentPane().add(choiceSelector, miContrain);
		miContrain.gridwidth = 1;
		// add f1 l1 y 2
		miContrain.weighty = 0.0;
		miContrain.anchor = GridBagConstraints.BASELINE;
		miContrain.fill = GridBagConstraints.HORIZONTAL;
		miContrain.gridx = 0;
		miContrain.gridy = 1;
		miFrame.getContentPane().add(nombreLabel, miContrain);
		miContrain.fill = GridBagConstraints.HORIZONTAL;
		miContrain.gridx = 1;
		miContrain.gridy = 1;
		miFrame.getContentPane().add(nombreField, miContrain);
		miContrain.fill = GridBagConstraints.HORIZONTAL;
		miContrain.gridx = 0;
		miContrain.gridy = 2;
		miFrame.getContentPane().add(idLabel, miContrain);
		miContrain.fill = GridBagConstraints.HORIZONTAL;
		miContrain.gridx = 1;
		miContrain.gridy = 2;
		miFrame.getContentPane().add(idField, miContrain);
		// add buttons
		miContrain.gridx = 0;
		miContrain.gridy = 3;
		miFrame.getContentPane().add(cancelButton, miContrain);
		miContrain.fill = GridBagConstraints.HORIZONTAL;
		miContrain.gridx = 1;
		miContrain.gridy = 3;
		aceptarButton.addActionListener(this);
		miFrame.getContentPane().add(aceptarButton, miContrain);
		miFrame.setVisible(false);
	}
	
	public void cargarNuevo() {
		choiceSelector.setVisible(false);
		idLabel.setVisible(false);
		nombreLabel.setVisible(true);
		idField.setVisible(false);
		idField.setEditable(true);
		nombreField.setVisible(true);
		aceptarButton.setText("Crear");
		aceptarButton.setVisible(true);
		this.estado = "nuevo";
		this.miFrame.setVisible(true);
		this.miFrame.setTitle("Alta Empleado");
	}
	
	public void cargarBorrar(String[] empleados) {
		choiceSelector.setVisible(true);
		choiceSelector.setModel(new DefaultComboBoxModel<String>(empleados));
		idLabel.setVisible(false);
		nombreLabel.setVisible(false);
		idField.setVisible(false);
		idField.setEditable(false);
		nombreField.setVisible(false);
		aceptarButton.setText("Borrar");
		aceptarButton.setVisible(true);
		this.estado = "borrar";
		this.miFrame.setVisible(true);
		this.miFrame.setTitle("Borrar Empleado");
	}
	
	public void cargarConsultar() {
		choiceSelector.setVisible(false);
		idLabel.setVisible(true);
		nombreLabel.setVisible(true);
		idField.setVisible(true);
		idField.setEditable(true);
		nombreField.setVisible(true);
		aceptarButton.setText("Consultar");
		aceptarButton.setVisible(true);
		this.estado = "consultar";
		this.miFrame.setVisible(true);
		this.miFrame.setTitle("Consultar Empleado");
	}
	
	public void cargarEditar(String[] empleados) {
		choiceSelector.setModel(new DefaultComboBoxModel<String>(empleados));
		choiceSelector.setVisible(true);
		idLabel.setVisible(true);
		nombreLabel.setVisible(true);
		idField.setVisible(true);
		idField.setEditable(false);
		nombreField.setVisible(true);
		aceptarButton.setText("Editar");
		aceptarButton.setVisible(true);
		this.estado = "editar";
		this.miFrame.setVisible(true);
		this.miFrame.setTitle("Editar Empleado");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(aceptarButton.equals(e.getSource())) {
			if (this.estado.equals("nuevo")) {
				Boolean res = this.empContrl.crearEmpleado(this.nombreField.getText());

			} else if (this.estado.equals("borrar")) {
				
				int resp= JOptionPane.showConfirmDialog(null,
			             "¿Seguro que quiere borrar?", "Borrar empleado", JOptionPane.YES_NO_OPTION);
				if (JOptionPane.OK_OPTION == resp){
					Boolean res = this.empContrl.borrarEmpleado(this.choiceSelector.getSelectedItem().toString());
					String[] emps = this.empContrl.cargarListaEmpleados();
					this.cargarBorrar(emps);
					 }
				
			}else if (this.estado.equals("consultar")) {
				if (! this.nombreField.getText().isEmpty()) {
					Empleado emp = this.empContrl.consultarEmpleadoPorNombre(this.nombreField.getText());
					this.idField.setText(emp.getId().toString());
					this.nombreField.setText(emp.getNombre());
				} else if (! this.idField.getText().isEmpty()){
					Empleado emp = this.empContrl.consultarEmpleadoPorID(this.idField.getText());
					this.idField.setText(emp.getId().toString());
					this.nombreField.setText(emp.getNombre());
				}
				
			}else if (this.estado.equals("editar")) {
				Boolean res = this.empContrl.modificarEmpleado(this.nombreField.getText(), this.idField.getText());
				String[] emps = this.empContrl.cargarListaEmpleados();
				this.cargarEditar(emps);
			}
		} else if (choiceSelector.equals(e.getSource())) {
			if (this.estado.equals("editar")) {
				String nombreEmpleado = choiceSelector.getSelectedItem().toString();
				Empleado emp = this.empContrl.consultarEmpleadoPorNombre(nombreEmpleado);
				this.idField.setText(emp.getId().toString());
				this.nombreField.setText(emp.getNombre());
			}
		}
		
	}

}
