/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.cardapio.CardapioAssistencia;
import br.ifnmg.edu.cardapio.CardapioAssistenciaServiceLocal;
import br.ifnmg.edu.cardapio.CardapioAutoServico;
import br.ifnmg.edu.cardapio.CardapioAutoServicoServiceLocal;
import br.ifnmg.edu.cardapio.Componente;
import br.ifnmg.edu.cardapio.ComponenteServiceLocal;
import br.ifnmg.edu.cardapio.Prato;
import br.ifnmg.edu.cardapio.PratoServiceLocal;
import br.ifnmg.edu.compra.Compra;
import br.ifnmg.edu.compra.CompraServiceLocal;
import br.ifnmg.edu.produto.Produto;
import br.ifnmg.edu.produto.ProdutoServiceLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Lucas
 */
@Named(value = "relatorioBean")
@RequestScoped
public class RelatorioBean implements Serializable {

    List<Cliente> clientes;
    List<Compra> compras;
    List<Produto> produtos;
    List<CardapioAssistencia> cardapiosAssistencia;
    List<CardapioAutoServico> cardapiosAutoServico;
    List<Prato> pratos;
    List<Componente> componentes;
    List<Comentario> comentarios;

    @Inject
    ClienteServiceLocal clienteService;

    @Inject
    CompraServiceLocal compraService;

    @Inject
    ProdutoServiceLocal produtoService;

    @Inject
    CardapioAssistenciaServiceLocal cardapioAssistenciaService;

    @Inject
    CardapioAutoServicoServiceLocal cardapioAutoServicoService;

    @Inject
    PratoServiceLocal pratoService;

    @Inject
    ComponenteServiceLocal componenteService;
    
    @Inject
    ComentarioServiceLocal comentarioService;

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
        cardapiosAssistencia = cardapioAssistenciaService.findAll();
        cardapiosAutoServico = cardapioAutoServicoService.findAll();
        pratos = pratoService.findAll();
        componentes = componenteService.findAll();
        comentarios = comentarioService.localizarTodos();
    }

    public String produtos(Compra c) {
        String produtosNomes = new String();
        for (Produto produto : c.getProdutos()) {
            produtosNomes += produto.getNome();
            produtosNomes += ", ";
        }
        return produtosNomes;
    }

    public List<CardapioAutoServico> getCardapiosAutoServico() {
        return cardapiosAutoServico;
    }

    public void setCardapiosAutoServico(List<CardapioAutoServico> cardapiosAutoServico) {
        this.cardapiosAutoServico = cardapiosAutoServico;
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

    public List<CardapioAssistencia> getCardapiosAssistencia() {
        return cardapiosAssistencia;
    }

    public void setCardapiosAssistencia(List<CardapioAssistencia> cardapiosAssistencia) {
        this.cardapiosAssistencia = cardapiosAssistencia;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

}
