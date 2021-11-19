package ar.com.parkingcontrol.Entidades;

public class Parqueo {
    private String usuario;
    private String matricula;
    private String tiempo;

    public Parqueo() {
    }

    public Parqueo(String usuario, String matricula, String tiempo) {
        this.usuario = usuario;
        this.matricula = matricula;
        this.tiempo = tiempo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
