import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//genera ya lmacena las recomendaciones de los libros por su genero y otras caratersiticas
public class Recomendaciones {
    private List<String> librosRecomendados;

    public Recomendaciones(String archivoCSV) {
        librosRecomendados = new ArrayList<>();
        cargarRecomendacionesDesdeCSV(archivoCSV);
    }

    private void cargarRecomendacionesDesdeCSV(String archivoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (esPrimeraLinea) { 
                    esPrimeraLinea = false; // Saltar la cabecera
                    continue;
                }

                String[] datos = linea.split(","); // Suponiendo que las columnas están separadas por comas
                if (datos.length > 0) {
                    String titulo = datos[0].trim(); // El título está en la primera columna
                    librosRecomendados.add(titulo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public List<String> getLibrosRecomendados() {
        return librosRecomendados;
    }

    public boolean esLibroRecomendado(String titulo) {
        return librosRecomendados.contains(titulo);
    }
}
