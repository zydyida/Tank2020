package com.zhuyida.tank;

import com.zhuyida.tank.chainofresponsibility.BulletTankCollider;
import com.zhuyida.tank.chainofresponsibility.BulletWallCollider;
import com.zhuyida.tank.chainofresponsibility.Collider;
import com.zhuyida.tank.chainofresponsibility.ColliderChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame(PropertyMgr.get("Title"));

    private Player myTank;

    List<AbstractGameObject> objects;

    ColliderChain chain = new ColliderChain();

    public static final int FRAME_LOCATION_X = 0;
    public static final int FRAME_LOCATION_Y = 0;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    private TankFrame(String Title) {
        this.setTitle("tank war");
        this.setLocation(FRAME_LOCATION_X, FRAME_LOCATION_Y);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.addKeyListener(new TankKeyListener());

        initGameObjects();
    }



    private void initGameObjects() {
        myTank = new Player(100, 100, Dir.R, Group.GOOD);

        objects = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        for (int i = 0; i < tankCount; i++) {
            this.add(new Tank(100 + 50 * i, 200, Dir.D, Group.BAD));
        }

        this.add(new Wall(300, 200, 400, 50));
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("objects:" + objects.size(), 10, 50);
        /*g.drawString("bullets:" + bullets.size(), 10, 50);
        g.drawString("enemies:" + tanks.size(), 10, 70);
        g.drawString("explodes:" + explodes.size(), 10, 90);*/
        g.setColor(c);

        myTank.paint(g);
        for (int i=0; i<objects.size(); i++) {

            if (!objects.get(i).isLive()) {
                objects.remove(i);
                break;
            }

            AbstractGameObject go1 = objects.get(i);
            for (int j=0; j<objects.size(); j++) {
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1, go2);
            }

            if(objects.get(i).isLive()) {
                objects.get(i).paint(g);
            }
        }
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen); // paint the whole picture in memory
        g.drawImage(offScreenImage, 0, 0, null);
    }


    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}
