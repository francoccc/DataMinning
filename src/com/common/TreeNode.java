package com.common;

import java.util.List;

public class TreeNode {

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
