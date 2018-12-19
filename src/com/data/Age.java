package com.data;

/**
 *
 * @author Franco
 */
public enum Age {

    /** 老年 */
    SENIOR(1),
    /** 中年 */
    MIDDLE_AGED(2),
    /** 青年 */
    YOUTH(3);

    private final int level;

    Age(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
