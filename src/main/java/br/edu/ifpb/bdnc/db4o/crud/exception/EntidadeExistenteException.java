/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.exception;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
public class EntidadeExistenteException extends RuntimeException{

    public EntidadeExistenteException(String message) {
        super(message);
    }

    public EntidadeExistenteException() {
        super();
    }
    
}
