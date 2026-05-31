import java.util.Random;
import java.util.logging.Logger;

public class Enxugador implements Runnable{

    private static final Logger logger = Logger.getLogger(Enxugador.class.getName());

    private final Escorredor escorredor;
    private final Random random = new Random();

    private volatile boolean done = false;

    public Enxugador(Escorredor escorredor){
        this.escorredor = escorredor;
    }

    public void stop(){
        done = true;
    }

    @Override
    public void run(){
        while(!done){
            try{
                Prato prato = escorredor.retirar();
                logger.fine("Enxugador começou a secar: " + prato);

                int tempo = random.nextInt(8) + 3; 
                //Thread.sleep(tempo);

                logger.info("Enxugador secou " + prato);

            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}