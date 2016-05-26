package com.shyslav.controller.Cook;

import com.shyslav.models.CookOrder;
import com.shyslav.server.comands;
import com.shyslav.server.serverConnection;
import com.shyslav.start.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Shyshkin Vladyslav on 25.05.2016.
 */
public class CookModel {
    public static LinkedList<CookOrder> list = new LinkedList<>();
    public static volatile Thread tr = null;
    public static volatile boolean done = false;

    public static void initialize()
    {
        if(tr == null)
        {
            list = comands.getCookList();
            newDishThread();
        }
    }
    private static void newDishThread()
    {
        tr = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!done && serverConnection.scanner.hasNextLine() )
                {
                    System.out.println("run run run");
                    String line = serverConnection.scanner.nextLine();
                    if(line.equals("updateCook")) {
                        try {
                            Object o = serverConnection.objInputStream.readObject();
                            list = (LinkedList<CookOrder>) o;
                            Main.cookConroller.task.run();
                            playSound("cookMusic.wav");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(" !run !run !run");
            }
        });
        tr.start();
    }
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            CookController.class.getResourceAsStream(url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
