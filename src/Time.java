public class Time {
  public static double timeStarted = System.nanoTime();

  public static double getTime(){
    double nanoToSecond = 1E-9;
    return (System.nanoTime() - timeStarted ) * nanoToSecond;
  }
}
