/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.maneger;

import br.edu.ifpb.bdnc.db4o.crud.dao.DAO;
import br.edu.ifpb.bdnc.db4o.crud.dao.service.PessoaService;
import br.edu.ifpb.bdnc.db4o.crud.entity.Pessoa;
import br.edu.ifpb.bdnc.db4o.crud.exception.EntidadeExistenteException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
@Named
@Scope("session")
public class PessoaEditar {

    @Inject
    private PessoaService service;
    private Pessoa pessoa;

    public PessoaEditar() {
        this.pessoa = new Pessoa();
    }

    public String editarPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return "edit.xhtml?faces-redirect=true";
    }

    public String salvarPessoa() {
        service.editar(pessoa);
        try {
            logout();
            return "index.xhtml?faces-redirect=true";
        } catch (EntidadeExistenteException e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entidade existente", "Entidade existente");
            facesContext.addMessage("pessoa", message);
        }
        return null;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpSession session = request.getSession(false);
        session.invalidate();

    }

}
