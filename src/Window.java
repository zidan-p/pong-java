import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {
  public Graphics2D g2;
  public KL keylistener = new KL();

  // entity that drew in canvas
  public Rect player1, ai, ballRect; // let say it 8-bit game, so the ball must be 8 bit

  // controller
  public Ball ball;
  public PlayerController playerController;
  public AIController aiController;

  public Window(){
    this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    this.setTitle(Constants.SCREEN_TITLE);
    this.setResizable(true);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.addKeyListener(this.keylistener);
    g2 = (Graphics2D)this.getGraphics();
    Constants.TOOLBAR_HEIGHT = this.getInsets().top;
    System.out.println(Constants.TOOLBAR_HEIGHT);
    Constants.INSETS_BOTTOM = this.getInsets().bottom;

    // init entity
    // NOTE: I don't know if it's just my computer or else, but apparently
    // the draw method measure 0 point from the edge of the frame, so I must add some value
    // to wild hack this behaviour
    player1 = new Rect(
      Constants.PADDING_X,
      0,
      Constants.PADDLE_WIDTH,
      Constants.PADDLE_HEIGHT,
      Constants.PADDLE_COLOR
    );
    playerController = new PlayerController(player1, keylistener);

    ballRect = new Rect(
      Constants.SCREEN_WIDTH / 2,
      100,
      Constants.BALL_WIDTH,
      Constants.BALL_WIDTH,
      Constants.PADDLE_COLOR
    );

    ai = new Rect(
      Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.PADDING_X,
      0,
      Constants.PADDLE_WIDTH,
      Constants.PADDLE_HEIGHT,
      Constants.PADDLE_COLOR
    );
    ball = new Ball(ballRect,player1, ai);

    aiController = new AIController(new PlayerController(ai), ballRect);

  }

  public void update(double deltaTime){

    // implement double buffer for screen optimize
    Image dbImage = createImage(getWidth(), getHeight());
    Graphics dbg = dbImage.getGraphics();
    this.draw(dbg);
    g2.drawImage(dbImage, 0, 0, this);

    // update the object position
    playerController.update(deltaTime);
    aiController.update(deltaTime);
    ball.update(deltaTime);

  }

  public void draw(Graphics g){
    Graphics2D g2 = (Graphics2D) g;

    // fill all with orange
    g2.setColor(Color.ORANGE);
    g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    // draw entities
    player1.draw(g2);
    ai.draw(g2);
    ballRect.draw(g2);

  }

  @Override
  public void run() {
    double lastFrameTime = 0.0;
    while (true){
      double time = Time.getTime();
      double deltaTime = time - lastFrameTime;
      lastFrameTime = time;
      update(deltaTime);

      // how to limit the fps.
      // it will process the update 30 frame every seconds
      try{
        Thread.sleep(30);
      }catch (Exception e){}
    }
  }
}
