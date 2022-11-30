package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	public static String bd="";
	public static String login="root";
	public static String pass="12345678";
	public static String host="localhost";
	public static String url="";
	
	public static Connection con=null;
	public static ResultSet rs = null;
	
	public static void open() {
		
		try {
			if ((con==null) || (con.isClosed())) {
				url="jdbc:mysql://"+host+"/"+bd + "?useSSL=false";
				Class.forName("org.mariadb.jdbc.Driver");
				con=DriverManager.getConnection(url, login, pass);
			}
		} catch (Exception e) {
			System.out.println("No se puede conectar!!!");
		}
		
	}
	
	public static void close() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			
		}
	}
	// Metodo que busca la base dedatos que queremos y si no esta creada la creamos
	public static int createDB(String db) {
		try {
			/*
			 * Obtiene los nombres de las bases de datos que existen en nuestro servidor MySql
			 * y los compara con el nombre de la base de datos a crear
			 * devuelve 1 si la base de datos existe,0 si la base de datos no existia y la ha creado
			 * y -1 si ha salido una excepción o ha salido mal
			 */
			rs = con.getMetaData().getCatalogs();
			
			while(rs.next()){
				String basesdedatos = rs.getString(1);				
				if(db.equals(basesdedatos)){
					System.out.println("the database "+db+" exists");
					return 0;
				}			
			}
			
			// Crear base de datos
			 Statement stm;
	         stm = con.createStatement();
	         int status = stm.executeUpdate("CREATE DATABASE "+db);
	         if(status > 0) {
	            return 0;
	         }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}finally{
			// Cerramos el resulSet haya un excepción o no
			if( rs != null){
				try{
				    rs.close();
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
		return 0;
		
		
	}
	
//	/*
//	 *  Metodo que busca la base dedatos que queremos y si no esta creada la creamos 
//	 *  y lo asigna a la bd global
//	 */
//	public static int createDB(String db) {
//		try {
//			/*
//			 * Obtiene los nombres de las bases de datos que existen en nuestro servidor MySql
//			 * y los compara con el nombre de la base de datos a crear
//			 * devuelve 1 si la base de datos existe,0 si la base de datos no existia y la ha creado
//			 * y -1 si ha salido una excepción o ha salido mal
//			 */
//			rs = con.getMetaData().getCatalogs();
//			while(rs.next()){
//				String basesdedatos = rs.getString(1);
//				
//				if(db.equals(basesdedatos)){
//					bd=db;
//					return 0;
//				}
//				
//				
//				
//			}
//			Statement stm;
//	         stm = con.createStatement();
//	         int status = stm.executeUpdate("CREATE DATABASE "+ db);
//	         if(status > 0) {
//	        	 bd=db;
//	            return 0;
//	         }
//			
//			
//			
//			
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			return -1;
//		}finally{
//			// Cerramos el resulSet haya un excepción o no
//			if( rs != null){
//				try{
//				    rs.close();
//				}
//				catch(SQLException ex){
//					ex.printStackTrace();
//				}
//			}
//		}
//		return 0;
//		
//		
//	}
//
//	public static int addTable(String nom) {
//		/*
//		 * Añade una tabla a la base de datos que tenemos con un campo id preestablecido
//		 * Devuelve 0 si se ha creado la table con exito o 1 si no
//		 */
//		try {
//			host="localhost";
//			Statement stm;
//	        int status;
//			url="jdbc:mysql://"+host+"/"+bd + "?useSSL=false";
//	        con=DriverManager.getConnection(url, login, pass);
//	        stm = con.createStatement();
//			status = stm.executeUpdate("CREATE TABLE " + nom +"(id int unsigned auto_increment primary key)");
//	        if(status > 0) {
//	            return 0;
//	         }
//	        
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return 1;
//		
//		
//	}
//	
//	public static int addColumn(String table, String column,String tipo) {
//		/*
//		 * Añade una columno o regustro a la table 
//		 * table=nombre de la tabla
//		 * column=nombre de la columna a añadir
//		 * tipo=el tipo de dato de la columna y sus atributos
//		 * 
//		 */
//		try {
//			Statement stm;
//	        stm = con.createStatement();
//	        int status;
//	        stm.executeUpdate("ALTER TABLE " +table +"ADD COLUMN " + column + " " + tipo);
//			status = stm.executeUpdate("");
//	       
//			
//			
//			if(status > 0) {
//	            return 0;
//	         }
//	        
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return 1;
//		
//		
//	}
//

	
	
	
}