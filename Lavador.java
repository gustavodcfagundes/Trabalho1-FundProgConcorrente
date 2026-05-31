import java.util.logging.Logger;

public class Lavador implements Runnable{

    private static final Logger logger = Logger.getLogger(Lavador.class.getName());

    private final Escorredor escorredor;
    private final PratosSujosFactory factory;

    private volatile boolean done = false;

    public Lavador(Escorredor escorredor, PratosSujosFactory factory){
        this.escorredor = escorredor;
        this.factory = factory;
    }

    public void stop(){
        done = true;
    }

    @Override
    public void run(){
        while (!done){
            try{
                Prato prato = factory.obterPratoSujo();

                switch(prato.getNivelSujeira()){
                    case BAIXO:
                        //Thread.sleep(3); 
                        break;
                    case MEDIO:
                        //Thread.sleep(5); 
                        break;
                    case ENGORDURADO:
                        //Thread.sleep(10);
                        break;
                }
                escorredor.colocar(prato);
                logger.info("Lavador colocou " + prato);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
        logger.info("Lavador encerrado");
    }
}