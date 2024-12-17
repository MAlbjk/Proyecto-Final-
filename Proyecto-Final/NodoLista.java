public class NodoLista {
    public Libro libro;
    public NodoLista siguiente, anterior;

    public NodoLista(Libro libro) {
        this.libro = libro;
        this.siguiente = null;
        this.anterior = null;
    }
}
