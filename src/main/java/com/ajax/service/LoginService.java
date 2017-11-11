/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.service;

import com.ajax.model.Person;
import com.ajax.persistence.PersonEntitiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class LoginService {
    @Autowired
    PersonEntitiesManager personEntitiesManager;
    
    public Person login(String username, String password) {
        
        Person person = personEntitiesManager.login(username, password);
        
        return person;
    }
}
