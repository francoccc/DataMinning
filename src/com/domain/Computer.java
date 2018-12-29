package com.domain;

/**
 * Computer POJO
 *
 * @author Franco
 */
public class Computer extends AbstractDomain {

    private int id;
    private int age;
    private int income;
    private boolean student;
    private int credit;
    private boolean buys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isBuys() {
        return buys;
    }

    public void setBuys(boolean buys) {
        this.buys = buys;
    }

    @Override
    public Object clone() {
        Computer obj = new Computer();
        obj.id = this.id;
        obj.age = this.age;
        obj.income = this.income;
        obj.student = this.student;
        obj.credit = this.credit;
        obj.buys = this.buys;
        return obj;
    }
}
