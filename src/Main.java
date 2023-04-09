import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        RSA encryption = new RSA();
        encryption.init();

        //BigInteger numero = new BigInteger("3967");
        //BigInteger resultado = numero.pow(5623);
        //BigInteger modulo = new BigInteger("8051");
        //System.out.println(resultado.mod(modulo));
        //System.out.println(Math.pow(76, 7) % 8051);
        Pseudoaleatorios aleatorios = new Pseudoaleatorios();
        //aleatorios.pseudoaleatorios();
        /*
        Euclides euclides = new Euclides();
        int[] euclidesarr = euclides.euclidesExtendido(79, 51);
        System.out.println(euclidesarr[0]+ " " + euclidesarr[1]+ " " + euclidesarr[2]);

         */
    }
}