package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



 class Test {
    public static void main(String [] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReadWriteLock RW = new ReadWriteLock();

        		JFrame frame = new JFrame("Test");
                        
  frame.setVisible(true);
  frame.setSize(1000,500);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
  JPanel panel = new JPanel();
  panel.setBackground(new java.awt.Color(0, 204, 204));
  frame.add(panel);
  JButton button = new JButton("hello agin1");
  panel.add(button);

  button.addActionListener (new WriterAction());
  

  JButton button2 = new JButton("hello agin2");
  
  panel.add(button2);
  
  button2.addActionListener (new ReaderAction()); 
   
       /*
        executorService.execute(new Reader(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Writer(RW));*/
        Reader read = new Reader(RW);
        Writer write = new Writer(RW);
          Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(read);
        t2.setName("thread2");
        Thread t3 = new Thread(write);
        t3.setName("thread3");
        Thread t4 = new Thread(read);
        t4.setName("thread4");
        Thread t5 = new Thread(write);
        t5.setName("thread5");
 t1.start();
        t3.start();
        t2.start();
        t4.start();
        t5.start();
        executorService.shutdown();
    }
      public static class WriterAction implements ActionListener {        
  public void actionPerformed (ActionEvent e) {  
          ExecutorService executorService = Executors.newCachedThreadPool();
        ReadWriteLock RW = new ReadWriteLock();
 executorService.execute(new Writer(RW));
    }}
          static class ReaderAction implements ActionListener {        
  public void actionPerformed (ActionEvent e) {     
    JFrame frame2 = new JFrame("Clicked");
         
    frame2.setVisible(true);
    frame2.setSize(800,600);
    
    JLabel label = new JLabel("you clicked me");
    JPanel panel = new JPanel();
    frame2.add(panel);
    panel.add(label);       
  }
  
  }
}

