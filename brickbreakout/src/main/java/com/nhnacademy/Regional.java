package com.nhnacademy;

import java.awt.Rectangle;

public interface Regional {
        public int getX();
        public int getY();
        public Rectangle getRegion();
        public boolean isCollision(Regional other);
    }
