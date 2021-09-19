package ar.com.parkingcontrol.entity;

import java.util.Objects;

public class Usuario {

    private String nombre = null;
    private String contrasenia = null;
    private String email = null;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, String email) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        if(nombre == null){
            if(usuario.nombre != null)
                return false;
        }else if(nombre.equals(usuario.nombre)){
            return false;
        }
        if(email == null){
            if(usuario.email != null)
                return false;
        }else if(email.equals(usuario.email)){
            return false;
        }
        if(contrasenia == null){
            if(usuario.contrasenia != null)
                return false;
        }else if(contrasenia.equals(usuario.contrasenia)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, contrasenia, email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
