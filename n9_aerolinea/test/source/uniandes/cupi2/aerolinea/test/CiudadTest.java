/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CiudadTest.java,v 1.12 2006/12/07 16:41:45 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 07-dic-2005
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
 * Esta es la clase usada para verificar que los métodos de la clase Ciudad estén correctamente implementados
 */
public class CiudadTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Ciudad ciudad;

    // -----------------------------------------------------------------
    // Métodos
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
            fail( "No puede fallar la construcción a partir de un archivo válido" );
        }
    }

    /**
     * Prueba la adición de vuelos. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarVuelo. <br>
     * <b> Objetivo: </b> probar que el método agregarVuelo() agrega correctamente vuelos a la lista de vuelos de una ciudad. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un vuelo cuyo código no sea igual al de otro, se debe agregar sin problemas. <br>
     * 2. Al agregar un vuelo cuyo código sea igual al de otro pero en una fecha diferente, se debe agregar sin problemas. <br>
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
                assertEquals( "El número de vuelos es incorrecto", i + 1, vuelos.size( ) );
            }

            for( int i = 0; i < 10; i++ )
            {
                ciudad.agregarVuelo( i, "2005-13-12", "12", "00" );

                ArrayList vuelos = ciudad.darVuelos( );
                assertEquals( "El número de vuelos es incorrecto", 10 + i + 1, vuelos.size( ) );
            }

            // Se verifica que los vuelos estén ordenados por código
            ArrayList vuelos = ciudad.darVuelos( );
            for( int i = 0; i < vuelos.size( ) - 1; i++ )
            {
                Vuelo actual = ( Vuelo )vuelos.get( i );
                Vuelo siguiente = ( Vuelo )vuelos.get( i );

                assertTrue( "Los vuelos no están ordenados", actual.darCodigo( ) <= siguiente.darCodigo( ) );
            }

        }
        catch( Exception e )
        {
            fail( "No puede fallar porque no hay ningún problema con los datos" );
        }
    }

    /**
     * Prueba la adición de vuelos. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarVuelo. <br>
     * <b> Objetivo: </b> probar que el método agregarVuelo() arroje excepción en casos de error. <br>
     * <b> Resultados esperados: </b> <br>     
     * 1. Al agregar un vuelo cuya fecha tenga un formato incorrecto se debe arrojar excepción.
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
            fail( "No se debería arrojar esta excepción" );
        }
        catch( ParseException e1 )
        {
            // Se verifica que el número de vuelos no haya cambiado
            ArrayList vuelos2 = ciudad.darVuelos( );
            assertEquals( "El número de vuelos no es correcto", vuelos1.size( ), vuelos2.size( ) );
        }
        
    }

    /**
     * Prueba el método que retorna la lista de vuelos. <br>
     * <b> Métodos a probar: </b> <br>
     * darVuelos. <br>
     * <b> Objetivo: </b> probar que el método darVuelos() retorne correctamente la lista de los vuelos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la lista de los vuelos esta debe ser retornada de forma correcta.
     */
    public void testDarVuelos( )
    {
        setupEscenario1( );

        ArrayList vuelos = ciudad.darVuelos( );
        assertEquals( "El número de vuelos es incorrecto", 0, vuelos.size( ) );

        setupEscenario2( );

        vuelos = ciudad.darVuelos( );
        assertEquals( "El número de vuelos es incorrecto", 6, vuelos.size( ) );
    }

    /**
     * Prueba el método para verificar la existencia de un vuelo. <br>
     * <b> Métodos a probar: </b> <br>
     * existeCodigo. <br>
     * <b> Objetivo: </b> probar que el método existeCodigo() indique correctamente sobre la existencia de un código. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al dar el código de un vuelo existente este debe ser encontrado. <br>
     * 2. Al dar el código de un vuelo inexistente este no debe ser encontrado. <br>
     */
    public void testExisteCodigo( )
    {
        setupEscenario2( );

        assertTrue( "El código indicado debería existir", ciudad.buscarVuelo( 455 ) != null );
        assertTrue( "El código indicado debería existir", ciudad.buscarVuelo( 456 ) != null );
        assertFalse( "El código indicado no debería existir", ciudad.buscarVuelo( 666 ) != null );
    }
}