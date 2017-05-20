package com.shyslav.controller.cook;

import com.happycake.sitemodels.HappyCakeNotifications;
import com.happycake.sitemodels.Order;
import com.happycake.sitemodels.OrderList;
import com.shyslav.controller.actions.ClientActions;
import com.shyslav.defaults.HappyCakeResponse;
import com.shyslav.start.StartDesktopApplication;
import org.apache.log4j.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 25.05.2016.
 */
public class CookActionHelper {
    private static final Logger log = Logger.getLogger(CookActionHelper.class.getName());
    private final ClientActions client;
    private final ArrayList<Order> queue;
    private final CookController cookController;

    public CookActionHelper(CookController cookController) {
        this.client = StartDesktopApplication.userEntity.getUserBean().getClientActions();
        this.queue = new ArrayList<>();
        this.cookController = cookController;
        registerOrderUpdatesNotification();
    }

    /**
     * Start dish thread
     */
    private void registerOrderUpdatesNotification() {
        reloadOrders();
        StartDesktopApplication.userEntity.registerPingerListener(HappyCakeNotifications.UPDATEORDERS, (event) -> reloadOrders());
    }

    /**
     * Reload orders
     */
    private void reloadOrders() {
        HappyCakeResponse happyCakeResponse = StartDesktopApplication.userEntity.getUserBean().getClientActions().selectOrderForCook();
        if (happyCakeResponse.isSuccess()) {
            OrderList orderList = happyCakeResponse.getObject(OrderList.class);
            synchronized (queue) {
                queue.clear();
                queue.addAll(orderList);
                cookController.updateOrders();
                playSound();
            }
        } else {
            log.trace("Unable to get orders for cook");
            System.exit(0);
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
                        CookController.class.getResourceAsStream("/sounds/cookMusic.wav"));
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
            client.addOrder(order);
        }
    }
}
