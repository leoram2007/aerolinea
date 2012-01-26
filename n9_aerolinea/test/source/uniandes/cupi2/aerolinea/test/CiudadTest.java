/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CiudadTest.java,v 1.12 2006/12/07 16:41:45 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario S�nchez - 07-dic-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.aerolinea.test;

import java.text.ParseException;
import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.aerolinea.mundo.AerolineaExcepcion;
import uniandes.cupi2.aerolinea.mundo.Ciudad;
import uniandes.cupi2.aerolinea.mundo.Vuelo;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Ciudad est�n correctamente implementados
 */
public class CiudadTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Ciudad ciudad;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Ciudad
     */
    private void setupEscenario1( )
    {
        ciudad = new Ciudad( "prueba", 0.5, 0.5 );
    }

    /**
     * Construye una nueva ciudad con datos de sus vuelos
     */
    private void setupEscenario2( )
    {
        try
        {
            ciudad = new Ciudad( "Madrid", 0.7777777777777778, 0.44 );
            ciudad.agregarVuelo( 455, "2005-12-20", "16", "35" );
            ciudad.agregarVuelo( 455, "2005-12-22", "16", "35" );
            ciudad.agregarVuelo( 455, "2005-12-24", "16", "35" );
            ciudad.agregarVuelo( 455, "2005-12-27", "16", "35" );
            ciudad.agregarVuelo( 456, "2005-12-25", "12", "05" );
            ciudad.agregarVuelo( 456, "2006-01-01", "12", "05" );
        }
        catch( Exception e )
        {
            fail( "No puede fallar la construcci�n a partir de un archivo v�lido" );
        }
    }

    /**
     * Prueba la adici�n de vuelos. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarVuelo. <br>
     * <b> Objetivo: </b> probar que el m�todo agregarVuelo() agrega correctamente vuelos a la lista de vuelos de una ciudad. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un vuelo cuyo c�digo no sea igual al de otro, se debe agregar sin problemas. <br>
     * 2. Al agregar un vuelo cuyo c�digo sea igual al de otro pero en una fecha diferente, se debe agregar sin problemas. <br>
     * 
     */
    public void testAgregarVueloOK( )
    {
        setupEscenario1( );

        try
        {
            for( int i = 0; i < 10; i++ )
            {
                ciudad.agregarVuelo( i, "2005-12-12", "12", "00" );

                ArrayList vuelos = ciudad.darVuelos( );
                assertEquals( "El n�mero de vuelos es incorrecto", i + 1, vuelos.size( ) );
            }

            for( int i = 0; i < 10; i++ )
            {
                ciudad.agregarVuelo( i, "2005-13-12", "12", "00" );

                ArrayList vuelos = ciudad.darVuelos( );
                assertEquals( "El n�mero de vuelos es incorrecto", 10 + i + 1, vuelos.size( ) );
            }

            // Se verifica que los vuelos est�n ordenados por c�digo
            ArrayList vuelos = ciudad.darVuelos( );
            for( int i = 0; i < vuelos.size( ) - 1; i++ )
            {
                Vuelo actual = ( Vuelo )vuelos.get( i );
                Vuelo siguiente = ( Vuelo )vuelos.get( i );

                assertTrue( "Los vuelos no est�n ordenados", actual.darCodigo( ) <= siguiente.darCodigo( ) );
            }

        }
        catch( Exception e )
        {
            fail( "No puede fallar porque no hay ning�n problema con los datos" );
        }
    }

    /**
     * Prueba la adici�n de vuelos. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarVuelo. <br>
     * <b> Objetivo: </b> probar que el m�todo agregarVuelo() arroje excepci�n en casos de error. <br>
     * <b> Resultados esperados: </b> <br>     
     * 1. Al agregar un vuelo cuya fecha tenga un formato incorrecto se debe arrojar excepci�n.
     */
    public void testAgregarVueloError( )
    {
        setupEscenario2( );
        ArrayList vuelos1 = ciudad.darVuelos( );


        try
        {
            ciudad.agregarVuelo( 450, "2005-12-2aaaa0", "10", "20" );
            fail( "Debe fallar porque el formato de la fecha no es correcto" );
        }
        catch( AerolineaExcepcion e1 )
        {
            fail( "No se deber�a arrojar esta excepci�n" );
        }
        catch( ParseException e1 )
        {
            // Se verifica que el n�mero de vuelos no haya cambiado
            ArrayList vuelos2 = ciudad.darVuelos( );
            assertEquals( "El n�mero de vuelos no es correcto", vuelos1.size( ), vuelos2.size( ) );
        }
        
    }

    /**
     * Prueba el m�todo que retorna la lista de vuelos. <br>
     * <b> M�todos a probar: </b> <br>
     * darVuelos. <br>
     * <b> Objetivo: </b> probar que el m�todo darVuelos() retorne correctamente la lista de los vuelos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la lista de los vuelos esta debe ser retornada de forma correcta.
     */
    public void testDarVuelos( )
    {
        setupEscenario1( );

        ArrayList vuelos = ciudad.darVuelos( );
        assertEquals( "El n�mero de vuelos es incorrecto", 0, vuelos.size( ) );

        setupEscenario2( );

        vuelos = ciudad.darVuelos( );
        assertEquals( "El n�mero de vuelos es incorrecto", 6, vuelos.size( ) );
    }

    /**
     * Prueba el m�todo para verificar la existencia de un vuelo. <br>
     * <b> M�todos a probar: </b> <br>
     * existeCodigo. <br>
     * <b> Objetivo: </b> probar que el m�todo existeCodigo() indique correctamente sobre la existencia de un c�digo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al dar el c�digo de un vuelo existente este debe ser encontrado. <br>
     * 2. Al dar el c�digo de un vuelo inexistente este no debe ser encontrado. <br>
     */
    public void testExisteCodigo( )
    {
        setupEscenario2( );

        assertTrue( "El c�digo indicado deber�a existir", ciudad.buscarVuelo( 455 ) != null );
        assertTrue( "El c�digo indicado deber�a existir", ciudad.buscarVuelo( 456 ) != null );
        assertFalse( "El c�digo indicado no deber�a existir", ciudad.buscarVuelo( 666 ) != null );
    }
}