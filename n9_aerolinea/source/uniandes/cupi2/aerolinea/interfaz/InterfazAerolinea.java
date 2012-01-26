/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazAerolinea.java,v 1.14 2006/12/07 16:32:07 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 07-dic-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.aerolinea.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.aerolinea.mundo.Aerolinea;
import uniandes.cupi2.aerolinea.mundo.AerolineaExcepcion;
import uniandes.cupi2.aerolinea.mundo.Ciudad;
import uniandes.cupi2.aerolinea.mundo.Vuelo;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazAerolinea extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El nombre del archivo donde se guarda la información de la aerolínea
     */
    private static final String ARCHIVO_AEROLINEA = "./data/aerolinea.dat";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Aerolinea aerolinea;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se selecciona la ciudad
     */
    private PanelSeleccionCiudad panelCiudad;

    /**
     * Es el panel con los botones para agregar o eliminar ciudades
     */
    private PanelBotonesCiudad panelBotones;

    /**
     * Es el panel para mostrar los vuelos
     */
    private PanelListaVuelos panelVuelos;

    /**
     * Es el panel para mostrar los datos de un vuelo
     */
    private PanelDatosVuelo panelDatosVuelo;

    /**
     * Es el panel para los puntos de extensión
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post: </b> Se inicializó la ventana principal de la aplicación
     * @param laAerolinea La aerolínea que se va a utilizar - laAerolinea!=null
     */
    public InterfazAerolinea( Aerolinea laAerolinea )
    {
        aerolinea = laAerolinea;

        // Construye la forma
        getContentPane( ).setLayout( new GridBagLayout( ) );
        setSize( 799, 580 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setResizable( false );
        setTitle( "AirCP2" );

        GridBagConstraints gbcPSC = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 10, 5, 5 ), 0, 0 );
        panelCiudad = new PanelSeleccionCiudad( this );
        getContentPane( ).add( panelCiudad, gbcPSC );

        GridBagConstraints gbcPB = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        panelBotones = new PanelBotonesCiudad( this );
        getContentPane( ).add( panelBotones, gbcPB );

        GridBagConstraints gbcPV = new GridBagConstraints( 1, 0, 1, 2, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        panelVuelos = new PanelListaVuelos( this );
        getContentPane( ).add( panelVuelos, gbcPV );

        GridBagConstraints gbcPD = new GridBagConstraints( 0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        panelDatosVuelo = new PanelDatosVuelo( this );
        getContentPane( ).add( panelDatosVuelo, gbcPD );

        GridBagConstraints gbcPE = new GridBagConstraints( 0, 3, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        panelExtension = new PanelExtension( this );
        getContentPane( ).add( panelExtension, gbcPE );

        panelCiudad.actualizarImagen( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muestra el diálogo para agregar una ciudad
     */
    public void mostrarDialogoAgregarCiudad( )
    {
        DialogoAgregarCiudad dac = new DialogoAgregarCiudad( this );
        dac.setVisible( true );
    }

    /**
     * Agrega una ciudad a la aerolínea
     * @param dialogo El diálogo usado para introducir los datos de la nueva ciudad - dialogo!=null
     * @param nombre El nombre de la ciudad
     * @param coordX La coordenada x de la ciudad - 0<coordX<1
     * @param coordY La coordenada y de la ciudad - 0<coordY<1
     */
    public void agregarCiudad( DialogoAgregarCiudad dialogo, String nombre, double coordX, double coordY )
    {
        try
        {
            if( nombre == null || nombre.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "No se pudo agregar la ciudad:\n" + "Es necesario que ingrece el nombre de la ciudad", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                aerolinea.agregarCiudad( nombre, coordX, coordY );

                panelCiudad.actualizarImagen( );
                panelCiudad.repaint( );

                dialogo.dispose( );
            }

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo agregar la ciudad:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra el diálogo para agregar un vuelo hacia la ciudad mostrada
     */
    public void mostrarDialogoAgregarVuelo( )
    {
        Ciudad ciudadDestino = panelVuelos.darCiudad( );
        if( ciudadDestino != null )
        {
            DialogoAgregarVuelo dav = new DialogoAgregarVuelo( this, ciudadDestino );
            dav.setVisible( true );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar primero una ciudad", "Atención!", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    /**
     * Agrega un nuevo vuelo a la ciudad destino
     * @param dialogo Es el diálogo usado para agregar el vuelo- dialogo!=null
     * @param ciudadDestino Es la ciudad destino del vuelo - ciudadDestino != null && ciudadDestino != ""
     * @param codigo El código del vuelo - codigo!=null
     * @param fecha La fecha en la que se realizará el vuelo - fecha!=null
     * @param horas La hora de despegue - horas!=null
     * @param minutos Los minutos de la hora de despegue - minutos!=null
     */
    public void agregarVuelo( DialogoAgregarVuelo dialogo, Ciudad ciudadDestino, String codigo, String fecha, String horas, String minutos )
    {
        try
        {
            int elCodigo = Integer.parseInt( codigo );
            aerolinea.agregarVuelo( ciudadDestino, elCodigo, fecha, horas, minutos );
            panelVuelos.actualizarLista( );
            dialogo.dispose( );
        }
        catch( AerolineaExcepcion e )
        {

            JOptionPane.showMessageDialog( this, "No se pudo agregar el vuelo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( ParseException e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo agregar el vuelo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo agregar el vuelo. El código debe ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra los datos de un vuelo en el panel correspondiente
     * @param v El vuelo del cual se van a mostrar los datos - v!=null
     */
    public void mostrarVuelo( Vuelo v )
    {
        panelDatosVuelo.cambiarVuelo( v );
    }

    /**
     * Selecciona la ciudad más cercana al punto indicado y actualiza los paneles
     * @param coordX La coordenada X del punto
     * @param coordY La coordenada Y del punto
     */
    public void seleccionarCiudad( double coordX, double coordY )
    {
        Ciudad ciudadCercana = aerolinea.darCiudadMasCercana( coordX, coordY );

        if( ciudadCercana != null )
        {
            panelCiudad.seleccionarCiudad( ciudadCercana );
            panelCiudad.repaint( );
            panelVuelos.cambiarCiudad( ciudadCercana );
        }
    }

    /**
     * Elimina una ciudad
     */
    public void eliminarCiudad( )
    {
        Ciudad ciudad = panelVuelos.darCiudad( );
        if( ciudad != null )
        {
            int resultado = JOptionPane.showConfirmDialog( this, "¿Está seguro de que desea eliminar la ciudad " + ciudad.darNombre( ) + "?\nSe eliminarán todos los vuelos y reservas con destino a esa ciudad" );
            if( resultado == JOptionPane.YES_OPTION )
            {
                try
                {
                    aerolinea.eliminarCiudad( ciudad.darNombre( ) );

                    panelCiudad.seleccionarCiudad( null );
                    panelCiudad.repaint( );
                    panelVuelos.cambiarCiudad( null );
                    panelVuelos.actualizarLista( );

                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, "No se pudo eliminar la ciudad:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    /**
     * Muestra el diálogo usado para reservar sillas en un vuelo
     * @param vuelo El vuelo en el que se van a reservar las sillas - vuelo!=null
     * @param silla La silla que se va a reservar - silla!=null
     */
    public void mostrarDialogoReservar( Vuelo vuelo, String silla )
    {
        DialogoDatosReserva ddr = new DialogoDatosReserva( this, vuelo, silla );
        ddr.setVisible( true );
    }

    /**
     * Realiza la reserva
     * @param dialogo Es el diálogo usado para introducir los datos de la reserva - dialogo!=null
     * @param nombre El nombre de la persona que está reservando - nombre!=null
     * @param cedula La cédula de la persona que está reservando - cedula!=null
     * @param silla La silla que será reservada - silla!=null
     * @param vuelo Es el vuelo en el que se realiza la reserva - vuelo!=null
     */
    public void reservar( DialogoDatosReserva dialogo, String nombre, String cedula, String silla, Vuelo vuelo )
    {
        try
        {
            int ced = Integer.parseInt( cedula );

            if( ced < 0 )
            {
                JOptionPane.showMessageDialog( this, "No se pudo guardar la reserva:\n El número de la cédula debe ser un valor numérico positivo", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( nombre == null || nombre.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "No se pudo guardar la reserva:\n Debe ingresar el nombre de la persona que realiza la reserva", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                vuelo.reservarSilla( silla, nombre, ced );
                panelDatosVuelo.actualizar( );
                dialogo.dispose( );
            }
        }
        catch( NumberFormatException nfe )
        {
            JOptionPane.showMessageDialog( this, "No se pudo guardar la reserva:\n El número de la cédula debe ser un valor numérico", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( AerolineaExcepcion e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo guardar la reserva:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Este método se encarga de generar el manifiesto de embarque del vuelo, en un archivo que selecciona el usuario
     * @param vuelo El vuelo para el cual se va a generar el manifiesto - vuelo!=null
     */
    public void generarManifiesto( Vuelo vuelo )
    {
        try
        {
            JFileChooser fc = new JFileChooser( "./data/" );
            fc.setDialogTitle( "Guardar el Manifiesto de Embarque" );
            int resultado = fc.showSaveDialog( this );
            if( resultado == JOptionPane.YES_OPTION )
            {
                File archivoManifiesto = fc.getSelectedFile( );
                vuelo.generarManifiestoEmbarque( archivoManifiesto );
                JOptionPane.showMessageDialog( this, "Manifiesto Generado en el archivo:\n" + archivoManifiesto.getAbsolutePath( ) );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo guardar el manifiesto de embarque:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Retorna la ciudad base de la aerolínea
     * @return Se retornó la ciudad base de la aerolínea
     */
    public Ciudad darCiudadBaseAerolinea( )
    {
        return aerolinea.darCiudadBase( );
    }

    /**
     * Retorna la lista de ciudades a las que viaja la aerolínea
     * @return Se retornó la lista de ciudades a las que viaja la aerolínea
     */
    public ArrayList darCiudadesAerolinea( )
    {
        return aerolinea.darCiudades( );
    }

    /**
     * Este es el método que se llama cuando se cierra la ventana principal de la aplicación. <br>
     * Cuando la aplicación se termine hay que salvar la información de la aerolínea.
     */
    public void dispose( )
    {
        File archivoAerolinea = new File( ARCHIVO_AEROLINEA );
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( archivoAerolinea ) );
            oos.writeObject( aerolinea );
            oos.close( );

            super.dispose( );
        }
        catch( FileNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, "Error salvando los datos: " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, "Error salvando los datos: " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = aerolinea.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
    	Vuelo aux=(Vuelo)panelVuelos.getListaVuelos().getSelectedValue();
    	int codigoVueloSelect = aux.darCodigo();
    	String resultado = aerolinea.metodo2(codigoVueloSelect);
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 3
     */
    public void reqFuncOpcion3( )
    {
        String resultado = aerolinea.metodo3( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 4
     */
    public void reqFuncOpcion4( )
    {
        String resultado = aerolinea.metodo4( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 5
     */
    public void reqFuncOpcion5( )
    {
        String resultado = aerolinea.metodo5( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Argumentos para la ejecución. No deben usarse
     */
    public static void main( String[] args )
    {
        Aerolinea aerolinea = null;
        try
        {
            File archivoAerolinea = new File( ARCHIVO_AEROLINEA );

            if( !archivoAerolinea.exists( ) )
            {
                aerolinea = new Aerolinea( "Bogota", 0.59, 0.625 );
            }
            else
            {
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivoAerolinea ) );
                aerolinea = ( Aerolinea )ois.readObject( );
                ois.close( );
            }
        }
        catch( Exception e )
        {
            aerolinea = new Aerolinea( "Bogota", 0.59, 0.625 );
        }
        finally
        {
            InterfazAerolinea interfaz = new InterfazAerolinea( aerolinea );
            interfaz.setVisible( true );
        }
    }

}