//conecta libros similares para generar recomendaciones automaticas
public class NodoGrafoLibro {
    private Libro libro;
    private ListaRelaciones relaciones;
    private NodoGrafoLibro siguiente;

    public NodoGrafoLibro(Libro libro) {
        this.libro = libro;
        this.relaciones = new ListaRelaciones();
        this.siguiente = null;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public ListaRelaciones getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(ListaRelaciones relaciones) {
        this.relaciones = relaciones;
    }

    public NodoGrafoLibro getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGrafoLibro siguiente) {
        this.siguiente = siguiente;
    }
}
