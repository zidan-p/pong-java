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
      if((rect.y + Constants.PADDLE_SPEED * delta) + rect.height < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)
        this.rect.y += Constants.PADDLE_SPEED * delta;
    }else if(keylistener.isKeyPressed(KeyEvent.VK_UP)){
      if(rect.y - Constants.PADDLE_SPEED * delta > Constants.TOOLBAR_HEIGHT)
        this.rect.y -= Constants.PADDLE_SPEED * delta;
    }
  }
}
