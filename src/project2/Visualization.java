package project2;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JPanel;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;



/**
*
* @author Nacer Abreu
*/



public class Visualization implements GLEventListener, KeyListener{

    private GLCanvas canvas;
    private GLU glu;
    private Animator anim;
    private BufferedImage img;
    private Texture texture;
    private float camera_x;
    private float camera_y;
    private float camera_z;
    private float center_x;
    private float center_y;
    private float center_z;
    private float up_x;
    private float up_y;
    private float up_z;
    private float angle; // angle of rotation for the camera direction
    private float x_red_coord;
    private float x_blue_coord;

    public Visualization() {

        cameraInit();

        x_red_coord = 0f;
        x_blue_coord = 0f;
        
        // Construct an FPS animator, which drives drawable's display()
        // at the specified frames per second
        FPSAnimator animator = new FPSAnimator(canvas, 60);

        GLCapabilities caps = new GLCapabilities();
        canvas = new GLCanvas(caps);
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);
    }//end of constructor

    @Override
    public void init(GLAutoDrawable drawable) {
      
      // get the OpenGL graphics context
      GL gl = drawable.getGL();
      
      // get GL Utilities
      glu = new GLU();
      
      // set background (clear) color
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
      
      // set clear depth value to farthest
      gl.glClearDepth(1.0f);
      
      // enables depth testing
      gl.glEnable(gl.GL_DEPTH_TEST);
      
      // the type of depth test to do
      gl.glDepthFunc(gl.GL_LEQUAL);
      
      // best perspective correction
      gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, gl.GL_NICEST);
      
      // blends colors nicely, and smoothes out lighting
      gl.glShadeModel(gl.GL_SMOOTH);
      
      loadTexture("American_Football_field.png", gl);
      
    }//end of init()

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL(); // get the OpenGL 2 graphics context

        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();

        /* Perspective. (fov, aspect, near, far)
         */
        glu.gluPerspective(45, 1, 1, 1000);

        glu.gluLookAt(
                camera_x, camera_y, camera_z, /* eye */
                center_x, center_y, center_z, /* center */
                up_x, up_y, up_z); /* up */

        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

        gl.glPushMatrix();
            gl.glColor3f(1.0f, 1.0f, 1.0f); // white
            drawField(gl);
            drawRedBar(gl);
            drawBlueBar(gl);
        gl.glPopMatrix();

           
    }//end of display()

    private void drawField(GL gl){
        gl.glPushMatrix();
            gl.glEnable(gl.GL_TEXTURE_2D);
            gl.glEnable(GL.GL_BLEND);
            gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
            gl.glTranslatef(-5f, -5f, 0.0f); //Centers the football field graph.
            texture.enable();
            texture.bind();
            gl.glBegin(gl.GL_QUADS);
                 gl.glTexCoord2d(1, 1); gl.glVertex3f( 0.0f,  0.0f, 0.0f);
                 gl.glTexCoord2d(1, 0); gl.glVertex3f( 0.0f, 10.0f, 0.0f);
                 gl.glTexCoord2d(0, 0); gl.glVertex3f(10.0f, 10.0f, 0.0f);
                 gl.glTexCoord2d(0, 1); gl.glVertex3f(10.0f,  0.0f, 0.0f);
             gl.glEnd();
             texture.disable();             
        gl.glPopMatrix();
    }//end of drawField()

    /**
     * This function creates the bar that represents the longest kick by
     * kicker#1 
     * @param gl 
     */
    private void drawRedBar(GL gl) {
        gl.glPushMatrix();
            gl.glTranslatef(-4.965f, 1.1f, 0.0f);
            gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
            gl.glLineWidth(6.0f);
            gl.glBegin(GL.GL_LINES);            
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glVertex3f(x_red_coord, 0.0f, 0.0f);
            gl.glEnd();
        gl.glPopMatrix();

    }//end of drawRedBar()
    
    /**
     * This function creates the bar that represents the longest kick by
     * kicker#2 
     * @param gl 
     */
    private void drawBlueBar(GL gl) {
        gl.glPushMatrix();
            gl.glTranslatef(-4.965f, -0.2f, 0.0f);
            gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
            gl.glLineWidth(6.0f);
            gl.glBegin(GL.GL_LINES);
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glVertex3f(x_blue_coord, 0.0f, 0.0f);
            gl.glEnd();
        gl.glPopMatrix();

    }//end of drawBlueBar()

    
    public void setXCoordinate(float yards, String color) {
        
        float coord = (float) ((0.39f * yards) / 5.0f);
        if (color.equalsIgnoreCase("red")){
            x_red_coord = coord;
        }
        
        if (color.equalsIgnoreCase("blue")){
            x_blue_coord = coord;
        }       
    }//end of setXCoordinates  

    
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL gl = drawable.getGL(); // get the OpenGL 2 graphics context
 
      if (height == 0) height = 1; // prevent divide by zero
      float aspect = (float)width / height;
 
      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);
 
      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(gl.GL_PROJECTION); // choose projection matrix
      gl.glLoadIdentity(); // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
 
      // Enable the model-view transform
      gl.glMatrixMode(gl.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
    }//end of reshape()
    

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        String keyString = "" + ke.getKeyChar() + "";

        if ((ke.getKeyCode() == KeyEvent.VK_UP)
                && ((ke.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Moves UPWARD in yx plane
            camera_y += 0.5;
        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
            // Moves FORWARD on xz plane
            // if (camera_z < sceneBoundary_z) {
            camera_z += 0.1;
            center_z += 0.1;
            // }
        }

        if ((ke.getKeyCode() == KeyEvent.VK_DOWN)
                && ((ke.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Moves DOWNWARD on xz plane
// if (camera_y >= 0.5) // Do not allow to go beneath 'x' plane
// {
                camera_y -= 0.5;
// }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            // Moves BACKWARDS on xz plane
            // if (camera_z > (sceneBoundary_z * -1)) {
            camera_z -= 0.1;
            center_z -= 0.1;
            // }
        }

        if ((ke.getKeyCode() == KeyEvent.VK_LEFT)
                && ((ke.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // PAN LEFT.
            if (angle == 0f) {
                angle = 359f;
            } else {
                angle -= 1f;
            }

        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            // Moves camera to the LEFT on the xz plane
            //if (camera_x < sceneBoundary_x) {
            camera_x += 0.1;
            center_x += 0.1;
            // }
        }

        if ((ke.getKeyCode() == KeyEvent.VK_RIGHT)
                && ((ke.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // PAN RIGHT
            if (angle == 359f) {
                angle = 0f;
            } else {
                angle += 1f;
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Moves camera to the RIGHT on the xz plane
            //if (camera_x > (sceneBoundary_x * -1) ) {
            camera_x -= 0.1;
            center_x -= 0.1;
            //}
        }
        
       // Resets Camera's glu.gluLookAt parameters
        if (keyString.equals("r")) {
            cameraInit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void attachToPanel(JPanel pnlPrimary) {
        pnlPrimary.add(canvas);
        anim = new Animator(canvas);
        anim.start();
    }

    
    private void loadTexture(String fileName, GL gl) {
        img = null;
        try {
            img = ImageIO.read(new File(fileName));
            texture = TextureIO.newTexture(img, true);
        } catch (Exception ex) {
            Logger.getLogger(Visualization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end of loadTexture()
    
    private void cameraInit() {
        camera_x = 0;
        camera_y = 0;
        camera_z = -12.2f;
        center_x = 0;
        center_y = 0;
        center_z = 0;
        up_x = 0;
        up_y = 1;
        up_z = 0;
        
    }

}//end of class


