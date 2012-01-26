/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.7 2006/12/07 16:32:07 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 07-dic-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.aerolinea.interfaz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando Opción 3
     */
    private static final String OPCION_3 = "OPCION_3";

    /**
     * Comando Opción 4
     */
    private static final String OPCION_4 = "OPCION_4";

    /**
     * Comando Opción 5
     */
    private static final String OPCION_5 = "OPCION_5";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;

    /**
     * Botón Opción 3
     */
    private JButton btnOpcion3;

    /**
     * Botón Opción 4
     */
    private JButton btnOpcion4;

    /**
     * Botón Opción 5
     */
    private JButton btnOpcion5;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param principal Ventana principal - principal!=null
     */
    public PanelExtension( InterfazAerolinea principal )
    {
        ventanaPrincipal = principal;

        setBorder( new TitledBorder( "Puntos de Extensión" ) );
        setLayout( new FlowLayout( ) );

        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

        // Botón opción 3
        btnOpcion3 = new JButton( "Opción 3" );
        btnOpcion3.setActionCommand( OPCION_3 );
        btnOpcion3.addActionListener( this );
        add( btnOpcion3 );

        // Botón opción 4
        btnOpcion4 = new JButton( "Opción 4" );
        btnOpcion4.setActionCommand( OPCION_4 );
        btnOpcion4.addActionListener( this );
        add( btnOpcion4 );

        // Botón opción 5
        btnOpcion5 = new JButton( "Opción 5" );
        btnOpcion5.setActionCommand( OPCION_5 );
        btnOpcion5.addActionListener( this );
        add( btnOpcion5 );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param evento Acción que generó el evento - e!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( OPCION_1.equals( comando ) )
        {
            ventanaPrincipal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( comando ) )
        {
            ventanaPrincipal.reqFuncOpcion2( );
        }
        else if( OPCION_3.equals( comando ) )
        {
            ventanaPrincipal.reqFuncOpcion3( );
        }
        else if( OPCION_4.equals( comando ) )
        {
            ventanaPrincipal.reqFuncOpcion4( );
        }
        else if( OPCION_5.equals( comando ) )
        {
            ventanaPrincipal.reqFuncOpcion5( );
        }
    }

}
