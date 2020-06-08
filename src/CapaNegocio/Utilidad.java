package CapaNegocio;

import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import javax.swing.JComboBox;

//Clase con metodos estáticos que contienen algunos cálculos independientes 
public class Utilidad {

    public static int calcularDiasEntreFechas(JDateChooser desde, JDateChooser hasta) {

        int dias = -1;

        if (desde.getDate() != null && hasta.getDate() != null) {

            Calendar inicio = desde.getCalendar();
            Calendar fin = hasta.getCalendar();

            while (inicio.before(fin) || inicio.equals(fin)) {

                dias++;
                inicio.add(Calendar.DATE, 1);
            }
            return dias;
        } else {
            return dias;
        }
    }

    public static int getTotalPersonas(int adultos, JComboBox cbMenores) {
        int totalPersonas;

        if (!cbMenores.getSelectedItem().toString().equals("Sin menores")) {
            int menores = Integer.parseInt(cbMenores.getSelectedItem().toString());
            totalPersonas = adultos + menores;
            return totalPersonas;
        } else {
            totalPersonas = adultos;
            return totalPersonas;
        }
    }

}
