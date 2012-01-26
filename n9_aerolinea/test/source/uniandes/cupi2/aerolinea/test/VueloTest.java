/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VueloTest.java,v 1.12 2007/04/13 04:17:31 carl-veg Exp $
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import uniandes.cupi2.aerolinea.mundo.AerolineaExcepcion;
import uniandes.cupi2.aerolinea.mundo.Silla;
import uniandes.cupi2.aerolinea.mundo.Vuelo;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Vuelo estén correctamente implementados
 */
public class VueloTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Vuelo vuelo;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo vuelo con datos básicos
     */
    private void setupEscenario1( )
    {
        try
        {
            vuelo = new Vuelo( 111, "2005-12-12", "14", "00" );
        }
        catch( Exception e )
        {
            fail( "No puede fallar la construcción con parámetros válidos" );
        }
    }

    /**
     * Construye un nuevo vuelo con algunas reservas
     */
    private void setupEscenario2( )
    {

        try
        {
            vuelo = new Vuelo( 123, "2005-12-19", "18", "40" );

            String silla = "2-A";
            vuelo.reservarSilla( silla, "Mauricio Rojas", 111 );

            silla = "4-D";
            vuelo.reservarSilla( silla, "Mauricio Rojas", 222 );

            silla = "6-E";
            vuelo.reservarSilla( silla, "Mauricio Rojas", 333 );

            silla = "2-B";
            vuelo.reservarSilla( silla, "Pablo Marmol", 212 );

            silla = "2-C";
            vuelo.reservarSilla( silla, "Pablo Marmol", 202 );

            silla = "4-A";
            vuelo.reservarSilla( silla, "Pablo Marmol", 101 );

            silla = "1-D";
            vuelo.reservarSilla( silla, "Maritza Parra", 109 );

            silla = "1-C";
            vuelo.reservarSilla( silla, "Maritza Parra", 205 );

            silla = "1-A";
            vuelo.reservarSilla( silla, "Maritza Parra", 106 );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            fail( "No puede fallar la construcción a partir de un archivo válido" );
        }
    }

    /**
     * Prueba el método que da la capacidad del vuelo. <br>
     * <b> Métodos a probar: </b> <br>
     * darCapacidad. <br>
     * <b> Objetivo: </b> probar que el método darCapacidad() retorne la capacidad correcta del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la capacidad del vuelo este debe ser retornado de forma correcta.
     */
    public void testDarCapacidad( )
    {
        setupEscenario1( );

        assertEquals( "La capacidad del vuelo está equivocada", Vuelo.NUMERO_FILAS * Vuelo.LETRAS.length, vuelo.darCapacidad( ) );
    }

    /**
     * Prueba el método que da la fecha y hora del vuelo. <br>
     * <b> Métodos a probar: </b> <br>
     * darFechaYHora. <br>
     * <b> Objetivo: </b> probar que el método darFechaYHora() retorne la fecha y hora correctas del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la fecha y hora del vuelo estas deben ser retornados de forma correcta.
     */
    public void testDarFechaYHora( )
    {
        setupEscenario1( );

        String cadenaEsperada = "2005-12-12 14:00";
        assertEquals( "La fecha y hora no están bien formadas", cadenaEsperada, vuelo.darFechaYHora( ) );
    }

    /**
     * Prueba el método para que da el número de sillas disponibles del vuelo. <br>
     * <b> Métodos a probar: </b> <br>
     * darNumeroSillasDisponible. <br>
     * <b> Objetivo: </b> probar que el método darNumeroSillasDisponible() retorne el número de sillas correcta del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el número de sillas disponibles del vuelo este debe ser retornado de forma correcta.
     */
    public void testDarNumeroSillasDisponibles( )
    {
        setupEscenario2( );

        assertEquals( "El número de sillas disponibles es incorrecto", 66, vuelo.darNumeroSillasDisponibles( ) );
    }

    /**
     * Prueba el método para reservar sillas. <br>
     * <b> Métodos a probar: </b> <br>
     * reservarSilla. <br>
     * <b> Objetivo: </b> probar que el método reservarSilla() realice las reservas de las sillas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al apartar una silla que no está reservada, la reserva debe poder realizarse. <br>
     */
    public void testReservarSillaOK( )
    {
        setupEscenario2( );

        try
        {
            Silla[][] sillas = vuelo.darSillas( );
            int disponibles1 = vuelo.darNumeroSillasDisponibles( );
            assertFalse( "La silla no debería estar reservada", sillas[ 8 ][ 0 ].estaReservada( ) );
            assertFalse( "La silla no debería estar reservada", sillas[ 8 ][ 1 ].estaReservada( ) );

            String sillaReserva1 = "8-A";
            String sillaReserva2 = "8-B";
            vuelo.reservarSilla( sillaReserva1, "Pedro", 123 );
            vuelo.reservarSilla( sillaReserva2, "Marta", 235 );

            sillas = vuelo.darSillas( );
            assertTrue( "La silla debería estar reservada", sillas[ 8 ][ 0 ].estaReservada( ) );
            assertTrue( "La silla debería estar reservada", sillas[ 8 ][ 1 ].estaReservada( ) );

            int disponibles2 = vuelo.darNumeroSillasDisponibles( );

            assertEquals( "El número de sillas disponibles no es correcto", disponibles1 - 2, disponibles2 );
        }
        catch( AerolineaExcepcion e )
        {
            fail( "No debería fallar" );
        }
    }

    /**
     * Prueba el método para reservar sillas. <br>
     * <b> Métodos a probar: </b> <br>
     * reservarSilla. <br>
     * <b> Objetivo: </b> probar que el método reservarSilla() arroje excepción al tratar de realizar una reserva con una cédula con la que ya se ha reservado. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al apartar una silla con una cédula utilizada en otra reserva se debe arrojar excepción. <br>
     */
    public void testReservarSillaError( )
    {
        setupEscenario2( );

        try
        {
            String sillaReserva = "9-A";
            vuelo.reservarSilla( sillaReserva, "Pedro", 109 );
            fail( "Debería fallar porque la cédula está repetida" );
        }
        catch( AerolineaExcepcion e )
        {
            // Se verifica que la reserva no se halla realizado
            Silla[][] sillas = vuelo.darSillas( );

            assertFalse( "La silla no debería estar reservada", sillas[ 9 ][ 0 ].estaReservada( ) );
        }
    }

    /**
     * Prueba el método para generar el manifiesto del embarque. <br>
     * <b> Métodos a probar: </b> <br>
     * generarManifiestoEmbarque. <br>
     * <b> Objetivo: </b> probar que el método generarManifiestoEmbarque() genere correctamente el archivo con la información del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al generar el archivo con la información del vuelo este debe tener el formato correcto. <br>
     */
    public void testGenerarMamifiestoEmbarque( )
    {
        setupEscenario2( );

        File archivoManifiesto = new File( "./test/data/manifiesto.txt" );

        // Si existe ya un manifiesto generado hay que borrarlo
        if( archivoManifiesto.exists( ) )
        {
            archivoManifiesto.delete( );
        }

        // Generar el nuevo manifiesto
        try
        {
            vuelo.generarManifiestoEmbarque( archivoManifiesto );
        }
        catch( IOException e )
        {
            fail( "No debería producirse ninguna excepción: " + e.getMessage( ) );
        }

        try
        {
            // Verificar que el manifiesto generado tenga los datos esperados
            BufferedReader br = new BufferedReader( new FileReader( archivoManifiesto ) );
            String datosVuelo = br.readLine( );
            assertTrue( "El manifiesto no tiene el número del vuelo", datosVuelo.indexOf( "123" ) != -1 );
            assertTrue( "El manifiesto no tiene la fecha del vuelo", datosVuelo.indexOf( "2005-12-19" ) != -1 );
            assertTrue( "El manifiesto no tiene la fecha del vuelo", datosVuelo.indexOf( "18:40" ) != -1 );

            char[] letras = new char[]{ 'A', 'B', 'C', 'D', 'E' };
            String lineaSilla = br.readLine( );
            for( int i = 0; i < 14; i++ )
            {
                for( int j = 0; j < letras.length; j++ )
                {
                    assertTrue( "El manifiesto no tiene la información de la fila " + i, lineaSilla.indexOf( "" + i ) != -1 );
                    assertTrue( "El manifiesto no tiene la información de la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( letras[ j ] ) != -1 );

                    if( i == 2 && letras[ j ] == 'A' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Mauricio Rojas" ) != -1 );
                    }
                    else if( i == 4 && letras[ j ] == 'D' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Mauricio Rojas" ) != -1 );
                    }
                    else if( i == 6 && letras[ j ] == 'E' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Mauricio Rojas" ) != -1 );
                    }
                    else if( i == 1 && letras[ j ] == 'D' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Maritza Parra" ) != -1 );
                    }
                    else if( i == 1 && letras[ j ] == 'C' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Maritza Parra" ) != -1 );
                    }
                    else if( i == 1 && letras[ j ] == 'A' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Maritza Parra" ) != -1 );
                    }
                    else if( i == 2 && letras[ j ] == 'B' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Pablo Marmol" ) != -1 );
                    }
                    else if( i == 4 && letras[ j ] == 'A' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Pablo Marmol" ) != -1 );
                    }
                    else if( i == 2 && letras[ j ] == 'C' )
                    {
                        assertTrue( "El manifiesto no tiene la información de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Pablo Marmol" ) != -1 );
                    }
                    else
                    {
                        assertTrue( "El manifiesto no indica que la silla " + i + "-" + letras[ j ] + " está vacía", lineaSilla.indexOf( "vacía" ) != -1 );
                    }

                    lineaSilla = br.readLine( );
                }
            }

            br.close( );
            archivoManifiesto.delete( );
        }
        catch( IOException e )
        {
            fail( "No debería producirse ninguna excepción: " + e.getMessage( ) );
        }
    }
}