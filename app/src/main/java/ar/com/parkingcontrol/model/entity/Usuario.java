package ar.com.parkingcontrol.model.entity;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer id;
    private String nombreUsuario;
    private String password;
    private String email;

    public Usuario() {}

    public Usuario(String nombreUsuario, String password, String email) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "{\"Id\" : "+this.id+",\"NombreUsuario\" : \""+this.nombreUsuario+"\", \"Password\" : \""+this.password+"\", \"Email\" : \""+this.email+"\"}";
    }
}
