package com.zhuyida.tank;

import com.zhuyida.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable {
    private Player myTank;

    List<AbstractGameObject> objects;

    ColliderChain chain = new ColliderChain();

    public GameModel() {
        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(100, 100, Dir.R, Group.GOOD);

        objects = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        for (int i = 0; i < tankCount; i++) {
            this.add(new Tank(100 + 80 * i, 200, Dir.D, Group.BAD));
        }

//        this.add(new Wall(300, 200, 400, 50));
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

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
            AbstractGameObject go1 = objects.get(i);
            if (!objects.get(i).isLive()) {
                objects.remove(i);
                break;
            }
        }

        for (int i=0; i<objects.size(); i++) {
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

    public Player getMyTank() {
        return myTank;
    }
}
