/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotonesCiudad.java,v 1.5 2006/12/07 16:03:38 da-romer Exp $
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
import javax.swing.JPanel;

/**
 * Es el panel donde se controlan las acciones de agregar o eliminar una ciudad
 */
public class PanelBotonesCiudad extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el bot�n Agregar
     */
    private static final String AGREGAR = "Agregar";

    /**
     * Comando para el bot�n Eliminar
     */
    private static final String ELIMINAR = "Eliminar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el bot�n para agregar una ciudad
     */
    private JButton botonAgregar;

    /**
     * Es el bot�n para eliminar una ciudad
     */
    private JButton botonEliminar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param principal Ventana principal - principal!=null
     */
    public PanelBotonesCiudad( InterfazAerolinea principal )
    {
        ventanaPrincipal = principal;

        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        botonAgregar = new JButton( "Agregar Ciudad" );
        botonAgregar.setActionCommand( AGREGAR );
        botonAgregar.addActionListener( this );
        add( botonAgregar, gbc );

        gbc = new GridBagConstraints( 1, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        botonEliminar = new JButton( "Eliminar Ciudad" );
        botonEliminar.setActionCommand( ELIMINAR );
        botonEliminar.addActionListener( this );
        add( botonEliminar, gbc );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Es el m�todo que se llama cuando de hace click sobre un bot�n
     * @param evento Es el evento de click sobre un bot�n - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( AGREGAR.equals( comando ) )
        {
            ventanaPrincipal.mostrarDialogoAgregarCiudad( );
        }
        else if( ELIMINAR.equals( comando ) )
        {
            ventanaPrincipal.eliminarCiudad( );
        }
    }

}
