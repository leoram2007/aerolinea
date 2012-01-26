/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoDatosReserva.java,v 1.7 2006/12/07 16:03:38 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario S�nchez - 10/12/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.aerolinea.interfaz;

import javax.swing.JDialog;

import uniandes.cupi2.aerolinea.mundo.Vuelo;

/**
 * Es el di�logo usado para mostrar los datos de una reserva
 */
public class DialogoDatosReserva extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el nombre de las silla que se quiere reservar
     */
    private String sillaReserva;

    /**
     * Es el vuelo para el cual se est� realizando la reserva
     */
    private Vuelo vuelo;

    /**
     * Es una referencia a la ventana principal de la interfaz
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se indican los datos de la reserva
     */
    private PanelDatosReserva panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo e inicializa los componentes
     * @param ia Es una referencia a la ventana principal de la interfaz - ia!=null
     * @param v Es una referencia al vuelo para el que se est� realizando la reserva - v!=null
     * @param silla El nombre de la silla que se va a reservar - silla!=null
     */
    public DialogoDatosReserva( InterfazAerolinea ia, Vuelo v, String silla )
    {
        super( ia, true );
        ventanaPrincipal = ia;
        vuelo = v;
        sillaReserva = silla;

        panelDatos = new PanelDatosReserva( this );
        getContentPane( ).add( panelDatos );

        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        pack( );
        setTitle( "Reservar Silla" );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Realiza la reserva
     * @param nombre El nombre de la persona que est� reservando
     * @param cedula La c�dula de la persona que est� reservando
     */
    public void reservar( String nombre, String cedula )
    {
        ventanaPrincipal.reservar( this, nombre, cedula, sillaReserva, vuelo );
    }
}
