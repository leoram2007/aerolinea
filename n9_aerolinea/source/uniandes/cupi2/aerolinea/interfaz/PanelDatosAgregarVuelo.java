/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosAgregarVuelo.java,v 1.6 2006/12/07 16:32:07 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co=
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 10/12/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.aerolinea.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

/**
 * Es el panel donde se ingresa la información para agregar un vuelo
 */
public class PanelDatosAgregarVuelo extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón Agregar
     */
    private static final String AGREGAR = "Agregar";

    /**
     * Comando para el botón Cancelar
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el diálogo al que pertenece el panel
     */
    private DialogoAgregarVuelo dialogo;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta Destino;
     */
    private JLabel etiquetaDestino;

    /**
     * Etiqueta Codigo;
     */
    private JLabel etiquetaCodigo;

    /**
     * Etiqueta Fecha:
     */
    private JLabel etiquetaFecha;

    /**
     * Etiqueta Hora:
     */
    private JLabel etiquetaHora;

    /**
     * Campo de texto donde se muestra el destino del vuelo
     */
    private JTextField txtDestino;

    /**
     * Campo de texto donde se ingresa el código del vuelo
     */
    private JTextField txtCodigo;

    /**
     * Calendario para seleccionar la fecha del vuelo
     */
    private JCalendar calendarioFecha;

    /**
     * ComboBox para seleccionar la hora de despegue del vuelo
     */
    private JComboBox cbbHoras;

    /**
     * ComboBox para seleccionar los minutos de la hora de despegue del vuelo
     */
    private JComboBox cbbMinutos;

    /**
     * Botón usado para agregar el vuelo
     */
    private JButton botonAgregarVuelo;

    /**
     * Botón para cancelar la creación del vuelo
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param dialogoAV Es una referencia al diálogo que contiene el panel
     * @param destino Es el nombre de la ciudad destino del vuelo que se está creando
     */
    public PanelDatosAgregarVuelo( DialogoAgregarVuelo dialogoAV, String destino )
    {
        dialogo = dialogoAV;
        setLayout( new GridBagLayout( ) );

        // Construir e inicializar las etiquetas
        inicializarEtiquetas( );

        // Construir e inicializar los campos
        inicializarCampos( destino );

        // Construir e inicializar los botones
        inicializarBotones( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa las etiquetas del panel
     */
    private void inicializarEtiquetas( )
    {
        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        etiquetaDestino = new JLabel( "Destino:" );
        add( etiquetaDestino, gbcE );

        gbcE.gridy = 1;
        etiquetaCodigo = new JLabel( "Código:" );
        add( etiquetaCodigo, gbcE );

        gbcE.gridy = 2;
        etiquetaFecha = new JLabel( "Fecha:" );
        add( etiquetaFecha, gbcE );

        gbcE.gridy = 3;
        etiquetaHora = new JLabel( "Hora:" );
        add( etiquetaHora, gbcE );
    }

    /**
     * Inicializa los campos del panel
     * @param destino El destino que debe mostrarse
     */
    private void inicializarCampos( String destino )
    {
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtDestino = new JTextField( );
        txtDestino.setText( destino );
        txtDestino.setEnabled( false );
        add( txtDestino, gbcC );

        gbcC.gridy = 1;
        txtCodigo = new JTextField( );
        add( txtCodigo, gbcC );

        gbcC.gridy = 2;
        calendarioFecha = new JCalendar( );
        add( calendarioFecha, gbcC );

        gbcC.gridwidth = 1;
        gbcC.gridy = 3;
        cbbHoras = new JComboBox( );
        for( int i = 0; i < 24; i++ )
        {
            cbbHoras.addItem( "" + i );
        }
        add( cbbHoras, gbcC );

        gbcC.gridx = 2;
        cbbMinutos = new JComboBox( );
        for( int i = 0; i < 60; i += 5 )
        {
            cbbMinutos.addItem( "" + i );
        }
        add( cbbMinutos, gbcC );
    }

    /**
     * Inicializa los botones del panel
     */
    private void inicializarBotones( )
    {
        JPanel panelBotones = new JPanel( );

        botonAgregarVuelo = new JButton( "Agregar Vuelo" );
        botonAgregarVuelo.setActionCommand( AGREGAR );
        botonAgregarVuelo.addActionListener( this );
        panelBotones.add( botonAgregarVuelo );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        panelBotones.add( botonCancelar );

        GridBagConstraints gbcB = new GridBagConstraints( 0, 4, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbcB );
    }

    /**
     * Es el método que se llama cuando de hace click sobre un botón
     * @param evento Es el evento de click sobre un botón - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( AGREGAR.equals( comando ) )
        {
            String codigo = txtCodigo.getText( );
            String horas = ( String )cbbHoras.getSelectedItem( );
            String minutos = ( String )cbbMinutos.getSelectedItem( );

            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            Date fecha = calendarioFecha.getDate( );
            String strFecha = sdf.format( fecha );

            dialogo.agregarVuelo( codigo, strFecha, horas, minutos );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }

    }

}
