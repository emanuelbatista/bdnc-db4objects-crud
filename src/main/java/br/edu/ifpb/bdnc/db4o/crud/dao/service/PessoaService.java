/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.dao.service;

import br.edu.ifpb.bdnc.db4o.crud.dao.DAO;
import br.edu.ifpb.bdnc.db4o.crud.dao.PessoaDAO;
import br.edu.ifpb.bdnc.db4o.crud.entity.Pessoa;
import br.edu.ifpb.bdnc.db4o.crud.exception.EntidadeExistenteException;
import com.db4o.ObjectSet;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
@Service
public class PessoaService implements Serializable {

    @Inject
    private DAO<Pessoa> repositorio;

    public void salvar(Pessoa pessoa) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", pessoa.getId());
        List<Pessoa> pessoas=repositorio.findParam(Pessoa.class, param);
        if(pessoas.isEmpty()){
            repositorio.save(pessoa);
        }else{
            throw new EntidadeExistenteException();
        }
    }
    
    public void editar(Pessoa pessoa){
        Map<String, Object> param = new HashMap<>();
        param.put("id", pessoa.getId());
        Pessoa pessoa1=repositorio.findParam(Pessoa.class, param).get(0);
        repositorio.editar(pessoa1, pessoa);
    }

    public void remover(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        Pessoa pessoas = repositorio.findParam(Pessoa.class, param).get(0);
        if (pessoas != null) {
            repositorio.delete(pessoas);
        } else {
            throw new EntidadeExistenteException();
        }
    }

    public List<Pessoa> listarTodos() {
        return repositorio.findAll(Pessoa.class);
    }

}
