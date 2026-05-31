import java.util.Random;

public class Prato{
    
    public enum NivelSujeira{
        BAIXO, MEDIO, ENGORDURADO
    }

    private static int contador = 0; 

    private int numeroSerie;
    private NivelSujeira nivelSujeira;

    public Prato(){
        this.numeroSerie = ++contador;
        this.nivelSujeira = gerarSujeiraAleatoria();
    }

    private NivelSujeira gerarSujeiraAleatoria(){
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 30){
            return NivelSujeira.BAIXO;
        }else if (chance < 90){
            return NivelSujeira.MEDIO;
        }else{
            return NivelSujeira.ENGORDURADO;
        }
    }

    public int getNumeroSerie(){
        return numeroSerie;
    }

    public NivelSujeira getNivelSujeira(){
        return nivelSujeira;
    }

    public String toString(){
        return "Prato " + numeroSerie + " (" + nivelSujeira + ")";
    }
}