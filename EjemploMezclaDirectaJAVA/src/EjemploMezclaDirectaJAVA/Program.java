package EjemploMezclaDirectaJAVA;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Algoritmo de Mezcla Directa en Java");

        Scanner scanner = new Scanner(System.in);

        do {
            // Solicitar al usuario que ingrese los datos
            System.out.print("Ingrese los elementos separados por espacio: ");
            int[] datos = leerDatos();

            // Mostrar datos ingresados
            System.out.println("Datos ingresados:");
            mostrarDatos(datos);

            // Obtener la hora de inicio
            long startTime = System.currentTimeMillis();

            // Aplicar el algoritmo de mezcla directa
            mezclaDirecta(datos, 0, datos.length - 1);

            // Obtener la hora de finalización
            long endTime = System.currentTimeMillis();

            // Mostrar los datos ordenados
            System.out.println("Datos ordenados:");
            mostrarDatos(datos);

            // Mostrar tiempo de inicio, tiempo de finalización y tiempo total de ejecución en segundos
            System.out.println("\nTiempo de inicio: " + startTime);
            System.out.println("Tiempo de finalización: " + endTime);
            System.out.println("Tiempo total transcurrido: " + (endTime - startTime) / 1000.0 + " segundos");

            // Preguntar al usuario si desea agregar más números
            System.out.print("¿Desea ingresar más números? (s/n): ");
        } while (scanner.nextLine().equals("s"));
    }

    static int[] leerDatos() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] elementos = input.split(" ");
        int[] datos = new int[elementos.length];

        for (int i = 0; i < elementos.length; i++) {
            datos[i] = Integer.parseInt(elementos[i]);
        }

        return datos;
    }

    static void mostrarDatos(int[] datos) {
        for (int elemento : datos) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }

    static void mezclaDirecta(int[] datos, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;

            // Dividir la lista en dos mitades y ordenar cada mitad
            mezclaDirecta(datos, izquierda, medio);
            mezclaDirecta(datos, medio + 1, derecha);

            // Combinar las dos mitades ordenadas
            combinar(datos, izquierda, medio, derecha);
        }
    }

    static void combinar(int[] datos, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        // Crear arreglos temporales
        int[] izquierdaArray = new int[n1];
        int[] derechaArray = new int[n2];

        // Copiar datos a los arreglos temporales
        System.arraycopy(datos, izquierda, izquierdaArray, 0, n1);
        System.arraycopy(datos, medio + 1, derechaArray, 0, n2);

        // Fusionar los arreglos temporales
        int indiceIzquierda = 0, indiceDerecha = 0;
        int indiceActual = izquierda;

        while (indiceIzquierda < n1 && indiceDerecha < n2) {
            if (izquierdaArray[indiceIzquierda] <= derechaArray[indiceDerecha]) {
                datos[indiceActual] = izquierdaArray[indiceIzquierda];
                indiceIzquierda++;
            } else {
                datos[indiceActual] = derechaArray[indiceDerecha];
                indiceDerecha++;
            }
            indiceActual++;
        }

        // Copiar los elementos restantes de izquierdaArray, si los hay
        System.arraycopy(izquierdaArray, indiceIzquierda, datos, indiceActual, n1 - indiceIzquierda);

        // Copiar los elementos restantes de derechaArray, si los hay
        System.arraycopy(derechaArray, indiceDerecha, datos, indiceActual, n2 - indiceDerecha);
    }
}

