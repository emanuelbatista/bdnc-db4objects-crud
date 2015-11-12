/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.dao;

import br.edu.ifpb.bdnc.db4o.crud.config.Db4oFactory;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 * @param <T>
 */
@Repository
@Scope("singleton")
public class Dao<T> implements DAO<T> {



    public Dao() {
    }

    @Override
    public synchronized void save(T t) {
        ObjectContainer db = Db4oFactory.newInstance();
        try {
            db.store(t);
        } finally {
            db.close();
        }

    }

    @Override
    public synchronized List<T> findAll(Class<T> clazz) {
        ObjectContainer db = Db4oFactory.newInstance();
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
    public synchronized List<T> findParam(Class<T> clazz, Map<String, Object> param) {
        ObjectContainer db = Db4oFactory.newInstance();
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
    public synchronized void delete(T t) {
        ObjectContainer db = Db4oFactory.newInstance();
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
    public synchronized void editar(T antiga, T nova) {

        ObjectContainer db =Db4oFactory.newInstance();
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
