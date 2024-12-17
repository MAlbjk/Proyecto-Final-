//realaciones entere libros para generar las recomendacioes por genero 
public class GrafoLibros {
    private NodoGrafoLibro primero;
    private NodoGrafoLibro ultimo;

    public GrafoLibros() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacio() {
        return this.primero == null && this.ultimo == null;
    }

    public void agregarLibro(Libro libro) {
        if (!existeLibro(libro)) {
            NodoGrafoLibro nuevoNodo = new NodoGrafoLibro(libro);
            if (estaVacio()) {
                primero = nuevoNodo;
                ultimo = nuevoNodo;
            } else {
                ultimo.setSiguiente(nuevoNodo);
                ultimo = nuevoNodo;
            }
        }
    }

    public boolean existeLibro(Libro libro) {
        NodoGrafoLibro actual = primero;
        while (actual != null) {
            if (actual.getLibro().getTitulo().equalsIgnoreCase(libro.getTitulo())) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void eliminarNodo(String titulo) {
        if (estaVacio()) return;

        NodoGrafoLibro anterior = null;
        NodoGrafoLibro actual = primero;

        while (actual != null) {
            if (actual.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                if (anterior == null) { // Eliminar el primer nodo
                    primero = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                if (actual == ultimo) { // Eliminar el último nodo
                    ultimo = anterior;
                }
                break;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        // También eliminar las relaciones hacia este nodo
        NodoGrafoLibro nodoActual = primero;
        while (nodoActual != null) {
            nodoActual.getRelaciones().eliminarRelacionPorTitulo(titulo);
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public void generarRelacionesPorGenero() {
        NodoGrafoLibro actual = primero;
        while (actual != null) {
            NodoGrafoLibro comparador = actual.getSiguiente();
            while (comparador != null) {
                if (actual.getLibro().getGenero().equalsIgnoreCase(comparador.getLibro().getGenero())) {
                    agregarRelacion(actual.getLibro(), comparador.getLibro(), 1.0f);
                    agregarRelacion(comparador.getLibro(), actual.getLibro(), 1.0f);
                }
                comparador = comparador.getSiguiente();
            }
            actual = actual.getSiguiente();
        }
    }

    public void agregarRelacion(Libro origen, Libro destino, float peso) {
        NodoGrafoLibro actual = primero;
        while (actual != null) {
            if (actual.getLibro().equals(origen)) {
                actual.getRelaciones().agregarRelacion(destino, peso);
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    public String obtenerRecomendaciones(Libro libro) {
        NodoGrafoLibro actual = primero;
        while (actual != null) {
            if (actual.getLibro().equals(libro)) {
                return actual.getRelaciones().obtenerRelacionesConPesos();
            }
            actual = actual.getSiguiente();
        }
        return "No se encontraron recomendaciones.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodoGrafoLibro actual = primero;
        while (actual != null) {
            sb.append("Libro: ").append(actual.getLibro().getTitulo()).append("\n");
            sb.append("Relaciones: ").append(actual.getRelaciones().toString()).append("\n");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
}
