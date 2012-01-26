/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AerolineaTest.java,v 1.10 2006/12/07 16:32:13 da-romer Exp $
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
import uniandes.cupi2.aerolinea.mundo.Aerolinea;
import uniandes.cupi2.aerolinea.mundo.AerolineaExcepcion;
import uniandes.cupi2.aerolinea.mundo.Ciudad;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Aerolinea estén correctamente implementados
 */
public class AerolineaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Aerolinea aerolinea;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva ciudad a partir de los datos contenidos en un archivo
     */
    private void setupEscenario1( )
    {
        try
        {
            aerolinea = new Aerolinea( "Bogota", 0.59, 0.625 );
            aerolinea.agregarCiudad( "Houston", 0.5065913370998116, 0.49 );
            Ciudad houston = aerolinea.darCiudadMasCercana( 0.5065913370998116, 0.49 );
            aerolinea.agregarVuelo( houston, 111, "2005-12-22", "05", "30" );
            aerolinea.agregarCiudad( "Londres", 0.783427495291902, 0.35333333333333333 );
            aerolinea.agregarCiudad( "Paris", 0.7984934086629002, 0.38 );
            aerolinea.agregarCiudad( "Roma", 0.9999, 0.55 );
        }
        catch( Exception e )
        {
            fail( "No puede fallar la construcción a partir de un archivo válido" );
        }
    }

    /**
     * Prueba la adición de ciudades. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarCiudad, darCiudades. <br>
     * <b> Objetivo: </b> Probar que el método agregarCiudad() agrega correctamente ciudades a la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar una ciudad cuyo nombre no corresponda a ninguna ciudad registrada, esta debe ser insertada sin errores.
     */
    public void testAgregarCiudadOK( )
    {
        setupEscenario1( );

        ArrayList ciudades1 = aerolinea.darCiudades( );

        try
        {
            aerolinea.agregarCiudad( "Milano", 0.7, 0.30 );

            boolean encontre = false;
            ArrayList ciudades2 = aerolinea.darCiudades( );
            for( int i = 0; i < ciudades2.size( ); i++ )
            {
                Ciudad c = ( Ciudad )ciudades2.get( i );
                if( c.darNombre( ).equals( "Milano" ) )
                    encontre = true;
            }

            assertTrue( "No se encontró la nueva ciudad", encontre );

            assertEquals( "El número de ciudades no es el correcto", ciudades1.size( ) + 1, ciudades2.size( ) );
        }
        catch( AerolineaExcepcion e )
        {
            fail( "No debería fallar" );
        }
    }

    /**
     * Prueba la adición de ciudades. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarCiudad, darCiudades. <br>
     * <b> Objetivo: </b> Probar que el método agregarCiudad() arroje excepción al tratar de agregas una ciudad cuyo nombre ya pertenece a otra registrada. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de agregar una ciudad cuyo nombre sea igual al de la ciudad base se debe arrojar error. <br>
     * 2. Al tratar de agregar una ciudad cuyo nombre sea igual al de otra ciudad ya registrada se debe arrojar excepción.
     */
    public void testAgregarCiudadError( )
    {
        setupEscenario1( );

        try
        {
            aerolinea.agregarCiudad( "Bogota", 0.59, 0.625 );
            fail( "Debe fallar porque ya hay otra ciudad con el mismo nombre" );

        }
        catch( AerolineaExcepcion e )
        {
            try
            {
                aerolinea.agregarCiudad( "Paris", 0.7, 0.30 );
                fail( "Debe fallar porque ya hay otra ciudad con el mismo nombre" );
            }
            catch( AerolineaExcepcion e1 )
            {
                // Se verifica que el número de ciudades no haya cambiado
                ArrayList ciudades = aerolinea.darCiudades( );

                assertEquals( "El número de ciudades no debería haber cambiado", 4, ciudades.size( ) );
            }

        }
    }

    /**
     * Prueba la adición de vuelos. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarVuelo. <br>
     * <b> Objetivo: </b> Probar que el método agregarVuelo() agrega correctamente vuelos a la lista de vuelos de una ciudad. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un vuelo a una ciudad existente cuyo código no sea igual al de otro, se debe agregar sin problemas. <br>
     */
    public void testAgregarVuelo( )
    {
        setupEscenario1( );

        ArrayList ciudades = aerolinea.darCiudades( );
        Ciudad ciudadDestino = ( Ciudad )ciudades.get( 0 );
        ArrayList vuelosAntes = ciudadDestino.darVuelos( );

        try
        {
            aerolinea.agregarVuelo( ciudadDestino, 123, "2005-12-12", "12", "00" );

            ArrayList vuelos = ciudadDestino.darVuelos( );
            assertEquals( "No se agregó el vuelo", vuelosAntes.size( ) + 1, vuelos.size( ) );
        }
        catch( Exception e )
        {
            fail( "No debería fallar" );
        }
    }

    /**
     * Prueba la adición de vuelos. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarVuelo. <br>
     * <b> Objetivo: </b> Probar que el método agregarVuelo() arroja excepción en casos de error. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de agregar un vuelo con código negativo se debe arrojar excepción. <br>
     * 2. Al tratar de agregar un vuelo con el mismo código y fecha de otro vuelo hacia la misma ciudad se debe arrojar excepción. <br>
     * 3. Al tratar de agregar un vuelo con un formato de fecha inválido se debe arrojar excepción.
     */
    public void testAgregarVueloError( )
    {
        setupEscenario1( );

        Ciudad houston = aerolinea.darCiudadMasCercana( 0.5065913370998116, 0.49 );
        ArrayList vuelosAntes = houston.darVuelos( );

        try
        {
            aerolinea.agregarVuelo( houston, -100, "2005-12-22", "05", "30" );
            fail( "No se debería haber agregado el vuelo" );
        }
        catch( Exception e )
        {
            try
            {
                aerolinea.agregarVuelo( houston, 111, "2005-12-22", "05", "30" );
                fail( "No se debería haber agregado el vuelo" );
            }
            catch( AerolineaExcepcion e1 )
            {
                try
                {
                    aerolinea.agregarVuelo( houston, 100, "fff12-2005-28", "24", "70" );
                    fail( "No se debería haber agregado el vuelo" );
                }
                catch( AerolineaExcepcion e2 )
                {

                    fail( "No debería arrojar esta excepción" );
                }
                catch( ParseException e2 )
                {
                    // Se verifica que el número de los vuelos hacia la ciudad no haya cambiado
                    ArrayList vuelos = houston.darVuelos( );
                    assertEquals( "No se agregó el vuelo", vuelosAntes.size( ), vuelos.size( ) );
                }
            }
            catch( ParseException e1 )
            {
                fail( "No debería arrojar esta excepción" );
            }
        }
    }

    /**
     * Prueba el método que retorna la ciudad base. <br>
     * <b> Métodos a probar: </b> <br>
     * darCiudadBase. <br>
     * <b> Objetivo: </b> probar que el método darCiudadBase() retorna correctamente la ciudad base. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la ciudad base esta debe ser retornada de forma correcta.
     */
    public void testDarCiudadBase( )
    {
        setupEscenario1( );

        Ciudad ciudadBase = aerolinea.darCiudadBase( );
        assertEquals( "El nombre de la ciudad base es incorrecto", "Bogota", ciudadBase.darNombre( ) );
    }

    /**
     * Prueba el método que retorna las ciudades. <br>
     * <b> Métodos a probar: </b> <br>
     * darCiudadades. <br>
     * <b> Objetivo: </b> probar que el método darCiudades() retorna correctamente la lista de ciudades. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir las ciudades esta lista debe ser retornada de forma correcta.
     */
    public void testDarCiudades( )
    {
        setupEscenario1( );

        ArrayList ciudades = aerolinea.darCiudades( );

        assertEquals( "El número de ciudades es incorrecto", 4, ciudades.size( ) );

        for( int i = 0; i < 3; i++ )
        {
            Ciudad c = ( Ciudad )ciudades.get( i );
            assertTrue( "El nombre de la ciudad es incorrecto", "Houston".equals( c.darNombre( ) ) || "Paris".equals( c.darNombre( ) ) || "Londres".equals( c.darNombre( ) ) || "Roma".equals( c.darNombre( ) ) );
        }
    }

    /**
     * Prueba el método que retorna la ciudad más cercana a una coordenada. <br>
     * <b> Métodos a probar: </b> <br>
     * darCiudadadMasCercana. <br>
     * <b> Objetivo: </b> probar que el método darCiudadMasCercana() retorna correctamente la ciudad más próxima a un punto. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la ciudad más cercana a un punto esta debe ser retornada de forma correcta.
     */
    public void testDarCiudadMasCercana( )
    {
        setupEscenario1( );

        Ciudad c1 = aerolinea.darCiudadMasCercana( 0.55, 0.5 );
        assertEquals( "La ciudad más cercana debería ser Houston", "Houston", c1.darNombre( ) );

        Ciudad c2 = aerolinea.darCiudadMasCercana( 0.78, 0.3 );
        assertEquals( "La ciudad más cercana debería ser Londres", "Londres", c2.darNombre( ) );
    }

    /**
     * Prueba el método para eliminar una ciudad. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminarCiudad. <br>
     * <b> Objetivo: </b> probar que el método eliminarCiudad() elimina correctamente las ciudades de la aerolínea. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar una ciudad existente, este debe desaparecer de la lista de ciudades.
     */
    public void testEliminarCiudad( )
    {
        setupEscenario1( );

        ArrayList ciudades = aerolinea.darCiudades( );
        Ciudad ciudadEliminada = ( Ciudad )ciudades.get( 1 );
        try
        {
            // Elimina una ciudad del medio
            aerolinea.eliminarCiudad( ciudadEliminada.darNombre( ) );
            ArrayList ciudades2 = aerolinea.darCiudades( );
            assertEquals( "El número de ciudades no es correcto", ciudades.size( ) - 1, ciudades2.size( ) );
            for( int i = 0; i < ciudades2.size( ); i++ )
            {
                Ciudad c = ( Ciudad )ciudades2.get( i );
                assertFalse( c.darNombre( ).equals( ciudadEliminada.darNombre( ) ) );
            }
        }
        catch( AerolineaExcepcion e )
        {
            fail( "No debería fallar nada" );
        }

        ciudadEliminada = ( Ciudad )ciudades.get( 0 );

        try
        {
            // Elimina la primera ciudad de la lista
            aerolinea.eliminarCiudad( ciudadEliminada.darNombre( ) );

            ArrayList ciudades2 = aerolinea.darCiudades( );
            assertEquals( "El número de ciudades no es correcto", ciudades.size( ) - 2, ciudades2.size( ) );
            for( int i = 0; i < ciudades2.size( ); i++ )
            {
                Ciudad c = ( Ciudad )ciudades2.get( i );
                assertFalse( c.darNombre( ).equals( ciudadEliminada.darNombre( ) ) );
            }
        }
        catch( AerolineaExcepcion e )
        {
            fail( "No debería fallar nada" );
        }

        ciudadEliminada = ( Ciudad )ciudades.get( 3 );

        try
        {
            // Elimina la ultima ciudad de la lista
            aerolinea.eliminarCiudad( ciudadEliminada.darNombre( ) );

            ArrayList ciudades2 = aerolinea.darCiudades( );
            assertEquals( "El número de ciudades no es correcto", ciudades.size( ) - 3, ciudades2.size( ) );
            for( int i = 0; i < ciudades2.size( ); i++ )
            {
                Ciudad c = ( Ciudad )ciudades2.get( i );
                assertFalse( c.darNombre( ).equals( ciudadEliminada.darNombre( ) ) );
            }
        }
        catch( AerolineaExcepcion e )
        {
            fail( "No debería fallar nada" );
        }

    }

    /**
     * Prueba el método para eliminar una ciudad. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminarCiudad. <br>
     * <b> Objetivo: </b> probar que el método eliminarCiudad() arroja excepción al tratar de eliminar una ciudad que no exista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de eliminar una ciudad inexistente se debe arrojar excepción.
     */
    public void testEliminarCiudadError( )
    {
        setupEscenario1( );
        ArrayList ciudades1 = aerolinea.darCiudades( );

        try
        {
            aerolinea.eliminarCiudad( "La ciudad" );
            fail( "No debería haber eliminado la ciudad" );
        }
        catch( AerolineaExcepcion e )
        {
            // Se verifica que el número de ciudades no se haya alterado
            ArrayList ciudades2 = aerolinea.darCiudades( );
            assertEquals( "El número de ciudades no debería haber cambiado", ciudades1.size( ), ciudades2.size( ) );

        }
    }
}