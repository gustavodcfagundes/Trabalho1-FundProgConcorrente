public class Teste {

    public static void main(String[] args)
            throws InterruptedException {

        Escorredor escorredor = new Escorredor(3);

        escorredor.colocar(new Prato());
        escorredor.colocar(new Prato());
        escorredor.colocar(new Prato());

        System.out.println(
                escorredor.retirar());

        escorredor.colocar(new Prato());

        System.out.println(
                escorredor.retirar());

        System.out.println(
                escorredor.retirar());

        System.out.println(
                escorredor.retirar());
    }
}