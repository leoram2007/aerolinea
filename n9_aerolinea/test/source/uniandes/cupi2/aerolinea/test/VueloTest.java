/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VueloTest.java,v 1.12 2007/04/13 04:17:31 carl-veg Exp $
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import uniandes.cupi2.aerolinea.mundo.AerolineaExcepcion;
import uniandes.cupi2.aerolinea.mundo.Silla;
import uniandes.cupi2.aerolinea.mundo.Vuelo;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Vuelo est�n correctamente implementados
 */
public class VueloTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Vuelo vuelo;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo vuelo con datos b�sicos
     */
    private void setupEscenario1( )
    {
        try
        {
            vuelo = new Vuelo( 111, "2005-12-12", "14", "00" );
        }
        catch( Exception e )
        {
            fail( "No puede fallar la construcci�n con par�metros v�lidos" );
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
            fail( "No puede fallar la construcci�n a partir de un archivo v�lido" );
        }
    }

    /**
     * Prueba el m�todo que da la capacidad del vuelo. <br>
     * <b> M�todos a probar: </b> <br>
     * darCapacidad. <br>
     * <b> Objetivo: </b> probar que el m�todo darCapacidad() retorne la capacidad correcta del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la capacidad del vuelo este debe ser retornado de forma correcta.
     */
    public void testDarCapacidad( )
    {
        setupEscenario1( );

        assertEquals( "La capacidad del vuelo est� equivocada", Vuelo.NUMERO_FILAS * Vuelo.LETRAS.length, vuelo.darCapacidad( ) );
    }

    /**
     * Prueba el m�todo que da la fecha y hora del vuelo. <br>
     * <b> M�todos a probar: </b> <br>
     * darFechaYHora. <br>
     * <b> Objetivo: </b> probar que el m�todo darFechaYHora() retorne la fecha y hora correctas del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la fecha y hora del vuelo estas deben ser retornados de forma correcta.
     */
    public void testDarFechaYHora( )
    {
        setupEscenario1( );

        String cadenaEsperada = "2005-12-12 14:00";
        assertEquals( "La fecha y hora no est�n bien formadas", cadenaEsperada, vuelo.darFechaYHora( ) );
    }

    /**
     * Prueba el m�todo para que da el n�mero de sillas disponibles del vuelo. <br>
     * <b> M�todos a probar: </b> <br>
     * darNumeroSillasDisponible. <br>
     * <b> Objetivo: </b> probar que el m�todo darNumeroSillasDisponible() retorne el n�mero de sillas correcta del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el n�mero de sillas disponibles del vuelo este debe ser retornado de forma correcta.
     */
    public void testDarNumeroSillasDisponibles( )
    {
        setupEscenario2( );

        assertEquals( "El n�mero de sillas disponibles es incorrecto", 66, vuelo.darNumeroSillasDisponibles( ) );
    }

    /**
     * Prueba el m�todo para reservar sillas. <br>
     * <b> M�todos a probar: </b> <br>
     * reservarSilla. <br>
     * <b> Objetivo: </b> probar que el m�todo reservarSilla() realice las reservas de las sillas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al apartar una silla que no est� reservada, la reserva debe poder realizarse. <br>
     */
    public void testReservarSillaOK( )
    {
        setupEscenario2( );

        try
        {
            Silla[][] sillas = vuelo.darSillas( );
            int disponibles1 = vuelo.darNumeroSillasDisponibles( );
            assertFalse( "La silla no deber�a estar reservada", sillas[ 8 ][ 0 ].estaReservada( ) );
            assertFalse( "La silla no deber�a estar reservada", sillas[ 8 ][ 1 ].estaReservada( ) );

            String sillaReserva1 = "8-A";
            String sillaReserva2 = "8-B";
            vuelo.reservarSilla( sillaReserva1, "Pedro", 123 );
            vuelo.reservarSilla( sillaReserva2, "Marta", 235 );

            sillas = vuelo.darSillas( );
            assertTrue( "La silla deber�a estar reservada", sillas[ 8 ][ 0 ].estaReservada( ) );
            assertTrue( "La silla deber�a estar reservada", sillas[ 8 ][ 1 ].estaReservada( ) );

            int disponibles2 = vuelo.darNumeroSillasDisponibles( );

            assertEquals( "El n�mero de sillas disponibles no es correcto", disponibles1 - 2, disponibles2 );
        }
        catch( AerolineaExcepcion e )
        {
            fail( "No deber�a fallar" );
        }
    }

    /**
     * Prueba el m�todo para reservar sillas. <br>
     * <b> M�todos a probar: </b> <br>
     * reservarSilla. <br>
     * <b> Objetivo: </b> probar que el m�todo reservarSilla() arroje excepci�n al tratar de realizar una reserva con una c�dula con la que ya se ha reservado. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al apartar una silla con una c�dula utilizada en otra reserva se debe arrojar excepci�n. <br>
     */
    public void testReservarSillaError( )
    {
        setupEscenario2( );

        try
        {
            String sillaReserva = "9-A";
            vuelo.reservarSilla( sillaReserva, "Pedro", 109 );
            fail( "Deber�a fallar porque la c�dula est� repetida" );
        }
        catch( AerolineaExcepcion e )
        {
            // Se verifica que la reserva no se halla realizado
            Silla[][] sillas = vuelo.darSillas( );

            assertFalse( "La silla no deber�a estar reservada", sillas[ 9 ][ 0 ].estaReservada( ) );
        }
    }

    /**
     * Prueba el m�todo para generar el manifiesto del embarque. <br>
     * <b> M�todos a probar: </b> <br>
     * generarManifiestoEmbarque. <br>
     * <b> Objetivo: </b> probar que el m�todo generarManifiestoEmbarque() genere correctamente el archivo con la informaci�n del vuelo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al generar el archivo con la informaci�n del vuelo este debe tener el formato correcto. <br>
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
            fail( "No deber�a producirse ninguna excepci�n: " + e.getMessage( ) );
        }

        try
        {
            // Verificar que el manifiesto generado tenga los datos esperados
            BufferedReader br = new BufferedReader( new FileReader( archivoManifiesto ) );
            String datosVuelo = br.readLine( );
            assertTrue( "El manifiesto no tiene el n�mero del vuelo", datosVuelo.indexOf( "123" ) != -1 );
            assertTrue( "El manifiesto no tiene la fecha del vuelo", datosVuelo.indexOf( "2005-12-19" ) != -1 );
            assertTrue( "El manifiesto no tiene la fecha del vuelo", datosVuelo.indexOf( "18:40" ) != -1 );

            char[] letras = new char[]{ 'A', 'B', 'C', 'D', 'E' };
            String lineaSilla = br.readLine( );
            for( int i = 0; i < 14; i++ )
            {
                for( int j = 0; j < letras.length; j++ )
                {
                    assertTrue( "El manifiesto no tiene la informaci�n de la fila " + i, lineaSilla.indexOf( "" + i ) != -1 );
                    assertTrue( "El manifiesto no tiene la informaci�n de la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( letras[ j ] ) != -1 );

                    if( i == 2 && letras[ j ] == 'A' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Mauricio Rojas" ) != -1 );
                    }
                    else if( i == 4 && letras[ j ] == 'D' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Mauricio Rojas" ) != -1 );
                    }
                    else if( i == 6 && letras[ j ] == 'E' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Mauricio Rojas" ) != -1 );
                    }
                    else if( i == 1 && letras[ j ] == 'D' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Maritza Parra" ) != -1 );
                    }
                    else if( i == 1 && letras[ j ] == 'C' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Maritza Parra" ) != -1 );
                    }
                    else if( i == 1 && letras[ j ] == 'A' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Maritza Parra" ) != -1 );
                    }
                    else if( i == 2 && letras[ j ] == 'B' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Pablo Marmol" ) != -1 );
                    }
                    else if( i == 4 && letras[ j ] == 'A' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Pablo Marmol" ) != -1 );
                    }
                    else if( i == 2 && letras[ j ] == 'C' )
                    {
                        assertTrue( "El manifiesto no tiene la informaci�n de la reserva en la silla " + i + "-" + letras[ j ], lineaSilla.indexOf( "Pablo Marmol" ) != -1 );
                    }
                    else
                    {
                        assertTrue( "El manifiesto no indica que la silla " + i + "-" + letras[ j ] + " est� vac�a", lineaSilla.indexOf( "vac�a" ) != -1 );
                    }

                    lineaSilla = br.readLine( );
                }
            }

            br.close( );
            archivoManifiesto.delete( );
        }
        catch( IOException e )
        {
            fail( "No deber�a producirse ninguna excepci�n: " + e.getMessage( ) );
        }
    }
}