package com.data;

/**
 * 需要统计的属性记录
 *
 * @author Franco
 */
public class AttrRecord extends BaseRecord {

    private int age;
    private int income;
    private int credit;
    private boolean student;

    public AttrRecord(boolean decision, int age, int income, int credit, boolean student) {
        super(decision);
        this.age = age;
        this.income = income;
        this.credit = credit;
        this.student = student;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }
}
