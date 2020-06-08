package CapaNegocio;

import CapaNegocio.Entidades.Vuelo;
import CapaDatos.VueloJpaController;
import java.util.List;
import javax.swing.table.DefaultTableModel;

//Clase de negocio que recibe peticiones de la capa de presentación 
//y que funciona como intermediario entre la capa de presentacion y la capa de datos 
//tiene acceso a la capa de datos en la parte de vuelos
public class NegocioVuelo {

    private VueloJpaController vueloController;
    private List<Vuelo> vuelos;

    public NegocioVuelo() {
        vueloController = new VueloJpaController();
    }

    public void buscarVuelos(String ciudadOrigen, String ciudadDestino, String fecha, int pasajeros) {
        vuelos = vueloController.findVueloEntities(ciudadOrigen, ciudadDestino, fecha, pasajeros);
    }

    public Vuelo getVuelo(long id) {
        return vueloController.findVuelo(id);
    }

    public void mostrarVuelos(List<Vuelo> vuelos, DefaultTableModel dtmVuelos) {
        for (int i = 0; i < vuelos.size(); i++) {
            dtmVuelos.addRow(new Object[]{vuelos.get(i).getId(), vuelos.get(i).getAerolinea(), vuelos.get(i).getFechaPartida(), vuelos.get(i).getHoraPartida(), vuelos.get(i).getFechaLlegada(),
                vuelos.get(i).getHoraLlegada(), vuelos.get(i).getEscala(), vuelos.get(i).getAsientosDisp(), vuelos.get(i).getPrecio()});
        }
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

}
