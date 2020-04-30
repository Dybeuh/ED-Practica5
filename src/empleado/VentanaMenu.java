package empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaMenu extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem mi1,mi2,mi3,mi4;
    private EmpleadoControlador empleadoControlador = new EmpleadoControlador();
	
	public VentanaMenu () {
		setLayout(null);
	    mb=new JMenuBar();
	    setJMenuBar(mb);
	    menu1=new JMenu("Gestión empleado");
	    mb.add(menu1);
	    mi1=new JMenuItem("Nuevo empleado");
	    mi1.addActionListener(this);
	    menu1.add(mi1);
	    mi2=new JMenuItem("Borrar empleado");
	    mi2.addActionListener(this);
	    menu1.add(mi2);
	    mi3=new JMenuItem("Consultar empleado");
	    mi3.addActionListener(this);
	    menu1.add(mi3);
	    mi4=new JMenuItem("Editar empleado");
	    mi4.addActionListener(this);
	    menu1.add(mi4);
	    setBounds(10,20,300,200);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(mi1.equals(arg0.getSource())) {
			this.empleadoControlador.miVista.cargarNuevo();
		}else if (mi2.equals(arg0.getSource())) {
			String[] emps = this.empleadoControlador.cargarListaEmpleados();
			this.empleadoControlador.miVista.cargarBorrar(emps);
		}else if (mi3.equals(arg0.getSource())) {
			this.empleadoControlador.miVista.cargarConsultar();
		}else if (mi4.equals(arg0.getSource())) {
			String[] emps = this.empleadoControlador.cargarListaEmpleados();
			this.empleadoControlador.miVista.cargarEditar(emps);
		}
		
	}
}