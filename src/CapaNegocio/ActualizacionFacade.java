package CapaNegocio;

import CapaDatos.HotelJpaController;
import CapaDatos.VueloJpaController;
import CapaNegocio.Entidades.Hotel;
import CapaNegocio.Entidades.Reserva;
import CapaNegocio.Entidades.Vuelo;
import CapaNegocio.Interfaces.IActualizacionFacade;

//Clase FACADE para actualizar la disponibilidad de vuelos y hoteles
public class ActualizacionFacade implements IActualizacionFacade {

    private VueloJpaController vueloController;
    private HotelJpaController hotelController;

    //SINGLETON
    //constructor privado
    private ActualizacionFacade() {
        vueloController = new VueloJpaController();
        hotelController = new HotelJpaController();
    }
    //objeto estatico para poder usarse en el método estático que devuelve la instancia
    private static ActualizacionFacade actualizacion;

    //si todavia no se crea la instancia entonces se crea y devuelve
    //si ya está creada se devuelve
    public static ActualizacionFacade getActualizacionFacade() {

        if (actualizacion == null) {
            actualizacion = new ActualizacionFacade();
        }
        return actualizacion;
    }

    //en un solo metodo se unifica la actualizacion de vuelos y hoteles
    @Override
    public void actualizarDisponibilidad(Vuelo vuelo, Hotel hotel, Reserva reserva) {

        vuelo.setAsientosDisp(vuelo.getAsientosDisp() - reserva.getPasajeros());
        hotel.setDisponibilidad(hotel.getDisponibilidad() - reserva.getPasajeros());
        vueloController.edit(vuelo);
        hotelController.edit(hotel);
    }

}
