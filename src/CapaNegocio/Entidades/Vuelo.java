package CapaNegocio.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Clase de entidad que se mapea con la tabla de vuelo en la bd
//solo contiene propiedades y sus getters y setters
@Entity
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aerolinea, fechaPartida, horaPartida, fechaLlegada, horaLlegada, ciudadOrigen, ciudadDestino, escala;
    private int asientosDisp, capacidad;
    private float precio;

    public Vuelo() {
    }

    public Vuelo(String aerolinea, String fechaPartida, String horaPartida, String fechaLlegada, String horaLlegada, String ciudadOrigen, String cuidadDestino, int asientosDisp, int capacidad, float precio, String escala) {
        this.aerolinea = aerolinea;
        this.fechaPartida = fechaPartida;
        this.horaPartida = horaPartida;
        this.fechaLlegada = fechaLlegada;
        this.horaLlegada = horaLlegada;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = cuidadDestino;
        this.asientosDisp = asientosDisp;
        this.capacidad = capacidad;
        this.precio = precio;
        this.escala = escala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public String getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(String fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getAsientosDisponibles() {
        return asientosDisp;
    }

    public void setAsientosDisponibles(int asientosDisp) {
        this.asientosDisp = asientosDisp;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCuidadDestino() {
        return ciudadDestino;
    }

    public void setCuidadDestino(String cuidadDestino) {
        this.ciudadDestino = cuidadDestino;
    }

    public int getAsientosDisp() {
        return asientosDisp;
    }

    public void setAsientosDisp(int asientosDisp) {
        this.asientosDisp = asientosDisp;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "aerolinea=" + aerolinea + ", fechaPartida=" + fechaPartida + ", horaPartida=" + horaPartida + ", fechaLlegada=" + fechaLlegada + ", horaLlegada=" + horaLlegada + ", ciudadOrigen=" + ciudadOrigen + ", cuidadDestino=" + ciudadDestino + ", asientosDisp=" + asientosDisp + ", capacidad=" + capacidad + ", precio=" + precio + ", escala=" + escala + '}';
    }

}
