package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

 class Test {
    public static void main(String [] args) throws InterruptedException {
        
        
       ExecutorService executorService = Executors.newCachedThreadPool();
       ReadWriteLock RW = new ReadWriteLock();
       Reader Reader = new Reader(RW);
       Writer Writer = new Writer(RW);

  JFrame frame = new JFrame("Test");     
  frame.setVisible(true);
  frame.setSize(600,300);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  JPanel panel = new JPanel();
  panel.setBackground(new java.awt.Color(0, 204, 204));
  frame.add(panel);
  JButton button = new JButton("Writer");
  panel.add(button);
  button.addActionListener(new ActionListener(){  
      int i = 0 ;
public void actionPerformed(ActionEvent e){  

   Thread t3 = new Thread(Writer);
             t3.setName("thread3");
             t3.start();
        }  
    });  
  
  JButton button2 = new JButton("reader");
  panel.add(button2);
  button2.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){  
      
             Thread t1 = new Thread(Reader);
        t1.setName("thread1");
         t1.start();
        }  
    });  
      
    //    int y = 0;
    // while(y<50){executorService.execute(new Writer(RW));y++;} 
     
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
     /*
        
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
        t5.start(); */
        executorService.shutdown();
    }

}

