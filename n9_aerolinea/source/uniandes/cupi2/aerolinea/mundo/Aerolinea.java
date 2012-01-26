/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Aerolinea.java,v 1.21 2006/12/08 21:05:45 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario S�nchez - 07-dic-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.aerolinea.mundo;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Esta es la clase que representa una aerol�nea <br>
 * <b>inv: </b> <br>
 * origen != null <br>
 * No puede haber dos vuelos con el mismo c�digo <br>
 * No puede haber ciudades con nombre repetido <br>
 * Ninguna ciudad destino puede ser la misma ciudad base <br>
 */
public class Aerolinea implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la ciudad base de la aerol�nea, de la cual salen todos sus vuelos
     */
    private Ciudad origen;

    /**
     * Es la primera ciudad de la lista de ciudades a donde vuela la aerol�nea
     */
    private Ciudad ciudad1;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una aerol�nea de la cual se conoce �nicamente la ciudad base
     * @param nombreCiudadOrigen El nombre de la ciudad base de la aerol�nea - nombreCiudadBase != null && nombreCiudadOrigen != ""
     * @param coordenada_x La coordenada x donde se ubica la ciudad base - 0 < coordenada_x < 1
     * @param coordenada_y La coordenada y donde se ubica la ciudad base - 0 < coordenada_y < 1
     */
    public Aerolinea( String nombreCiudadOrigen, double coordenada_x, double coordenada_y )
    {
        origen = new Ciudad( nombreCiudadOrigen, coordenada_x, coordenada_y );
        ciudad1 = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la ciudad base de la aerol�nea
     * @return ciudadBase La ciudad base de la aerol�nea
     */
    public Ciudad darCiudadBase( )
    {
        return origen;
    }

    /**
     * Retorna la ciudad con el nombre indicado o null si no la encuentra
     * @param nombreCiudad El nombre de la ciudad que se est� buscando - nombreCiudad!=null
     * @return Se retorn� la ciudad con el nombre indicado o null si no se encuentr�.
     */
    public Ciudad darCiudad( String nombreCiudad )
    {
        for( Ciudad p = ciudad1; p != null; p = p.darSiguiente( ) )
        {
            if( p.darNombre( ).equalsIgnoreCase( nombreCiudad ) )
                return p;
        }
        return null;
    }

    /**
     * Retorna la ciudad destino m�s cercana a un punto dado (en distancia euclidiana)
     * @param coord_x La coordenada x del punto
     * @param coord_y La coordenada y del punto
     * @return La ciudad destino m�s cercana al punto dado. Si no hay ninguna ciudad retorna null.
     */
    public Ciudad darCiudadMasCercana( double coord_x, double coord_y )
    {
        Ciudad masCercana = null;
        double menorDistancia = Double.MAX_VALUE;
        Ciudad ciudadTemp = ciudad1;
        while( ciudadTemp != null )
        {
            double deltaX = Math.abs( ciudadTemp.darCoordenadaX( ) - coord_x );
            double deltaY = Math.abs( ciudadTemp.darCoordenadaY( ) - coord_y );
            double distancia = Math.sqrt( deltaX * deltaX + deltaY * deltaY );
            if( distancia < menorDistancia )
            {
                masCercana = ciudadTemp;
                menorDistancia = distancia;
            }
            ciudadTemp = ciudadTemp.darSiguiente( );
        }
        return masCercana;
    }

    /**
     * Agrega una nueva ciudad, verificando previamente que no existe otra ciudad con el mismo nombre. <br>
     * <b>post: </b>Se agreg� una ciudad a la aerol�nea. Inicialmente no hay vuelos a esa ciudad.
     * @param nombre El nombre de la nueva ciudad - nombre != null && nombre != ""
     * @param coord_x La coordenada x de la ciudad - 0 < coord_x < 1
     * @param coord_y La coordenada y de la ciudad - 0 < coord_y < 1
     * @throws AerolineaExcepcion Se lanza esta excepci�n si el nombre de la ciudad est� repetido
     */
    public void agregarCiudad( String nombre, double coord_x, double coord_y ) throws AerolineaExcepcion
    {
        if( nombre.equalsIgnoreCase( origen.darNombre( ) ) )
            throw new AerolineaExcepcion( "El nombre de la nueva ciudad no puede ser igual al de la ciudad base" );

        if( darCiudad( nombre ) != null )
            throw new AerolineaExcepcion( "El nombre de la nueva ciudad no puede ser igual al de otra ciudad ya existente" );

        // Agrega la nueva ciudad como primera de la lista
        Ciudad nuevaCiudad = new Ciudad( nombre, coord_x, coord_y );
        nuevaCiudad.agregarDespues( ciudad1 );
        ciudad1 = nuevaCiudad;
        verificarInvariante( );
    }

    /**
     * Elimina una ciudad dado su nombre. <br>
     * <b>post: </b> Se elimin� la ciudad de la aerol�nea, con todos los vuelos que ten�a.
     * @param nombre El nombre de la ciudad que se debe eliminar - nombre != null
     * @throws AerolineaExcepcion Se lanza esta excepci�n si no se encuentra una ciudad con el nombre suministrado
     */
    public void eliminarCiudad( String nombre ) throws AerolineaExcepcion
    {
        if( ciudad1 == null )
            throw new AerolineaExcepcion( "No existe una ciudad llamada " + nombre );

        if( nombre.equalsIgnoreCase( ciudad1.darNombre( ) ) )
        {
            // Es la primera ciudad de la lista
            ciudad1 = ciudad1.darSiguiente( );
        }
        else
        {
            Ciudad anterior = localizarAnterior( nombre );
            if( anterior != null )
                anterior.desconectarSiguiente( );
            else
                throw new AerolineaExcepcion( "No existe una ciudad llamada " + nombre );
        }
        verificarInvariante( );
    }

    /**
     * Busca la ciudad anterior a la ciudad con el nombre especificado. <br>
     * <b>pre: </b> ciudad1 != null && nombre != ciudad1.darNombre( )
     * @param nombre Nombre de la ciudad de la que se desea la ciudad anterior - nombre != null
     * @return La ciudad anterior a la ciudad con el nombre dado. Se retorna null si la ciudad con nombre dado no existe o si es la primera de la lista
     */
    private Ciudad localizarAnterior( String nombre )
    {
        Ciudad anterior = ciudad1;
        Ciudad actual = ciudad1.darSiguiente( );
        while( actual != null && !actual.darNombre( ).equalsIgnoreCase( nombre ) )
        {
            anterior = actual;
            actual = actual.darSiguiente( );
        }
        return actual != null ? anterior : null;
    }

    /**
     * Retorna una lista con las ciudades a las que vuela la aerol�nea
     * @return Lista de ciudades
     */
    public ArrayList darCiudades( )
    {
        ArrayList ciudades = new ArrayList( );

        for( Ciudad p = ciudad1; p != null; p = p.darSiguiente( ) )
            ciudades.add( p );

        return ciudades;
    }

    /**
     * Agrega un nuevo vuelo a una ciudad. <br>
     * <b>post: </b>Se agreg� un vuelo a la ciudadDestino
     * @param ciudadDestino La ciudad a la que se le va a agregar el vuelo - ciudadDestino != null && ciudadDestino != ""
     * @param codigo El c�digo del vuelo
     * @param fecha La fecha en la que se realizar� el vuelo - fecha != null
     * @param horas La hora de despegue - horas!=null
     * @param minutos Los minutos de la hora de despegue - minutos!=null
     * @throws AerolineaExcepcion Se lanza esta excepci�n si el c�digo no puede usarse para un vuelo a esta ciudad
     * @throws ParseException Se lanza esta excepci�n si la fecha y hora de despegue no es v�lida
     */
    public void agregarVuelo( Ciudad ciudadDestino, int codigo, String fecha, String horas, String minutos ) throws AerolineaExcepcion, ParseException
    {
        // Verificar que el c�digo sea positivo
        if( codigo < 0 )
            throw new AerolineaExcepcion( "El c�digo debe ser un valor num�rico positivo" );

        // Verificar que el c�digo del vuelo no exista
        if( contarCodigo( codigo ) != 0 )
            throw new AerolineaExcepcion( "El c�digo " + codigo + " no puede usarse en un vuelo hacia " + ciudadDestino.darNombre( ) );

        // Agregar el vuelo
        ciudadDestino.agregarVuelo( codigo, fecha, horas, minutos );
        verificarInvariante( );
    }

    /**
     * Cuenta el n�mero de veces que aparece el c�digo de un vuelo en la aerol�nea
     * @param codigo C�digo del vuelo que se est� buscando
     * @return Retorna el n�mero de vuelos de la aerol�nea que tienen dicho c�digo
     */
    private int contarCodigo( int codigo )
    {
        int acum = 0;
        for( Ciudad p = ciudad1; p != null; p = p.darSiguiente( ) )
        {
            if( p.buscarVuelo( codigo ) != null )
                acum++;
        }
        return acum;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv: </b> <br>
     * origen != null <br>
     * No puede haber dos vuelos con el mismo c�digo <br>
     * No puede haber ciudades con nombre repetido <br>
     * Ninguna ciudad destino puede ser la misma ciudad base <br>
     */
    private void verificarInvariante( )
    {
        assert origen != null;

        for( Ciudad p = ciudad1; p != null; p = p.darSiguiente( ) )
        {
            // Verifica que todos los vuelos de la ciudad referenciada por "p" tengan c�digos �nicos
            for( Vuelo q = p.darPrimerVuelo( ); q != null; q = q.darVueloSiguiente( ) )
            {
                assert contarCodigo( q.darCodigo( ) ) == 1 : "Los c�digos de los vuelos deber�an ser �nicos";
            }

            // Verifica que el nombre de la ciudad referenciada por "p" sea �nico
            assert contarNombre( p.darNombre( ) ) == 1 : "El nombre de las ciudades deber�a ser �nico";

            // Verifica que el nombre de la ciudad referenciada por "p" sea distinto al de la ciudad de origen
            assert !origen.darNombre( ).equalsIgnoreCase( p.darNombre( ) ) : "No deber�a haber una ciudad destino con el mismo nombre de la ciudad origen";
        }
    }

    /**
     * Cuenta el n�mero de ciudades que tienen el nombre que llega como par�metro
     * @param nombre Nombre de la ciudad que estamos buscando en la aerol�nea - nombre != null
     * @return N�mero de ciudades que tienen el nombre que llega como par�metro
     */
    private int contarNombre( String nombre )
    {
        int acum = 0;
        for( Ciudad p = ciudad1; p != null; p = p.darSiguiente( ) )
        {
            if( p.darNombre( ).equalsIgnoreCase( nombre ) )
                acum++;
        }
        return acum;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Punto de extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "respuesta1";
    }

    /**
     * Punto de extensi�n 2
     * @return respuesta2
     */
    public String metodo2(int codigoVueloSeleccionado )
    {
        return "respuesta2";
    }

    /**
     * Punto de extensi�n 3
     * @return respuesta3
     */
    public String metodo3( )
    {
        return "respuesta3";
    }

    /**
     * Punto de extensi�n 4
     * @return respuesta4
     */
    public String metodo4( )
    {
        return "respuesta4";
    }

    /**
     * Punto de extensi�n 5
     * @return respuesta5
     */
    public String metodo5( )
    {
        return "respuesta5";
    }
}