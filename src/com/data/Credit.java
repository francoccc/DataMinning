package com.data;

/**
 *
 * @author Franco
 */
public enum Credit {

    /** 优秀 */
    EXCELLENT(1),
    /** 一般 */
    FAIR(2);

    private final int level;

    Credit(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
