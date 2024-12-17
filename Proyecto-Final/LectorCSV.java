import java.io.*;
import java.util.*;
//carga datos desde el excel
public class LectorCSV {
    public static List<Libro> leerLibrosDesdeArchivo(String nombreArchivo) {
        List<Libro> libros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            boolean esPrimeraLinea = true; // Variable para identificar la primera línea

            while ((linea = br.readLine()) != null) {
                if (esPrimeraLinea) { 
                    esPrimeraLinea = false; // Saltar la primera línea
                    continue; 
                }

                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    try {
                        String titulo = datos[0].trim();
                        String autor = datos[1].trim();
                        String genero = datos[2].trim();
                        int anio = Integer.parseInt(datos[3].trim());
                        libros.add(new Libro(titulo, autor, genero, anio));
                    } catch (NumberFormatException e) {
                        System.out.println("Error en el formato del año: " + datos[3].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return libros;
    }
}
