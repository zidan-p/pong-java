import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {
  Graphics2D g2;
  KL keylistener = new KL();

  public Window(){
    this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    this.setTitle(Constants.SCREEN_TITLE);
    this.setResizable(true);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.addKeyListener(this.keylistener);
    g2 = (Graphics2D)this.getGraphics();
  }

  public void update(double deltaTime){

    // fill all with orange
    g2.setColor(Color.ORANGE);
    g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    Rect rect = new Rect(100, 100, 300, 300, Color.ORANGE);
    rect.draw(g2);
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
