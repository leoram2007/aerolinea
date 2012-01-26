/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Reserva.java,v 1.10 2006/12/07 20:24:38 da-romer Exp $
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

/**
 * Esta clase mantiene la informaci�n de la persona que hizo una reserva <br>
 * <b>inv: </b> <br>
 * nombre != null && nombre != "" <br>
 * cedula >= 0
 */
public class Reserva implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el nombre de la persona que hizo la reserva
     */
    private String nombre;

    /**
     * Es la c�dula de la persona que hizo la reserva
     */
    private int cedula;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una reserva con los datos de la persona que la realiz�
     * @param nombreP Es el nombre de la persona que hizo la reserva - nombreP != null && nombreP != ""
     * @param cedulaP Es la c�dula de la persona que hizo la reserva - cedulaP >= 0
     */
    public Reserva( String nombreP, int cedulaP )
    {
        nombre = nombreP;
        cedula = cedulaP;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la persona que hizo la reserva
     * @return nombre Nombre de la persona que hizo la reserva
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la c�dula de la persona que hizo la reserva
     * @return C�dula de la persona que hizo la reserva
     */
    public int darCedula( )
    {
        return cedula;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv: </b> <br>
     * nombre != null && nombre != "" <br>
     * cedula >= 0
     */
    private void verificarInvariante( )
    {
        assert nombre != null && !nombre.equals( "" );
        assert cedula >= 0;
    }
}