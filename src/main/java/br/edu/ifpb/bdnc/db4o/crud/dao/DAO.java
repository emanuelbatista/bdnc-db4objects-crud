/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 * @param <T>
 */
public interface DAO <T> extends Serializable{
    
    public void save(T t);
    public void editar(T antiga,T nova);
    public void delete(T t);
    public List<T> findAll(Class<T> clazz);
    public List<T> findParam(Class<T> clazz, Map<String,Object> param);
    
}
