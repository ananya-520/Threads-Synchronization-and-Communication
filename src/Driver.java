Driver.java

/**
*
*/
package project2;
/**
*
*/
public class Driver {
   public static void main(String[] args) {
       for (int i = 0; i < 20; i++) {
           Pointer shared = new Pointer();
           ThreadA threadA = new ThreadA(shared);
           ThreadB threadB = new ThreadB(shared);
           threadA.start();
           threadB.start();
           try {
               threadA.join();
               threadB.join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           if (shared.A1 != 125250 ||
               shared.B1 != 31375 ||
               shared.B2 != 145350 ||
               shared.A2 != 190500 ||
               shared.B3 != 270700 ||
               shared.A3 != 350900) {
               System.out.println("Incorrect Result!");
               return;
           }
       }
       System.out.println("\nAll iterations completed successfully.");
   }
}

