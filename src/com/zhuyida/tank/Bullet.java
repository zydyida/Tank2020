package com.zhuyida.tank;

import java.awt.*;
import java.awt.Rectangle;

public class Bullet extends AbstractGameObject {
    public static final int SPEED = 6;
    private int x, y;
    private Dir dir;
    private Group group;
    private boolean live = true;
    private int w = ResourceMgr.bulletU.getWidth();
    private int h = ResourceMgr.bulletU.getHeight();

    private Rectangle rect;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect = new Rectangle(x, y, w, h);
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
        //update the rect
        rect.x = x;
        rect.y = y;

        /*Color old = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.setColor(old);*/
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


    public Rectangle getRect() {
        return rect;
    }

    public void die() {
        this.setLive(false);
    }

    public void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.FRAME_WIDTH || y > TankFrame.FRAME_HEIGHT) {
            live = false;
        }
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", live=" + live +
                ", w=" + w +
                ", h=" + h +
                ", rect=" + rect +
                '}';
    }

    public Group getGroup() {
        return group;
    }
}
