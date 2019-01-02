package com.common;

import java.lang.reflect.Field;
import java.util.List;

public class TreeNode {

    private Field field;
    private Object value;
    private boolean decisionAttr;
    private String attrName;
    private List<TreeNode> treeNodeList;

    public TreeNode(){}

    public TreeNode(String attrName, List<TreeNode> treeNodeList) {
        this.attrName = attrName;
        this.treeNodeList = treeNodeList;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isDecisionAttr() {
        return decisionAttr;
    }

    public void setDecisionAttr(boolean decisionAttr) {
        this.decisionAttr = decisionAttr;
    }

    public void setTreeNodeList(List<TreeNode> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }

    public void print(int level) {
        for (int i=0; i<level;++i) {
            System.out.print("-");
        }
        System.out.println(this.attrName);
        ++level;
        if (null != this.getTreeNodeList()) {
            for (TreeNode node : this.getTreeNodeList()) {
                node.print(level);
            }
        }
    }
}
