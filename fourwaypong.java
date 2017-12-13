import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;

public class fourwaypong implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
  //Properties 
  JFrame theframe;
  anipong thepanel;
  Timer thetimer;
  
  //wasd
  boolean blnw = false;
  boolean blna = false;
  boolean blns = false;
  boolean blnd = false;
  //Arrow
  boolean blnup = false;
  boolean blnlt = false;
  boolean blndw = false;
  boolean blndrt = false;
  
  //Reverse
  int intXR = 0;
  int intYR = 0;
  
  //Methods 
  public void actionPerformed(ActionEvent evt){
    if(evt.getSource() == thetimer){
      thepanel.repaint();      
      //wasd Move
      if(blnw){
        thepanel.intY1 -= 10;
      }
      if(blns){
        thepanel.intY1 += 10;
      }
      if(blna){
        thepanel.intX1 -= 10;
      }
      if(blnd){
        thepanel.intX1 += 10;
      }
      if(blnup){
        thepanel.intY2 -= 10;
      }
      if(blndw){
        thepanel.intY2 += 10;
      }
      
      //Collisions
      Ellipse2D.Double ball = new Ellipse2D.Double(thepanel.intX, thepanel.intY, 50, 50);
      Rectangle2D.Double barleft = new Rectangle2D.Double(20, thepanel.intY1, 10, 90);
      Rectangle2D.Double barright = new Rectangle2D.Double(470, thepanel.intY2, 10, 90);
      Rectangle2D.Double bartop = new Rectangle2D.Double(thepanel.intX1, 20, 90, 10);
      Rectangle2D.Double barbot = new Rectangle2D.Double(thepanel.intX2, 470, 90, 10); 
      
      if(ball.intersects(barleft)){
        intXR = 1;
      }
      if(ball.intersects(barright)){
        intXR = 2;
      }
      if(ball.intersects(bartop)){
        intYR = 1;
      }
      if(ball.intersects(barbot)){
        intYR = 2;
      }
      
      //Screen Bounds 
      //X
      if(thepanel.intX >= 450 || intXR == 2){
        thepanel.intMX = -1 * (int)(Math.random() * 10 + 5);
        intXR = 0;
      }
      if(thepanel.intX <= 0 || intXR == 1){
        thepanel.intMX = (int)(Math.random() * 10 + 5);
        intXR = 0;
      }
      
      //Y 
      if(thepanel.intY >= 450 || intYR == 2){
        thepanel.intMY = -1 * (int)(Math.random() * 10 + 5);
        intYR = 0;
      }
      if(thepanel.intY <= 0 || intYR == 1){
        thepanel.intMY = (int)(Math.random() * 10 + 5);
        intYR = 0;
      }
      
      thepanel.intX += thepanel.intMX;
      thepanel.intY += thepanel.intMY;
      
    }
    
  }
  
  public void keyReleased(KeyEvent evt){
    if(evt.getKeyChar() == 'w'){
      blnw = false;
    }
    if(evt.getKeyChar() == 's'){
      blns = false;
    }
    if(evt.getKeyChar() == 'a'){
      blna = false;
    }
    if(evt.getKeyChar() == 'd'){
      blnd = false;
    }
    if(evt.getKeyCode() == 38){
      blnup = false;
    }
    if(evt.getKeyCode() == 40){
      blndw = false;
    }
    
  }
//Keys   
  public void keyPressed(KeyEvent evt){
    if(evt.getKeyChar() == 'w'){
      blnw = true;
    }
    if(evt.getKeyChar() == 's'){
      blns = true;
    }
    if(evt.getKeyChar() == 'a'){
      blna = true;
    }
    if(evt.getKeyChar() == 'd'){
      blnd = true;
    }
    if(evt.getKeyCode() == 38){
      blnup = true;
    }
    if(evt.getKeyCode() == 40){
      blndw = true;
    }
    
  }
  
  public void keyTyped(KeyEvent evt){
  }
  
  public void mouseReleased(MouseEvent evt){
  }
  
  public void mousePressed(MouseEvent evt){
    if(evt.getButton() == 1){
      thepanel.intX2 = 500 - evt.getX();
    }
  }
  
  public void mouseClicked(MouseEvent evt){
  }
  
  public void mouseMoved(MouseEvent evt){
  }
  
  public void mouseDragged(MouseEvent evt){
  }
  
  public void mouseExited(MouseEvent evt){
  }
  
  public void mouseEntered(MouseEvent evt){
  }
  
  
  
  //Constructor 
  public fourwaypong(){
    thepanel = new anipong();
    thepanel.setPreferredSize(new Dimension(500,500));
    thepanel.setLayout(null);
    thepanel.addMouseListener(this);
    thepanel.addMouseMotionListener(this);
    
    theframe = new JFrame();
    theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    theframe.setContentPane(thepanel);
    theframe.pack();
    theframe.setVisible(true);
    theframe.addKeyListener(this);
    
    thetimer = new Timer(1000/60, this);
    thetimer.start();
  }
  
  
  //Main
  public static void main(String[] args){
    new fourwaypong();
    
  }
  
  
  
  
}