/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelListaVuelos.java,v 1.11 2006/12/07 16:32:07 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.aerolinea.mundo.Ciudad;
import uniandes.cupi2.aerolinea.mundo.Vuelo;

/**
 * Es el panel donde se muestra la lista de los vuelos que llegan a una ciudad
 */
public class PanelListaVuelos extends JPanel implements ActionListener, ListSelectionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Comando para el botón Agregar
     */
    private static final String AGREGAR = "Agregar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ciudad destino para la que se muestran los vuelos
     */
    private Ciudad ciudadDestino;

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es la lista donde se muestran los vuelos que hay con destino a una ciudad
     */
    private JList listaVuelos;

    /**
     * Es el botón para agregar un vuelo
     */
    private JButton botonAgregarVuelo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param principal Ventana principal - principal!=null
     */
    public PanelListaVuelos( InterfazAerolinea principal )
    {
        ventanaPrincipal = principal;

        setBorder( new TitledBorder( "Vuelos con destino..." ) );
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcL = new GridBagConstraints( 0, 0, 1, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        listaVuelos = new JList( );
        listaVuelos.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaVuelos.addListSelectionListener( this );
        JScrollPane scroll = new JScrollPane( );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.getViewport( ).add( listaVuelos );
        add( scroll, gbcL );

        GridBagConstraints gbcB = new GridBagConstraints( 0, 5, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        botonAgregarVuelo = new JButton( "Agregar Vuelo" );
        botonAgregarVuelo.setActionCommand( AGREGAR );
        botonAgregarVuelo.addActionListener( this );
        add( botonAgregarVuelo, gbcB );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia la ciudad para la que se muestran los vuelos
     * @param nuevaCiudad La ciudad para la que ahora se mostrarán los vuelos
     */
    public void cambiarCiudad( Ciudad nuevaCiudad )
    {
        ciudadDestino = nuevaCiudad;

        if( nuevaCiudad != null )
            setBorder( new TitledBorder( "Vuelos con destino " + nuevaCiudad.darNombre( ) ) );
        else
            setBorder( new TitledBorder( "Vuelos con destino xxx " ) );

        actualizarLista( );
    }

    /**
     * Actualiza la lista de vuelos
     */
    public void actualizarLista( )
    {
        if( ciudadDestino != null )
        {
            ArrayList vuelos = null;

            vuelos = ciudadDestino.darVuelos( );

            listaVuelos.setListData( vuelos.toArray( ) );
        }
        else
        {
            listaVuelos.setListData( new Object[]{} );
        }
    }

    /**
     * Retorna la ciudad de la cual se están mostrando los vuelos
     * @return Se retornó la ciudad destino
     */
    public Ciudad darCiudad( )
    {
        return ciudadDestino;
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
            ventanaPrincipal.mostrarDialogoAgregarVuelo( );
        }
    }

    /**
     * Cambia el vuelo mostrado de acuerdo al nuevo vuelo seleccionado
     * @param evento El evento de cambio el ítem seleccionado en la lista
     */
    public void valueChanged( ListSelectionEvent evento )
    {
        if( listaVuelos.getSelectedIndex( ) != -1 )
        {
            Vuelo v = ( Vuelo )listaVuelos.getSelectedValue( );
            ventanaPrincipal.mostrarVuelo( v );
        }
    }

	
    /**
     * Retorna el vuelo del Jlist de vuelos que esta seleccionado
     */
    public JList getListaVuelos() 
    {
		return listaVuelos;
	}
    
}