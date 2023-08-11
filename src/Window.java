import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

  public Window(){
    this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    this.setTitle(Constants.SCREEN_TITLE);
    this.setResizable(true);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  @Override
  public void run() {

  }
}
