package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.*;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.swing.*;

/**
 * Every 15 seconds it's displayed a form to enter a password.
 * If password is incorrect then every 10 seconds the program starts to make screenshots,
 * save to root directory and send to email address.
 * Email address and password are stored in property file.
 *
 * When password is correct the program stops (or not begins) making screenshots. But password form will
 * be displayed every 15 anyway.
 *
 * There are main thread and two daemon threads int the program.
 *
 * Notice:
 * The mail must be allowed to receive massages from undefined programs.
 */
public class Main {

    //synchronized variable that indicates whether the password is entered correctly.
    static AtomicBoolean danger = new AtomicBoolean(false);

    static Properties props;
    static final String email;
    static final String pass;

    static int FILE_NUMBER = 1;

    //reads property file
    static{

        props = new Properties(){
        {
            try {
                load(new FileInputStream("src\\com\\company\\pass.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

        email = props.getProperty("email");
        pass = props.getProperty("pass");

    }


    static Sender sslSender = new Sender(email, pass);


    public static void main(String[] args) throws IOException, AWTException, MessagingException {


        //daemon thread that makes screenshots and send to email if password is incorrect
         new Thread(){
            BufferedImage screencapture = new Robot().createScreenCapture(
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );

            {
                setDaemon(true);
            }

            @Override
            public void run() {

                while(true){
                    if(danger.get() == true)
                    {
                        String fileName = "screencapture"+(FILE_NUMBER++)+".jpg";
                        File file = new File(fileName);
                            try {
                            ImageIO.write(screencapture, "jpg", file);
                            sslSender.send("This is Subject", "G:\\Java\\javaprojects\\PrintScreen\\"+fileName, email, email);

                            } catch (IOException e) {
                            e.printStackTrace();
                        }

                                try {
                                    sleep(10_000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                    }
                }
            }
        }.start();



        //daemon thread that displays password form
        new Thread(){

            {
                setDaemon(true);
            }

            @Override
            public void run() {
                while(true){

                    new Form();

                    try {
                        sleep(15_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        //main thread is just in infinity
        while(true);

    }


    //password form
    static class Form extends JFrame{

        //correct password
        static final String pass = "1";

        Form(){
            setSize(300, 150);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel panel = new JPanel();
            add(panel);
            placeComponents(panel);

            setVisible(true);
        }

        private void placeComponents(JPanel panel) {

            panel.setLayout(null);

            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(10, 40, 80, 25);
            panel.add(passwordLabel);

            JPasswordField passwordText = new JPasswordField(20);
            passwordText.setBounds(100, 40, 160, 25);
            panel.add(passwordText);

            JButton loginButton = new JButton("login");
            loginButton.setBounds(10, 80, 80, 25);
            panel.add(loginButton);

            //if password is incorrect, the program is notified about it
            loginButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!passwordText.getText().equals(pass)){
                        danger.set(true);
                    }
                    else{
                        danger.set(false);
                    }
                    dispose();

                }
            });

        }

    }
}
