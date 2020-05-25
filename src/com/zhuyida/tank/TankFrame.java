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

    public static final int FRAME_LOCATION_X = 0;
    public static final int FRAME_LOCATION_Y = 0;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    private GameModel gm = new GameModel();

    private TankFrame(String Title) {
        this.setTitle("tank war");
        this.setLocation(FRAME_LOCATION_X, FRAME_LOCATION_Y);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.addKeyListener(new TankKeyListener());

    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
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
            gm.getMyTank().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gm.getMyTank().keyReleased(e);
        }
    }

    public GameModel getGm() {
        return this.gm;
    }
}
