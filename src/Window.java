import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {
  Graphics2D g2;
  KL keylistener = new KL();

  // entity that play
  Rect player1, ai, ball; // let say it 8-bit game, so the ball must be 8 bit

  public Window(){
    this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    this.setTitle(Constants.SCREEN_TITLE);
    this.setResizable(true);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.addKeyListener(this.keylistener);
    g2 = (Graphics2D)this.getGraphics();

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

    ai = new Rect(
      Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.PADDING_X,
      0,
      Constants.PADDLE_WIDTH,
      Constants.PADDLE_HEIGHT,
      Constants.PADDLE_COLOR
    );

    ball = new Rect(
      Constants.SCREEN_WIDTH / 2,
      100,
      Constants.BALL_WIDTH,
      Constants.BALL_WIDTH,
      Constants.PADDLE_COLOR
    );
  }

  public void update(double deltaTime){

    // fill all with orange
    g2.setColor(Color.ORANGE);
    g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    // draw entities
    ball.draw(g2);
    player1.draw(g2);
    ai.draw(g2);
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
