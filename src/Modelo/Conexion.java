package Modelo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.ScriptRunner;


public class Conexion {
	public static String bd="";
	public static String login="root";
	public static String pass="";
	public static String host="localhost";
	public static String url="";
	public static ArrayList<String> bdds=new ArrayList<>();
	public static Connection con=null;
	public static ResultSet rs = null;
	
	public static boolean open() {
		
		try {
			if ((con==null) || (con.isClosed())) {
				url="jdbc:mysql://"+host+"/"+bd + "?useSSL=false";
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url, login, pass);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se puede conectar!!!");
			return false;
		}
		return true;
		
	}
	
	public static void close() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			
		}
	}
	public static void listabd() {
		bdds.clear();
		try {
			rs = con.getMetaData().getCatalogs();
			while(rs.next()){
				bdds.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static int createDB(String db) {
		try {
			/*
			 * Obtiene los nombres de las bases de datos que existen en nuestro servidor MySql
			 * y los compara con el nombre de la base de datos a crear
			 * devuelve 1 si la base de datos existe,0 si la base de datos no existia y la ha creado
			 * y -1 si ha salido una excepción o ha salido mal
			 */

			for(String bdd:bdds) {
				
				if(bdd.equalsIgnoreCase(db)) {
					bd=db;
					return 1;
				}
				
			}						
			Statement stm;
	        stm = con.createStatement();
	        stm.executeUpdate("CREATE DATABASE "+ db);
	        	 bd=db;
	            return 0;
	        
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
	}

	public static int addTable(String nom) {
		/*
		 * Añade una tabla a la base de datos que tenemos con un campo id preestablecido
		 * Devuelve 0 si se ha creado la table con exito o 
		 * 1 si no se ha creado pero existe
		 * -1 si ha salido algun error
		 */
		try {
			Statement stm;
	        int status;
			url="jdbc:mysql://"+host+"/"+bd + "?useSSL=false";
	        con=DriverManager.getConnection(url, login, pass);
	        String n=url;
	        stm = con.createStatement();
			status = stm.executeUpdate("CREATE TABLE " + nom +"(id int unsigned auto_increment primary key)");
	        if(status > 0) {
	            return 0;
	         }
	        
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block			
			// Si el error es que la tabla ya existe devuelve 1
			if(e.getMessage().equalsIgnoreCase("Table '" + nom +"' already exists")) {	
				return 1;
			}
			else return -1;			
		}
		return 1;
		
		
	}
	
	public static int addColumn(String table, String column,String tipo) {
		/*
		 * Añade una columno o regustro a la table 
		 * table=nombre de la tabla
		 * column=nombre de la columna a añadir
		 * tipo=el tipo de dato de la columna y sus atributos
		 * 
		 */
		try {
			Statement stm;
	        stm = con.createStatement();
	        int status;
	        status=stm.executeUpdate("ALTER TABLE " +table +" ADD COLUMN " + column + " " + tipo);
	       
			
			
			if(status > 0) {
	            return 0;
	         }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return -1;
		}

		return 1;
		
		
	}



	public static void addScript(String p) {
		//Metodo que lee un script y lo ejecuta en nuestro servidor MySQL
		try {
			ScriptRunner sr = new ScriptRunner(con);
		      //Creando reader para leer el objeto
		    Reader reader;
			reader = new BufferedReader(new FileReader(p));
			//Que no imprima el script por pantalla
			sr.setLogWriter(null);
		    //Corriendo el script
		    sr.runScript(reader);	      
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	
}