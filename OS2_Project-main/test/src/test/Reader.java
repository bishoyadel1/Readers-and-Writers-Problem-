package test;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Reader implements Runnable {
    private test.ReadWriteLock lock;

    public Reader(test.ReadWriteLock rw) { lock = rw; }

    public void run() {
        while (true) {
            try {
                lock.readLock();
                
                System.out.println("Thread "+Thread.currentThread().getName() + " is READING");     
                
                JFrame frame2 = new JFrame("reader");
                frame2.setVisible(true);
                frame2.setSize(400,250);
                JPanel panel = new JPanel();
                panel.setBackground(new java.awt.Color(0, 180, 190));
                frame2.add(panel);
      
          String fileName = "output.txt";
          File file = new File(fileName);
          FileReader fr = new FileReader(file);
          BufferedReader br = new BufferedReader(fr);
          String line;
          
             while((line = br.readLine()) != null)
             {
                          JLabel label = new JLabel(line);
                          panel.setBackground(new java.awt.Color(0, 180, 190));
                          frame2.add(panel);
                          frame2.add(panel);
                          panel.add(label);
                          
                       Thread.sleep(4000);  
                       //System.out.println(line);
             }
                Thread.sleep(8000);                                                                     
                System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");
                
                lock.readUnLock();
                frame2.setVisible(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
                
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
