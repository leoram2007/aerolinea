/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoAgregarCiudad.java,v 1.5 2006/12/07 16:03:38 da-romer Exp $
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

import javax.swing.JDialog;

/**
 * Es el diálogo usado para agregar una ciudad
 */
public class DialogoAgregarCiudad extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la interfaz
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se indican los datos de la ciudad
     */
    private PanelDatosAgregarCiudad panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo e inicializa sus componentes
     * @param ia Es una referencia a la ventana principal de la interfaz - ia!=null
     */
    public DialogoAgregarCiudad( InterfazAerolinea ia )
    {
        super( ia, true );
        ventanaPrincipal = ia;

        panelDatos = new PanelDatosAgregarCiudad( this );
        getContentPane( ).add( panelDatos );
        setResizable( false );
        setTitle( "Agregar Ciudad" );
        pack( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega una ciudad a la aerolínea
     * @param nombre El nombre de la ciudad
     * @param coordX La coordenada x de la ciudad - 0<coordX<1
     * @param coordY La coordenada y de la ciudad - 0<coordY<1
     */
    public void agregarCiudad( String nombre, double coordX, double coordY )
    {
        ventanaPrincipal.agregarCiudad( this, nombre, coordX, coordY );
    }
}
