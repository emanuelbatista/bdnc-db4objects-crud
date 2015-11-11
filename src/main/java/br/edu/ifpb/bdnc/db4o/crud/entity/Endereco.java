/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.db4o.crud.entity;

import java.io.Serializable;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
public class Endereco implements Serializable{
    
    private String rua;
    private String bairro;
    private Integer num;

    public Endereco(String rua, String bairro, Integer num) {
        this.rua = rua;
        this.bairro = bairro;
        this.num = num;
    }

    public Endereco() {
    }
    
    

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    
    
    
}
