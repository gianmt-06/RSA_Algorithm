import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Pseudoaleatorios {

    public void pseudoaleatorios() throws IOException {
        LinkedList NumList = new LinkedList();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese los valores iniciales: \na:");
        int a = Integer.parseInt(br.readLine());
        System.out.print("b: ");
        int b = Integer.parseInt(br.readLine());
        System.out.print("m: ");
        int m = Integer.parseInt(br.readLine());

        int x = 0;
        int i = 0;

        int temporal = 1;

        while (temporal != 0) {
            temporal = (a * x + b) % m;
            NumList.add(temporal);
            x = temporal;
            i++;
        }

        System.out.println(NumList);
        System.out.println("\nCantidad total: " + i);
    }
}
