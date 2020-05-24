package com.zhuyida.tank;

import java.awt.*;

public class Bullet extends AbstractGameObject {
    public static final int SPEED = 6;
    private int x, y;
    private Dir dir;
    private Group group;
    private boolean live = true;


    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isLive() {
        return live;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        
        move();
    }

    private void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }

        boundsCheck();
    }

    public void collidesWithTank(Tank tank) {
        if (!tank.isLive() || !this.isLive()) return;
        if (this.group == tank.getGroup()) return;

        Rectangle rect = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(),
                ResourceMgr.goodTankU.getWidth(), ResourceMgr.goodTankU.getHeight());

        if (rect.intersects(rectTank)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.setLive(false);
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.FRAME_WIDTH || y > TankFrame.FRAME_HEIGHT) {
            live = false;
        }
    }

}
