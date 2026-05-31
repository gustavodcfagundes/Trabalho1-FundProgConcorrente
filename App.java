import java.util.logging.Logger;

public class App{

    private static final Logger logger = Logger.getLogger(App.class.getName());

    private final Escorredor escorredor;
    private final PratosSujosFactory factory;

    private final Lavador lavador;
    private final Enxugador enxugador;

    private final Thread threadLavador;
    private final Thread threadEnxugador;

    public App(){
        escorredor = new Escorredor(10);
        factory = new PratosSujosFactory();

        lavador = new Lavador(escorredor, factory);
        enxugador = new Enxugador(escorredor);

        threadLavador = new Thread(lavador,"Thread-Lavador");
        threadEnxugador = new Thread(enxugador,"Thread-Enxugador");
    }

    public void work(){
        threadLavador.start();
        threadEnxugador.start();
    }

    public void stop(){
        lavador.stop();
        enxugador.stop();

        threadLavador.interrupt();
        threadEnxugador.interrupt();
        
        try{
            threadLavador.join();
            threadEnxugador.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        logger.info("Aplicação encerrada");
    }

    public static void main(String[] args){
        App app = new App();
        app.work();
        try{
            Thread.sleep(120000); 
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        app.stop();
    }
}