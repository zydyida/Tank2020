package com.zhuyida.tank.strategy;

import com.zhuyida.tank.*;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Player p) {
        int bX = p.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int bY = p.getY() + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;

        TankFrame.INSTANCE.add(new Bullet(bX, bY, p.getDir(), p.getGroup()));

    }
}
