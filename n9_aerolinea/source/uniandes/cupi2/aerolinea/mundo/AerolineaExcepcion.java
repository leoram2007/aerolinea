/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AerolineaExcepcion.java,v 1.3 2006/11/26 11:03:25 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 09-dic-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.aerolinea.mundo;

/**
 * Esta excepción se lanza si hay un problema en alguno de los métodos del mundo
 */
@SuppressWarnings("serial")
public class AerolineaExcepcion extends Exception
{
    /**
     * Construye la excepción con el mensaje que describe el problema
     * @param mensaje Mensaje que describe la causa de la excepción
     */
    public AerolineaExcepcion( String mensaje )
    {
        super( mensaje );
    }
}
