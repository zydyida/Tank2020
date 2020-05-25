package com.zhuyida.tank.chainofresponsibility;

import com.zhuyida.tank.AbstractGameObject;

public interface Collider {
    //return true:chain go on, return false:chain break;
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}
