package com.ajax.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author majiasheng
 */
public class Manager {

    List<Employee> subordinates;

    public Manager() {
        subordinates = new ArrayList<Employee>();
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void addSubordinates(Employee subordinate) {
        subordinates.add(subordinate);
    }

}
