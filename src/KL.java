import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KL: keylistener
public class KL implements KeyListener {

  // list key in keyboard is there are pressed
  private boolean[] keyPressed = new boolean[128];
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  // when pres
  public void keyPressed(KeyEvent e) {
    keyPressed[e.getKeyCode()] = true;
  }

  @Override
  // when release
  public void keyReleased(KeyEvent e) {
  keyPressed[e.getKeyCode()] = false;
  }

  public boolean isKeyPressed(int keyCode){
    return keyPressed[keyCode];
  }
}
