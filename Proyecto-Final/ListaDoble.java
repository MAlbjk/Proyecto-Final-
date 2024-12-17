//permite recorridos en ambas direciones y operaciones Basicas
public class ListaDoble {
    private NodoLista cabeza;

    //gregar un libro al inicio de la lista
    public void agregarInicio(Libro libro) {
        NodoLista nuevo = new NodoLista(libro);
        if (cabeza != null) {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
        }
        cabeza = nuevo;
    }

    //para eliminar un libro por título
    public boolean eliminar(String titulo) {
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.libro.getTitulo().equalsIgnoreCase(titulo)) {
                if (actual.anterior != null) {
                    actual.anterior.siguiente = actual.siguiente;
                } else {
                    cabeza = actual.siguiente; // Eliminar la cabeza
                }
                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                }
                return true; // Libro eliminado
            }
            actual = actual.siguiente;
        }
        return false; // Libro no encontrado
    }

    //para listar todos los libros
    public String listar() {
        StringBuilder sb = new StringBuilder();
        NodoLista actual = cabeza;
        while (actual != null) {
            sb.append(actual.libro.toString()).append("\n");
            actual = actual.siguiente;
        }
        return sb.length() > 0 ? sb.toString() : "La lista está vacía.";
    }
}
