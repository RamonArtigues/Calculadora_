package Main;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import Vista.Ventana;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Controlador.Calculadora;
import Modelo.Conexion;

/**
 *
 * @author Ramon
 * @version 1.0
 * Clase a ejecutar para iniciar nuestra calculadora
 */

public class Main {
	/**
	 * Corre nuestro programa i nos conecta al servidor MySQL
	 * 
	 * En el caso de no poder conectarse a MySQL este nos lo pedira hasta que se conecte <br>
	 * o se pulse cancelar, despues si la base de datos para guardar las operaciones no existe <br>
	 * nos pedira si queremos crear la base de datos y sus tablas y si queremos importar los datos de prueba
	 * @param args array String
	 */   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        try {  
        	
            /**
            *Seelecionamos el diseño de imagenes de una libreria llamada "JTatoo"
            *e importamos la libreria desde http://www.jtattoo.net/Download.html
            */
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");  
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {    	
            public void run() {
            	String user;
            	String pass;
            	
            	while(!Conexion.open()) {
            		
                	user=Conexion.login=JOptionPane.showInputDialog("Usuario de la bdd?");
                	pass=Conexion.pass=JOptionPane.showInputDialog("Contraseña de la bdd?");
                	if(user==null || pass==null) {
                    	JOptionPane.showMessageDialog(null, "Cancelado con exito");
                		System.exit(0);
                	}
                	if(!Conexion.open())
                    	JOptionPane.showMessageDialog(null, "Conexión fallida");
                	
            	}            	
            	JOptionPane.showMessageDialog(null, "Conectado a la base de datos");
            	
            	
            	//Lista las bases de datos que existen
            	Conexion.listabd();            	
            	if (Conexion.createDB("Operaciones")!=1) {
					int resp;
					resp = JOptionPane.showConfirmDialog(null, "Crear la base de datos y sus tablas?", null,JOptionPane.YES_NO_OPTION);
					if (resp == 0) {
						Conexion.open();
						Conexion.createDB("Operaciones");
						Conexion.addTable("Operacion");
						Conexion.addColumn("Operacion", "Proceso", "varchar(100)");
						Conexion.addColumn("Operacion", "Resultado", "varchar(100)");
						resp = JOptionPane.showConfirmDialog(null, "Importar datos de prueba?", null,JOptionPane.YES_NO_OPTION);
						if (resp == 0) Conexion.addScript("Ejemplos.sql");					
					} 
					
					
				}
				new Calculadora();
            }
        });
    }
    
}
