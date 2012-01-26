/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosReserva.java,v 1.5 2006/12/07 16:03:38 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co=
 * Ejercicio: n9_aerolinea
 * Autor: Mario S�nchez - 10/12/2005
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
     * Comando para el bot�n Agregar
     */
    private static final String AGREGAR = "Agregar";

    /**
     * Comando para el bot�n Cancelar
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el di�logo al cual pertenece este panel
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
     * Etiqueta C�dula;
     */
    private JLabel etiquetaCedula;

    /**
     * Campo de texto donde se ingresa el nombre de la persona
     */
    private JTextField txtNombre;

    /**
     * Campo de texto donde se ingresa la c�dula
     */
    private JTextField txtCedula;

    /**
     * Bot�n usado para agregar el vuelo
     */
    private JButton botonAgregarVuelo;

    /**
     * Bot�n para cancelar la creaci�n del vuelo
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ddr Es una referencia al di�logo al que pertenece el panel - ddr!=null
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
    // M�todos
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
        etiquetaCedula = new JLabel( "C�dula:" );
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
     * Es el m�todo que se llama cuando de hace click sobre un bot�n
     * @param evento Es el evento de click sobre un bot�n - evento!=null
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
