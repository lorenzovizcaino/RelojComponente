import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Reloj extends JLabel implements ActionListener,Serializable {

    private boolean modo24;
    private boolean activar_Alarma;
    private LocalTime time;
    private Timer timer;
    private TimerTask task;
    private String pattern; 
    private DateTimeFormatter timeFormatter;
    private int hora;
    private int min;
    private AsignarAlarmaEvent receptor;


    // Crear dos constructores
    public Reloj() {
        this.time = LocalTime.now();
        this.activar_Alarma = true;
        this.modo24=true;
        this.pattern="HH:mm:ss a";
        this.hora=0;
        this.min=0;
        // Dar formato a la etiqueta
        super.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY.brighter(), Color.GRAY, Color.BLUE.brighter(),
                Color.BLUE.brighter()));
        super.setBounds(10, 10, 50, 50);
        super.setOpaque(true);
        super.setBackground(Color.CYAN);
        super.setFont(new Font("Tahoma", Font.BOLD, 35));

    }

    public Reloj(boolean modo24, boolean activar_Alarma) {
        this.modo24 = modo24;
        this.pattern="HH:mm:ss a";
        this.activar_Alarma = activar_Alarma;
        this.time = LocalTime.now();
        
        // Dar formato a la etiqueta
        super.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY.brighter(), Color.GRAY, Color.BLUE.brighter(),
                Color.BLUE.brighter()));
        super.setBounds(10, 10, 50, 50);
        super.setOpaque(true);
        super.setBackground(Color.CYAN);

        super.setFont(new Font("Tahoma", Font.BOLD, 35));
    }

    public LocalTime getTime() {
        return time;
    }

    public DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }

    public void setModo24(boolean modo24) {
        this.modo24 = modo24;
    }

    public void setActivar_Alarma(boolean activar_Alarma) {
        this.activar_Alarma = activar_Alarma;
    }

    // Método para arrancar el reloj
    public boolean isModo24() {
        return modo24;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTimeFormatter(DateTimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }
    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public TimerTask getTask() {
        return task;
    }

    public void setTask(TimerTask task) {
        this.task = task;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public boolean isActivar_Alarma() {
        return activar_Alarma;
    }

    @Override
    public String toString() {

        return "Reloj [modo24=" + modo24 + ", activar_Alarma=" + activar_Alarma + "]";
    }

    public void arrancarReloj() {
        
        timer = new Timer();
        task = new TimerTask()
            {
             
            @Override
            public void run()
            {
                time = LocalTime.now();
                setText(time.format(timeFormatter).toString());

                if (hora==time.getHour()&&min==time.getMinute()){
                    //Crear un objeto de tipo event al que se le debe pasar tres datos, el objeto fuente que en este caso es el reloj
                    //la hora y los minutos en los que debe sonar la alarma.
                    receptor=new AsignarAlarmaEvent(this, hora, min);
                    System.out.println(receptor.getMessage());

                  
            }
                       
            }
            };
            timer.schedule(task,0, 1000);

    }

    public void pararReloj(){    
    
        task.cancel();           
    }

    public void modo24H() {
        if (modo24 == false)
            this.pattern="hh:mm:ss a";
        else
            this.pattern="HH:mm:ss a";
        timeFormatter = DateTimeFormatter.ofPattern(this.pattern);

    }

    public boolean metodoAlarma(){
      
        if (this.hora==time.getHour()&&this.min==time.getMinute()){
           
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Reloj reloj = new Reloj();
        
       
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("reloj.obj"));
            // Este mensaje lo almaceno para comprobar que funciona correctamente, no se
            // necesita
            salida.writeObject("Guardar este string y un objeto\n");
            salida.writeObject(reloj);
            salida.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


    public void addAsignarAlarmaListener(AsignarAlarmaListener receptor){
        this.receptor = (AsignarAlarmaEvent) receptor;
    }

    public void removeAsignarAlarmaListener (AsignarAlarmaListener receptor){
        this.receptor=null;
    }

}
