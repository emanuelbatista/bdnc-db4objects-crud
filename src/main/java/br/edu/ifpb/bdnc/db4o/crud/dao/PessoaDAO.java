/*
 * Pessoao change this license header, choose License Headers in Project Properties.
 * Pessoao change this template file, choose Pessoaools | Pessoaemplates
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
@Component
public class PessoaDAO {
     private final String FILE_DB = "/home/emanuel/NetBeansProjects/bdnc-db4o-example/src/main/resources/db4o";

    public PessoaDAO() {
    }

    public void save(Pessoa pessoa) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        try {
            db.store(pessoa);
        } finally {
            db.close();
        }

    }

    public List<Pessoa> findAll(Class<Pessoa> clazz) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        List<Pessoa> list = null;
        try {
            ObjectSet<Pessoa> set = db.query(clazz);
            list = set.stream().collect(Collectors.toList());
        } finally {
            db.close();
        }
        return list;
    }

    public List<Pessoa> findParam(Class<Pessoa> clazz, Map<String, Object> param) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        try {
            Query query = db.query();
            Constraint constraint = query.constrain(clazz);
            if (param != null) {
                for (Map.Entry<String, Object> entrySet : param.entrySet()) {
                    constraint = query.descend(entrySet.getKey()).constrain(entrySet.getValue()).and(constraint);
                }
                return (List<Pessoa>) query.execute().stream().collect(Collectors.toList());
            }
        } finally {
            db.close();
        }
        return Collections.EMPTY_LIST;
    }

    public void delete(Pessoa pessoa) {
        ObjectContainer db = Db4o.openFile(FILE_DB);
        try {
            ObjectSet<Pessoa> t2=db.get(pessoa);
            Pessoa pessoa1=t2.next();
            db.delete(pessoa1);
        }catch( Db4oIOException | DatabaseClosedException | DatabaseReadOnlyException ex){
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }
}
