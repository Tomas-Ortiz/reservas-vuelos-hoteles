package CapaNegocio;

import CapaNegocio.Entidades.Reserva;
import CapaDatos.ReservaJpaController;
import java.util.List;
import javax.swing.table.DefaultTableModel;

//Clase de negocio que recibe peticiones de la capa de presentación 
//y que funciona como intermediario entre la capa de presentacion y la capa de datos 
//tiene acceso a la capa de datos en la parte de reservas
public class NegocioReserva {

    private ReservaJpaController reserva;

    public NegocioReserva() {
        reserva = new ReservaJpaController();
    }

    public void mostrarReservas(List<Reserva> reservas, DefaultTableModel dtmReservas) {

        for (int i = 0; i < reservas.size(); i++) {
            dtmReservas.addRow(new Object[]{reservas.get(i).getVuelo().getAerolinea(), reservas.get(i).getClase(), reservas.get(i).getVuelo().getFechaPartida(),
                reservas.get(i).getVuelo().getHoraPartida(), reservas.get(i).GetHotel().getNombre(), reservas.get(i).getDesde(), reservas.get(i).getHasta(),
                reservas.get(i).getPasajeros(), reservas.get(i).getHabitaciones(), reservas.get(i).getAdultos(), reservas.get(i).getMenores(), reservas.get(i).getPrecioTotal()});
        }

    }

    public void insertarReserva(Reserva r) {

        reserva.create(r);

    }

    public List<Reserva> listarReservas() {

        return reserva.findReservaEntities();
    }

}
