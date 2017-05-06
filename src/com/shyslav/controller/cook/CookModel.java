package com.shyslav.controller.cook;

import com.happycake.sitemodels.Order;
import com.happycake.sitemodels.OrderList;
import com.shyslav.controller.ServerClient;
import com.shyslav.controller.alert.LazyJavaFXAlert;
import com.shyslav.defaults.HappyCakeRequest;
import com.shyslav.defaults.HappyCakeResponse;
import com.shyslav.start.StartApplication;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 25.05.2016.
 */
public class CookModel {
    private static final Logger log = Logger.getLogger(CookModel.class.getName());
    public static boolean done = false;
    private final ServerClient client;
    private final ArrayList<Order> queue;
    private final CookController cookController;

    public CookModel(CookController cookController) {
        this.client = StartApplication.userEntity.getUserBean().getClientActions().getClient();
        this.queue = new ArrayList<>();
        this.cookController = cookController;
        newDishThread();
    }

    private void newDishThread() {
        HappyCakeResponse happyCakeResponse = StartApplication.userEntity.getUserBean().getClientActions().selectOrderForCook();
        if (happyCakeResponse.isSuccess()) {
            OrderList orderList = happyCakeResponse.getObject(OrderList.class);
            synchronized (queue) {
                queue.addAll(orderList);
            }
        } else {
            log.trace("Unable to get orders for cook");
            System.exit(0);
        }

        if (StartApplication.userEntity.getEmp().getPositionID() == 3) {
            Thread thread = new Thread(() -> {
                while (!done) {
                    HappyCakeResponse read = client.read();
                    if (read.isSuccess()) {
                        OrderList orderList = read.getObject(Order.class);
                        if (orderList != null) {
                            synchronized (queue) {
                                queue.clear();
                                queue.addAll(orderList);
                                cookController.updateOrders();
                            }
                        }
                        playSound();
                    }
                }
            });
            thread.setName("Cook thread");
            thread.start();
        } else {
            LazyJavaFXAlert.alert("Предупреждение", "Это не ваш раздел но вы имеете доступ к нему",
                    "Вам не будут приходить новые уведомления, Вы видите только текущую загруженость данного раздела.", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Play music
     */
    public void playSound() {
        Thread music = new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        CookController.class.getResourceAsStream("cookMusic.wav"));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                log.error("Unable to play sound " + e.getMessage(), e);
            }
        });
        music.start();
    }

    /**
     * Get orders queue
     *
     * @return queue of orders
     */
    synchronized public ArrayList<Order> getQueue() {
        return queue;
    }

    /**
     * Close order by index
     *
     * @param index index of element in arraylist
     */
    public void closeOrder(int index) {
        synchronized (queue) {
            Order order = queue.get(index);
            order.setComplite(true);
            queue.remove(index);
            HappyCakeRequest request = new HappyCakeRequest("addOrder", order);
            client.write(request);
        }
    }
}
