package com.zhuyida.tank.chainofresponsibility;

import com.zhuyida.tank.AbstractGameObject;
import com.zhuyida.tank.Bullet;
import com.zhuyida.tank.Tank;
import com.zhuyida.tank.Wall;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 != go2 && go1 instanceof Tank && go2 instanceof Tank) {

//            System.out.println(go1);
//            System.out.println(go2);

            Tank t1 = (Tank)go1;
            Tank t2 = (Tank)go2;
            if(t1.isLive() && t2.isLive()) {
                if(t1.getRect().intersects(t2.getRect())) {
                    t1.back();
                    t2.back();
                }
            }
        }
        return true;
    }
}
