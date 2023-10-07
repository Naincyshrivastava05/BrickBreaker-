package BRICKBRAKER;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class mapgenerator {
    public int map[][];
    public int brickHeight;
    public int brickWidth;
    public  mapgenerator(int row, int col){
        map = new int[row][col];
        for(int i =0; i<map.length; i++){
            for(int j =0; j<map[0].length; j++){
                map[i][j] = 1;
              
            }
        }
        brickHeight = 540/col;
        brickWidth = 150/row;
        
    }


    public void draw(Graphics2D g){
          for(int i =0; i<map.length; i++){
            for(int j =0; j<map[0].length; j++){
               if(map[i][j]>0){
               if((i+j)%2==0){

                   g.setColor(Color.white);
                }
                else{
                     g.setColor(Color.gray);
                    }
                    
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
                g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
               }
            }
        }
    }
    public void setBrickVlaue(int value, int row , int col){
        map[row][col]  = value;
    }
}
