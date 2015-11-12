/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.config;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
public class Db4oFactory {

    private static final String FILE_DB = "/home/emanuel/NetBeansProjects/bdnc-db4o-example/src/main/resources/db4o.db";

    public static ObjectContainer newInstance() {
           Configuration configuration=Db4o.newConfiguration();
           configuration.encrypt(true);
           configuration.unicode(true);
           configuration.password("123");
           return Db4o.openFile(configuration, FILE_DB);
           
    }
}
