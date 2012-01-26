/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Silla.java,v 1.7 2006/12/07 20:24:38 da-romer Exp $
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

/**
 * Esta clase representa una silla de un vuelo <br>
 * <b>inv: </b> <br>
 * fila >= 0 <br>
 */
public class Silla implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La fila en la que se encuentra la silla
     */
    private int fila;

    /**
     * La letra de la silla
     */
    private char letra;

    /**
     * Es la reserva que hay para esta silla
     */
    private Reserva reserva;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la silla con la fila y letra especificadas, sin reserva
     * @param filaS La fila en la que se encuentra la silla - filaS >= 0
     * @param letraS La letra de la silla
     */
    public Silla( int filaS, char letraS )
    {
        fila = filaS;
        letra = letraS;
        reserva = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la fila de la silla
     * @return fila La fila de la silla
     */
    public int darFila( )
    {
        return fila;
    }

    /**
     * Retorna la letra de la silla
     * @return letra La letra de la silla
     */
    public char darLetra( )
    {
        return letra;
    }

    /**
     * Indica si la silla está reservada.
     * @return Se retornó true si hay una reserva para la silla o false en caso contrario
     */
    public boolean estaReservada( )
    {
        return reserva != null;
    }

    /**
     * Reserva la silla
     * @param reservaSilla La reserva que se va a asociar a esta silla
     */
    public void reservar( Reserva reservaSilla )
    {
        reserva = reservaSilla;
    }

    /**
     * Retorna la reserva para esta silla. Si no hay ninguna reserva retorna null.
     * @return Se retornó la reserva de la silla
     */
    public Reserva darReserva( )
    {
        return reserva;
    }

    /**
     * Retorna una cadena que identifica a la silla
     * @return Se retornó una cadena que identifica a la silla
     */
    public String toString( )
    {
        return "" + fila + letra;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv: </b> <br>
     * fila >= 0
     */
    private void verificarInvariante( )
    {
        assert fila >= 0;
    }
}