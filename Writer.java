package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
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
                    
                

  JFrame f = new JFrame("The Twilight Zone");
   //set size and location of frame
   f.setSize(800, 600);
   f.setLocation(100, 150);
   //make sure it quits when x is clicked
   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //set look and feel
   f.setDefaultLookAndFeelDecorated(true);
   JLabel labelM = new JLabel("Not Only of Sight, but of: ");
   labelM.setBounds(50, 50, 200, 30);
   JTextField motto1 = new JTextField();
   //set size of the text box
   motto1.setBounds(50, 100, 500, 80);
  // motto1.addActionListener( WriterActionn(){});
   //add elements to the frame
   JButton button = new JButton("hello agin1");
    f.add(button);
    button.setBounds(50, 200, 200, 30);
    f.add(button);
    button.addActionListener (new Test.WriterAction(){

    });
    
   f.add(labelM);
   f.add(motto1);
   f.setLayout(null);
   f.setVisible(true);
     
                
                /*
    JFrame frame3 = new JFrame("OKNO 3");
    frame3.setVisible(true);
    frame3.setSize(800,600);
    frame3.setLocation(100, 150);
    JLabel label = new JLabel("kliknales555555");
    JPanel panel = new JPanel();
    frame3.add(panel);
    panel.add(label);
    */
    
    
             
    
    
    
    
    
    
                //String str = "File Handling in Java using "+  " FileWriter and FileReader";
                FileWriter fw =new FileWriter("output.txt");//critical section
                String writeone = myObj.nextLine();
                fw.write(writeone);
                fw.close();
               // Thread.sleep(2000);             
                //critical section
                System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");    //critical section
                lock.writeUnLock();
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