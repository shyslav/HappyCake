package com.shyslav.controller.Cook;

import com.shyslav.controller.alert.sampleAlert;
import com.shyslav.models.CookOrder;
import com.shyslav.server.comands;
import com.shyslav.server.serverConnection;
import com.shyslav.start.Main;
import javafx.application.Platform;
import javafx.scene.control.Alert;

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
        if(serverConnection.emp.get(0).getPositionID()==3) {
            tr = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!done && serverConnection.scanner.hasNextLine()) {
                        String line = serverConnection.scanner.nextLine();
                        if (line.equals("updateCook")) {
                            try {
                                Object o = serverConnection.objInputStream.readObject();
                                list = (LinkedList<CookOrder>) o;
                                Main.cookConroller.updateOrders();
                                playSound("cookMusic.wav");
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Platform.runLater(()->sampleAlert.ConnectionError());
                }
            });
            tr.start();
        }else
        {
            sampleAlert sa = new sampleAlert("Предупреждение","Это не ваш раздел но вы имеете доступ к нему",
                    "Вам не будут приходить новые уведомления, Вы видите только текущую загруженость данного раздела.", Alert.AlertType.INFORMATION);
        }
    }
    public static synchronized void playSound(final String url) {
        Thread music = new Thread(new Runnable() {
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
        });
        music.start();
    }
}
