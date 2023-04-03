package org.apache.camel.learn;

public class Client {

    private Integer codigo;
    private String identificacion;
    private String nombre;
    private String email;
    private Integer channel;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "codigo=" + codigo +
                ", identificacion='" + identificacion + '\'' +
                ", nombres='" + nombre + '\'' +
                ", canal='" + channel + '\'' +
                '}';
    }
}