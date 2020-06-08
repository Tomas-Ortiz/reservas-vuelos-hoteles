package CapaNegocio.Interfaces;

import CapaNegocio.Entidades.Hotel;
import CapaNegocio.Entidades.Reserva;
import CapaNegocio.Entidades.Vuelo;

//interfaz que es implementada por la clase ActualizacionFacade
public interface IActualizacionFacade {

    public void actualizarDisponibilidad(Vuelo vuelo, Hotel hotel, Reserva reserva);

}
