package com;

import com.dao.ComputerDao;
import com.data.Age;
import com.data.AttrRecord;
import com.data.BaseRecord;
import com.domain.Computer;

import java.util.ArrayList;
import java.util.List;

/**
 * Test
 *
 * @author Franco
 */
public class Test {

    public static void main(String[] args) {
       Class<?> clazz = Computer.class;
       ComputerDao<Computer> dao = new ComputerDao(Computer.class);
       List<Computer> computers = dao.getAll();
       List<BaseRecord> records = new ArrayList<>();
       for(Computer computer : computers) {
           AttrRecord record = new AttrRecord(computer.isBuys(), computer.getAge(),
                   computer.getIncome(), computer.getCredit(), computer.isStudent());
       }
    }
}
