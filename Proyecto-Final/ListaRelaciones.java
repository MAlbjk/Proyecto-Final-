//Almacena las Coneciones entre los en los grafos osea las conexiones de los libros
public class ListaRelaciones {
    private RelacionLibro primero;
    private RelacionLibro ultimo;

    public ListaRelaciones() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return this.primero == null;
    }

    public void agregarRelacion(Libro destino, float peso) {
        if (!existeRelacion(destino)) {
            RelacionLibro nuevaRelacion = new RelacionLibro(destino, peso);
            if (estaVacia()) {
                primero = nuevaRelacion;
                ultimo = nuevaRelacion;
            } else {
                ultimo.setSiguiente(nuevaRelacion);
                ultimo = nuevaRelacion;
            }
        }
    }

    public boolean existeRelacion(Libro destino) {
        RelacionLibro actual = primero;
        while (actual != null) {
            if (actual.getDestino().equals(destino)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    //para eliminar una relación basada en el título del libro
    public void eliminarRelacionPorTitulo(String titulo) {
        if (estaVacia()) return;

        RelacionLibro anterior = null;
        RelacionLibro actual = primero;

        while (actual != null) {
            if (actual.getDestino().getTitulo().equalsIgnoreCase(titulo)) {
                if (anterior == null) { // Eliminar la primera relación
                    primero = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                if (actual == ultimo) { // Eliminar la última relación
                    ultimo = anterior;
                }
                break;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }

    public String obtenerRelacionesConPesos() {
        if (estaVacia()) {
            return "No hay relaciones.";
        }

        StringBuilder cadena = new StringBuilder();
        RelacionLibro actual = primero;

        while (actual != null) {
            cadena.append(actual.getDestino().getTitulo())
                  .append(" (Peso: ")
                  .append(actual.getPeso())
                  .append(")\n");
            actual = actual.getSiguiente();
        }

        return cadena.toString();
    }

    @Override
    public String toString() {
        if (estaVacia()) {
            return "No hay relaciones.";
        }

        StringBuilder cadena = new StringBuilder();
        RelacionLibro actual = primero;

        while (actual != null) {
            cadena.append(actual.getDestino().getTitulo())
                  .append(" (Peso: ")
                  .append(actual.getPeso())
                  .append("); ");
            actual = actual.getSiguiente();
        }

        return cadena.toString();
    }
}

