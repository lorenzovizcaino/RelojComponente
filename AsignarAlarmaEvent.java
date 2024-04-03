import java.util.EventObject;
//Vamos a definir la clase que define un suceso para alarma
public class AsignarAlarmaEvent extends EventObject{
   private int hora;
   private int min;
   
    public AsignarAlarmaEvent(Object source, int hora, int min) {
        super(source);
        this.hora=hora;
        this.min=min;
        //TODO Auto-generated constructor stub
    }

    public String getMessage(){
        return ("La alarma suean a las "+ hora+":"+min);
    }

    public int getHora() {
        return hora;
    }
   
    public int getMin() {
        return min;
    }
    
    
}
