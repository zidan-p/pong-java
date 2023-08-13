public class Ball {
  public Rect rect; // the ball
  public Rect leftPaddle, rightPaddle;

  // velocity
  private double vy = 150.0;
  private double vx = -50.0;

  public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle){
    this.rect = rect;
    this.leftPaddle = leftPaddle;
    this.rightPaddle = rightPaddle;
  }

  public void update(double delta){
    // position = position + velocity
    // velocity = velocity + acceleration

    if(vx < 0){
      // collision with left paddle
      if(
        this.rect.x <= this.leftPaddle.x + this.leftPaddle.width &&
        this.rect.x >= this.leftPaddle.x &&
        this.rect.y >= this.leftPaddle.y &&
        this.rect.y <= this.leftPaddle.y + this.leftPaddle.height
      ){
        this.vx *= -1;
      }
      else if(this.rect.x + this.rect.width < this.leftPaddle.x){
        System.out.println("You lost the game!!");
      }
    }else if (vx > 0){
      // collision with left paddle
      if(
        this.rect.x + this.rect.width >= this.rightPaddle.x &&
        this.rect.x <= this.rightPaddle.x + this.rightPaddle.width &&
        this.rect.y >= this.rightPaddle.y &&
        this.rect.y <= this.rightPaddle.y + this.rightPaddle.height
      ){
        this.vx *= -1;
      }
      else if(this.rect.x + this.rect.width > this.rightPaddle.x + this.rightPaddle.width){
        System.out.println("AI lost the game!");
      }
    }

    // bounce when hit the edge
    if(vy > 0){
      if(this.rect.y + this.rect.height > Constants.SCREEN_HEIGHT){
        this.vy *= -1;
      }
    }else if(vy < 0){
      if(this.rect.y < Constants.TOOLBAR_HEIGHT){
        this.vy *= -1;
      }
    }


    this.rect.x += vx * delta;
    this.rect.y += vy * delta;

    // System.out.println("ball position : " + this.rect.x + ", " + this.rect.y);
    // test accel
//    vx += vx < 0? -2 : 2;
//    vy += vy < 0? -2 : 2;
  }
}
