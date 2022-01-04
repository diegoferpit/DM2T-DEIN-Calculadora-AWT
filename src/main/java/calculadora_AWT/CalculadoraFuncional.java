package calculadora_AWT;


import java.awt.*;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/**
 *
 * @author Diego
 */
class CalculadoraFuncional extends Frame implements WindowListener {

    // Pantalla y números
    private TextArea pantalla;
    private Button tecla;
    private int num1;
    private int num2;
    private String operador;
    private boolean actualizarPantalla = true;
    
    
    // Paneles
    Panel panelPantalla = new Panel(new GridLayout(1,4)); // Panel del textField
    Panel panelNumeros = new Panel(new GridLayout(4,3)); // Panel de numeros
    Panel panelOperaciones = new Panel(new GridLayout(4,1)); // Panel de operaciones
    
    
    // Establecemos el orden en el que queremos situar los botones dentro del array
    String numeros[] = new String[]{
        "7", "8", "9",
        "4", "5", "6",
        "1", "2", "3",
        "0", "C", "="
    };
    
    // Establecemos el orden en el que queremos situar los botones dentro del array
    String operaciones[] = new String[]{
        "/",
        "x",
        "-",
        "+"
    };
    

    Button[] botonesNumeros = new Button[12];
    Button[] botonesOperaciones = new Button[4];
    
    
    public CalculadoraFuncional() {
        // Establecemos la colocación de los botones, en función del marco de la ventana
        
        // Configuramos (Posicion fila,columna y ancho/altura) botones en el panel de los números
        GridBagConstraints gbcP = new GridBagConstraints();
        gbcP.gridx = 0;
        gbcP.gridy = 0;
        gbcP.gridwidth = 3;
        gbcP.gridheight = 1;
        //gbcP.fill = GridBagConstraints.HORIZONTAL;
        
        // Configuramos (Posicion fila,columna y ancho/altura) botones en el panel de los números
        GridBagConstraints gbcN = new GridBagConstraints();
        gbcN.gridx = 1;
        gbcN.gridy = 1;
        gbcN.gridwidth = 1;
        gbcN.gridheight = 1;
        gbcN.fill = GridBagConstraints.HORIZONTAL;
                
        // Configuramos (Posicion fila,columna y ancho/altura) botones en el panel de las operaciones
        GridBagConstraints gbcO = new GridBagConstraints();
        gbcO.gridx = 2;
        gbcO.gridy = 1;
        gbcO.gridwidth = 1;
        gbcO.gridheight = 1;
        
        
        
        // Display
        pantalla = new TextArea("", 1, 10, TextArea.SCROLLBARS_NONE);
        pantalla.setBackground(Color.black);
        pantalla.setForeground(Color.white);
        panelPantalla.setFont(new Font("Arial", 1, 18));
        panelPantalla.add(pantalla);
           
        // Panel de los números
        for(int i=0; i<numeros.length; i++){
            tecla = new Button(numeros[i]);
            tecla.addActionListener(new OperacionesActionListener());
            tecla.setBackground(Color.black);
            tecla.setForeground(Color.white);
            panelNumeros.add(tecla);          
        }   
        panelNumeros.setFont(new Font("Arial", 1, 32));
                
        // Panel de las operaciones aritméticas
        for(int i=0; i<operaciones.length; i++){
            tecla = new Button(operaciones[i]);
            tecla.addActionListener(new OperacionesActionListener());
            tecla.setBackground(Color.orange);
            panelOperaciones.add(tecla);
        }        
        panelOperaciones.setFont(new Font("Arial", 1, 32));
        
        
        // Añadimos los paneles creados anteriormente cuando se ejecute la app
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.LIGHT_GRAY);
        this.add(panelPantalla,gbcP);
        this.add(panelNumeros,gbcN);
        this.add(panelOperaciones,gbcO);
        this.setResizable(false);
        this.setSize(200, 275);
        this.setVisible(true);
        
        // Permitimos que se cierre el programa en el símbolo de X de windows en la ventana
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    
    
    class OperacionesActionListener implements ActionListener{     
        @Override
        public void actionPerformed(ActionEvent e) {
            String teclaPulsada = ((Button)e.getSource()).getLabel();
            //pantalla.setText(teclaPulsada);
            // Controlamos que al pulsar C, se reestablezca la calculadora
            if(teclaPulsada.equals("C")) {
                pantalla.setText("0");
                num1 = 0;
                num2 = 0; 
                actualizarPantalla = true; 
            } else if(teclaPulsada.equals("=")) {
                // Guardamos el segundo número hasta que no pulsemos el '=' lo dejamos en pantalla
                num2 = Integer.parseInt(pantalla.getText());
                if(operador != null) {
                    // Se realizan las operaciones correspondientes y se muestra el resultadao
                    if(operador.equals("+")){
                        pantalla.setText(Integer.toString(num1 + num2));
                    } else if (operador.equals("-")) { 
                        pantalla.setText(Integer.toString(num1 - num2));
                    } else if (operador.equals("x")) { 
                        pantalla.setText(Integer.toString(num1 * num2));
                    } else if (operador.equals("/")) { 
                        pantalla.setText(Integer.toString(num1 / num2));
                    }
                }
                actualizarPantalla = true; 
                operador = null;
                // Controlamos que operador ha pulsado el usuario
            } else if(teclaPulsada.equals("+") || teclaPulsada.equals("-") || teclaPulsada.equals("x") || teclaPulsada.equals("/")) {
                // Guardamos el primer número tras pulsar el operador y lo dejamos en pantalla
                operador = teclaPulsada; 
                num1 = Integer.parseInt(pantalla.getText());
                actualizarPantalla = true; 
            } else {
                // Mostramos por pantalla la tecla pulsada en el momento
                if(actualizarPantalla == true) { 
                    pantalla.setText(teclaPulsada); 
                    actualizarPantalla = false; 
                } else { 
                    pantalla.setText(pantalla.getText() + teclaPulsada);
                }   
            }  
        }
    }
    
    
    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
    public static void main (String [] args){
        CalculadoraFuncional app = new CalculadoraFuncional();
    }
}