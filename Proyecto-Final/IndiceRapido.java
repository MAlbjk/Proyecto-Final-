import java.util.ArrayList;
import java.util.HashMap;

public class IndiceRapido {
    private HashMap<String, ArrayList<Libro>> librosPorGenero = new HashMap<>();
    private HashMap<String, ArrayList<Libro>> librosPorAutor = new HashMap<>();

    //para agregar un libro a los índices
    public void agregarLibro(Libro libro) {
        // Agregar al índice por género
        librosPorGenero.computeIfAbsent(libro.getGenero(), k -> new ArrayList<>()).add(libro);

        // Agregar al índice por autor
        librosPorAutor.computeIfAbsent(libro.getAutor(), k -> new ArrayList<>()).add(libro);
    }

    //para buscar libros por género
    public String buscarPorGenero(String genero) {
        ArrayList<Libro> libros = librosPorGenero.getOrDefault(genero, new ArrayList<>());
        if (libros.isEmpty()) {
            return "No se encontraron libros para el género: " + genero;
        }
        StringBuilder sb = new StringBuilder("Libros del género '" + genero + "':\n");
        for (Libro libro : libros) {
            sb.append(libro.toString()).append("\n");
        }
        return sb.toString();
    }

    // Método para buscar libros por autor
    public String buscarPorAutor(String autor) {
        ArrayList<Libro> libros = librosPorAutor.getOrDefault(autor, new ArrayList<>());
        if (libros.isEmpty()) {
            return "No se encontraron libros para el autor: " + autor;
        }
        StringBuilder sb = new StringBuilder("Libros del autor '" + autor + "':\n");
        for (Libro libro : libros) {
            sb.append(libro.toString()).append("\n");
        }
        return sb.toString();
    }
}
