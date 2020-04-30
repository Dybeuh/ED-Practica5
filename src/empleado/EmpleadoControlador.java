package empleado;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoControlador {

	// Conexión con BBDD
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/empresa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String login = "root";
		String password = "Campesino89!";

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		public EmpleadoFromVista miVista = new EmpleadoFromVista(this);
		
		public EmpleadoControlador() {
			// Cargar el Driver
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.out.println("Se ha producido un error al cargar el Driver");
			}
			// Establecer la conexión con la base de datos
			try {
				connection = DriverManager.getConnection(url, login, password);
			} catch (SQLException e) {
				System.out.println("Se produjo un error al conectar a la Base de Datos");
				e.printStackTrace();
			}
		}
		
		
		 public String[] cargarListaEmpleados() {
		    	String[] res = null;
		    	try {
		    		List<String> acu = new ArrayList<String>();
		            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            rs = statement.executeQuery("SELECT nombreEmpleado From empleados;");
		            while(rs.next()) {
		            	acu.add(rs.getString("nombreEmpleado"));
		            }
		            res = (String[]) acu.toArray(new String[acu.size()]);
		        } catch (SQLException e) {
		            System.out.println("Error en la sentencia SQL");
		        }
				return res;
		    }
		    
		    public Boolean crearEmpleado(String nombreEmpleado) {
		    	Boolean resultado = false;
		    	try {
		    		statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            statement.executeUpdate("INSERT INTO empleados(nombreEmpleado) VALUES ('"+ nombreEmpleado +"');");
		            resultado = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	return resultado;
		    }
		    
		    public Boolean borrarEmpleado(String nombreEmpleado) {
		    	Boolean resultado = false;
		    	try {
		    		statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            statement.executeUpdate("DELETE FROM empleados WHERE nombreEmpleado='"+ nombreEmpleado +"';");
		            resultado = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	return resultado;
		    }
		    
		    public Empleado consultarEmpleadoPorID(String idEmpleado) {
		    	Empleado empleado = new Empleado();
		    	try {
		            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            rs = statement.executeQuery("SELECT * From empleados WHERE idEmpleado="+ idEmpleado +";");
		            if (rs.next()) {
		            	empleado.setId(rs.getInt("idEmpleado"));
		            	empleado.setNombre(rs.getString("nombreEmpleado"));
		            }
		        } catch (SQLException e) {
		            System.out.println("Error en la sentencia SQL");
		        }
		    	return empleado;
		    }
		    
		    public Empleado consultarEmpleadoPorNombre(String nombreEmpleado) {
		    	Empleado empleado = new Empleado();
		    	try {
		            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            rs = statement.executeQuery("SELECT * From empleados WHERE nombreEmpleado='"+ nombreEmpleado +"';");
		            if (rs.next()) {
		            	empleado.setId(rs.getInt("idEmpleado"));
		            	empleado.setNombre(rs.getString("nombreEmpleado"));
		            }
		        } catch (SQLException e) {
		            System.out.println("Error en la sentencia SQL");
		        }
		    	return empleado;
		    }
		    
		    public Boolean modificarEmpleado(String nombreEmpleado, String idEmpleado) {
		    	Boolean resultado = false;
		    	try {
		    		statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            statement.executeUpdate("UPDATE empleados SET nombreEmpleado='"+ nombreEmpleado +"' WHERE idEmpleado="+ idEmpleado+ ";");
		            resultado = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	return resultado;
		    }
		    
		    
		    
		   
		}