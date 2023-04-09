public class Euclides {
    public int euclides(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return euclides(b, a % b);
        }
    }

    public int[] euclidesExtendido(int a, int b) {
        if (b == 0) {
            int[] resultado = {a, 1, 0};
            return resultado;
        } else {
            int[] resultadoAnterior = euclidesExtendido(b, a % b);
            int[] resultadoActual = {resultadoAnterior[0], resultadoAnterior[2], resultadoAnterior[1] - (a / b) * resultadoAnterior[2]};
            return resultadoActual;
        }
    }
}
