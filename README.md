import java.util.Scanner;

public class GeneradorPOO {

    public static void main(String[] args) {

        // Scanner para leer datos desde la consola
        Scanner sc = new Scanner(System.in);

        // Pedimos el nombre de la clase
        System.out.print("Ingrese el nombre de la clase: ");
        String nombreClase = sc.nextLine();

        // Pedimos la cantidad de atributos que tendrá la clase
        System.out.print("¿Cuántos atributos tendrá la clase?: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        // Arreglos para guardar tipos y nombres de los atributos
        String[] tipos = new String[cantidad];
        String[] nombres = new String[cantidad];

        // Ciclo para capturar cada atributo
        for (int i = 0; i < cantidad; i++) {

            System.out.println("\nAtributo #" + (i + 1));

            // Guardamos el tipo del atributo (int, String, double, etc.)
            System.out.print("Tipo del atributo: ");
            tipos[i] = sc.nextLine();

            // Guardamos el nombre del atributo
            System.out.print("Nombre del atributo: ");
            nombres[i] = sc.nextLine();
        }

        // Llamamos al método que generará el código de la clase
        generarClase(nombreClase, tipos, nombres);

        sc.close();
    }

    // Método encargado de construir dinámicamente el código de la clase
    public static void generarClase(String clase, String[] tipos, String[] nombres) {

        System.out.println("\n\n===== CÓDIGO GENERADO =====\n");

        // Declaración de la clase
        System.out.println("public class " + clase + " {");

        // =========================
        // 1. GENERAR ATRIBUTOS
        // =========================
        System.out.println("\n    // Atributos privados");
        for (int i = 0; i < tipos.length; i++) {
            System.out.println("    private " + tipos[i] + " " + nombres[i] + ";");
        }

        // =========================
        // 2. GENERAR CONSTRUCTOR
        // =========================
        System.out.println("\n    // Constructor");
        System.out.print("    public " + clase + "(");

        // Parámetros del constructor
        for (int i = 0; i < tipos.length; i++) {
            System.out.print(tipos[i] + " " + nombres[i]);
            if (i < tipos.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(") {");

        // Inicialización de atributos dentro del constructor
        for (int i = 0; i < nombres.length; i++) {
            System.out.println("        this." + nombres[i] + " = " + nombres[i] + ";");
        }

        System.out.println("    }");

        // =========================
        // 3. GENERAR GETTERS Y SETTERS
        // =========================
        System.out.println("\n    // Métodos Getter y Setter");

        for (int i = 0; i < tipos.length; i++) {

            // Getter
            System.out.println("\n    public " + tipos[i] + " get" 
                    + capitalizar(nombres[i]) + "() {");
            System.out.println("        return " + nombres[i] + ";");
            System.out.println("    }");

            // Setter
            System.out.println("\n    public void set" 
                    + capitalizar(nombres[i]) + "(" + tipos[i] + " " + nombres[i] + ") {");
            System.out.println("        this." + nombres[i] + " = " + nombres[i] + ";");
            System.out.println("    }");
        }

        // =========================
        // 4. CERRAR CLASE
        // =========================
        System.out.println("}");
    }

    // Método auxiliar que convierte la primera letra en mayúscula
    // Sirve para que los getters y setters sigan la convención Java
    public static String capitalizar(String texto) {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}