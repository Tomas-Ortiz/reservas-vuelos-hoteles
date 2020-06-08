package CapaNegocio.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Clase de entidad que se mapea con la tabla de reserva en la bd
//solo contiene propiedades y sus getters y setters
@Entity
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clase, desde, hasta;
    private int pasajeros, habitaciones, adultos, menores;
    private float precioTotal;

    //Muchas reservas pueden estar asociadas con un vuelo y hotel
    //Un vuelo y hotel tiene muchas reservas
    @ManyToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel Hotel;

    public Reserva() {
    }

    public Reserva(Long id, String clase, String desde, String hasta, int pasajeros, int habitaciones, int adultos, int menores, float precioTotal, Vuelo vuelo, Hotel Hotel) {
        this.id = id;
        this.clase = clase;
        this.desde = desde;
        this.hasta = hasta;
        this.pasajeros = pasajeros;
        this.habitaciones = habitaciones;
        this.adultos = adultos;
        this.menores = menores;
        this.precioTotal = precioTotal;
        this.vuelo = vuelo;
        this.Hotel = Hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getMenores() {
        return menores;
    }

    public void setMenores(int menores) {
        this.menores = menores;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Hotel GetHotel() {
        return Hotel;
    }

    public void setHotel(Hotel Hotel) {
        this.Hotel = Hotel;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", clase=" + clase + ", desde=" + desde + ", hasta=" + hasta + ", pasajeros=" + pasajeros + ", habitaciones=" + habitaciones + ", adultos=" + adultos + ", menores=" + menores + ", precioTotal=" + precioTotal + ", vuelo=" + vuelo + ", Hotel=" + Hotel + '}';
    }

}
