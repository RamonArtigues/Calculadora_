/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Calculadora {

	//Variable que guarda la operación que estamos haciendo
	private String op;
	//Variable qque guarda la primera y segunda cadena de numros de la operacion y el resultado anterior
	private String nums,num1,num2,numAnt;
	//Variable que mira si un numero tiene punto
	private boolean punto = false;
	// Aqui asignaremos nuestro formato para el resultado y no quede tan largo
	DecimalFormat df;

	// Variable de la vista de la calculadora
	Ventana ventana;

	public Calculadora() {	
		/* 
		 * Iniciando el valpor de ls variables para no tener problemas con el nullPointerException
		 * Y el correcto fiuncionamiento de la calculadora
		 */

		nums="";
		punto=false;
		num1="";
		num2=" ";
		op="";
		numAnt="";
		//Instanciamos nuestra ventana
		ventana = new Ventana();
		// Asignando el metodo de añadir numeros a cada botón i el punto
		ventana.btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("0");
			}});
		ventana.btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("1");
			}});
		ventana.btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("2");
			}});
		ventana.btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("3");
			}});
		ventana.btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("4");
			}});        
		ventana.btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("5");
			}});
		ventana.btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {      		
				addNumeros("6");
			}});
		ventana.btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("7");
			}});
		ventana.btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("8");
			}});
		ventana.btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("9");
			}});
		ventana.btnPunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existePunto(ventana.lblNumeros.getText());
				if(punto==false) addNumeros(".");
				
			}});
		//Resetear todos los datos que tenemos actualmente
		ventana.btnCE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.lblNumeros.setText("0");
				ventana.lblHistorial.setText("");
				nums="0";
				punto=false;
				num1="";
				num2=" ";
				op="";
			}
		});
		
		ventana.btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.lblHistorial.setText(numAnt);
				ventana.lblNumeros.setText("0");
				nums=num1;
				punto=false;

			}
		});
		
		
		//Eventos de las operaciones del boton
		ventana.btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				calcular("+");				
			}
		});
		ventana.btnRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				calcular("-");				
			}
		});
		ventana.btnDividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				calcular("/");				
			}
		});
		ventana.btnMultiplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				calcular("x");				
			}});
		ventana.btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				calcular(op);
				ventana.lblHistorial.setText(num1);	
			}});
		ventana.btnSigno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(ventana.lblNumeros.getText().charAt(0)!= '-') {
					// Añadimos el "-" al num1 o primer numero de la operacion
					ventana.lblNumeros.setText("-" + ventana.lblNumeros.getText());

				}else {

					ventana.lblNumeros.setText(ventana.lblNumeros.getText().substring(1));

				}

			}
		});		
		ventana.btnRaiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				calcular("√");
			}
		});}


	// Metodo que añade los numeros a nuestra cadena de numeros
	public void addNumeros(String n) {
		if(ventana.lblNumeros.getText().equals("0") && !n.equals(".")) {
			this.nums=n;

		}else {
			this.nums+=n;
		}
		ventana.lblNumeros.setText(this.nums);
	}

	// Miramos si existe algun punto en la operación	
	public void existePunto(String cadena) {
		for(int i=0;i<cadena.length();i++) {
			if(cadena.charAt(i) == '.') {
				this.punto=true;
				break;
			}else {
				this.punto=false;
			}			
		}
	}
	// Metodo que guarda o procesa la operación que estamos haciendo y guarda las operaciones en el historial	
	public void calcular(String op) {
		
		// En caso de que haya error resetea todas las variables y muestra un mensaje con "Error en la operación"
		try {
			//Evalua si en el label noay solo un numero 0 y si no hay una operación asignada 
			if(!ventana.lblNumeros.getText().equals("0") && this.op.equals("")) {
				num1=ventana.lblNumeros.getText();
				this.op=op;
				ventana.lblHistorial.setText(num1 + op);
				ventana.lblNumeros.setText("0");
			//Evalua si el 2 numero existe para hacer la operación o si la operación es una raiza ya que no necesita 2 numeros
			}else if(!num1.equals("")) {
				//Guardando el resultado anterior
				numAnt=num1;
				//Cogiendo el segundo jnumero que usamos para la operación
				num2=ventana.lblNumeros.getText();
				// Guardamos en el historial la operacion final
				ventana.lblHistorial.setText(this.num1+this.op+this.num2 + "=" + operacion(num1,this.op,num2).toPlainString());	
				//Reseteamos etiqueta de numeros
				ventana.lblNumeros.setText("0");
				//Asignamos el total al primer numero para seguir haciendo calculos
				num1=operacion(num1,this.op,num2).toPlainString();
				
				
			}
		} catch (Exception e) {
			// Clicka en el boton CE que borra todas las variables
			ventana.btnCE.doClick();
			//Muestra el error de operación
			JOptionPane.showMessageDialog(ventana, "Error en la operación");
			
			//Imprime el error por consola
			e.printStackTrace();
			
		}
	}
	// Se encargara de hacer la operacion de los dos numeros 
	public BigDecimal operacion(String op1,String sig,String op2) {
		BigDecimal total=new BigDecimal(0);
		BigDecimal dop1 =new BigDecimal(op1);
		BigDecimal dop2 = new BigDecimal(op2);
		
		
		
		if(sig.equals("+")) {
			total=dop1.add(dop2);

			return total;

		}
		if(sig.equals("-")) {
			total=dop1.subtract(dop2);

			return total;

		}
		if(sig.equals("/")) {
			total=dop1.divide(dop2);

			return total;

		}
		if(sig.equals("x")) {
			total=dop1.multiply(dop2); 

			return total;

		}

		if(sig.equals("√")) {
			total=dop1.sqrt(new MathContext(10));			
			return total;
		}
		return new BigDecimal(0);

	}

}
