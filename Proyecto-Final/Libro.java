public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private int anioPublicacion;

    public Libro(String titulo, String autor, String genero, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Género: " + genero + ", Año: " + anioPublicacion;
    }
}
