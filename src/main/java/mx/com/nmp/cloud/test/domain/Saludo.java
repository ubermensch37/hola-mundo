package mx.com.nmp.cloud.test.domain;

/**
 * @author osanchez
 */
public class Saludo {
    private Long id;
    private String contenido;

    public Saludo() {
        this.id = null;
        this.contenido = "";
    }

    public Saludo(Long id, String content) {
        this.id = id;
        this.contenido = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long nuevo){
        this.id = nuevo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String nuevo) {
        this.contenido = nuevo;
    }
}
