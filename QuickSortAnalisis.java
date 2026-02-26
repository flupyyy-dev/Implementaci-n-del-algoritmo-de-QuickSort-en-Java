import java.util.Random;

public class QuickSortAnalisis {

    static class Resultado {
        long tiempo;
        long comparaciones;

        Resultado(long tiempo, long comparaciones) {
            this.tiempo = tiempo;
            this.comparaciones = comparaciones;
        }
    }

    static long comparaciones = 0;

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparaciones++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Generadores de escenarios
    public static int[] generarAleatorio(int tamaño) {
        Random rand = new Random();
        int[] arr = new int[tamaño];
        for (int i = 0; i < tamaño; i++)
            arr[i] = rand.nextInt(100000);
        return arr;
    }

    public static int[] generarOrdenado(int tamaño) {
        int[] arr = new int[tamaño];
        for (int i = 0; i < tamaño; i++)
            arr[i] = i;
        return arr;
    }

    public static int[] generarInverso(int tamaño) {
        int[] arr = new int[tamaño];
        for (int i = 0; i < tamaño; i++)
            arr[i] = tamaño - i;
        return arr;
    }

    public static Resultado ejecutar(int tamaño, String tipo) {

        int[] arr;

        switch (tipo) {
            case "ORDENADO":
                arr = generarOrdenado(tamaño);
                break;
            case "INVERSO":
                arr = generarInverso(tamaño);
                break;
            default:
                arr = generarAleatorio(tamaño);
        }

        comparaciones = 0;

        long inicio = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        long fin = System.nanoTime();

        return new Resultado(fin - inicio, comparaciones);
    }

    public static void main(String[] args) {

        int[] tamaños = {1000, 2000, 5000, 10000};

        System.out.println("Escenario,Tamaño,Tiempo(ns),Comparaciones");

        for (int tamaño : tamaños) {

            Resultado aleatorio = ejecutar(tamaño, "ALEATORIO");
            System.out.println("ALEATORIO," + tamaño + "," +
                    aleatorio.tiempo + "," +
                    aleatorio.comparaciones);

            Resultado ordenado = ejecutar(tamaño, "ORDENADO");
            System.out.println("ORDENADO," + tamaño + "," +
                    ordenado.tiempo + "," +
                    ordenado.comparaciones);

            Resultado inverso = ejecutar(tamaño, "INVERSO");
            System.out.println("INVERSO," + tamaño + "," +
                    inverso.tiempo + "," +
                    inverso.comparaciones);
        }
    }
}