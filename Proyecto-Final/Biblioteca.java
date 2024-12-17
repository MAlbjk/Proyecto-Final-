import javax.swing.*;
import java.util.List;

public class Biblioteca {
    private ArbolBinario arbol; //para almacenar los libros
    private GrafoLibros grafoLibros; // grafo para manejar las relaciones entre libros

    public Biblioteca() {
        arbol = new ArbolBinario(); // Inicializamos el árbol binario
        grafoLibros = new GrafoLibros(); // Inicializamos el grafo
    }

    public void iniciar(String archivoCSV) {
        cargarLibrosAlArbolDesdeCSV(archivoCSV); // Cargar libros al iniciar

        int opcion=0;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "=== Sistema de Gestión de Biblioteca ===\n" +
                                "1. Añadir Libro\n" +
                                "2. Eliminar Libro\n" +
                                "3. Listar Libros en el Árbol\n" +
                                "4. Buscar Libro\n" +
                                "5. Mostrar Libros del Archivo CSV\n" +
                                "6. Mostrar Recomendaciones por Género\n" +
                                "7. Salir\n" +
                                "Seleccione una opción:"
                ));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> eliminarLibro();
                case 3 -> mostrarLibrosArbol();
                case 4 -> buscarLibro();
                case 5 -> mostrarLibrosCSV(archivoCSV);
                case 6 -> mostrarRecomendaciones();
                case 7 -> JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private void agregarLibro() {
        // Solicitar datos del libro al usuario
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:").trim();
        String autor = JOptionPane.showInputDialog("Ingrese el autor del libro:");
        String genero = JOptionPane.showInputDialog("Ingrese el género del libro:");
        int anioPublicacion = 0;

        while (true) {
            try {
                anioPublicacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de publicación:"));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: El año debe ser un número entero válido.");
            }
        }

        Libro nuevoLibro = new Libro(titulo, autor, genero, anioPublicacion);

        // Verificar si el libro ya existe
        if (arbol.buscarTitulo(titulo) != null) {
            JOptionPane.showMessageDialog(null, "El libro ya existe en la biblioteca.");
            return;
        }

        // Agregar el libro al árbol y al grafo
        arbol.insertar(nuevoLibro);
        grafoLibros.agregarLibro(nuevoLibro);

        // Generar relaciones automáticas en el grafo
        grafoLibros.generarRelacionesPorGenero();
        JOptionPane.showMessageDialog(null, "Libro agregado y relaciones generadas.");
    }

    private void eliminarLibro() {
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro a eliminar:").trim();

        // Eliminar del árbol y del grafo
        if (arbol.eliminar(titulo)) {
            grafoLibros.eliminarNodo(titulo);
            JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el libro.");
        }
    }

    private void mostrarLibrosArbol() {
        List<Libro> libros = arbol.obtenerLibrosEnOrden();
        if (libros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay libros registrados en el árbol.");
        } else {
            StringBuilder sb = new StringBuilder("Libros en el Árbol:\n");
            for (Libro libro : libros) {
                sb.append(libro).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private void buscarLibro() {
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro a buscar:").trim();
        Libro libro = arbol.buscarTitulo(titulo);

        if (libro != null) {
            JOptionPane.showMessageDialog(null, "Libro encontrado:\n" + libro);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el libro.");
        }
    }

    private void cargarLibrosAlArbolDesdeCSV(String archivo) {
        List<Libro> libros = LectorCSV.leerLibrosDesdeArchivo(archivo);
        for (Libro libro : libros) {
            arbol.insertar(libro);
            grafoLibros.agregarLibro(libro);
        }
        // Generar relaciones entre los libros cargados
        grafoLibros.generarRelacionesPorGenero();
        JOptionPane.showMessageDialog(null, "Libros del archivo CSV cargados y relaciones generadas.");
    }

    private void mostrarLibrosCSV(String archivo) {
        List<Libro> libros = LectorCSV.leerLibrosDesdeArchivo(archivo);
        if (libros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron libros en el archivo CSV.");
        } else {
            StringBuilder sb = new StringBuilder("Libros en el Archivo CSV:\n");
            for (Libro libro : libros) {
                sb.append(libro).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
    
    private void mostrarRecomendaciones() {
    String titulo = JOptionPane.showInputDialog("Ingrese el título del libro para ver recomendaciones:");

    // Buscar el libro en el árbol binario
    Libro libro = arbol.buscarTitulo(titulo);
    if (libro == null) {
        JOptionPane.showMessageDialog(null, "El libro no existe en la biblioteca.");
        return;
    }

    // Instanciar la clase Recomendaciones con el archivo CSV
    Recomendaciones recomendacionesHelper = new Recomendaciones("Libros.csv");

    // Obtener las recomendaciones de la lista precargada
    StringBuilder resultado = new StringBuilder("Recomendaciones para '" + libro.getTitulo() + "':\n");

    boolean tieneRecomendaciones = false;

    for (String libroRecomendado : recomendacionesHelper.getLibrosRecomendados()) {
        if (!libroRecomendado.equalsIgnoreCase(libro.getTitulo())) {
            resultado.append("- ").append(libroRecomendado).append("\n");
            tieneRecomendaciones = true;
        }
    }

    if (!tieneRecomendaciones) {
        resultado.append("No se encontraron recomendaciones basadas en género.");
    }

    JOptionPane.showMessageDialog(null, resultado.toString());
}

   
}
