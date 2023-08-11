import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {
  Graphics2D g2;

  public Window(){
    this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    this.setTitle(Constants.SCREEN_TITLE);
    this.setResizable(true);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    g2 = (Graphics2D)this.getGraphics();
  }

  public void update(double deltaTime){

    // fill all with orange
    g2.setColor(Color.ORANGE);
    g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    // fill cirlce red
    g2.setColor(Color.RED);
    g2.fillArc(300, 300, 50, 50, 0, 360);


    System.out.println(deltaTime + " has passed since last frame");
    System.out.println(1 / deltaTime + " fps");
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
