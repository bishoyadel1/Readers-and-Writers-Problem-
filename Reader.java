package test;

import java.util.concurrent.*;
import java.io.*;

public class Reader implements Runnable {
    private test.ReadWriteLock lock;

    public Reader(test.ReadWriteLock rw) { lock = rw; }

    public void run() {
        while (true) {
            try {
                lock.readLock();
                System.out.println("Thread "+Thread.currentThread().getName() + " is READING");               //critical section
 
                
          String fileName = "output.txt";
          File file = new File(fileName);
          FileReader fr = new FileReader(file);
          BufferedReader br = new BufferedReader(fr);
          String line;
             while((line = br.readLine()) != null)
             {
             //process the line
                    System.out.println(line);
             }
                
       
                Thread.sleep(2000);                                                                     //critical section
                System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");    //critical section
                lock.readUnLock();
                Thread.sleep(1000); //out of critical section
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
