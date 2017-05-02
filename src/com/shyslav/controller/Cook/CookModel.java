package com.shyslav.controller.Cook;

import appmodels._CookOrder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.LinkedList;

/**
 * @author Shyshkin Vladyslav on 25.05.2016.
 */
public class CookModel {
    public static LinkedList<_CookOrder> list = new LinkedList<>();
    public static volatile Thread tr = null;
    public static volatile boolean done = false;

    public static void initialize() {
        if (tr == null) {
//            list = ServerCommands.getCookList();
            newDishThread();
        }
    }

    private static void newDishThread() {
//        if (ServerConnect.emp.get(0).getPositionID() == 3) {
//            tr = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (!done && ServerConnect.scanner.hasNextLine()) {
//                        String line = ServerConnect.scanner.nextLine();
//                        if (line.equals("updateCook")) {
//                            try {
//                                Object o = ServerConnect.objInputStream.readObject();
//                                list = (LinkedList<_CookOrder>) o;
//                                StartApplication.cookConroller.updateOrders();
//                                playSound("cookMusic.wav");
//                            } catch (IOException | ClassNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    Platform.runLater(LazyJavaFXAlert::connectionError);
//                }
//            });
//            tr.start();
//        } else {
//            LazyJavaFXAlert.alert("Предупреждение", "Это не ваш раздел но вы имеете доступ к нему",
//                    "Вам не будут приходить новые уведомления, Вы видите только текущую загруженость данного раздела.", Alert.AlertType.INFORMATION);
//        }
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
