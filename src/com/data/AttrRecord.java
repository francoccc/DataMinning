package com.data;

/**
 * 需要统计的属性记录
 *
 * @author Franco
 */
public class AttrRecord extends BaseRecord {

    private Age age;
    private Income income;
    private Credit credit;
    private boolean student;

    public AttrRecord(boolean decision, Age age, Income income, Credit credit, boolean student) {
        super(decision);
        this.income = income;
        this.credit = credit;
        this.student = student;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }
}
