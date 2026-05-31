import java.util.logging.Logger;

public class Escorredor {

    private static final Logger logger = Logger.getLogger(Escorredor.class.getName());

    private final Prato[] buffer;
    private final int MAX;

    private int inicio = 0;
    private int fim = 0;
    private int totalPratos = 0;

    public Escorredor(int capacidade){
        this.MAX = capacidade;
        this.buffer = new Prato[capacidade];
    }

    public synchronized void colocar(Prato prato) throws InterruptedException{
        while (totalPratos == MAX){
            logger.fine("Escorredor cheio. Aguardando espaço");
            wait();
        }
        
        buffer[fim] = prato;
        fim = (fim + 1) % MAX;
        totalPratos++;

        validarLimites();

        if (totalPratos == MAX){
            logger.info("Escorredor cheio. Quantidade: " + totalPratos);
        }
        
        notifyAll();
    }

    public synchronized Prato retirar() throws InterruptedException{
        while (totalPratos == 0){
            logger.fine("Escorredor vazio. Aguardando pratos");
            wait();
        }

        Prato prato = buffer[inicio];
        buffer[inicio] = null;
        inicio = (inicio + 1) % MAX;
        totalPratos--;

        validarLimites(); 

        if (totalPratos == 0){
            logger.info("Escorredor vazio. Quantidade: " + totalPratos);
        }
        
        notifyAll();
        
        return prato;
    }
 
    private void validarLimites(){
        if(totalPratos < 0 || totalPratos > MAX){
            logger.severe("Limites do escorredor foram estrapolados. Quantidade: " + totalPratos);
            System.exit(1); 
        }
    }

    public int getTotalPratos(){
        return totalPratos;
    }

    public int getCapacidade(){
        return MAX;
    }
}