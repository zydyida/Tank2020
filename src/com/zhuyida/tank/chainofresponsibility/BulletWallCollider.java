package com.zhuyida.tank.chainofresponsibility;

import com.zhuyida.tank.AbstractGameObject;
import com.zhuyida.tank.Bullet;
import com.zhuyida.tank.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Wall) {

//            System.out.println(go1);
//            System.out.println(go2);

            Bullet b = (Bullet)go1;
            Wall w = (Wall)go2;
            if(b.isLive()) {
                if(b.getRect().intersects(w.getRect())) {
                    b.die();
                    return false;
                }
            }
        } else if (go1 instanceof Wall && go2 instanceof Bullet) {
            return collide(go2, go1);
        }

        return true;
    }
}
