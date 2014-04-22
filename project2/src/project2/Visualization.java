package project2;



import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.j2d.TextRenderer;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nasser
 */



public class Visualization implements GLEventListener, KeyListener{

    private GLCanvas canvas;
    private GLU glu;
    private Animator anim;
    private boolean enableHardwareAcceleratedMipmaps;
    private boolean enableMipmapping;
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
    private float angle2;
    private JPanel pnlPrimary;
    private TextRenderer text;
    private DecimalFormat form;

    public Visualization() {

        enableHardwareAcceleratedMipmaps = true;
        enableMipmapping = true;

        camera_x = 0;
        camera_y = 0;
        camera_z = -16;
        center_x = 0;
        center_y = 0;
        center_z = 2;
        up_x = 0;
        up_y = 1;
        up_z = 0;
 
        text = new TextRenderer(new Font("SansSerif", Font.BOLD, 12));
        form = new DecimalFormat("####0.00");

        // Construct an FPS animator, which drives drawable's display() 
        // at the specified frames per second
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        
        GLCapabilities caps = new GLCapabilities();
        canvas = new GLCanvas(caps);
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);
        

    }

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
        GL gl = drawable.getGL();  // get the OpenGL 2 graphics context
                     
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        /* fov, aspect, near, far */
        glu.gluPerspective(45, 1, 1, 100);
        glu.gluLookAt(  camera_x, camera_y, camera_z, /* eye */
                        center_x, center_y, center_z, /* center */
                        up_x, up_y, up_z); /* up */

        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glPushAttrib(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(gl.GL_TEXTURE_2D);
        
        gl.glTranslatef(-5, -5, -4); //Centers the football field graph.

         /* create a square on the XY note that OpenGL origin is at the lower 
            left but texture origin is at upper left => it has to be mirrored
            (gasman knows why i have to mirror X as well) */
         gl.glBegin(gl.GL_QUADS);
             gl.glRotatef(-90, 1, 0, 0); // Rotate World!
             gl.glTexCoord2d(1, 1); gl.glVertex3f( 0.0f,  0.0f, 0.0f);
             gl.glTexCoord2d(1, 0); gl.glVertex3f( 0.0f, 10.0f, 0.0f);
             gl.glTexCoord2d(0, 0); gl.glVertex3f(10.0f, 10.0f, 0.0f);
             gl.glTexCoord2d(0, 1); gl.glVertex3f(10.0f,  0.0f, 0.0f);
         gl.glEnd();
        
                displayCameraPositionInfo(drawable);
 
        texture.disable();

            
        
    }//end of display()

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL gl = drawable.getGL();  // get the OpenGL 2 graphics context
 
      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;
 
      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);
 
      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(gl.GL_PROJECTION);  // choose projection matrix
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            //    if (camera_z < sceneBoundary_z) {
            camera_z += 0.1;
            center_z += 0.1;
            //    }
        }

        if ((ke.getKeyCode() == KeyEvent.VK_DOWN)
                && ((ke.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Moves DOWNWARD on xz plane
//            if (camera_y >= 0.5) // Do not allow to go beneath 'x' plane
//            {
                camera_y -= 0.5;
//            }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            // Moves BACKWARDS on xz plane
            //    if (camera_z > (sceneBoundary_z * -1)) {
            camera_z -= 0.1;
            center_z -= 0.1;
            //    }
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
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glEnable(GL.GL_BLEND);

        img = null;
        try {
            img = ImageIO.read(new File(fileName));
            texture = TextureIO.newTexture(img, true);

            // when texture area is large, bilinear filter the first mipmap
            gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);

        } catch (Exception ex) {
            Logger.getLogger(Visualization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end of loadTexture()
    

    
private void displayCameraPositionInfo(GLAutoDrawable drawable) {
//        text.beginRendering(drawable.getWidth(), drawable.getHeight());
//        text.setColor(new Color(255, 255, 255)); // White
//
//        text.draw("Camera x: " + form.format(camera_x) + " y: "
//                + form.format(camera_y) + " z: " + form.format(camera_z)
//                + " pan: " + form.format(angle), 10, 10);

        System.out.print("Camera x: " + camera_x + " y: "
                + camera_y + " z: " + camera_z
                + " pan: " + angle + "\n");
//        
//        text.endRendering();
//        text.flush();
    }//displayCameraPositionInfo    
}//end of class
