/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Vuelo.java,v 1.18 2006/12/07 20:24:38 da-romer Exp $
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
 * Esta clase representa un vuelo de la aerolínea. <br>
 * Cada silla se identifica por la fila en la que se encuentran y un letra. <br>
 * <b>inv: </b> <br>
 * codigo >= 0 <br>
 * capacidad = NUMERO_FILAS * LETRAS.length <br>
 * sillasDisponibles >= 0 && sillasDisponibles <= capacidad <br>
 * fechaYHora != null <br>
 * sillas es una matriz de NUMERO_FILAS filas y LETRAS.lenght columnas <br>
 * sillasDisponibles tiene el número de sillas no reservadas del vuelo <br>
 * Solamente puede haber una reserva por cédula en un vuelo <br>
 * Los vuelos están bien enlazados hacia adelante y hacia atrás
 */
public class Vuelo implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es un arreglo con las constantes de las letras para las sillas
     */
    public static final char[] LETRAS = new char[]{ 'A', 'B', 'C', 'D', 'E' };

    /**
     * Indica el número de filas que hay en el avión
     */
    public static final int NUMERO_FILAS = 15;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una matriz con las sillas que hay en el vuelo. <br>
     * La primera coordenada corresponde a la fila de la silla <br>
     * La segunda coordenada corresponde a la letra de la silla.
     */
    private Silla[][] sillas;

    /**
     * Indica la capacidad total del vuelo
     */
    private int capacidad;

    /**
     * Indica el número de sillas disponibles actualmente en el vuelo
     */
    private int sillasDisponibles;

    /**
     * Indica la fecha y hora para la que está programado el vuelo
     */
    private Date fechaYHora;

    /**
     * Indica el código del vuelo
     */
    private int codigo;

    /**
     * Es el siguiente vuelo de la lista
     */
    private Vuelo siguiente;

    /**
     * Es el vuelo anterior en la lista
     */
    private Vuelo anterior;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo vuelo con los datos suministrados <br>
     * <b>post: </b>Se construyó el vuelo <br>
     * @param codigoVuelo El código del vuelo - codigoVuelo >= 0
     * @param fechaVuelo La fecha en la que se realizará el vuelo - fechaVuelo != null y fechaVuelo tiene el formato yyyy-MM-dd
     * @param horasVuelo La hora de despegue - 0 <= horaVuelo < 24
     * @param minutosVuelo Los minutos de la hora de despegue - 0 <= minutosVuelo < 60
     * @throws ParseException Se lanza esta excepción si la fecha y hora de despegue no es válida
     */
    public Vuelo( int codigoVuelo, String fechaVuelo, String horasVuelo, String minutosVuelo ) throws ParseException
    {
        codigo = codigoVuelo;

        // Inicializa la fecha y hora
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        fechaYHora = sdf.parse( fechaVuelo + " " + horasVuelo + ":" + minutosVuelo );

        // Inicializa las sillas
        capacidad = NUMERO_FILAS * LETRAS.length;
        sillasDisponibles = capacidad;
        sillas = new Silla[NUMERO_FILAS][LETRAS.length];

        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < LETRAS.length; j++ )
            {
                sillas[ i ][ j ] = new Silla( i, LETRAS[ j ] );
            }
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna una silla del vuelo dada su posición
     * @param fila Fila en la que se encuentra la silla - fila >= 0 && fila < NUMERO_FILAS
     * @param col Columna en la que se encuentra la silla - col >= 0 && col < LETRAS.lenght
     * @return Se retornó la silla que se encuentra en la fila y columnas indicadas
     */
    public Silla darSilla( int fila, int col )
    {
        return sillas[ fila ][ col ];
    }

    /**
     * Retorna una silla del vuelo dado su nombre
     * @param nombreSilla Nombre de la silla buscada - nombreSilla != null
     * @return Se retornó la silla con el nombre dado como parámetro o null si dicha silla no existe.
     */
    public Silla darSilla( String nombreSilla )
    {
        String strFila = nombreSilla.substring( 0, nombreSilla.indexOf( "-" ) );
        String strLetra = nombreSilla.substring( nombreSilla.indexOf( "-" ) + 1 );
        char chrLetra = strLetra.charAt( 0 );

        int numFila = Integer.parseInt( strFila );
        for( int j = 0; j < LETRAS.length; j++ )
        {
            if( LETRAS[ j ] == chrLetra )
                return sillas[ numFila ][ j ];
        }
        return null;
    }

    /**
     * Retorna la matriz de sillas del vuelo (este método solo debería ser utilizado en pruebas)
     * @return Se retornó la matriz de sillas del vuelo
     */
    public Silla[][] darSillas( )
    {
        return sillas;
    }

    /**
     * Retorna el código del vuelo
     * @return Se retornó el código del vuelo
     */
    public int darCodigo( )
    {
        return codigo;
    }

    /**
     * Retorna el número de sillas disponibles
     * @return Se retornó el número de sillas disponibles
     */
    public int darNumeroSillasDisponibles( )
    {
        return sillasDisponibles;
    }

    /**
     * Retorna la capacidad máxima del vuelo
     * @return Se retornó la capacidad máxima del vuelo
     */
    public int darCapacidad( )
    {
        return capacidad;
    }

    /**
     * Retorna una cadena con la fecha y la hora del vuelo
     * @return Se retornó una cadena con la fecha-hora del vuelo
     */
    public String darFechaYHora( )
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        return sdf.format( fechaYHora );
    }

    /**
     * Retorna la fecha
     * @return Se retornó la fecha del vuelo
     */
    public Date darFecha( )
    {
        return fechaYHora;
    }

    /**
     * Reserva una silla del vuelo. <br>
     * <b>pre: </b>La silla que se va a reservar no debe estar ya apartada. <br>
     * <b>post: </b>Se reservó la silla indicada. <br>
     * @param sillaVuelo El nombre (<fila>-<letra>) de las silla que se va a reservar - sillaVuelo es válida
     * @param nombre El nombre de la persona que realiza la reserva
     * @param cedula La cédula de la persona que realiza la reserva
     * @throws AerolineaExcepcion Se lanza esta excepción si ya hay una reserva con la cédula indicada
     */
    public void reservarSilla( String sillaVuelo, String nombre, int cedula ) throws AerolineaExcepcion
    {
        // Verificar que la cédula indicada no haya sido usada para otra reserva
        if( contarReservas( cedula ) != 0 )
            throw new AerolineaExcepcion( "La cédula " + cedula + " ya fue usada para otra reserva en el mismo vuelo" );

        darSilla( sillaVuelo ).reservar( new Reserva( nombre, cedula ) );
        sillasDisponibles--;
        verificarInvariante( );
    }

    /**
     * Calcula el número de veces que aparece una cédula con una reserva en el vuelo
     * @param cedula Cédula buscada
     * @return Número de veces que aparece una cédula con una reserva en el vuelo
     */
    private int contarReservas( int cedula )
    {
        int acum = 0;
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < LETRAS.length; j++ )
            {
                if( sillas[ i ][ j ].estaReservada( ) && sillas[ i ][ j ].darReserva( ).darCedula( ) == cedula )
                    acum++;
            }
        }
        return acum;
    }

    /**
     * Retorna una cadena que sirve para reconocer el vuelo
     * @return Se retornó la cadena para reconocer el vuelo
     */
    public String toString( )
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        String strFecha = sdf.format( fechaYHora );

        DecimalFormat df = new DecimalFormat( "00.00" );
        String disponibilidad = df.format( ( ( double )sillasDisponibles / ( double )capacidad * 100 ) );

        return codigo + " - " + strFecha + " (" + disponibilidad + "% libre)";
    }

    /**
     * Retorna el vuelo anterior en la lista
     * @return Se retornó el vuelo anterior en la lista
     */
    public Vuelo darVueloAnterior( )
    {
        return anterior;
    }

    /**
     * Retorna el vuelo siguiente en la lista
     * @return Se retornó el vuelo siguiente en la lista
     */
    public Vuelo darVueloSiguiente( )
    {
        return siguiente;
    }

    /**
     * Genera el manifiesto de embarque del vuelo en el archivo indicado. <br>
     * El manifiesto de embarque tiene información sobre el vuelo y sobre la posición ocupada por cada uno de los pasajeros.
     * @param archivoManifiesto El archivo donde debe generarse el manifiesto - archivoManifiesto!=null
     * @throws IOException Se lanza esta excepción si hay problemas escribiendo el archivo
     */
    public void generarManifiestoEmbarque( File archivoManifiesto ) throws IOException
    {
        PrintWriter out = new PrintWriter( archivoManifiesto );
        out.println( toString( ) );

        int numSilla = 1;

        for( int j = 0; j < NUMERO_FILAS; j++ )
        {
            for( int k = 0; k < LETRAS.length; k++ )
            {
                out.print( numSilla + ".  Fila " + sillas[ j ][ k ].darFila( ) + ", Silla " + sillas[ j ][ k ].darLetra( ) + ": " );
                if( sillas[ j ][ k ].estaReservada( ) )
                {
                    Reserva reserva = sillas[ j ][ k ].darReserva( );
                    out.println( reserva.darNombre( ) + ", cédula No " + reserva.darCedula( ) );
                }
                else
                {
                    out.println( "vacía" );
                }
                numSilla++;
            }
        }
        out.close( );
    }

    /**
     * Inserta el vuelo después del actual. <br>
     * <b>post: </b> Se insertó el vuelo especificado después del vuelo actual. <br>
     * @param vuelo El vuelo a insertar - vuelo!=null
     */
    public void insertarDespues( Vuelo vuelo )
    {
        vuelo.siguiente = siguiente;
        if( siguiente != null )
            siguiente.anterior = vuelo;
        vuelo.anterior = this;
        siguiente = vuelo;
        verificarInvariante( );
    }

    /**
     * Inserta el vuelo antes del actual. <br>
     * <b>post: </b> Se insertó el vuelo especificado antes del vuelo actual. <br>
     * @param vuelo El vuelo a insertar - vuelo!=null
     */
    public void insertarAntes( Vuelo vuelo )
    {
        if( anterior != null )
            anterior.siguiente = vuelo;
        vuelo.anterior = anterior;
        vuelo.siguiente = this;
        anterior = vuelo;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv: </b> <br>
     * codigo >= 0 <br>
     * fechaYHora != null <br>
     * capacidad = NUMERO_FILAS * LETRAS.length <br>
     * sillasDisponibles >= 0 && sillasDisponibles <= capacidad <br>
     * sillas es una matriz de NUMERO_FILAS filas y LETRAS.lenght columnas <br>
     * sillasDisponibles tiene el número de sillas no reservadas del vuelo <br>
     * Solamente puede haber una reserva por cédula en un vuelo <br>
     * Los vuelos están bien enlazados hacia adelante y hacia atrás
     */
    private void verificarInvariante( )
    {
        assert codigo >= 0 : "El código no debe tener un valor negativo";
        assert fechaYHora != null : "La fecha y hora del vuelo ";
        assert capacidad == NUMERO_FILAS * LETRAS.length : "La capacidad del vuelo no es correcta";
        assert sillasDisponibles >= 0 && sillasDisponibles <= capacidad : "La cantidad de sillas disponibles no es válida";
        assert sillas.length == NUMERO_FILAS && sillas[ 0 ].length == LETRAS.length : "La capacidad del vuelo no es correcta";
        assert sillasDisponibles == contarSillasVacias( ) : "La cantidad de sillas disponibles no es válida";

        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < LETRAS.length; j++ )
            {
                if( sillas[ i ][ j ].estaReservada( ) )
                    assert contarReservas( sillas[ i ][ j ].darReserva( ).darCedula( ) ) == 1 : "Sólo debe haber una reserva por silla";
            }
        }

        if( siguiente != null )
            assert siguiente.anterior == this : "Los vuelos no se encuentran bien enlazados";
        if( anterior != null )
            assert anterior.siguiente == this : "Los vuelos no se encuentran bien enlazados";
    }

    /**
     * Calcula el número de sillas vacías en el vuelo
     * @return Se retornó el número de sillas vacías en el vuelo
     */
    private int contarSillasVacias( )
    {
        int acum = 0;
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < LETRAS.length; j++ )
            {
                if( !sillas[ i ][ j ].estaReservada( ) )
                    acum++;
            }
        }
        return acum;
    }
}