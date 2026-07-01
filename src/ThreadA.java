/**
 * 
 */
package project2;
/**
*
*/
public class ThreadA extends Thread {
   private Pointer shared;
   public ThreadA(Pointer shared) {
       this.shared = shared;
   }
   @Override
   public void run() {
       try {
           // Function A1
           synchronized (shared) {
               shared.A1 = Computation.sum(500);
               System.out.println("FuncA1 = " + shared.A1);
               shared.A1Done = true;
               shared.notify();
           }
           // Wait until B2 finishes
           synchronized (shared) {
               while (!shared.B2Done)
                   shared.wait();
               shared.A2 = shared.B2 + Computation.sum(300);
               System.out.println("FuncA2 = " + shared.A2);
               shared.A2Done = true;
               shared.notify();
           }
           // Wait until B3 finishes
           synchronized (shared) {
               while (!shared.B3Done)
                   shared.wait();
               shared.A3 = shared.B3 + Computation.sum(400);
               System.out.println("FuncA3 = " + shared.A3);
           }
       }
       catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}