package test;
import java.util.Scanner;
import java.io.*;

public class Writer implements Runnable {
    private ReadWriteLock lock;

    public Writer(ReadWriteLock rw) {
        lock = rw;
    }

    public void run()  {
         Scanner myObj = new Scanner(System.in);
        while (true){
            try {
                lock.writeLock();
                System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");  
              
                FileWriter fw =new FileWriter("output.txt");
                String writeone = myObj.nextLine();
                fw.write(writeone);
                fw.close();
                                                                        
                System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");   
                lock.writeUnLock();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
              catch (IOException e) {
                e.printStackTrace(); 
            }
        }
    }
}