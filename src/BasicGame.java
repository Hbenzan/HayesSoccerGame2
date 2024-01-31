import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class BasicGame implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //** Step 1: Declare Astronaut and its Image
    public SoccerBall oliver;
    public Image oliverPic;

    public SoccerBall hayes;
    public Image hayesPic;

    public SoccerBall Jamal;
    public Image JamalPic;
    public Image backgroundPic;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGame ex = new BasicGame();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGame() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game
        //  *** STEP 2; CONSTRUCT ASTRONAUT AND ITS IMAGE
        oliver = new SoccerBall("Oliver",200, 300);
        oliverPic = Toolkit.getDefaultToolkit().getImage("HayesSoccerBall.jpeg");

        hayes = new SoccerBall("Hayes",100,200 );
        hayesPic = Toolkit.getDefaultToolkit().getImage("OrangeSoccerBall.png");

        Jamal = new SoccerBall("Jamal",400,400 );
        JamalPic = Toolkit.getDefaultToolkit().getImage("GreenSoccerBall.jpeg");
        Jamal.dx = (int) (Math.random() * 6);
        Jamal.dy = 2;

        backgroundPic = Toolkit.getDefaultToolkit().getImage("SoccerField.jpeg");

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //call the move() code for each object
        oliver.move();
        Jamal.move ();
        hayes.wrap();
    }

    public void collisions(){
        //check whether oliver and hayes are colliding
        // if they collide, reverse their diretions



        if (oliver.rec.intersects(hayes.rec)){
            oliver.dx = oliver.dx + 10;
            oliver.dy = -oliver.dy;
            hayes.dx = -hayes.dx;
            hayes.dy = -hayes.dy;
        }

        if (Jamal.rec.intersects(oliver.rec)){
            Jamal.dx = Jamal.dx - 10;
            Jamal.dy = oliver.dy;

        }


    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(backgroundPic,0,0, WIDTH, HEIGHT, null);
        //draw the images
        //*** STEP 3: DRAW THE IMAGE TO THE SCREEN
        // g.drawImage(oliverPic, 500,0 100,100,null);
        g.drawImage(oliverPic, oliver.xpos, oliver.ypos, oliver.width, oliver.height, null);

        //g.drawRect(100,200,100,100);
        //draw hitboxes
        //  g.setColor(Color.RED);
        //  g.drawRect(oliver.xpos, oliver.ypos, oliver.width, oliver.height);
        g.setColor(Color.YELLOW);
        g.drawRect(oliver.rec.x,oliver.rec.y,oliver.rec.width, oliver.rec.height);
        g.setColor(Color.ORANGE);
        g.drawRect(hayes.rec.x,hayes.rec.y,hayes.rec.width, hayes.rec.height);
        g.setColor(Color.BLUE);
        g.drawRect(Jamal.rec.x,Jamal.rec.y,Jamal.rec.width, Jamal.rec.height);

        g.drawImage (hayesPic, hayes.xpos, hayes.ypos, hayes.width, hayes.height, null);
        g.drawImage (JamalPic, Jamal.xpos, Jamal.ypos, Jamal.width, Jamal.height, null);
        g.dispose();
        bufferStrategy.show();


    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}