import java.awt.event.KeyEvent;

public class PlayerController {
  public Rect rect;
  public KL keylistener;

  public PlayerController(Rect rect, KL keylistener){
    this.rect = rect;
    this.keylistener = keylistener;
  }

  public void update(double delta){
    if(keylistener.isKeyPressed(KeyEvent.VK_DOWN)){
      this.rect.y += (int) (100 * delta);
    }
  }
}
