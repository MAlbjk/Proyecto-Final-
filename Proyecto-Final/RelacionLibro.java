//realaciona la conexion entre 2 libros enel grafo 
public class RelacionLibro {
    private Libro destino;
    private float peso;
    private RelacionLibro siguiente;

    public RelacionLibro(Libro destino) {
        this.destino = destino;
        this.peso = 0.0f; // relación sin peso
        this.siguiente = null;
    }

    public RelacionLibro(Libro destino, float peso) {
        this.destino = destino;
        this.peso = peso; // relación ponderada
        this.siguiente = null;
    }

    public Libro getDestino() {
        return destino;
    }

    public void setDestino(Libro destino) {
        this.destino = destino;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public RelacionLibro getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(RelacionLibro siguiente) {
        this.siguiente = siguiente;
    }
}
