import java.util.Scanner;

public class GeneradorPOO {

    public static void main(String[] args) {

        // Scanner principal para interactuar con el usuario
        Scanner sc = new Scanner(System.in);

        // =========================
        // 1. DATOS BÁSICOS
        // =========================

        System.out.print("Ingrese el nombre de la clase: ");
        String nombreClase = sc.nextLine();

        System.out.print("¿Cuántos atributos tendrá?: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        String[] tipos = new String[cantidad];
        String[] nombres = new String[cantidad];

        // Capturamos los atributos
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nAtributo #" + (i + 1));

            System.out.print("Tipo: ");
            tipos[i] = sc.nextLine();

            System.out.print("Nombre: ");
            nombres[i] = sc.nextLine();
        }

        // =========================
        // 2. CONFIGURACIÓN AVANZADA
        // =========================

        System.out.print("\n¿Desea incluir Scanner en el código generado? (si/no): ");
        String usarScanner = sc.nextLine();

        System.out.println("\nTipo de programa:");
        System.out.println("1. Solo clase modelo");
        System.out.println("2. Clase con método main");
        System.out.println("3. Programa interactivo con Scanner");
        System.out.print("Seleccione opción (1-3): ");
        int tipoPrograma = sc.nextInt();

        // Generamos el código final
        generarCodigo(nombreClase, tipos, nombres, usarScanner, tipoPrograma);

        sc.close();
    }

    public static void generarCodigo(String clase, String[] tipos, String[] nombres,
                                      String usarScanner, int tipoPrograma) {

        System.out.println("\n\n===== CÓDIGO GENERADO =====\n");

        // Si el usuario quiere Scanner, lo importamos
        if (usarScanner.equalsIgnoreCase("si") || tipoPrograma == 3) {
            System.out.println("import java.util.Scanner;");
            System.out.println();
        }

        // Declaración de clase
        System.out.println("public class " + clase + " {");

        // =========================
        // ATRIBUTOS
        // =========================
        System.out.println("\n    // Atributos privados");
        for (int i = 0; i < tipos.length; i++) {
            System.out.println("    private " + tipos[i] + " " + nombres[i] + ";");
        }

        // =========================
        // CONSTRUCTOR
        // =========================
        System.out.print("\n    // Constructor\n");
        System.out.print("    public " + clase + "(");

        for (int i = 0; i < tipos.length; i++) {
            System.out.print(tipos[i] + " " + nombres[i]);
            if (i < tipos.length - 1) System.out.print(", ");
        }

        System.out.println(") {");

        for (int i = 0; i < nombres.length; i++) {
            System.out.println("        this." + nombres[i] + " = " + nombres[i] + ";");
        }

        System.out.println("    }");

        // =========================
        // GETTERS Y SETTERS
        // =========================
        System.out.println("\n    // Getters y Setters");

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
        // MÉTODO MAIN OPCIONAL
        // =========================
        if (tipoPrograma == 2 || tipoPrograma == 3) {

            System.out.println("\n    // Método principal");
            System.out.println("    public static void main(String[] args) {");

            if (tipoPrograma == 3) {
                System.out.println("        // Scanner para entrada de datos");
                System.out.println("        Scanner sc = new Scanner(System.in);");
            }

            System.out.println("\n        // Crear objeto de la clase");
            System.out.print("        " + clase + " obj = new " + clase + "(");

            for (int i = 0; i < tipos.length; i++) {
                System.out.print("/* valor */");
                if (i < tipos.length - 1) System.out.print(", ");
            }

            System.out.println(");");

            System.out.println("\n        // Aquí puedes usar el objeto");
            System.out.println("        System.out.println(obj);");

            System.out.println("    }");
        }

        System.out.println("}");
    }

    // Método auxiliar para capitalizar nombres (convención Java)
    public static String capitalizar(String texto) {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}