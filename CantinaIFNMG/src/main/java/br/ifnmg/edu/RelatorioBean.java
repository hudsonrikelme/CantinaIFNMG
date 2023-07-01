/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.compra.Compra;
import br.ifnmg.edu.compra.CompraServiceLocal;
import br.ifnmg.edu.produto.Produto;
import br.ifnmg.edu.produto.ProdutoServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Lucas
 */
@Named(value = "relatorioBean")
@ViewScoped
public class RelatorioBean implements Serializable {

    List<Cliente> clientes;
    List<Compra> compras;
    List<Produto> produtos;

    @Inject
    ClienteServiceLocal clienteService;
    
    @Inject
    CompraServiceLocal compraService;
    
    @Inject
    ProdutoServiceLocal produtoService;

    /**
     * Creates a new instance of RelatorioBean
     */
    public RelatorioBean() {
    }

    @PostConstruct
    public void carregarDados() {
        clientes = clienteService.localizarTodos();
        compras = compraService.localizarTodosComProdutos();
        produtos = produtoService.localizarTodos();
    }

    public String produtos(Compra c) {
        String produtosNomes = new String();
        for (Produto produto : c.getProdutos()) {
            produtosNomes += produto.getNome();
            produtosNomes += ", ";
        }
        return produtosNomes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
