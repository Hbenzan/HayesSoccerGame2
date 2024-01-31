import java.awt.*;

public class SoccerBall {


    public String name;
    public int xpos;
    public int ypos;
    public int dx = 3; //speed in the x direction
    public int dy = 1; //speed in the y direction
    public int width = 100;
    public int height = 100;
    public boolean isAlive;
    public Rectangle rec;


    public SoccerBall (String paraName, int paramXpos, int paramYpos){
        name = paraName;
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle(xpos, ypos, width, height);


    }

    public void move(){
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos > 1000 - width) {
            dx = -dx;
        } // right side

        if (xpos < 0) {
            dx = -dx;
        } // left side


        if (ypos > 700 - height) {
            dy = -dy;
        }
        if (ypos < 0) {
            dy = -dy;
        }
        rec = new Rectangle(xpos, ypos, width, height); //so the hitbox move with the astronaut
    } //end of move method


    public void wrap (){
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos > 1100){

            xpos = - width;
        } //r to l

        if (xpos < -width) {

            xpos = 1100;
        }

        if (ypos > 700) {

            ypos = - height;
        }
        //bottom to top
        if (ypos < -height) {

            ypos = 700;
        }

        rec = new Rectangle(xpos, ypos, width, height);
    }






}
