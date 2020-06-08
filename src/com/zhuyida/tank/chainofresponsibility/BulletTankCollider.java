package com.zhuyida.tank.chainofresponsibility;

import com.zhuyida.tank.AbstractGameObject;
import com.zhuyida.tank.Bullet;
import com.zhuyida.tank.ResourceMgr;
import com.zhuyida.tank.Tank;
import com.zhuyida.tank.net.Client;
import com.zhuyida.tank.net.TankDieMsg;

import java.awt.*;

public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Tank) {
            Bullet b = (Bullet)go1;
            Tank t = (Tank)go2;

            if (!b.isLive() || !t.isLive()) return false;
            if (b.getGroup() == t.getGroup()) return true;

            Rectangle rectTank = t.getRect();

            if (b.getRect().intersects(rectTank)) {
                b.die();
                t.die();

                Client.INSTANCE.send(new TankDieMsg(t.getId(), b.getId()));

                return false;
            }
        } else if (go1 instanceof Tank && go2 instanceof Bullet) {
            collide(go2, go1);
        }

        return true;
    }
}
