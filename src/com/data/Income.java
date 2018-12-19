package com.data;

/**
 *
 * @author Franco
 */
public enum Income {

    /** 高收入 */
    HIGH(3),
    /** 中收入 */
    MIDDLE(2),
    /** 低收入 */
    LOW(1);

    private final int level;

    Income(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
