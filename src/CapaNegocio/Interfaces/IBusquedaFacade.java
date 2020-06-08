package CapaNegocio.Interfaces;

import CapaNegocio.NegocioHotel;
import CapaNegocio.NegocioVuelo;

//interfaz que es implementada por la clase BusquedaFacade
public interface IBusquedaFacade {

    public void buscar(String ciudadOrigen, String ciudadDestino, String fecha, int pasajeros, int totalPersonas, NegocioVuelo vuelo, NegocioHotel hotel);
}
