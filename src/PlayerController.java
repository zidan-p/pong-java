import java.awt.event.KeyEvent;

public class PlayerController {
  public Rect rect;
  public KL keylistener;

  public PlayerController(Rect rect, KL keylistener){
    this.rect = rect;
    this.keylistener = keylistener;
  }

  // when the controller didn't exist, let AI control the character
  public PlayerController(Rect rect){
    this.rect = rect;
    keylistener = null;
  }

  public void update(double delta){
//    System.out.println("is keylistener null? - " + (keylistener == null));
    if(this.keylistener != null){
      if(keylistener.isKeyPressed(KeyEvent.VK_DOWN)){
        this.moveDown(delta);
      }else if(keylistener.isKeyPressed(KeyEvent.VK_UP)){
        this.moveUp(delta);
      }
    }

  }

  public void moveDown(double delta){
    if((rect.y + Constants.PADDLE_SPEED * delta) + rect.height < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)
      this.rect.y += Constants.PADDLE_SPEED * delta;
  }

  public void moveUp(double delta){
    if(rect.y - Constants.PADDLE_SPEED * delta > Constants.TOOLBAR_HEIGHT)
      this.rect.y -= Constants.PADDLE_SPEED * delta;
  }
}
