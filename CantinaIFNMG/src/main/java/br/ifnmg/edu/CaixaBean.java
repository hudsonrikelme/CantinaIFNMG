/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Lucas
 */
@Named(value = "caixaBean")
@SessionScoped
public class CaixaBean implements Serializable {

    @Inject
    ProdutoServiceLocal produtoService;

    private List<Produto> produtosSelecionados;
    private Produto produto;
    private List<Produto> produtos;

    public CaixaBean() {
        produtosSelecionados = new ArrayList<>();
        produto = new Produto();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    public List<Produto> getProdutosSelecionados() {
        return produtosSelecionados;
    }

    public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
        this.produtosSelecionados = produtosSelecionados;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    //</editor-fold>

    public String adicionarProduto() {
        produtosSelecionados.add(produto);
        produto = null;
        
        return "caixa?faces-redirect=true";

    }

    @PostConstruct
    public void cargaProdutos() {
        Produto p1 = new Produto();
        p1.setDescricao("Coxinha grande");
        p1.setNome("Coxinha");
        p1.setPreco(BigDecimal.valueOf(3.5));

        Produto p2 = new Produto();
        p2.setDescricao("Refri lata");
        p2.setNome("Refri lata");
        p2.setPreco(BigDecimal.valueOf(3.5));

        Produto p3 = new Produto();
        p3.setDescricao("Suco grande");
        p3.setNome("Suco");
        p3.setPreco(BigDecimal.valueOf(3.5));

        produtoService.salvar(p1);
        produtoService.salvar(p2);
        produtoService.salvar(p3);
        produtos = produtoService.localizarTodos();
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
//    private void reset()
//    {
//        produto = new Produto();
//    }
}
