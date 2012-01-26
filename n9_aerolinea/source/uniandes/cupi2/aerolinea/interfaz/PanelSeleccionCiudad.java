/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelSeleccionCiudad.java,v 1.8 2007/04/13 04:17:10 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_aerolinea
 * Autor: Mario Sánchez - 10-dic-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.aerolinea.interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import uniandes.cupi2.aerolinea.mundo.Ciudad;

/**
 * Es el panel usado para seleccionar una ciudad destino en la ventana principal
 */
public class PanelSeleccionCiudad extends JPanel implements MouseListener
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ventana principal de la aplicación
     */
    private InterfazAerolinea ventanaPrincipal;

    /**
     * Es la imagen del mundo que se muestra
     */
    private BufferedImage imagenMundo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param principal Es una referencia a la ventana principal - principal
     */
    public PanelSeleccionCiudad( InterfazAerolinea principal )
    {
        ventanaPrincipal = principal;

        setMinimumSize( new Dimension( 354, 210 ) );
        setPreferredSize( new Dimension( 354, 210 ) );

        addMouseListener( this );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método se encarga de actualizar el dibujo del mundo
     * @param g Es la superficie del panel sobre la que se debe dibujar
     */
    protected void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        if( imagenMundo != null )
        {
            // Calcular la posición de la imagen
            int posX = ( getWidth( ) - imagenMundo.getWidth( ) ) / 2;

            // Copiar la imagen al panel
            Graphics2D g2d = ( Graphics2D )g;
            g2d.drawImage( imagenMundo, posX, 5, Color.BLACK, null );
        }
    }

    /**
     * Selecciona una ciudad en el mapa
     * @param ciudadSeleccionada La ciudad seleccionada en el mapa
     */
    public void seleccionarCiudad( Ciudad ciudadSeleccionada )
    {
        actualizarImagen( );

        if( ciudadSeleccionada != null )
        {
            // Marcar la ciudad seleccionada
            int ciudadX = ( int ) ( ciudadSeleccionada.darCoordenadaX( ) * imagenMundo.getWidth( ) );
            int ciudadY = ( int ) ( ciudadSeleccionada.darCoordenadaY( ) * imagenMundo.getHeight( ) );

            imagenMundo.getGraphics( ).setColor( Color.BLUE );
            imagenMundo.getGraphics( ).drawOval( ciudadX - 2, ciudadY - 2, 4, 4 );

            // Dibujar la línea entre la ciudad base y la ciudad seleccionada
            Ciudad ciudadBase = ventanaPrincipal.darCiudadBaseAerolinea( );
            int baseX = ( int ) ( ciudadBase.darCoordenadaX( ) * imagenMundo.getWidth( ) );
            int baseY = ( int ) ( ciudadBase.darCoordenadaY( ) * imagenMundo.getHeight( ) );

            int direccion = 1;
            if( baseX < ciudadX )
                direccion = -1;

            double ctrlx1 = ( ciudadX + baseX ) / 2 + 20 * direccion;
            double ctrly1 = ( ciudadY + baseY ) / 2 - 20;

            BasicStroke stroke = new BasicStroke( 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 4.0f, 2.0f }, 0 );
            QuadCurve2D.Double curva = new QuadCurve2D.Double( ( double )baseX, ( double )baseY, ctrlx1, ctrly1, ( double )ciudadX, ( double )ciudadY );
            Graphics2D g = imagenMundo.createGraphics( );
            g.setColor( Color.CYAN );
            g.setStroke( stroke );
            g.draw( curva );
        }
    }

    /**
     * Actualiza la imagen del mundo con las ciudades a las que vuela la aerolínea
     */
    public void actualizarImagen( )
    {
        try
        {
            // Cargar la imagen del mundo
            imagenMundo = ImageIO.read( new File( "./data/mapaPeque.jpg" ) );

            Graphics2D g = imagenMundo.createGraphics( );
            g.setColor( Color.CYAN );

            // Dibujar la ciudad base
            Ciudad ciudadBase = ventanaPrincipal.darCiudadBaseAerolinea( );
            int baseX = ( int ) ( ciudadBase.darCoordenadaX( ) * imagenMundo.getWidth( ) );
            int baseY = ( int ) ( ciudadBase.darCoordenadaY( ) * imagenMundo.getHeight( ) );

            g.setColor( Color.WHITE );
            g.fillOval( baseX - 2, baseY - 2, 5, 5 );
            g.setColor( Color.YELLOW );

            // Dibujar las ciudades
            ArrayList ciudades = ventanaPrincipal.darCiudadesAerolinea( );
            for( int i = 0; i < ciudades.size( ); i++ )
            {
                Ciudad ciudad = ( Ciudad )ciudades.get( i );

                int ciudadX = ( int ) ( ciudad.darCoordenadaX( ) * imagenMundo.getWidth( ) );
                int ciudadY = ( int ) ( ciudad.darCoordenadaY( ) * imagenMundo.getHeight( ) );

                g.fillOval( ciudadX - 1, ciudadY - 1, 3, 3 );
            }
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Este es el método que se llama cuando se hace click sobre el panel
     * @param evento Es el evento del click sobre el panel
     */
    public void mouseClicked( MouseEvent evento )
    {
        int esquina = ( getWidth( ) - imagenMundo.getWidth( ) ) / 2;
        int posX = evento.getX( );
        int posY = evento.getY( );

        if( posY > 5 && posX > esquina && posX < esquina + imagenMundo.getWidth( ) )
        {
            double coordX = ( double ) ( posX - esquina ) / ( double )imagenMundo.getWidth( );
            double coordY = ( double ) ( posY - 5 ) / ( double )imagenMundo.getHeight( );
            ventanaPrincipal.seleccionarCiudad( coordX, coordY );
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
