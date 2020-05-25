package com.zhuyida.tank.strategy;

import com.zhuyida.tank.Player;
import com.zhuyida.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    public void fire(Player p);
}
