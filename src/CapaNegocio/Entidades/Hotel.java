package CapaNegocio.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Clase de entidad que se mapea con la tabla de hotel en la bd
//solo contiene propiedades y sus getters y setters
@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre, direccion, pais, ciudad;
    private int estrellas, disponibilidad;
    private float precioNoche;

    public Hotel() {
    }

    public Hotel(String nombre, String direccion, String pais, String ciudad, int estrellas, int disponibilidad, float precioNoche) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pais = pais;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
        this.disponibilidad = disponibilidad;
        this.precioNoche = precioNoche;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public float getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(float precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Hotel{" + "nombre=" + nombre + ", direccion=" + direccion + ", pais=" + pais + ", ciudad=" + ciudad + ", estrellas=" + estrellas + ", disponibilidad=" + disponibilidad + ", precioNoche=" + precioNoche + '}';
    }
}
