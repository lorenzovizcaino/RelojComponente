

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prueba {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Ventana Creada");
        JButton jBStart, jBStop;
        jBStart=new JButton("Start");
        jBStop=new JButton("Stop");
        Reloj miReloj;

        
        JPanel panelInf=new JPanel();
        panelInf.add(jBStart);
        panelInf.add(jBStop);
        
       

        
        try {
            ObjectInputStream flujoEntrada=new ObjectInputStream(new FileInputStream("reloj.obj"));
            String str=(String)flujoEntrada.readObject();
            System.out.println(str);

            miReloj=(Reloj)flujoEntrada.readObject();  
            //Inicializa con un valor por defecto, el cual se debe modificar a través de la interfaz
            miReloj.setModo24(false); 
            //Inicializa con una hora para probar. Este valor se debe
            //modificar por el usuario a través de la interfaz
            miReloj.setHora(21);
            miReloj.setMin(16);

            miReloj.modo24H(); 
            
           //Añadir los botones en la ventana
            jBStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                miReloj.arrancarReloj();; }
            });

            jBStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    miReloj.pararReloj();; }
                });
            System.out.println("Características del "+miReloj.toString());

            miReloj.toString();
            ventana.add(miReloj,BorderLayout.CENTER);
            ventana.add(panelInf,BorderLayout.SOUTH);
            flujoEntrada.close();
        } catch (Exception e) {
            // TODO: handle exception

            System.out.println("Este es mi primer error"+ e.getMessage());
        }
        
		
       
       
       


        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// finaliza el programa cuando se da click en la X
        ventana.setSize(290, 300);// configurando tamaño de la ventana
        ventana.setVisible(true);// configurando visualización de la ventana
        

    }



}
