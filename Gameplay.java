package BRICKBRAKER;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay  extends JPanel implements KeyListener, ActionListener{

    public boolean play = false;
    private int score = 0;
    private int totalbricks = 30;

    private  Timer timer;
    private int delay =5;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private mapgenerator map; 

    public Gameplay(){
        map = new mapgenerator(3,10);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){
            //background
            g.setColor(Color.black);
            g.fillRect(1, 1, 692, 592);


            //drawing map
            map.draw((Graphics2D)g);

            //borders

            g.setColor(Color.yellow);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0, 0, 692, 3);
            g.fillRect(692, 0, 3, 592);


            //scores
            g.setColor(Color.white);
             g.setFont (new Font("Serif", Font.BOLD,25));
            g.drawString(" "+score, 590, 30);

            //paddle

            g.setColor(Color.GREEN);
            g.fillRect(playerX, 550, 100, 8);

            //ball
             g.setColor(Color.YELLOW);
            g.fillOval(ballposX , ballposY, 20, 20);

            if(totalbricks <=0){
                 play = false;
                ballXdir = 0;
                ballYdir = 0;
                g.setColor(Color.GREEN);
                g.setFont (new Font("Serif", Font.BOLD,30));
                  g.drawString("You Won, Final Score: "+score, 200, 300);

                     g.setFont (new Font("Serif", Font.BOLD,20));
                  g.drawString("Press Enter to Restart", 230, 350);
            }


            if(ballposY>570){
                play = false;
                ballXdir = 0;
                ballYdir = 0;
                g.setColor(Color.RED);
                g.setFont (new Font("Serif", Font.BOLD,30));
                  g.drawString("Game over , Scores:"+score, 190, 300);

                     g.setFont (new Font("Serif", Font.BOLD,20));
                  g.drawString("Press Enter to Restart", 230, 350);
            }
            g.dispose();
    }


    
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){

            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100 , 8)))
            {
                ballYdir = -ballYdir;
            }

           A: for(int i =0; i<map.map.length; i++){
                for(int j =0; j<map.map[0].length; j++){
                    if(map.map[i][j]>0){
                        int brickX = j*map.brickWidth+80;
                        int brickY  =  i*map.brickHeight+50;
                        int brickHeight = map.brickHeight;
                        int brickWidth = map.brickWidth;

                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect =  new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRact = rect;

                        if(ballRect.intersects(brickRact))
                        {
                            map.setBrickVlaue(0, i, j);
                            totalbricks--;
                            score+=5;
                            if(ballposX+19<=brickRact.x ||ballposY+1>= brickRact.x+brickRact.width){
                                ballXdir = -ballXdir;
                            }
                            else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
                ballposX+=ballXdir;
                ballposY+=ballYdir;

                //Leftborder
                if(ballposX<0){
                    ballXdir=  -ballXdir;
                }

                //top border
                  if(ballposY<0){
                    ballYdir = -ballYdir;
                }

                //right border
                  if(ballposX>670){
                    ballXdir = -ballXdir;
                }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            } else{
                  moveRight();
            }
        }
         if(e.getKeyCode()==KeyEvent.VK_LEFT){
             if(playerX < 10){
                playerX = 10;
            } else{
                  moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                 ballXdir= -1;
                 ballYdir = -2;
                 playerX = 310;
                 score = 0;
                 totalbricks = 30; 
                 map = new mapgenerator(3, 10);
                 repaint();
            }
        }

    }
    public void moveRight(){
        play = true;
        playerX+=20;
    }
    public void moveLeft(){
          play = true;
        playerX-=20;
    }

    
}
