


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
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
* This class creates a bar visualization based on the .cvs chosen by the user.
* This class uses JOGL to create or map the graphical elements on a GLCanvas, 
* which subsequently is added to the main JFrame of this application.
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
    private float x_red_coord;  // specfies the length of the red bar
    private float x_blue_coord; // specifies the length of the blue bar.
    private float blueWidth;
    private float redWidth;
    private float x_red_animated_coord;
    private float x_blue_animated_coord;
    private boolean isLongestEnable;
    private float longestFG;
    private boolean isShortestEnable;
    private float shortestFG;
    private boolean isAverageEnable;
    private float averageFG; 
    
    
    /**
     * Constructor.
     */
    public Visualization() {
        cameraInit();
        x_red_coord = 0f;
        x_blue_coord = 0f;
        blueWidth = 0f;
        redWidth = 0f;
        x_red_animated_coord = 0.0f;
        x_blue_animated_coord = 0.0f;
        
        this.longestFG = 0f;
        this.isLongestEnable = false;
        
        this.shortestFG = 0f;
        this.isShortestEnable = false;
        
        this.averageFG = 0f;
        this.isAverageEnable = false;
        
        // Construct an FPS animator, which drives drawable's display() at the 
        // specified frames per second
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
      
      gl.glEnable(GL.GL_SMOOTH);
      
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
            drawLongestFG(gl);
            drawShortestFG(gl);
            drawAverageFG(gl);
        gl.glPopMatrix();
        gl.glFlush();    
        
    }//end of display()
    
    /**
     * This function calculates the approximate x coordinate for the specified 
     * numbers of yards supplied.
     * @param yards The number of yards to convert to x coordinates
     * @return float The return value is the x coordinate  
     */
    private float calculateXCoord(float yards){
        
        return (float) ((0.39f * yards) / 5.0f);
        
    }// end of calculateXCoord();
    
    /**
     * This function enables a parallel bar that represents the longest field 
     * goal kick.
     * @param yards The humber of yards for the longest kick.
     */
    public void enableLongest(float yards){
        if(isLongestEnable){
            this.isLongestEnable = false;
        }else{
            this.longestFG = calculateXCoord(yards);
            this.isLongestEnable = true;
        }
    }// end of enableLongest()

    /**
     * This function enables a parallel bar that represents the shortest field 
     * goal kick.
     * @param yards The number of yards for the shortest kick.
     */
    public void enableShortest(float yards){
        if(isShortestEnable){
            this.isShortestEnable = false;
        }else{
            this.shortestFG = calculateXCoord(yards);
            this.isShortestEnable = true;
        }
    }// end of enableShortest()    
    
    /**
     * This function enables a parallel bar that represents the average field 
     * goal kick.
     * @param yards The number of yards for the average kick.
     */
    public void enableAverage(float yards){
        if(isAverageEnable){
            this.isAverageEnable = false;
        }else{
            this.averageFG = calculateXCoord(yards);
            this.isAverageEnable = true;
        }
    }// end of enableShortest()     
    
    /**
     * This function draws draws a vertical bar representing the longest FG.
     * @param gl 
     */
    private void drawLongestFG(GL gl) {
    
        if (isLongestEnable){
            gl.glPushMatrix();
                 gl.glTranslatef(-4.965f, 0.0f, 0.0f);
                 gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
                 gl.glLineWidth(4.0f);
                gl.glBegin(GL.GL_LINES);
                    gl.glVertex3f( longestFG,   4.0f, 0.0f);
                    gl.glVertex3f( longestFG,  -3.5f, 0.0f);
                gl.glEnd();
            gl.glPopMatrix();
        }
    }//End of drawLongestFG()
    
    /**
     * This function draws draws a vertical bar representing the shortest FG.
     * @param gl 
     */
    private void drawShortestFG(GL gl) {
        
        if (isShortestEnable){
            gl.glPushMatrix();
                 gl.glTranslatef(-4.965f, 0.0f, 0.0f);
                 gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
                 gl.glLineWidth(4.0f);
                gl.glBegin(GL.GL_LINES);
                    gl.glVertex3f( shortestFG,   4.0f, 0.0f);
                    gl.glVertex3f( shortestFG,  -3.5f, 0.0f);
                gl.glEnd();
            gl.glPopMatrix();
        }
    }//End of drawShortestFG()
    
    /**
     * This function draws draws a vertical bar representing the average FG.
     * @param gl 
     */
    private void drawAverageFG(GL gl) {
        
        if (isAverageEnable){
            gl.glPushMatrix();
                 gl.glTranslatef(-4.965f, 0.0f, 0.0f);
                 gl.glColor3f(1.5f, 1.0f, 0.5f); // White
                 gl.glLineWidth(4.0f);
                gl.glBegin(GL.GL_LINES);
                    gl.glVertex3f( averageFG,   4.0f, 0.0f);
                    gl.glVertex3f( averageFG,  -3.5f, 0.0f);
                gl.glEnd();
            gl.glPopMatrix();
        }
    }//End of drawAverageFG()    
    
    /**
    * This function draws a rectangle and applies the a GL_TEXTURE_2D to it.
    * In this case, an image of an American football field.
    * @param gl - The basic interface to OpenGL, providing access to core 
    * functionality
    */
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
     * the red kicker 
     * @param gl The basic interface to OpenGL, providing access to core 
     * functionality
     */
    private void drawRedBar(GL gl) {
        float x = -4.965f;
        float y = 1.1f;
        
        float width = 0;
        if (redWidth > blueWidth){
            width = 0.2f;
        }
        
        if (x_red_coord >= x_red_animated_coord){
            x_red_animated_coord += 0.09;
        }
        
        gl.glPushMatrix();
            gl.glTranslatef(x, y, -0.1f);
            gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
            gl.glBegin(GL.GL_QUADS);
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glVertex3f(0.0f, 0.1f + width, 0.0f);
                gl.glVertex3f(x_red_animated_coord, 0.1f + width, 0.0f);
                gl.glVertex3f(x_red_animated_coord, 0.0f, 0.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        if (x_blue_coord < x_red_coord) {
            drawFilledCircle(gl, (x) + x_red_coord, (y) + (0.15f));
        }
        
    }//end of drawRedBar()
    
    /**
     * This function creates the bar that represents the longest kick by
     * the blue kicker. 
     * @param gl - The basic interface to OpenGL, providing access to core 
     * functionality
     */
    private void drawBlueBar(GL gl) {
        float x = -4.965f;
        float y = -0.2f;
        
        float width = 0;
        if (blueWidth > redWidth){
            width = 0.2f;
        }
        
        if (x_blue_coord >= x_blue_animated_coord){
            x_blue_animated_coord += 0.09;
        }
        gl.glPushMatrix();
            gl.glTranslatef(x, y, -0.1f);
            gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
            gl.glBegin(GL.GL_QUADS);
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glVertex3f(0.0f, 0.1f + width, 0.0f);
                gl.glVertex3f(x_blue_animated_coord, 0.1f + width, 0.0f);
                gl.glVertex3f(x_blue_animated_coord, 0.0f, 0.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        if (x_blue_coord > x_red_coord){
            drawFilledCircle(gl, (x) + x_blue_coord, (y) + (0.15f));
        }
    }//end of drawBlueBar()

    /*
     * Function that handles the drawing of a circle using the triangle fan
     * method. This will create a filled circle. 
     * @param gl - The basic interface to OpenGL, providing access to core 
     * functionality
     */
    private void drawFilledCircle(GL gl, float x, float y) {
        float x_circle = 0;
        float y_circle = 0;
        float radius = 0.07f; /** Controls the size of the circle */
        float pi = (float) 3.1416;
        float twicePi = (float) (2.0f * Math.PI);

        int triangleAmount = 20; //# of triangles used to draw circle

        gl.glPushMatrix();
            gl.glTranslatef(x, y, -0.1f);
            gl.glColor3f(0.0f, 1.0f, 1.0f); // Blue
            gl.glBegin(GL.GL_TRIANGLE_FAN);
                gl.glVertex2f(x_circle, y_circle); // center of circle
                for (int i = 0; i <= triangleAmount; i++) {
                    gl.glVertex2f(
                            (float) (x_circle + (radius * Math.cos(i * twicePi / triangleAmount))),
                            (float) (y_circle + (radius * Math.sin(i * twicePi / triangleAmount))));
                }
            gl.glEnd();
        gl.glPopMatrix();
    }//End of drawFilledCircle()

    /**
     * This function converts the number of yards supplied to the corresponding
     * X-Coord on the texture map associated to the GLCanvas of MainForm.
     * @param yards Number of yards
     * @param color Color of the bar be affected. The option are blue or red.
     */
    public void setXCoordinate(float yards, String color) {
        
        float coord = (float) ((0.39f * yards) / 5.0f);
        if (color.equalsIgnoreCase("red")){
            x_red_coord = coord;
            x_red_animated_coord = 0.0f;
        }
        
        if (color.equalsIgnoreCase("blue")){
            x_blue_coord = coord;
            x_blue_animated_coord = 0.0f;
        }       
    }//end of setXCoordinates  

    /**
     * Sets the width of the red bar
     * @param width with of the red bar
     */
    public void setRedWidth(float width) {

        this.redWidth = (width);
        System.out.println("Red Width: " + this.redWidth);        
     
    }//end of setRedWidth()
    
    /**
     * Sets the width of the blue bar.
     * @param width width of the blue bar.
     */
    public void setBlueWidth(float width) {

        this.blueWidth = (width);
        System.out.println("Blue Width: " + this.blueWidth);        
     
    }//end of setRedWidth()    
    
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
                camera_y -= 0.5;
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            // Moves BACKWARDS on xz plane
            camera_z -= 0.1;
            center_z -= 0.1;
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
            camera_x += 0.1;
            center_x += 0.1;
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
            camera_x -= 0.1;
            center_x -= 0.1;
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

    /**
     * This function is used to attach any JPanel to the righthand 
     * (primary panel) of the MainForm splitPanel.
     * @param pnlPrimary 
     */
    public void attachToPanel(JPanel pnlPrimary) {
        pnlPrimary.add(canvas);
        anim = new Animator(canvas);
        anim.start();
    }

    
    private void loadTexture(String fileName, GL gl) {
        img = null;
        try {
        	URL fileURL = this.getClass().getResource("resources/American_Football_field.png");  
            img = ImageIO.read(fileURL);
            texture = TextureIO.newTexture(img, true);
        } catch (Exception ex) {
            Logger.getLogger(Visualization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end of loadTexture()
    
    /**
     * Initializes the position of the JOGL camera
     */
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


