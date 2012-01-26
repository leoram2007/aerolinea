/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoAgregarVuelo.java,v 1.4 2006/12/07 16:32:07 da-romer Exp $
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

import java.awt.BorderLayout;

import javax.swing.JDialog;

import uniandes.cupi2.aerolinea.mundo.Ciudad;

/**
 * Es el diálogo usado para agregar un vuelo
 */
public class DialogoAgregarVuelo extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ciudad destino del vuelo que se está creando
     */
    private Ciudad ciudadDestino;

    /**
     * Es una referencia a la ventana principal de la interfaz
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se indican los datos del vuelo
     */
    private PanelDatosAgregarVuelo panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo e inicializa sus componentes
     * @param ia Es una referencia a la ventana principal de la interfaz - ia!=null
     * @param ciudad Es la ciudad destino del vuelo que se está creando - ciudad!=null
     */
    public DialogoAgregarVuelo( InterfazAerolinea ia, Ciudad ciudad )
    {
        super( ia, true );
        ventanaPrincipal = ia;
        ciudadDestino = ciudad;

        panelDatos = new PanelDatosAgregarVuelo( this, ciudadDestino.darNombre( ) );
        getContentPane( ).add( panelDatos, BorderLayout.CENTER );

        setTitle( "Agregar Vuelo" );
        pack( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo vuelo a la ciudad destino
     * @param codigo El código del vuelo - codigo!=null
     * @param fecha La fecha en la que se realizará el vuelo - fecha!=null
     * @param horas La hora de despegue - horas!=null
     * @param minutos Los minutos de la hora de despegue - minutos!=null
     */
    public void agregarVuelo( String codigo, String fecha, String horas, String minutos )
    {
        ventanaPrincipal.agregarVuelo( this, ciudadDestino, codigo, fecha, horas, minutos );
    }
}
