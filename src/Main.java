public class Main {
  public static void main(String[] args){
    System.out.println("saya akan memulai membuat pong (`~`) ");
    Window window = new Window();

    // don't forget to pass the class,
    // the class will show nothing because it runs when the thread already running in separate process
    Thread t1  = new Thread(window);
    t1.start();

  }
}
