/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Lucas Freitas &lt;lpf1 at ifnmg.edu.br&gt;
 */
@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    @Inject
    ProdutoServiceLocal produtoService;

    private Produto p;
    private List<Produto> produtos;

    public ProdutoBean() {
        p = new Produto();
    }

    public String salvar() {
        produtoService.salvar(p);
        reset();
        
        return "cadastroProdutos?faces-redirect=true";
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    private void reset() {
        p = new Produto();
    }
}
