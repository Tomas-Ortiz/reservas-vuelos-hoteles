package CapaPresentación;

import CapaNegocio.ActualizacionFacade;
import CapaNegocio.Entidades.Hotel;
import CapaNegocio.Entidades.Reserva;
import CapaNegocio.Entidades.Vuelo;
import CapaNegocio.BusquedaFacade;
import CapaNegocio.NegocioReserva;
import CapaNegocio.Excepcion;
import CapaNegocio.NegocioHotel;
import CapaNegocio.NegocioVuelo;
import CapaNegocio.Utilidad;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class frmPrincipal extends javax.swing.JFrame {

    //modelos de tablas de vuelos, hoteles y reserva
    private DefaultTableModel dtmVuelos;
    private DefaultTableModel dtmHoteles;
    private DefaultTableModel dtmReservas;

    //clases facades
    private BusquedaFacade busquedaFacade;
    private ActualizacionFacade actualizacionFacade;

    //clases de negocio
    private NegocioReserva negocioReserva;
    private NegocioVuelo negocioVuelo;
    private NegocioHotel negocioHotel;

    private List<Vuelo> vuelos;
    private List<Hotel> hoteles;
    private List<Reserva> reservas;

    private String fecha, desde, hasta;
    private String origen, destino;
    private int pasajeros, totalPersonas;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public frmPrincipal() {

        initComponents();

        configurarFrame();

        agregarIconosInicio();

        centrarElementosTabla();

        ocultarColumnasId();

        obtenerModelosTablas();

        //Singleton
        //si no está creada se crea y se utiliza esa misma en toda la clase
        busquedaFacade = BusquedaFacade.getBusquedaFacade();
        actualizacionFacade = ActualizacionFacade.getActualizacionFacade();

        negocioReserva = new NegocioReserva();
        negocioVuelo = new NegocioVuelo();
        negocioHotel = new NegocioHotel();

        vuelos = new ArrayList();
        hoteles = new ArrayList();
        reservas = new ArrayList();

        listarReservasTabla();
    }

    private void configurarFrame() {
        this.setTitle("Reserva: hoteles y vuelos");
        this.setSize(1260, 650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void obtenerModelosTablas() {
        dtmVuelos = (DefaultTableModel) jtblVuelos.getModel();
        dtmHoteles = (DefaultTableModel) jtblHoteles.getModel();
        dtmReservas = (DefaultTableModel) jtblReservas.getModel();
    }

    private void agregarIconosInicio() {
        rsscalelabel.RSScaleLabel.setScaleLabel(lblAvionIcono, "src/iconos/avion-icono.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(lblHotelIcono, "src/iconos/hotel-icono.png");
    }

    private void centrarElementosTabla() {

        DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
        dtc.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < jtblVuelos.getColumnModel().getColumnCount(); i++) {
            jtblVuelos.getColumnModel().getColumn(i).setHeaderRenderer(dtc);
            jtblVuelos.getColumnModel().getColumn(i).setCellRenderer(dtc);
        }

        for (int i = 0; i < jtblHoteles.getColumnModel().getColumnCount(); i++) {
            jtblHoteles.getColumnModel().getColumn(i).setHeaderRenderer(dtc);
            jtblHoteles.getColumnModel().getColumn(i).setCellRenderer(dtc);
        }

        for (int i = 0; i < jtblReservas.getColumnModel().getColumnCount(); i++) {
            jtblReservas.getColumnModel().getColumn(i).setHeaderRenderer(dtc);
            jtblReservas.getColumnModel().getColumn(i).setCellRenderer(dtc);
        }
    }

    //si no se han encontrado resultados se muestra un mensaje en la tabla correspondiente
    private void verificarTablasVacias(DefaultTableModel dtmVuelos, DefaultTableModel dtmHoteles, DefaultTableModel dtmReservas) {

        if (jtblVuelos.getRowCount() == 0) {
            dtmVuelos.addRow(new Object[]{null, "No se han", "encontrado", "resultados."});
        }

        if (jtblHoteles.getRowCount() == 0) {
            dtmHoteles.addRow(new Object[]{null, "No se han", "encontrado", "resultados."});
        }

        if (jtblReservas.getRowCount() == 0) {
            dtmReservas.addRow(new Object[]{"No se han", "encontrado", "resultados."});
        }

    }

    private void limpiarBusqueda() {
        tfOrigen.setText(null);
        tfDestino.setText(null);
        jdcFecha.setDate(null);
        jdcDesde.setDate(null);
        jdcHasta.setDate(null);
        cbAdultos.setSelectedIndex(0);
        cbClase.setSelectedIndex(0);
        cbHabitaciones.setSelectedIndex(0);
        cbMenores.setSelectedIndex(0);
        cbPasajeros.setSelectedIndex(0);
    }

    private void limpiarTablas() {

        eliminarFilas();
        vaciarArrays();

    }

    private void eliminarFilas() {
        dtmVuelos.setRowCount(0);
        dtmHoteles.setRowCount(0);
    }

    private void vaciarArrays() {
        vuelos = null;
        hoteles = null;
    }

    private void agregarDatosTablas() {

        negocioVuelo.mostrarVuelos(vuelos, dtmVuelos);

        negocioHotel.mostrarHoteles(hoteles, dtmHoteles);
    }

    private void ocultarColumnasId() {

        jtblVuelos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblVuelos.getColumnModel().getColumn(0).setMinWidth(0);
        jtblVuelos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jtblVuelos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        jtblHoteles.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblHoteles.getColumnModel().getColumn(0).setMinWidth(0);
        jtblHoteles.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jtblHoteles.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }

    private void limpiarTablaReserva() {
        dtmReservas.setRowCount(0);
    }

    private void listarReservasTabla() {

        limpiarTablaReserva();

        reservas = negocioReserva.listarReservas();

        negocioReserva.mostrarReservas(reservas, dtmReservas);

    }

    //Se muestran en pantalla la cantidad de resultados obtenidos de vuelos y hoteles
    private void mostrarCantidadResultados() {

        int cantVuelos = 0;
        int cantHotel = 0;

        if (Excepcion.validarCantResultados(vuelos, hoteles, cantVuelos, cantHotel)) {
            cantVuelos = vuelos.size();
            cantHotel = hoteles.size();
        }

        lblResultadosVuelos.setText("(" + cantVuelos + ")");
        lblResultadosHoteles.setText("(" + cantHotel + ")");
    }

    //se le solicita a la capa de negocio los vuelos y hoteles encontrados
    private void getVuelosHoteles() {
        vuelos = negocioVuelo.getVuelos();
        hoteles = negocioHotel.getHoteles();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        jtpVentanas = new javax.swing.JTabbedPane();
        jpInicio = new javax.swing.JPanel();
        lblOrigen = new javax.swing.JLabel();
        tfOrigen = new javax.swing.JTextField();
        lblDestino = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        LblPasajeros = new javax.swing.JLabel();
        lblClase = new javax.swing.JLabel();
        lblAdultos = new javax.swing.JLabel();
        lblDesde = new javax.swing.JLabel();
        lblMenores = new javax.swing.JLabel();
        lblHabitaciones = new javax.swing.JLabel();
        lblHasta = new javax.swing.JLabel();
        cbAdultos = new javax.swing.JComboBox();
        tfDestino = new javax.swing.JTextField();
        cbMenores = new javax.swing.JComboBox();
        cbHabitaciones = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        cbClase = new javax.swing.JComboBox();
        cbPasajeros = new javax.swing.JComboBox();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jdcDesde = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblHoteles = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblVuelos = new javax.swing.JTable();
        btnLimpiarBusqueda = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jdcHasta = new com.toedter.calendar.JDateChooser();
        lblHoteles = new javax.swing.JLabel();
        lblVuelos1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lblTitulo = new javax.swing.JLabel();
        lblHotelIcono = new javax.swing.JLabel();
        lblAvionIcono = new javax.swing.JLabel();
        btnReservar = new javax.swing.JButton();
        btnLimpiarTablas = new javax.swing.JButton();
        lblResultadosHoteles = new javax.swing.JLabel();
        lblResultadosVuelos = new javax.swing.JLabel();
        jpReservas = new javax.swing.JPanel();
        lblReservas = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblReservas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1123, 565, 110, 40));

        jtpVentanas.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N

        jpInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOrigen.setText("Origen");
        jpInicio.add(lblOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 102, -1));
        jpInicio.add(tfOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 130, 30));

        lblDestino.setText("Destino");
        jpInicio.add(lblDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 108, -1));

        lblFecha.setText("Fecha");
        jpInicio.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 99, -1));

        LblPasajeros.setText("Pasajeros");
        jpInicio.add(LblPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 70, -1));

        lblClase.setText("Clase");
        jpInicio.add(lblClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, -1));

        lblAdultos.setText("Adultos");
        jpInicio.add(lblAdultos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 106, -1));

        lblDesde.setText("Desde");
        jpInicio.add(lblDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 53, -1));

        lblMenores.setText("Menores");
        jpInicio.add(lblMenores, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 98, -1));

        lblHabitaciones.setText("Habitaciones");
        jpInicio.add(lblHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 84, -1));

        lblHasta.setText("Hasta");
        jpInicio.add(lblHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 190, 52, -1));

        cbAdultos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));
        jpInicio.add(cbAdultos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 58, 30));
        jpInicio.add(tfDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 130, 30));

        cbMenores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sin menores", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));
        jpInicio.add(cbMenores, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, 30));

        cbHabitaciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        jpInicio.add(cbHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 58, 30));
        jpInicio.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 1220, 10));

        cbClase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Economy", "Premium Economy", "Business" }));
        jpInicio.add(cbClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, -1, 30));

        cbPasajeros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jpInicio.add(cbPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, 58, 30));

        jdcFecha.setDateFormatString("yyyy-MM-dd");
        jpInicio.add(jdcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 120, 30));

        jdcDesde.setDateFormatString("yyyy-MM-dd");
        jpInicio.add(jdcDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 120, 30));

        jtblHoteles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Hotel", "Dirección", "Estrellas", "Disponibilidad", "Precio por noche"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblHoteles.setRowHeight(25);
        jtblHoteles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblHoteles.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblHoteles);
        if (jtblHoteles.getColumnModel().getColumnCount() > 0) {
            jtblHoteles.getColumnModel().getColumn(0).setResizable(false);
            jtblHoteles.getColumnModel().getColumn(1).setResizable(false);
            jtblHoteles.getColumnModel().getColumn(2).setResizable(false);
            jtblHoteles.getColumnModel().getColumn(3).setResizable(false);
            jtblHoteles.getColumnModel().getColumn(4).setResizable(false);
            jtblHoteles.getColumnModel().getColumn(5).setResizable(false);
        }

        jpInicio.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 550, 140));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 300));

        jtblVuelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Aerolínea", "Fecha partida", "Hora partida", "Fecha llegada", "Hora llegada", "Escala", "Asientos", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblVuelos.setRowHeight(25);
        jtblVuelos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblVuelos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtblVuelos);
        if (jtblVuelos.getColumnModel().getColumnCount() > 0) {
            jtblVuelos.getColumnModel().getColumn(0).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(1).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(2).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(3).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(4).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(5).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(6).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(7).setResizable(false);
            jtblVuelos.getColumnModel().getColumn(8).setResizable(false);
        }

        jpInicio.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 650, 140));

        btnLimpiarBusqueda.setText("Limpiar búsqueda");
        btnLimpiarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarBusquedaActionPerformed(evt);
            }
        });
        jpInicio.add(btnLimpiarBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 140, 40));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jpInicio.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 110, 40));

        jdcHasta.setDateFormatString("yyyy-MM-dd");
        jpInicio.add(jdcHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 210, 120, 30));

        lblHoteles.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHoteles.setText("Hoteles encontrados");
        jpInicio.add(lblHoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, -1, -1));

        lblVuelos1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVuelos1.setText("Vuelos encontados");
        jpInicio.add(lblVuelos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));
        jpInicio.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1200, 10));
        jpInicio.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 1200, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Para buscar hoteles y vuelos complete la siguiente información:");
        jpInicio.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 510, -1));
        jpInicio.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 1200, 10));

        lblTitulo.setFont(new java.awt.Font("Californian FB", 0, 28)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Reserva de hoteles y vuelos");
        lblTitulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpInicio.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 312, -1));

        lblHotelIcono.setPreferredSize(new java.awt.Dimension(256, 250));
        jpInicio.add(lblHotelIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 140, 90, 80));

        lblAvionIcono.setPreferredSize(new java.awt.Dimension(256, 250));
        jpInicio.add(lblAvionIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 90, 80));

        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });
        jpInicio.add(btnReservar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, 110, 40));

        btnLimpiarTablas.setText("Limpiar resultados");
        btnLimpiarTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTablasActionPerformed(evt);
            }
        });
        jpInicio.add(btnLimpiarTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 140, 40));

        lblResultadosHoteles.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblResultadosHoteles.setText("(0)");
        jpInicio.add(lblResultadosHoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 330, -1, -1));

        lblResultadosVuelos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblResultadosVuelos.setText("(0)");
        jpInicio.add(lblResultadosVuelos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, -1, -1));

        jtpVentanas.addTab("Inicio", jpInicio);

        jpReservas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblReservas.setFont(new java.awt.Font("Californian FB", 0, 28)); // NOI18N
        lblReservas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReservas.setText("Mis reservas");
        lblReservas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblReservas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpReservas.add(lblReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 312, -1));

        jScrollPane4.setPreferredSize(new java.awt.Dimension(452, 300));

        jtblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aerolínea", "Clase", "Fecha partida", "Hora partida", "Hotel", "Desde", "Hasta", "Pasajeros", "Habitaciones", "Adultos", "Menores", "Precio total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblReservas.setRowHeight(30);
        jtblReservas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblReservas.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jtblReservas);
        if (jtblReservas.getColumnModel().getColumnCount() > 0) {
            jtblReservas.getColumnModel().getColumn(0).setResizable(false);
            jtblReservas.getColumnModel().getColumn(1).setResizable(false);
            jtblReservas.getColumnModel().getColumn(2).setResizable(false);
            jtblReservas.getColumnModel().getColumn(3).setResizable(false);
            jtblReservas.getColumnModel().getColumn(4).setResizable(false);
            jtblReservas.getColumnModel().getColumn(5).setResizable(false);
            jtblReservas.getColumnModel().getColumn(6).setResizable(false);
            jtblReservas.getColumnModel().getColumn(7).setResizable(false);
            jtblReservas.getColumnModel().getColumn(8).setResizable(false);
            jtblReservas.getColumnModel().getColumn(9).setResizable(false);
            jtblReservas.getColumnModel().getColumn(10).setResizable(false);
            jtblReservas.getColumnModel().getColumn(11).setResizable(false);
        }

        jpReservas.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 1060, 240));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("A continuación se presentan sus reservas realizadas:");
        jpReservas.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 510, -1));
        jpReservas.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 1200, 10));
        jpReservas.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1200, 10));

        jtpVentanas.addTab("Reservas", jpReservas);

        getContentPane().add(jtpVentanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        limpiarTablas();

        if (Excepcion.validarFechas(jdcFecha, jdcDesde, jdcHasta)) {

            fecha = sdf.format(jdcFecha.getDate());
            desde = sdf.format(jdcDesde.getDate());
            hasta = sdf.format(jdcHasta.getDate());

            pasajeros = Integer.parseInt(cbPasajeros.getSelectedItem().toString());
            int adultos = Integer.parseInt(cbAdultos.getSelectedItem().toString());

            totalPersonas = Utilidad.getTotalPersonas(adultos, cbMenores);

            origen = tfOrigen.getText();
            destino = tfDestino.getText();

            //FACADE
            //se obtienen y validan los datos de entrada y
            //se buscan los vuelos y hoteles que coincidan con las busquedas
            busquedaFacade.buscar(origen, destino, fecha, pasajeros, totalPersonas, negocioVuelo, negocioHotel);

            getVuelosHoteles();
            agregarDatosTablas();

            verificarTablasVacias(dtmVuelos, dtmHoteles, dtmReservas);
        }
        
        mostrarCantidadResultados();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed

        //Se obtienen los datos de entrada y se crea una nueva reserva
        Reserva reserva = new Reserva();

        //se obtiene el vuelo y hotel seleccionado (fila)
        int filaVuelo = jtblVuelos.getSelectedRow();
        int filaHotel = jtblHoteles.getSelectedRow();

        boolean valido = Excepcion.verificarFilasSeleccionadas(jtblVuelos, jtblHoteles, filaVuelo, filaHotel);

        //Se debe seleccionar un vuelo y hotel para poder reservar
        if (valido == false) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el vuelo y hotel para reservar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            long idVuelo = Long.parseLong(jtblVuelos.getValueAt(filaVuelo, 0).toString());

            long idHotel = Long.parseLong(jtblHoteles.getValueAt(filaHotel, 0).toString());

            Vuelo vuelo = negocioVuelo.getVuelo(idVuelo);
            Hotel hotel = negocioHotel.getHotel(idHotel);

            //La reserva se asocia con un vuelo y hotel (1:1)
            reserva.setVuelo(vuelo);
            reserva.setHotel(hotel);
            reserva.setClase(cbClase.getSelectedItem().toString());

            if (Excepcion.validarFechas(jdcDesde, jdcHasta)) {

                desde = sdf.format(jdcDesde.getDate());
                hasta = sdf.format(jdcHasta.getDate());

                reserva.setDesde(desde);
                reserva.setHasta(hasta);

                reserva.setPasajeros(Integer.parseInt(cbPasajeros.getSelectedItem().toString()));
                reserva.setAdultos(Integer.parseInt(cbAdultos.getSelectedItem().toString()));

                if (!cbMenores.getSelectedItem().toString().equals("Sin menores")) {
                    reserva.setMenores(Integer.parseInt(cbMenores.getSelectedItem().toString()));
                } else {
                    reserva.setMenores(0);
                }
                reserva.setHabitaciones(Integer.parseInt(cbHabitaciones.getSelectedItem().toString()));

                //Precio total es la suma del precio del vuelo + precio por noche del hotel * dias
                int dias = Utilidad.calcularDiasEntreFechas(jdcDesde, jdcHasta);

                float precioTotal = (float) jtblVuelos.getValueAt(filaVuelo, 8);
                float precioNoche = (float) jtblHoteles.getValueAt(filaHotel, 5);

                precioTotal += dias * precioNoche;

                reserva.setPrecioTotal(precioTotal);

                negocioReserva.insertarReserva(reserva);

                listarReservasTabla();

                JOptionPane.showMessageDialog(null, "¡Has realizado la reserva exitósamente!", "Reserva", JOptionPane.INFORMATION_MESSAGE);

                //FACADE
                //Una vez reservado, se actualiza la disponibilidad de vuelos y hoteles
                actualizacionFacade.actualizarDisponibilidad(vuelo, hotel, reserva);

                eliminarFilas();

                origen = vuelo.getCiudadOrigen();
                destino = vuelo.getCuidadDestino();
                fecha = vuelo.getFechaPartida();
                pasajeros = reserva.getPasajeros();
                totalPersonas = Utilidad.getTotalPersonas(reserva.getAdultos(), cbMenores);

                //se actualiza la tabla despues de una reserva para mostrar la nueva disponibilidad
                busquedaFacade.buscar(origen, destino, fecha, pasajeros, totalPersonas, negocioVuelo, negocioHotel);

                getVuelosHoteles();
                agregarDatosTablas();
            }
        }
    }//GEN-LAST:event_btnReservarActionPerformed

    private void btnLimpiarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarBusquedaActionPerformed

        limpiarBusqueda();

    }//GEN-LAST:event_btnLimpiarBusquedaActionPerformed

    private void btnLimpiarTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTablasActionPerformed

        limpiarTablas();
        mostrarCantidadResultados();

    }//GEN-LAST:event_btnLimpiarTablasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblPasajeros;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiarBusqueda;
    private javax.swing.JButton btnLimpiarTablas;
    private javax.swing.JButton btnReservar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbAdultos;
    private javax.swing.JComboBox cbClase;
    private javax.swing.JComboBox cbHabitaciones;
    private javax.swing.JComboBox cbMenores;
    private javax.swing.JComboBox cbPasajeros;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdcDesde;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private com.toedter.calendar.JDateChooser jdcHasta;
    private javax.swing.JPanel jpInicio;
    private javax.swing.JPanel jpReservas;
    private javax.swing.JTable jtblHoteles;
    private javax.swing.JTable jtblReservas;
    private javax.swing.JTable jtblVuelos;
    private javax.swing.JTabbedPane jtpVentanas;
    private javax.swing.JLabel lblAdultos;
    private javax.swing.JLabel lblAvionIcono;
    private javax.swing.JLabel lblClase;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHabitaciones;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblHotelIcono;
    private javax.swing.JLabel lblHoteles;
    private javax.swing.JLabel lblMenores;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblReservas;
    private javax.swing.JLabel lblResultadosHoteles;
    private javax.swing.JLabel lblResultadosVuelos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVuelos1;
    private javax.swing.JTextField tfDestino;
    private javax.swing.JTextField tfOrigen;
    // End of variables declaration//GEN-END:variables
}
