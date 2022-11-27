/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author Usuario
 */
public class Calculadora {

	//Variable que guarda la operacion que estamos haciendo
	private String op;
	//Variable qque guarda la primera y segunda cadena de numros de la operacion y su resultado
	private String nums,num1,num2, resultado;
	//Variable que mira si un numero tiene punto
	private boolean punto = true;
	// Aqui asignaremos nuestro formato para el resultado y no quede tan largo
	DecimalFormat df;

	// Variable de la vista de la calculadora
	Ventana ventana;

	public Calculadora() {	
		/* 
		 * Iniciando el valpor de ls variables para no tener problemas con el nullPointerException
		 * Y el correcto fiuncionamiento de la calculadora
		 */
		
		nums="0";
		punto=false;
		num1="";
		resultado="";
		op="";
		//Instanciamos nuestra ventana
		ventana = new Ventana();
		
		// Esteb sera el formato de nuestro resultado
		df=new DecimalFormat("0.00000");
		
		// Asignando el metodo de añadir numeros a cada botón i el punto
		ventana.btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("0");
			}
		});
		ventana.btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("1");
			}
		});
		ventana.btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("2");
			}
		});
		ventana.btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("3");
			}
		});
		ventana.btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("4");
			}
		});        
		ventana.btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("5");
			}
		});
		ventana.btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {      		
				addNumeros("6");
			}
		});

		ventana.btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("7");
			}
		});

		ventana.btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("8");
			}
		});
		ventana.btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros("9");
			}
		});
		
		ventana.btnPunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumeros(".");
				
			}
		});
		//Resetear todos los datos que tenemos actualmente
		ventana.btnCE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.lblNumeros.setText("0");
				nums="0";
				punto=false;
				num1="";
				resultado="";
				
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
		ventana.btnRaiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				calcular("/");				
			}
		});
		ventana.btnMultiplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				calcular("x");				
			}
		});

		// Evento btn igual
		
		ventana.btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
							
			}
		});
	}


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
			}			
			
		}
		this.punto=false;
		
		
	}

	// Metodo que guarda o procesa la operación que estamos haciendo y guarda las operaciones en el historial	
	public void calcular(String op) {
		if(!ventana.lblNumeros.getText().equals("0") && this.op.equals("")) {
			num1=ventana.lblNumeros.getText();
			this.op=op;
			ventana.lblHistorial.setText(num1 + op);
			ventana.lblNumeros.setText("0");
		}else {
			num2=ventana.lblNumeros.getText();
			// Guardamos en el historial la operacion final
			ventana.lblHistorial.setText(this.num1+this.op+this.num2 + "=" + operacion(num1,this.op,num2).toPlainString() );	
			//Assignamos el resultado a nuestro calculo actual
			ventana.lblNumeros.setText("0");
			//Asignamos el total al primer numero para seguir haciendo calculos
			num1=operacion(num1,this.op,num2).toPlainString();
			this.op=op;
		}
		
	}
	
	// Se encargara de hacer la operacion de los dos numeros 
	public BigDecimal operacion(String op1,String sig,String op2) {
		BigDecimal total=new BigDecimal(0);
		BigDecimal dop1 =new BigDecimal(op1);
		BigDecimal dop2 =new BigDecimal(op2);
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
		
		return new BigDecimal(0);

	}

}
