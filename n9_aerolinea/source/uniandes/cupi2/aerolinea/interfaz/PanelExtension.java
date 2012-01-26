/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.7 2006/12/07 16:32:07 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario S�nchez - 07-dic-2005
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
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando Opci�n 3
     */
    private static final String OPCION_3 = "OPCION_3";

    /**
     * Comando Opci�n 4
     */
    private static final String OPCION_4 = "OPCION_4";

    /**
     * Comando Opci�n 5
     */
    private static final String OPCION_5 = "OPCION_5";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazAerolinea ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;

    /**
     * Bot�n Opci�n 3
     */
    private JButton btnOpcion3;

    /**
     * Bot�n Opci�n 4
     */
    private JButton btnOpcion4;

    /**
     * Bot�n Opci�n 5
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

        setBorder( new TitledBorder( "Puntos de Extensi�n" ) );
        setLayout( new FlowLayout( ) );

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Opci�n 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Opci�n 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

        // Bot�n opci�n 3
        btnOpcion3 = new JButton( "Opci�n 3" );
        btnOpcion3.setActionCommand( OPCION_3 );
        btnOpcion3.addActionListener( this );
        add( btnOpcion3 );

        // Bot�n opci�n 4
        btnOpcion4 = new JButton( "Opci�n 4" );
        btnOpcion4.setActionCommand( OPCION_4 );
        btnOpcion4.addActionListener( this );
        add( btnOpcion4 );

        // Bot�n opci�n 5
        btnOpcion5 = new JButton( "Opci�n 5" );
        btnOpcion5.setActionCommand( OPCION_5 );
        btnOpcion5.addActionListener( this );
        add( btnOpcion5 );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param evento Acci�n que gener� el evento - e!=null
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
