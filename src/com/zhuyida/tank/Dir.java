package com.zhuyida.tank;

import java.util.Random;

public enum Dir {
    //public static final int L;编译器指定
    L, U, R, D;

    private static Random r = new Random();

    public static Dir randomDir() {
        return values()[r.nextInt(values().length)];
    }
}

//int dir = 1, 2, 3, 4  dir = 5