package com;

import com.algorithm.DescisionTree;
import com.algorithm.ID3Selector;
import com.common.TreeNode;
import com.dao.ComputerDao;
import com.data.AttrRecord;
import com.data.BaseRecord;
import com.domain.Computer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
           records.add(record);
       }

       Field[] fields = AttrRecord.class.getDeclaredFields();
       Set<Field> attrSet = new HashSet<>();
       for(Field field : fields) {
           if(field.getName().equals("decisionAttr")) {
               continue;
           }
           attrSet.add(field);
       }

       DescisionTree tree = new DescisionTree(new ID3Selector());
       TreeNode root = tree.create(records, attrSet);
       AttrRecord test = new AttrRecord(false, 2, 3, 1, true);
       AttrRecord test2 = new AttrRecord(false, 3, 3, 1, false);
       System.out.println(tree.decide(root, test));
       System.out.println(tree.decide(root, test2));
    }
}
