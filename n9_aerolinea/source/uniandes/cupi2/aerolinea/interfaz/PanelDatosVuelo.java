/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosVuelo.java,v 1.9 2006/12/07 16:03:38 da-romer Exp $
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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.aerolinea.mundo.Silla;
import uniandes.cupi2.aerolinea.mundo.Vuelo;

/**
 * Este es el panel donde se muestran los datos de un vuelo y se pueden realizar reservar
 */
public class PanelDatosVuelo extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón Reservar
     */
    private static final String RESERVAR = "Reservar";

    /**
     * Comando para el botón que genera el Manifiesto de Embarque
     */
    private static final String GENERAR_MANIFIESTO = "Manifiesto";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al vuelo del que se están mostrando los datos
     */
    private Vuelo vuelo;

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón para reservar sillas
     */
    private JButton botonReservar;

    /**
     * Es el botón para generar el manifiesto de embarque
     */
    private JButton botonGenerarManifiesto;

    /**
     * Etiqueta Código:
     */
    private JLabel etiquetaCodigo;

    /**
     * Etiqueta Fecha:
     */
    private JLabel etiquetaFecha;

    /**
     * Etiqueta Porcentaje Libre:
     */
    private JLabel etiquetaPorcentajeLibre;

    /**
     * Es el campo para mostrar el código del vuelo
     */
    private JTextField txtCodigo;

    /**
     * Es el campo para mostrar la fecha y hora del vuelo
     */
    private JTextField txtFecha;

    /**
     * Es el campo para mostrar el porcentaje de la sillas del vuelo que se encuentran disponibles
     */
    private JTextField txtPorcentajeLibre;

    /**
     * Es la matriz de checkboxes usada para representar las sillas
     */
    private JCheckBox[][] chkSillas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param principal Ventana principal - principal!=null
     */
    public PanelDatosVuelo( InterfazAerolinea principal )
    {
        ventanaPrincipal = principal;

        setBorder( new TitledBorder( "Datos del vuelo..." ) );
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 3, 3, 3, 3 ), 0, 0 );

        etiquetaCodigo = new JLabel( "Código:" );
        add( etiquetaCodigo, gbcE );

        gbcE.gridx = 2;
        etiquetaFecha = new JLabel( "Fecha:" );
        add( etiquetaFecha, gbcE );

        gbcE.gridx = 4;
        etiquetaPorcentajeLibre = new JLabel( "Porcentaje Libre:" );
        add( etiquetaPorcentajeLibre, gbcE );

        GridBagConstraints gbcT = new GridBagConstraints( 1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 3, 3, 3 ), 0, 0 );

        txtCodigo = new JTextField( );
        txtCodigo.setEditable( false );
        add( txtCodigo, gbcT );

        gbcT.gridx = 3;
        txtFecha = new JTextField( );
        txtFecha.setEditable( false );
        add( txtFecha, gbcT );

        gbcT.gridx = 5;
        txtPorcentajeLibre = new JTextField( );
        txtPorcentajeLibre.setEditable( false );
        add( txtPorcentajeLibre, gbcT );

        JPanel panelSillas = new JPanel( new GridLayout( Vuelo.LETRAS.length, Vuelo.NUMERO_FILAS ) );

        chkSillas = new JCheckBox[Vuelo.NUMERO_FILAS][Vuelo.LETRAS.length];
        for( int j = 0; j < chkSillas[ 0 ].length; j++ )
        {
            for( int i = 0; i < chkSillas.length; i++ )
            {
                chkSillas[ i ][ j ] = new JCheckBox( );
                chkSillas[ i ][ j ].setEnabled( false );
                chkSillas[ i ][ j ].setText( "" + i + "-" + Vuelo.LETRAS[ j ] );
                panelSillas.add( chkSillas[ i ][ j ] );
            }
        }

        GridBagConstraints gbcS = new GridBagConstraints( 0, 1, 6, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 3, 3, 3 ), 0, 0 );
        add( panelSillas, gbcS );

        GridBagConstraints gbcB = new GridBagConstraints( 0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 3, 3, 3, 3 ), 0, 0 );
        botonReservar = new JButton( "Reservar" );
        botonReservar.setActionCommand( RESERVAR );
        botonReservar.addActionListener( this );
        add( botonReservar, gbcB );

        gbcB.gridx = 5;
        gbcB.gridwidth = 1;
        botonGenerarManifiesto = new JButton( "Manifiesto de Embarque" );
        botonGenerarManifiesto.setActionCommand( GENERAR_MANIFIESTO );
        botonGenerarManifiesto.addActionListener( this );
        add( botonGenerarManifiesto, gbcB );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el vuelo mostrado en el panel
     * @param nuevoVuelo El vuelo que deberá mostrarse en el panel ahora
     */
    public void cambiarVuelo( Vuelo nuevoVuelo )
    {
        vuelo = nuevoVuelo;

        actualizar( );
    }

    /**
     * Actualiza toda la información del vuelo
     */
    public void actualizar( )
    {
        if( vuelo != null )
        {
            setBorder( new TitledBorder( "Datos del vuelo " + vuelo.darCodigo( ) ) );

            txtCodigo.setText( Integer.toString( vuelo.darCodigo( ) ) );
            txtFecha.setText( vuelo.darFechaYHora( ) );

            DecimalFormat df = new DecimalFormat( "00.00" );
            String disponibilidad = df.format( ( ( double )vuelo.darNumeroSillasDisponibles( ) / ( double )vuelo.darCapacidad( ) * 100 ) );
            txtPorcentajeLibre.setText( disponibilidad + "%" );

            actualizarSillas( );
        }
        else
        {
            setBorder( new TitledBorder( "Datos del vuelo..." ) );
            txtCodigo.setText( "" );
            txtFecha.setText( "" );
            txtPorcentajeLibre.setText( "" );
            limpiarSillas( );
        }
    }

    /**
     * Actualiza la información de las sillas mostradas
     */
    public void actualizarSillas( )
    {
        for( int i = 0; i < Vuelo.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Vuelo.LETRAS.length; j++ )
            {
                Silla silla = vuelo.darSilla( i, j );
                chkSillas[ i ][ j ].setText( "" + silla.darFila( ) + "-" + silla.darLetra( ) );

                if( silla.estaReservada( ) )
                {
                    chkSillas[ i ][ j ].setEnabled( false );
                    chkSillas[ i ][ j ].setSelected( true );
                }
                else
                {
                    chkSillas[ i ][ j ].setEnabled( true );
                    chkSillas[ i ][ j ].setSelected( false );
                }
            }
        }
    }

    /**
     * Este método se encarga de limpiar la información de las sillas mostrada
     */
    private void limpiarSillas( )
    {
        for( int i = 0; i < chkSillas.length; i++ )
        {
            for( int j = 0; j < chkSillas[ i ].length; j++ )
            {
                chkSillas[ i ][ j ].setSelected( false );
                chkSillas[ i ][ j ].setEnabled( false );
            }
        }
    }

    /**
     * Es el método que se llama cuando de hace click sobre un botón
     * @param evento Es el evento de click sobre un botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( RESERVAR.equals( comando ) )
        {
            if( vuelo != null )
            {
                ArrayList sillasReserva = new ArrayList( );

                for( int i = 0; i < chkSillas.length; i++ )
                {
                    for( int j = 0; j < chkSillas[ i ].length; j++ )
                    {
                        if( chkSillas[ i ][ j ].isSelected( ) && chkSillas[ i ][ j ].isEnabled( ) )
                        {
                            sillasReserva.add( chkSillas[ i ][ j ].getText( ) );
                        }
                    }
                }

                if( sillasReserva.size( ) == 1 )
                {
                    String silla = sillasReserva.get( 0 ).toString( );
                    ventanaPrincipal.mostrarDialogoReservar( vuelo, silla );
                }
                else if( sillasReserva.size( ) > 1 )
                {
                    JOptionPane.showMessageDialog( this, "No puede realizar la reserva de más de una silla", "¡Atención!", JOptionPane.INFORMATION_MESSAGE );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Debe seleccionar la silla que va a reservar", "¡Atención!", JOptionPane.INFORMATION_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe seleccionar un vuelo antes", "¡Atención!", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        else if( GENERAR_MANIFIESTO.equals( comando ) )
        {
            if( vuelo != null )
                ventanaPrincipal.generarManifiesto( vuelo );
            else
            {
                JOptionPane.showMessageDialog( this, "Debe seleccionar un vuelo antes", "¡Atención!", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

}
