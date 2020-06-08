package CapaNegocio;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

//Clase que se encarga de validar los datos de entrada del formulario
public class Excepcion {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean validarFechas(JDateChooser jdcFecha, JDateChooser jdcDesde, JDateChooser jdcHasta) {

        try {
            sdf.format(jdcFecha.getDate());
            sdf.format(jdcDesde.getDate());
            sdf.format(jdcHasta.getDate());

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, ingrese una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean validarFechas(JDateChooser jdcDesde, JDateChooser jdcHasta) {

        try {
            sdf.format(jdcDesde.getDate());
            sdf.format(jdcHasta.getDate());

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, ingrese una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean validarCantResultados(List vuelos, List hoteles, int cantVuelos, int cantHoteles) {

        try {
            cantVuelos = vuelos.size();
            cantHoteles = hoteles.size();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean verificarFilasSeleccionadas(JTable jtblVuelos, JTable jtblHoteles, int filaVuelo, int filaHotel) {

        try {
            Long.parseLong(jtblVuelos.getValueAt(filaVuelo, 0).toString());
            Long.parseLong(jtblHoteles.getValueAt(filaHotel, 0).toString());

            return true;

        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }
}
