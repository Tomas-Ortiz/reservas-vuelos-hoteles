package CapaNegocio;

import CapaDatos.HotelJpaController;
import CapaDatos.VueloJpaController;
import CapaNegocio.Interfaces.IBusquedaFacade;

//Clase FACADE para buscar vuelos y hoteles
public class BusquedaFacade implements IBusquedaFacade {

    private VueloJpaController vueloController;
    private HotelJpaController hotelController;

    //SINGLETON
    //constructor privado
    private BusquedaFacade() {
        vueloController = new VueloJpaController();
        hotelController = new HotelJpaController();
    }

    //objeto estatico para poder usarse en el método estático que devuelve la instancia
    private static BusquedaFacade busqueda;

    //si todavia no se crea la instancia entonces se crea
    //si ya está creada se devuelve
    public static BusquedaFacade getBusquedaFacade() {

        if (busqueda == null) {
            busqueda = new BusquedaFacade();
        }
        return busqueda;
    }

    //en un solo método se unifica la busqueda de vuelos y hoteles
    @Override
    public void buscar(String ciudadOrigen, String ciudadDestino, String fecha, int pasajeros, int totalPersonas, NegocioVuelo vuelo, NegocioHotel hotel) {
        vuelo.buscarVuelos(ciudadOrigen, ciudadDestino, fecha, pasajeros);
        hotel.buscarHoteles(ciudadDestino, totalPersonas);
    }

}
