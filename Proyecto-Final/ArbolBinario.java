import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {
    private NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }

    // insertar un libro en el árbol
    public void insertar(Libro libro) {
        // Normalizamos el título antes de insertarlo
        String tituloNormalizado = libro.getTitulo().trim().toLowerCase();
        Libro libroNormalizado = new Libro(tituloNormalizado, libro.getAutor(), libro.getGenero(), libro.getAnioPublicacion());
        raiz = insertarRecursivo(raiz, libroNormalizado);
    }

    private NodoArbol insertarRecursivo(NodoArbol nodo, Libro libro) {
        if (nodo == null) {
            return new NodoArbol(libro);
        }

        if (libro.getTitulo().compareTo(nodo.libro.getTitulo()) < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, libro);
        } else if (libro.getTitulo().compareTo(nodo.libro.getTitulo()) > 0) {
            nodo.derecha = insertarRecursivo(nodo.derecha, libro);
        }

        return nodo;
    }

    // para buscar un libro por título
    public Libro buscarTitulo(String titulo) {
        return buscarTituloRecursivo(raiz, titulo.trim().toLowerCase());
    }

    private Libro buscarTituloRecursivo(NodoArbol nodo, String titulo) {
        if (nodo == null) {
            return null; // No encontrado
        }

        if (titulo.equals(nodo.libro.getTitulo())) {
            return nodo.libro; // Libro encontrado
        }

        if (titulo.compareTo(nodo.libro.getTitulo()) < 0) {
            return buscarTituloRecursivo(nodo.izquierda, titulo);
        } else {
            return buscarTituloRecursivo(nodo.derecha, titulo);
        }
    }

    // para eliminar un libro por título
    public boolean eliminar(String titulo) {
        if (buscarTitulo(titulo.trim().toLowerCase()) == null) {
            return false; // El libro no existe
        }
        raiz = eliminarRecursivo(raiz, titulo.trim().toLowerCase());
        return true;
    }

    private NodoArbol eliminarRecursivo(NodoArbol nodo, String titulo) {
        if (nodo == null) {
            return null;
        }

        if (titulo.compareTo(nodo.libro.getTitulo()) < 0) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, titulo);
        } else if (titulo.compareTo(nodo.libro.getTitulo()) > 0) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, titulo);
        } else {
            // Caso 1: Nodo sin hijos
            if (nodo.izquierda == null && nodo.derecha == null) {
                return null;
            }
            // Caso 2: Nodo con un hijo
            if (nodo.izquierda == null) {
                return nodo.derecha;
            }
            if (nodo.derecha == null) {
                return nodo.izquierda;
            }
            // Caso 3: Nodo con dos hijos
            NodoArbol sucesor = encontrarMinimo(nodo.derecha);
            nodo.libro = sucesor.libro;
            nodo.derecha = eliminarRecursivo(nodo.derecha, sucesor.libro.getTitulo());
        }

        return nodo;
    }

    private NodoArbol encontrarMinimo(NodoArbol nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }

    // Obtener libros en orden (recorrido in-order)
    public List<Libro> obtenerLibrosEnOrden() {
        List<Libro> libros = new ArrayList<>();
        obtenerLibrosRecursivo(raiz, libros);
        return libros;
    }

    private void obtenerLibrosRecursivo(NodoArbol nodo, List<Libro> libros) {
        if (nodo != null) {
            obtenerLibrosRecursivo(nodo.izquierda, libros);
            libros.add(nodo.libro);
            obtenerLibrosRecursivo(nodo.derecha, libros);
        }
    }
}




