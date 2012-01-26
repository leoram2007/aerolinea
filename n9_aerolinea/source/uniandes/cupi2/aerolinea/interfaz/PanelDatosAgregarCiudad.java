/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosAgregarCiudad.java,v 1.6 2006/12/07 16:32:07 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co=
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 10/12/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.aerolinea.interfaz;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Es el panel donde se introducen los datos para agregar una ciudad
 */
public class PanelDatosAgregarCiudad extends JPanel implements ActionListener, MouseListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón ok
     */
    private static final String AGREGAR_CIUDAD = "Ok";

    /**
     * Comando para el botón cancelar
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el diálogo al que pertenece el panel
     */
    private DialogoAgregarCiudad dialogo;

    /**
     * Es la imagen del mundo que se muestra
     */
    private BufferedImage imagenMundo;

    /**
     * La coordenada X de la nueva ciudad en la imagen
     */
    private int coordenadaX;

    /**
     * La coordenada Y de la nueva ciudad en la imagen
     */
    private int coordenadaY;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es la etiqueta donde se dibuja la imagen
     */
    private JLabel etiquetaImagen;

    /**
     * Etiqueta Nombre
     */
    private JLabel etiquetaNombre;

    /**
     * Etiqueta Coordenada X
     */
    private JLabel etiquetaCoordX;

    /**
     * Etiqueta Coordenada Y
     */
    private JLabel etiquetaCoordY;

    /**
     * Campo para el nombre
     */
    private JTextField txtNombre;

    /**
     * Campo para la Coordenada X
     */
    private JTextField txtCoordX;

    /**
     * Campo para la Coordenada Y
     */
    private JTextField txtCoordY;

    /**
     * Botón usado para agregar el vuelo
     */
    private JButton botonAgregarCiudad;

    /**
     * Botón para cancelar la creación del vuelo
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param dac Es el diálogo que contiene el panel - dac!=null
     */
    public PanelDatosAgregarCiudad( DialogoAgregarCiudad dac )
    {
        dialogo = dac;

        coordenadaX = -1;
        coordenadaY = -1;

        actualizarImagen( );

        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        etiquetaImagen = new JLabel( new ImageIcon( imagenMundo ) );
        etiquetaImagen.setBorder( new LineBorder( Color.WHITE, 1 ) );
        etiquetaImagen.addMouseListener( this );
        add( etiquetaImagen, gbcE );

        // Inicializar las etiquetas
        inicializarEtiquetas( );

        // Inicializar los campos
        inicializarCampos( );

        // Inicializar los botones
        inicializarBotones( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa las etiquetas del panel
     */
    private void inicializarEtiquetas( )
    {
        GridBagConstraints gbcE = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        etiquetaNombre = new JLabel( "Nombre:" );
        add( etiquetaNombre, gbcE );

        gbcE.gridy = 2;
        etiquetaCoordX = new JLabel( "Coordenada X:" );
        add( etiquetaCoordX, gbcE );

        gbcE.gridy = 3;
        etiquetaCoordY = new JLabel( "Coordenada Y:" );
        add( etiquetaCoordY, gbcE );

    }

    /**
     * Inicializa los campos del panel
     */
    private void inicializarCampos( )
    {
        GridBagConstraints gbcC = new GridBagConstraints( 1, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtNombre = new JTextField( );
        add( txtNombre, gbcC );

        gbcC.gridy = 2;
        txtCoordX = new JTextField( );
        txtCoordX.setEditable( false );
        add( txtCoordX, gbcC );

        gbcC.gridy = 3;
        txtCoordY = new JTextField( );
        txtCoordY.setEditable( false );
        add( txtCoordY, gbcC );

    }

    /**
     * Inicializa los botones del panel
     */
    private void inicializarBotones( )
    {
        JPanel panelBotones = new JPanel( );

        botonAgregarCiudad = new JButton( "Agregar Ciudad" );
        botonAgregarCiudad.setActionCommand( AGREGAR_CIUDAD );
        botonAgregarCiudad.addActionListener( this );
        panelBotones.add( botonAgregarCiudad );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        panelBotones.add( botonCancelar );

        GridBagConstraints gbcB = new GridBagConstraints( 0, 4, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbcB );
    }

    /**
     * Actualiza la imagen del mundo con la posición de la ciudad que se va a agregar
     */
    public void actualizarImagen( )
    {
        try
        {
            // Cargar la imagen del mundo
            imagenMundo = ImageIO.read( new File( "./data/mapaGrande.jpg" ) );

            if( coordenadaX != -1 && coordenadaY != -1 )
            {
                Graphics2D g = imagenMundo.createGraphics( );
                g.setColor( Color.CYAN );

                g.setColor( Color.WHITE );
                g.fillOval( coordenadaX - 2, coordenadaY - 2, 5, 5 );
            }

            if( etiquetaImagen != null )
                etiquetaImagen.setIcon( new ImageIcon( imagenMundo ) );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Este es el método que se llama cuando se hace click sobre la imagen
     * @param evento Es el evento del click sobre la imagen - evento!=null
     */
    public void mouseClicked( MouseEvent evento )
    {
        int esquina = ( etiquetaImagen.getWidth( ) - imagenMundo.getWidth( ) ) / 2;
        coordenadaX = evento.getX( );
        coordenadaY = evento.getY( );

        double coordX = ( double ) ( coordenadaX - esquina ) / ( double )imagenMundo.getWidth( );
        double coordY = ( double ) ( coordenadaY ) / ( double )imagenMundo.getHeight( );

        txtCoordX.setText( "" + coordX );
        txtCoordY.setText( "" + coordY );

        actualizarImagen( );
    }

    /**
     * Es el método que se llama cuando de hace click sobre un botón
     * @param evento Es el evento de click sobre un botón - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( AGREGAR_CIUDAD.equals( comando ) )
        {
            String nombre = txtNombre.getText( );
            if( coordenadaX != -1 && coordenadaY != -1 )
            {
                double coordX = Double.parseDouble( txtCoordX.getText( ) );
                double coordY = Double.parseDouble( txtCoordY.getText( ) );

                dialogo.agregarCiudad( nombre, coordX, coordY );
            }
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mousePressed( MouseEvent e )
    {
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mouseReleased( MouseEvent e )
    {
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mouseEntered( MouseEvent e )
    {
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mouseExited( MouseEvent e )
    {
    }
}