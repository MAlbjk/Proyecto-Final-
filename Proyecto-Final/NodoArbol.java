public class NodoArbol {
    public Libro libro;
    public NodoArbol izquierda, derecha;

    public NodoArbol(Libro libro) {
        this.libro = libro;
        this.izquierda = null;
        this.derecha = null;
    }
}
