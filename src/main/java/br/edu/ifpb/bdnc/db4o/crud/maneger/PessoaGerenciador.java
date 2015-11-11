/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.maneger;

import br.edu.ifpb.bdnc.db4o.crud.dao.service.PessoaService;
import br.edu.ifpb.bdnc.db4o.crud.entity.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
@Controller("pessoaGerenciador")
@Scope("session")
public class PessoaGerenciador implements Serializable {

    @Inject
    private PessoaService service;
    private Pessoa pessoa;

    public PessoaGerenciador() {
        this.pessoa = new Pessoa();
    }

    public void removerPessoa(Pessoa pessoa) {
        service.remover(pessoa.getId());
    }

    public List<Pessoa> listarPessoas() {
        return service.listarTodos();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void addPessoa(Pessoa pessoa) {
        service.salvar(pessoa);
        logout();
    }
    

    public void logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpSession session = request.getSession(false);
        session.invalidate();

    }

}
