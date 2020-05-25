package com.zhuyida.tank.chainofresponsibility;

import com.zhuyida.tank.AbstractGameObject;
import com.zhuyida.tank.Bullet;
import com.zhuyida.tank.Tank;
import com.zhuyida.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Wall) {

//            System.out.println(go1);
//            System.out.println(go2);

            Tank t = (Tank) go1;
            Wall w = (Wall)go2;
            if(t.isLive()) {
                if(t.getRect().intersects(w.getRect())) {
                    t.back();
                }
            }
        } else if (go1 instanceof Wall && go2 instanceof Tank) {
            collide(go2, go1);
        }

        return true;
    }
}
