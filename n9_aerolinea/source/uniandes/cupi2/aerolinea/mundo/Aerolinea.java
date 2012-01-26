/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Aerolinea.java,v 1.21 2006/12/08 21:05:45 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 07-dic-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.aerolinea.mundo;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Esta es la clase que representa una aerolínea <br>
 * <b>inv: </b> <br>
 * origen != null <br>
 * No puede haber dos vuelos con el mismo código <br>
 * No puede haber ciudades con nombre repetido <br>
 * Ninguna ciudad destino puede ser la misma ciudad base <br>
 */
public class Aerolinea implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la ciudad base de la aerolínea, de la cual salen todos sus vuelos
     */
    private Ciudad origen;

    /**
     * Es la primera ciudad de la lista de ciudades a donde vuela la aerolínea
     */
    private Ciudad ciudad1;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una aerolínea de la cual se conoce únicamente la ciudad base
     * @param nombreCiudadOrigen El nombre de la ciudad base de la aerolínea - nombreCiudadBase != null && nombreCiudadOrigen != ""
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
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la ciudad base de la aerolínea
     * @return ciudadBase La ciudad base de la aerolínea
     */
    public Ciudad darCiudadBase( )
    {
        return origen;
    }

    /**
     * Retorna la ciudad con el nombre indicado o null si no la encuentra
     * @param nombreCiudad El nombre de la ciudad que se está buscando - nombreCiudad!=null
     * @return Se retornó la ciudad con el nombre indicado o null si no se encuentró.
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
     * Retorna la ciudad destino más cercana a un punto dado (en distancia euclidiana)
     * @param coord_x La coordenada x del punto
     * @param coord_y La coordenada y del punto
     * @return La ciudad destino más cercana al punto dado. Si no hay ninguna ciudad retorna null.
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
     * <b>post: </b>Se agregó una ciudad a la aerolínea. Inicialmente no hay vuelos a esa ciudad.
     * @param nombre El nombre de la nueva ciudad - nombre != null && nombre != ""
     * @param coord_x La coordenada x de la ciudad - 0 < coord_x < 1
     * @param coord_y La coordenada y de la ciudad - 0 < coord_y < 1
     * @throws AerolineaExcepcion Se lanza esta excepción si el nombre de la ciudad está repetido
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
     * <b>post: </b> Se eliminó la ciudad de la aerolínea, con todos los vuelos que tenía.
     * @param nombre El nombre de la ciudad que se debe eliminar - nombre != null
     * @throws AerolineaExcepcion Se lanza esta excepción si no se encuentra una ciudad con el nombre suministrado
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
     * Retorna una lista con las ciudades a las que vuela la aerolínea
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
     * <b>post: </b>Se agregó un vuelo a la ciudadDestino
     * @param ciudadDestino La ciudad a la que se le va a agregar el vuelo - ciudadDestino != null && ciudadDestino != ""
     * @param codigo El código del vuelo
     * @param fecha La fecha en la que se realizará el vuelo - fecha != null
     * @param horas La hora de despegue - horas!=null
     * @param minutos Los minutos de la hora de despegue - minutos!=null
     * @throws AerolineaExcepcion Se lanza esta excepción si el código no puede usarse para un vuelo a esta ciudad
     * @throws ParseException Se lanza esta excepción si la fecha y hora de despegue no es válida
     */
    public void agregarVuelo( Ciudad ciudadDestino, int codigo, String fecha, String horas, String minutos ) throws AerolineaExcepcion, ParseException
    {
        // Verificar que el código sea positivo
        if( codigo < 0 )
            throw new AerolineaExcepcion( "El código debe ser un valor numérico positivo" );

        // Verificar que el código del vuelo no exista
        if( contarCodigo( codigo ) != 0 )
            throw new AerolineaExcepcion( "El código " + codigo + " no puede usarse en un vuelo hacia " + ciudadDestino.darNombre( ) );

        // Agregar el vuelo
        ciudadDestino.agregarVuelo( codigo, fecha, horas, minutos );
        verificarInvariante( );
    }

    /**
     * Cuenta el número de veces que aparece el código de un vuelo en la aerolínea
     * @param codigo Código del vuelo que se está buscando
     * @return Retorna el número de vuelos de la aerolínea que tienen dicho código
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
     * No puede haber dos vuelos con el mismo código <br>
     * No puede haber ciudades con nombre repetido <br>
     * Ninguna ciudad destino puede ser la misma ciudad base <br>
     */
    private void verificarInvariante( )
    {
        assert origen != null;

        for( Ciudad p = ciudad1; p != null; p = p.darSiguiente( ) )
        {
            // Verifica que todos los vuelos de la ciudad referenciada por "p" tengan códigos únicos
            for( Vuelo q = p.darPrimerVuelo( ); q != null; q = q.darVueloSiguiente( ) )
            {
                assert contarCodigo( q.darCodigo( ) ) == 1 : "Los códigos de los vuelos deberían ser únicos";
            }

            // Verifica que el nombre de la ciudad referenciada por "p" sea único
            assert contarNombre( p.darNombre( ) ) == 1 : "El nombre de las ciudades debería ser único";

            // Verifica que el nombre de la ciudad referenciada por "p" sea distinto al de la ciudad de origen
            assert !origen.darNombre( ).equalsIgnoreCase( p.darNombre( ) ) : "No debería haber una ciudad destino con el mismo nombre de la ciudad origen";
        }
    }

    /**
     * Cuenta el número de ciudades que tienen el nombre que llega como parámetro
     * @param nombre Nombre de la ciudad que estamos buscando en la aerolínea - nombre != null
     * @return Número de ciudades que tienen el nombre que llega como parámetro
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
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Punto de extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "respuesta1";
    }

    /**
     * Punto de extensión 2
     * @return respuesta2
     */
    public String metodo2(int codigoVueloSeleccionado )
    {
        return "respuesta2";
    }

    /**
     * Punto de extensión 3
     * @return respuesta3
     */
    public String metodo3( )
    {
        return "respuesta3";
    }

    /**
     * Punto de extensión 4
     * @return respuesta4
     */
    public String metodo4( )
    {
        return "respuesta4";
    }

    /**
     * Punto de extensión 5
     * @return respuesta5
     */
    public String metodo5( )
    {
        return "respuesta5";
    }
}