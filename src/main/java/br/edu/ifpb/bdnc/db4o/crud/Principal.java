/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud;

import br.edu.ifpb.bdnc.db4o.crud.entity.Endereco;
import br.edu.ifpb.bdnc.db4o.crud.entity.Pessoa;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
public class Principal {

    public static void cmain(String[] args) {
        ObjectContainer objectContainer = Db4o.openFile("/home/emanuel/NetBeansProjects/bdnc-db4o-example/src/main/resources/db4o");
        Pessoa p=new Pessoa("kiko", 100, new Endereco());
        objectContainer.store(p);
        
        ObjectSet o=objectContainer.query(Pessoa.class);
        Pessoa pe=(Pessoa)o.next();
        objectContainer.delete(pe);
        
        objectContainer.close();
              
    }

}
