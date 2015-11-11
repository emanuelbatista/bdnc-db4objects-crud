/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.dao;

import br.edu.ifpb.bdnc.db4o.crud.entity.Pessoa;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 * @param <T>
 */
@Repository
public class Dao<T> implements DAO<T> {

    private final String FILE_DB = "/home/emanuel/NetBeansProjects/bdnc-db4o-example/src/main/resources/db4o";

    public Dao() {
    }

    @Override
    public void save(T t) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        try {
            db.store(t);
        } finally {
            db.close();
        }

    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        List<T> list = null;
        try {
            ObjectSet<T> set = db.query(clazz);
            list = set.stream().collect(Collectors.toList());
        } finally {
            db.close();
        }
        return list;
    }

    @Override
    public List<T> findParam(Class<T> clazz, Map<String, Object> param) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        try {
            Query query = db.query();
            Constraint constraint = query.constrain(clazz);
            if (param != null) {
                for (Map.Entry<String, Object> entrySet : param.entrySet()) {
                    constraint = query.descend(entrySet.getKey()).constrain(entrySet.getValue()).and(constraint);
                }
                return (List<T>) query.execute().stream().collect(Collectors.toList());
            }
        } finally {
            db.close();
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void delete(T t) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        try {
            ObjectSet<T> objectSet = db.get(t);
            T t1 = objectSet.next();
            db.delete(t1);
        } catch (Db4oIOException | DatabaseClosedException | DatabaseReadOnlyException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }

    @Override
    public void editar(T antiga, T nova) {

        ObjectContainer db =Db4o.openFile(FILE_DB);
        try {
           ObjectSet<T> set=db.get(antiga);
           T aux=set.next();
           db.delete(aux);
           db.store(nova);
           
        } finally {
            db.close();
        }
    }

}
