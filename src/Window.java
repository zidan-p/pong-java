import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

  public Window(){
    this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    this.setTitle(Constants.SCREEN_TITLE);
    this.setResizable(true);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void update(double deltaTime){
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
//      try{
//        Thread.sleep(30);
//      }catch (Exception e){}
    }
  }
}
