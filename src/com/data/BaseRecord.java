package com.data;

/**
 *
 * @author Franco
 */
public class BaseRecord {

    private boolean decisionAttr;

    public BaseRecord(boolean decisionAttr) {
        this.decisionAttr = decisionAttr;
    }

    public boolean isDecisionAttr() {
        return decisionAttr;
    }

    public void setDecisionAttr(boolean decisionAttr) {
        this.decisionAttr = decisionAttr;
    }
}
