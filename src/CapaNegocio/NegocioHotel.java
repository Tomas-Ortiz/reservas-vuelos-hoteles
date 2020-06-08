package CapaNegocio;

import CapaNegocio.Entidades.Hotel;
import CapaDatos.HotelJpaController;
import java.util.List;
import javax.swing.table.DefaultTableModel;

//Clase de negocio que recibe peticiones de la capa de presentación 
//y que funciona como intermediario entre la capa de presentacion y la capa de datos 
//tiene acceso a la capa de datos en la parte de hoteles
public class NegocioHotel {

    private HotelJpaController hotelController;
    private List<Hotel> hoteles;

    public NegocioHotel() {
        hotelController = new HotelJpaController();
    }

    public void buscarHoteles(String ciudadDestino, int totalPersonas) {
        hoteles = hotelController.findHotelEntities(ciudadDestino, totalPersonas);
    }

    public Hotel getHotel(long id) {
        return hotelController.findHotel(id);
    }

    public void mostrarHoteles(List<Hotel> hoteles, DefaultTableModel dtmHoteles) {
        for (int i = 0; i < hoteles.size(); i++) {
            dtmHoteles.addRow(new Object[]{hoteles.get(i).getId(), hoteles.get(i).getNombre(), hoteles.get(i).getDireccion(), hoteles.get(i).getEstrellas(),
                hoteles.get(i).getDisponibilidad(), hoteles.get(i).getPrecioNoche()});
        }
    }

    public List<Hotel> getHoteles() {
        return hoteles;
    }

}
