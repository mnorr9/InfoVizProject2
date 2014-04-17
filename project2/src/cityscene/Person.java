package cityscene;

import javax.media.opengl.GL;

public class Person {

    private static final long serialVersionUID = 1L;
    private float PI = 3.14159265f;
    private boolean leftArmForward;
    private float lowerLeftArmRotate;
    private boolean rightArmForward;
    private float lowerRightArmRotate;
    private boolean upperLeftLegForward;
    private boolean upperRightLegForward;
    private float upperLeftLegRotate;
    private float upperRightLegRotate;
    private boolean bendLowerLeftLeg;
    private boolean bendLowerRightLeg;
    private boolean lowerLeftLegForward;
    private boolean lowerRightLegForward;
    private float lowerLeftLegRotate;
    private float lowerRightLegRotate;
    private float leftKneeValue;
    private float rightKneeValue;

    public Person() {


        lowerLeftArmRotate = 0;
        leftArmForward = true;
        lowerRightArmRotate = 45;
        rightArmForward = false;
        upperLeftLegForward = true;
        upperRightLegForward = false;
        lowerLeftLegForward = true;
        lowerRightLegForward = false;
        upperLeftLegRotate = 0;
        upperRightLegRotate = 0;
        bendLowerLeftLeg = true;
        bendLowerRightLeg = false;
        lowerLeftLegRotate = 0;
        lowerRightLegRotate = 0;
        leftKneeValue = 0;
        rightKneeValue = 0;
    }

    

    
    public void createCylinder(GL gl, float radius, float height, float theta,
            float red, float green, float blue) {
        

        float radian, t;
        t = theta;
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glColor3f(red, green, blue);
            gl.glVertex3f(0.0f, height, 0.0f);
            gl.glVertex3f(radius, height, 0.0f);
            while (t <= 360) {
                radian = (float) (PI * t / 180.0);
                gl.glVertex3f((float) (radius * Math.cos(radian)), 0f,
                        (float) (radius * Math.sin(radian)));
                t = t + theta;
            }
        gl.glEnd();


        t = theta;
        gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glColor3f(red, green, blue);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(radius, 0.0f, 0.0f);
            while (t <= 360) {
                radian = (float) (PI * t / 180.0);
                gl.glVertex3f((float) (radius * Math.cos(radian)), height,
                        (float) (radius * Math.sin(radian)));
                t = t + theta;
            }
        gl.glEnd();


        t = theta;
        gl.glBegin(GL.GL_QUAD_STRIP);
            gl.glVertex3f(radius, 0.0f, 0.0f);
            gl.glVertex3f(radius, height, 0.0f);
            while (t <= 360) {
                radian = (float) (PI * t / 180.0);
                gl.glVertex3f((float) (radius * Math.cos(radian)), 0.0f,
                        (float) (radius * Math.sin(radian)));
                gl.glVertex3f((float) (radius * Math.cos(radian)), height,
                        (float) (radius * Math.sin(radian)));
                t = t + theta;
            }
        gl.glEnd();
    }// end of createCylinder

    void createSphere(GL gl, float radius, float inc) {
        float r, angle1, angle2, radian1, radian2;

        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);

        for (angle1 = 5.0f; angle1 < 180; angle1 += inc) {
            radian1 = angle1 * PI / 180;
            r = (float) (radius * Math.sin(radian1));
            gl.glBegin(GL.GL_POLYGON);
            for (angle2 = 0.0f; angle2 < 360; angle2 += inc) {
                radian2 = angle2 * PI / 180;
                gl.glVertex3d(r * Math.sin(radian2), radius * Math.cos(radian1), r * Math.cos(radian2));
            }
            gl.glEnd();
        }
        for (angle1 = 5.0f; angle1 < 180; angle1 += inc) {
            radian1 = angle1 * PI / 180;
            r = (float) (radius * Math.sin(radian1));
            gl.glBegin(GL.GL_POLYGON);
            for (angle2 = 0.0f; angle2 < 360; angle2 += inc) {
                radian2 = angle2 * PI / 180;
                gl.glVertex3d(radius * Math.cos(radian1), r * Math.cos(radian2), r * Math.sin(radian2));
            }
            gl.glEnd();
        }
    }

    public void createCircle(GL gl, float radius, float height, float theta) {
        float PI = 3.1415f;
        float radian, r, h, t;

        r = radius;
        h = height;
        t = theta;
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        // gl.glLineWidth(2.5f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(r, 0.0f, 0.0f);
        while (t <= 360) {
            radian = (float) (PI * t / 180.0);
            gl.glVertex3f((float) (r * Math.cos(radian)), 0f,
                    (float) (r * Math.sin(radian)));
            t = t + theta;
        }
        gl.glEnd();
    }

    private void createLowerLeftLeg(GL gl) {
        gl.glPushMatrix();
            gl.glTranslatef(0, -11.0f, 0f);
            gl.glRotatef(lowerLeftLegRotate, 0, 0, 1);
            gl.glTranslatef(leftKneeValue, 0, 0);
            createCylinder(gl, 1.5f, 5, 1, 1, 1, 0);
            gl.glColor3f(1f, 0f, 0f);
            gl.glTranslatef(0, 5, 0);
            createSphere(gl, 1.5f, 1);

            gl.glPushMatrix();
                gl.glRotatef(90, 0, 0, 1);
                gl.glTranslatef(-6, -2, 0);
                createCylinder(gl, 1, 3, 1, 0, 0, 1);
            gl.glPopMatrix();

            if (upperLeftLegRotate >= 0) {
                bendLowerLeftLeg = true;
            } else {
                bendLowerLeftLeg = false;
            }

            if (lowerLeftLegRotate == -30) {
                lowerLeftLegForward = false;
            }

            if (lowerLeftLegForward && bendLowerLeftLeg) {
                lowerLeftLegRotate -= 5;
                leftKneeValue -= 0.5;
            } else if (!(lowerLeftLegForward) && bendLowerLeftLeg) {
                lowerLeftLegRotate += 5;
                leftKneeValue += 0.5;
            } else if (!(lowerLeftLegForward) && !(bendLowerLeftLeg)) {
                lowerLeftLegRotate = 0;
                leftKneeValue = 0;
                lowerLeftLegForward = true;
            } else {
                lowerLeftLegForward = true;
            }
        gl.glPopMatrix();
    }

    private void createUpperLeftLeg(GL gl) {
//        int index = gl.glGenLists(4);
//        gl.glNewList(index, GL.GL_COMPILE);
        gl.glPushMatrix();
            gl.glTranslatef(0, 0, -1.5f);
            gl.glRotatef(upperLeftLegRotate, 0, 0, 1f);
            gl.glTranslatef(0, -7, 0);
            createCylinder(gl, 1.5f, 6, 1, 1, 1, 0);
            gl.glColor3f(1f, 0f, 0f);
            gl.glTranslatef(0, 6f, 0);
            createSphere(gl, 1.5f, 1);
            createLowerLeftLeg(gl);
            if (upperLeftLegRotate == 30) {
                upperLeftLegForward = false;
            }
            if (upperLeftLegRotate == -30) {
                upperLeftLegForward = true;
            }
            if (upperLeftLegForward) {
                upperLeftLegRotate += 5;
            } else {
                upperLeftLegRotate -= 5;
            }
        gl.glPopMatrix();
//        gl.glEndList();
    }

    private void createLowerRightLeg(GL gl) {
        gl.glPushMatrix();
            gl.glTranslatef(0, -11.0f, 0f);
            gl.glRotatef(lowerRightLegRotate, 0, 0, 1);
            gl.glTranslatef(rightKneeValue, 0, 0);
            createCylinder(gl, 1.5f, 5, 1, 1, 1, 0);
            gl.glColor3f(1f, 0f, 0f);
            gl.glTranslatef(0, 5, 0);
            createSphere(gl, 1.5f, 1);

            gl.glPushMatrix();
                gl.glRotatef(90, 0, 0, 1);
                gl.glTranslatef(-6, -2, 0);
                createCylinder(gl, 1, 3, 1, 0, 0, 1);
            gl.glPopMatrix();

            if (upperRightLegRotate >= 0) {
                bendLowerRightLeg = true;
            } else {
                bendLowerRightLeg = false;
            }

            if (lowerRightLegRotate == -30) {
                lowerRightLegForward = false;
            }

            if (lowerRightLegForward && bendLowerRightLeg) {
                lowerRightLegRotate -= 5;
                rightKneeValue -= 0.5;
            } else if (!(lowerRightLegForward) && bendLowerRightLeg) {
                lowerRightLegRotate += 5;
                rightKneeValue += 0.5;
            } else if (!(lowerRightLegForward) && !(bendLowerRightLeg)) {
                lowerRightLegRotate = 0;
                rightKneeValue = 0;
                lowerRightLegForward = true;
            } else {
                lowerRightLegForward = true;
            }

        gl.glPopMatrix();
    }

    private void createUpperRightLeg(GL gl) {
        gl.glPushMatrix();
            gl.glTranslatef(0, 0, 1.5f);
            gl.glRotatef(upperRightLegRotate, 0, 0, 1f);
            gl.glTranslatef(0, -7, 0);
            createCylinder(gl, 1.5f, 6, 1, 1, 1, 0);
            gl.glColor3f(1f, 0f, 0f);
            gl.glTranslatef(0, 6f, 0);
            createSphere(gl, 1.5f, 1);
            createLowerRightLeg(gl);

            if (upperRightLegRotate == 30) {
                upperRightLegForward = false;
            }
            if (upperRightLegRotate == -30) {
                upperRightLegForward = true;
            }
            if (upperRightLegForward) {
                upperRightLegRotate += 5;
            } else {
                upperRightLegRotate -= 5;
            }
        gl.glPopMatrix();

    }

    private void createLeftLeg(GL gl) {
        createUpperLeftLeg(gl);
        //gl.glCallList(4); // 
        //createLowerLeftLeg(gl);
    }

    private void createRightLeg(GL gl) {
        createUpperRightLeg(gl);
    }

    private void createUpperLeftArm(GL gl) {
        createCylinder(gl, 1, 5, 1, 1, 1, 0);
        gl.glColor3f(1f, 0f, 0f);
        gl.glTranslatef(0, 5, 0);
        createSphere(gl, 1, 1);
    }

    private void createLowerLeftArm(GL gl) {
        createCylinder(gl, 1, 5, 1, 1, 1, 0);
        gl.glColor3f(1f, 0f, 0f);
        gl.glTranslatef(0, 5, 0);
        createSphere(gl, 1, 1);
        gl.glTranslatef(0, -6, 0);
        createSphere(gl, 1, 1);
    }

    private void createUpperRightArm(GL gl) {
        createCylinder(gl, 1, 5, 1, 1, 1, 0);
        gl.glColor3f(1f, 0f, 0f);
        gl.glTranslatef(0, 5, 0);
        createSphere(gl, 1, 1);
    }

    private void createLowerRightArm(GL gl) {
        createCylinder(gl, 1, 5, 1, 1, 1, 0);
        gl.glColor3f(1f, 0f, 0f);
        gl.glTranslatef(0, 5, 0);
        createSphere(gl, 1, 1);
        gl.glTranslatef(0, -6, 0);
        createSphere(gl, 1, 1);
    }

    private void createBody(GL gl) {
        gl.glPushMatrix();
        gl.glTranslated(0, -1, 0);
        createCylinder(gl, 3, 9, 1, 0, 0, 1);
        gl.glPopMatrix();
    }

    private void createNeck(GL gl) {
        gl.glPushMatrix();
        gl.glTranslatef(0, 8, 0);
        createCylinder(gl, 1f, 1, 1, 1, 1, 0);
        gl.glPopMatrix();
    }

    private void createHead(GL gl) {
        gl.glPushMatrix();
        gl.glTranslatef(0, 11, 0);
        createSphere(gl, 2, 1);
        gl.glPopMatrix();
    }

    public void createPerson(GL gl) {

        createLeftLeg(gl);
        createRightLeg(gl);
        createHead(gl);
        createNeck(gl);
        createBody(gl);
        
        gl.glPushMatrix();
            gl.glTranslatef(0, 3, -4f);
            createUpperLeftArm(gl);
            gl.glTranslated(0, -5, 0);
            gl.glRotatef(lowerLeftArmRotate, 0f, 0f, 1f);
            gl.glTranslated(0, -5, 0);
            createLowerLeftArm(gl);
            if (lowerLeftArmRotate == 45) {
                leftArmForward = false;
            }
            if (lowerLeftArmRotate == 0) {
                leftArmForward = true;
            }
            if (leftArmForward) {
                lowerLeftArmRotate += 5;
            } else {
                lowerLeftArmRotate -= 5;
            }
        gl.glPopMatrix();

        gl.glPushMatrix();
            gl.glTranslatef(0, 3, 4f);
            createUpperRightArm(gl);
            gl.glTranslated(0, -5, 0);
            gl.glRotatef(lowerRightArmRotate, 0f, 0f, 1f);
            gl.glTranslated(0, -5, 0);
            createLowerRightArm(gl);
            if (lowerRightArmRotate == 45) {
                rightArmForward = false;
            }
            if (lowerRightArmRotate == 0) {
                rightArmForward = true;
            }
            if (rightArmForward) {
                lowerRightArmRotate += 5;
            } else {
                lowerRightArmRotate -= 5;
            }
        gl.glPopMatrix();
    }
}