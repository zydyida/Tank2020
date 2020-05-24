package com.zhuyida.tank;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame(PropertyMgr.get("Title"));

    private Player myTank;

    List<AbstractGameObject> objects;

    public static final int FRAME_LOCATION_X = 0; //Integer.parseInt(PropertyMgr.get("FrameLocationX"));
    public static final int FRAME_LOCATION_Y = 0; //Integer.parseInt(PropertyMgr.get("FrameLocationY"));
    public static final int FRAME_WIDTH = 800; //Integer.parseInt(PropertyMgr.get("FrameWidth"));
    public static final int FRAME_HEIGHT = 600; //Integer.parseInt(PropertyMgr.get("FrameHeight"));

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
        /*g.drawString("bullets:" + bullets.size(), 10, 50);
        g.drawString("enemies:" + tanks.size(), 10, 70);
        g.drawString("explodes:" + explodes.size(), 10, 90);*/
        g.setColor(c);

        myTank.paint(g);
        for (int i=0; i<objects.size(); i++) {
            objects.get(i).paint(g);
        }
        /*for (int i = 0; i < tanks.size(); i++) {
            if (!tanks.get(i).isLive()) {
                tanks.remove(i);
            } else {
                tanks.get(i).paint(g);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collidesWithTank(tanks.get(j));
            }

            if (!bullets.get(i).isLive()) {
                bullets.remove(i);
            } else {
                bullets.get(i).paint(g);
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isLive()) {
                explodes.remove(i);
            } else {
                explodes.get(i).paint(g);
            }
        }*/
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
