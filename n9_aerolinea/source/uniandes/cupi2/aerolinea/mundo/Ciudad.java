/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Ciudad.java,v 1.17 2007/04/13 04:17:20 carl-veg Exp $
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
 * Esta clase representa una ciudad a la que llegan vuelos de la aerol�nea <br>
 * <b>inv: </b> <br>
 * nombre != null && nombre != "" <br>
 * 0 < coordenada_x < 1 <br>
 * 0 < coordenada_y < 1 <br>
 * Los vuelos est�n ordenados por c�digo de forma ascendente
 */
public class Ciudad implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nombre de la ciudad
     */
    private String nombre;

    /**
     * La coordenada x de la ciudad en el plano mundial
     */
    private double coordenada_x;

    /**
     * La coordenada y de la ciudad en el plano mundial
     */
    private double coordenada_y;

    /**
     * La siguiente ciudad de la lista
     */
    private Ciudad siguiente;

    /**
     * Es el primer vuelo de la lista
     */
    private Vuelo vuelo1;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva ciudad <br>
     * <b>post: </b>Se construy� una nueva ciudad
     * @param nombreC El nombre de la nueva ciudad - nombreC != null && nombreC != ""
     * @param coord_xC La coordenada x de la nueva ciudad - 0 < coord_xC < 1
     * @param coord_yC La coordenada y de la nueva ciudad - 0 < coord_yC < 1
     */
    public Ciudad( String nombreC, double coord_xC, double coord_yC )
    {
        nombre = nombreC;
        coordenada_x = coord_xC;
        coordenada_y = coord_yC;
        siguiente = null;
        vuelo1 = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la coordenada x de la ciudad
     * @return Se retorn� la coordenada x de la ciudad
     */
    public double darCoordenadaX( )
    {
        return coordenada_x;
    }

    /**
     * Retorna la coordenada y de la ciudad
     * @return Se retorn� la coordenada y de la ciudad
     */
    public double darCoordenadaY( )
    {
        return coordenada_y;
    }

    /**
     * Retorna el nombre de la ciudad
     * @return Se retorn� el nombre de la ciudad
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna una lista con todos los vuelos que hay hacia esta ciudad
     * @return Se retorn� la lista de vuelos
     */
    public ArrayList darVuelos( )
    {
        ArrayList vuelos = new ArrayList( );
        for( Vuelo p = vuelo1; p != null; p = p.darVueloSiguiente( ) )
            vuelos.add( p );
        return vuelos;
    }

    /**
     * Agrega un nuevo vuelo a la ciudad dejando la lista ordenada ascendentemente por c�digo. <br>
     * <b>post: </b> Se agreg� un vuelo a la ciudad. <br>
     * @param codigo El c�digo del vuelo - c�digo >= 0 && no existe otro vuelo con ese c�digo
     * @param fecha La fecha en la que se realizar� el vuelo - fecha != null y fecha tiene el formato yyyy-MM-dd
     * @param horas La hora de despegue - 0 <= hora < 24
     * @param minutos Los minutos de la hora de despegue 0 <= hora < 60
     * @throws AerolineaExcepcion 
     * @throws ParseException Se lanza esta excepci�n si la fecha y hora de despegue no es v�lida
     */
    public void agregarVuelo( int codigo, String fecha, String horas, String minutos ) throws AerolineaExcepcion, ParseException
    {
        Vuelo nuevo = new Vuelo( codigo, fecha, horas, minutos );
        if( vuelo1 == null )
            vuelo1 = nuevo;
        else if( vuelo1.darCodigo( ) >= codigo )
        {
            vuelo1.insertarAntes( nuevo );
            vuelo1 = nuevo;
        }
        else
        {
            Vuelo vueloTemp0 = null;
            Vuelo vueloTemp1 = vuelo1;
            while( vueloTemp1 != null && vueloTemp1.darCodigo( ) < codigo )
            {
                vueloTemp0 = vueloTemp1;
                vueloTemp1 = vueloTemp1.darVueloSiguiente( );
            }
            vueloTemp0.insertarDespues( nuevo );
        }
        verificarInvariante( );
    }

    /**
     * Este m�todo localiza y retorna un vuelo, dado su c�digo
     * @param codigo El c�digo que se est� buscando
     * @return Se retorn� el vuelo con el c�digo pedido o null si no lo encuentra
     */
    public Vuelo buscarVuelo( int codigo )
    {
        for( Vuelo p = vuelo1; p != null; p = p.darVueloSiguiente( ) )
        {
            if( p.darCodigo( ) == codigo )
                return p;
        }
        return null;
    }

    /**
     * Retorna el primer vuelo que lleva a la ciudad
     * @return Se retorn� el primer vuelo que lleva a la ciudad
     */
    public Vuelo darPrimerVuelo( )
    {
        return vuelo1;
    }

    /**
     * Retorna la siguiente ciudad de la lista
     * @return Se retorn� la siguiente ciudad de la lista
     */
    public Ciudad darSiguiente( )
    {
        return siguiente;
    }

    /**
     * Cambia la siguiente ciudad de la lista
     * @param ciudad La ciudad que ahora ser� la siguiente
     */
    public void agregarDespues( Ciudad ciudad )
    {
        siguiente = ciudad;
    }

    /**
     * Desconecta la ciudad que le sigue en la lista a la ciudad actual. <br>
     * <b>pre: </b> No es el la �ltima ciudad de la lista. <br>
     * <b>post: </b> La ciudad siguiente a la actual fue desconectada de la lista.<br>
     */
    public void desconectarSiguiente( )
    {
        siguiente = siguiente.siguiente;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv: </b> <br>
     * nombre != null && nombre != "" <br>
     * 0 < coordenada_x < 1 <br>
     * 0 < coordenada_y < 1 <br>
     * Los vuelos est�n ordenados por c�digo de forma ascendente
     */
    public void verificarInvariante( )
    {
        assert nombre != null && !nombre.equals( "" ) : "El nombre de la ciudad debe estar definido";
        assert 0 < coordenada_x && coordenada_x < 1 : "La coordenada x no es v�lida";
        assert 0 < coordenada_y && coordenada_y < 1 : "La coordenada y no es v�lida";

        if( vuelo1 != null )
        {
            Vuelo actual = vuelo1;
            Vuelo siguiente = vuelo1.darVueloSiguiente( );
            while( siguiente != null )
            {
                assert actual.darCodigo( ) < siguiente.darCodigo( ) : "Los vuelos deber�an estar organizados de forma ascendente";
                actual = siguiente;
                siguiente = siguiente.darVueloSiguiente( );
            }
        }
    }
}