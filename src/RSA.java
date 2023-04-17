import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class RSA {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void init() throws IOException {
        while(true){
            System.out.println("\nRSA");
            System.out.print("Ingrese la operación que desea realizar: \n1)Generar claves \n2)Encriptar mensaje \n3)Desencriptar mensaje \nOpción: ");
            int option = Integer.parseInt(br.readLine());
            System.out.println();

            switch (option){
                case 1:
                    generateKeys();
                    break;
                case 2:
                    int[] publicKey = new int[2];
                    System.out.println("Digite la clave pública compartida por el destinatario: ");
                    System.out.print("n: ");
                    publicKey[0] = Integer.parseInt(br.readLine());
                    System.out.print("e: ");
                    publicKey[1] = Integer.parseInt(br.readLine());
                    encrypt(publicKey);
                    break;
                case 3:
                    int[] privateKey = new int[2];
                    System.out.println("Digite su clave privada: ");
                    System.out.print("n: ");
                    privateKey[0] = Integer.parseInt(br.readLine());
                    System.out.print("d: ");
                    privateKey[1] = Integer.parseInt(br.readLine());
                    decrypt(privateKey);
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            System.out.println("\nPresione [Q] para salir o cualquier otra tecla para continuar");
            String exit = br.readLine();

            if (exit.equals("Q") || exit.equals("q")){
                break;
            }
        }
    }

    public void generateKeys(){
        int[] primes = new int[2];

        try {
            System.out.print("Ingrese el primer número primo: ");
            primes[0] = Integer.parseInt(br.readLine());
            while (!isPrime(primes[0])) {
                System.out.print("El número ingresado no es primo. Ingrese de nuevo el número: ");
                primes[0] = Integer.parseInt(br.readLine());
            }

            System.out.print("Ingrese el segundo número primo: ");
            primes[1] = Integer.parseInt(br.readLine());
            while (!isPrime(primes[1])) {
                System.out.print("El número ingresado no es primo. Ingrese de nuevo el número: ");
                primes[1] = Integer.parseInt(br.readLine());
            }

            int product = primes[0] * primes[1];
            int eulerFunction = (primes[0] - 1) * (primes[1] - 1);

            System.out.print("Ingrese el valor de e para la llave pública (e): ");
            int e = Integer.parseInt(br.readLine());

            while (!validValue(e, eulerFunction)) {
                System.out.print("Ingrese nuevamente (e): ");
                e = Integer.parseInt(br.readLine());
            }

            int[] publicKey = {product, e};
            System.out.println("\nLLAVE PÚBLICA (n,e): (" + publicKey[0] + "," + publicKey[1] + ")");

            int[] privateKey = {product, inverse(e, eulerFunction)};
            System.out.println("LLAVE PRIVADA (n,d): (" + privateKey[0] + "," + privateKey[1] + ")");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validValue(int e, int phi) {
        Euclides euclides = new Euclides();

        if (e <= 1 || e >= phi) {
            System.out.println("Numero fuera del rango válido.");
            System.out.println("El valor debe ser un numero mayor a 1 y menor que " + phi + " (phi)");
            return false;
        }

        if (euclides.euclides(phi, e) != 1) {
            System.out.println("MCD(" + phi + ", " + e + "): " + euclides.euclides(phi, e) + ", numeros no coprimos, " +
                    "escoja un valor válido para e");
            return false;
        }

        return true;
    }

    public int inverse(int num, int mod) {
        Euclides euclides = new Euclides();
        int[] coefBenzout = euclides.euclidesExtendido(num, mod);
        if (coefBenzout[1] < 0) {
            return coefBenzout[1] + mod;
        }
        return coefBenzout[1];
    }

    public String encrypt(int[] publicKey) {
        String encryptedMessage = "";
        try {
            System.out.println("Ingrese el mensaje que desea encriptar: ");
            String message = br.readLine();

            BigInteger resultado;
            BigInteger modulo = new BigInteger(String.valueOf(publicKey[0]));

            for (int i = 0; i < message.length(); i++) {
                resultado = (BigInteger.valueOf((int) message.charAt(i)).pow(publicKey[1])).mod(modulo);
                encryptedMessage = encryptedMessage + resultado + " ";
            }

            System.out.println(encryptedMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return encryptedMessage;
    }

    public void decrypt(int[] privateKey){
        try {
            String[] message;
            String messageASCII = "";
            String decryptedMessage = "";

            System.out.print("MENSAJE ENCRIPTADO: ");
            String encryptedMessage = br.readLine();
            message = encryptedMessage.split("\\s+");

            BigInteger numero;
            BigInteger resultado;
            BigInteger modulo = new BigInteger(String.valueOf(privateKey[0]));

            for (int i = 0; i < message.length; i++){
                numero = new BigInteger(message[i]);
                resultado = fastExponentiation(numero, privateKey[1], modulo);
                decryptedMessage = decryptedMessage + resultado.mod(modulo) + " ";
                messageASCII = messageASCII + Character.toString(((char) Integer.parseInt(String.valueOf(resultado.mod(modulo)))));
            }

            System.out.println("MENSAJE DESENCRIPTADO: " + messageASCII);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigInteger fastExponentiation(BigInteger base, int exponent, BigInteger modulo) {
        BigInteger result = BigInteger.ONE;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = result.multiply(base).mod(modulo);
            }
            base = base.multiply(base).mod(modulo);
            exponent /= 2;
        }
        return result;
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
