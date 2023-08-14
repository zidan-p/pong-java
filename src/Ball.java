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
        this.rect.x + (vx * delta) <= this.leftPaddle.x + this.leftPaddle.width &&
        this.rect.x >= this.leftPaddle.x &&
        (
          this.rect.y + (vy * delta) >= this.leftPaddle.y ||
          this.rect.y + (vy * delta) <= this.leftPaddle.y + this.leftPaddle.height
        )
      ){
        // err, i don't know ...
        double theta = calculateNewVelocityAngle(leftPaddle);
        double newVx = Math.abs((Math.cos(theta) * Constants.BALL_SPEED));
        double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

        double oldSign = Math.signum(vx);
        this.vx = newVx * (-1.0 * oldSign);
        this.vy = newVy;

        System.out.println("theta from paddle : " + theta);
        System.out.println("Ball speed : " + Constants.BALL_SPEED);
        System.out.println("cos theta time speed: " + (Math.cos(theta)) * Constants.BALL_SPEED);
        System.out.println("cos theta : " + Math.cos(theta));
        System.out.println("sin theta : " + Math.sin(theta));
//        System.out.println("new vx : " + this.vx);
//        System.out.println("new vy : " + this.vy);
      }
      else if(this.rect.x + this.rect.width < this.leftPaddle.x){
        System.out.println("You lost the game!!");
      }
    }else if (vx > 0){
      // collision with left paddle
      if(
        this.rect.x + (vx * delta) + this.rect.width >= this.rightPaddle.x &&
        this.rect.x <= this.rightPaddle.x + this.rightPaddle.width &&
        (
          this.rect.y + (vy * delta) >= this.rightPaddle.y ||
          this.rect.y + (vy * delta) + this.rect.height <= this.rightPaddle.y + this.rightPaddle.height
        )
      ){
        // same here, i don't know ...
        double theta = calculateNewVelocityAngle(rightPaddle);
        double newVx = Math.abs((Math.cos(theta) * Constants.BALL_SPEED));
        double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

        double oldSign = Math.signum(vx);
        this.vx = newVx * (-1.0 * oldSign);
        this.vy = newVy;
      }
      else if(this.rect.x + this.rect.width > this.rightPaddle.x + this.rightPaddle.width){
        System.out.println("AI lost the game!");
      }
    }

    // bounce when hit the edge
    if(vy > 0){
      if(this.rect.y + (vx * delta) + this.rect.height > Constants.SCREEN_HEIGHT){
        this.vy *= -1;
      }
    }else if(vy < 0){
      if(this.rect.y + (vx * delta) < Constants.TOOLBAR_HEIGHT){
        this.vy *= -1;
      }
    }


    this.rect.x += vx * delta;
    this.rect.y += vy * delta;
  }

  private double calculateNewVelocityAngle(Rect paddle){
    // where the ball hit the paddle, get relative position from center paddle vv
    double relativeIntersectY = (paddle.y + (paddle.height) / 2.0) - (rect.y + (rect.height / 2));
    double normalizedIntersectY = relativeIntersectY / (paddle.height / 2.0); // it should between -1.0 to 1.0
    double thetaDegree = normalizedIntersectY * Constants.MAX_ANGLE;

    System.out.println("theta degree : " + thetaDegree);
    System.out.println("math to radian : " + Math.toRadians(thetaDegree));
    System.out.println("normalized intersect : " + normalizedIntersectY);
    System.out.println("paddle height : " + paddle.height);
    System.out.println("relative intersect : " + relativeIntersectY);
    return Math.toRadians(thetaDegree);
  }
}
