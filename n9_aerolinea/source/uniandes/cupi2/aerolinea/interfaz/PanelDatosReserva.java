/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosReserva.java,v 1.5 2006/12/07 16:03:38 da-romer Exp $
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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Es el panel donde se ingresan los datos de la persona que realiza una reserva
 */
public class PanelDatosReserva extends JPanel implements ActionListener
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
     * Es el diálogo al cual pertenece este panel
     */
    private DialogoDatosReserva dialogo;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta Nombre;
     */
    private JLabel etiquetaNombre;

    /**
     * Etiqueta Cédula;
     */
    private JLabel etiquetaCedula;

    /**
     * Campo de texto donde se ingresa el nombre de la persona
     */
    private JTextField txtNombre;

    /**
     * Campo de texto donde se ingresa la cédula
     */
    private JTextField txtCedula;

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
     * @param ddr Es una referencia al diálogo al que pertenece el panel - ddr!=null
     */
    public PanelDatosReserva( DialogoDatosReserva ddr )
    {
        dialogo = ddr;

        setLayout( new GridBagLayout( ) );

        // Construir e inicializar las etiquetas
        inicializarEtiquetas( );

        // Construir e inicializar los campos
        inicializarCampos( );

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

        etiquetaNombre = new JLabel( "Nombre:" );
        add( etiquetaNombre, gbcE );

        gbcE.gridy = 1;
        etiquetaCedula = new JLabel( "Cédula:" );
        add( etiquetaCedula, gbcE );

    }

    /**
     * Inicializa los campos del panel
     */
    private void inicializarCampos( )
    {
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtNombre = new JTextField( );
        add( txtNombre, gbcC );

        gbcC.gridy = 1;
        txtCedula = new JTextField( );
        add( txtCedula, gbcC );

    }

    /**
     * Inicializa los botones del panel
     */
    private void inicializarBotones( )
    {
        JPanel panelBotones = new JPanel( );

        botonAgregarVuelo = new JButton( "Reservar" );
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
            String nombre = txtNombre.getText( );
            String cedula = txtCedula.getText( );
            dialogo.reservar( nombre, cedula );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
    }

}
