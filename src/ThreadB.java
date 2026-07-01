/**
*
*/
package project2;
/**
*
*/
public class ThreadB extends Thread {
   private Pointer shared;
   public ThreadB(Pointer shared) {
       this.shared = shared;
   }
   @Override
   public void run() {
       try {
           // Function B1
           synchronized (shared) {
               shared.B1 = Computation.sum(250);
               System.out.println("FuncB1 = " + shared.B1);
           }
           // Wait until A1 finishes
           synchronized (shared) {
               while (!shared.A1Done)
                   shared.wait();
               shared.B2 = shared.A1 + Computation.sum(200);
               System.out.println("FuncB2 = " + shared.B2);
               shared.B2Done = true;
               shared.notify();
           }
           // Wait until A2 finishes
           synchronized (shared) {
               while (!shared.A2Done)
                   shared.wait();
               shared.B3 = shared.A2 + Computation.sum(400);
               System.out.println("FuncB3 = " + shared.B3);
               shared.B3Done = true;
               shared.notify();
           }
       }
       catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}
