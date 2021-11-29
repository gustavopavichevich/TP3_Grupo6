package ar.com.parkingcontrol.model.entity;

public class Parqueo {

    private Integer id;
    private Integer idUsuario;
    private String matricula;
    private Integer tiempo;

    public Parqueo() { }

    public Parqueo(String matricula, Integer tiempo, Integer idUsuario) {
        this.matricula = matricula;
        this.tiempo = tiempo;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString(){
        return  "{\"Id\" : "+this.id+", \"idUsuario\" : "+this.idUsuario+", \"Matricula\" : \""+this.matricula+"\", \"Tiempo\" : "+this.tiempo+"}";
    }

}
