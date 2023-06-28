/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.produto.ProdutoServiceLocal;
import br.ifnmg.edu.produto.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Inject
    ClienteServiceLocal clienteService;

    @Inject
    CompraServiceLocal compraService;

    private List<Produto> produtosSelecionados;
    private Produto produto;
    private List<Produto> produtos;
    private BigDecimal total;
    private Cliente cliente;
    private List<Cliente> clientes;
    private Compra.TipoPagamento tipoPagamentoSelecionado;
    private List<Compra.TipoPagamento> tiposPagamento;
    private Boolean pago;

    public CaixaBean() {
        produtosSelecionados = new ArrayList<>();
        produto = new Produto();
        total = BigDecimal.ZERO;
        cliente = new Cliente();
        tiposPagamento = Arrays.asList(Compra.TipoPagamento.values());
    }

    //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {

        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Compra.TipoPagamento getTipoPagamentoSelecionado() {
        return tipoPagamentoSelecionado;
    }

    public void setTipoPagamentoSelecionado(Compra.TipoPagamento tipoPagamentoSelecionado) {
        this.tipoPagamentoSelecionado = tipoPagamentoSelecionado;
    }

    public List<Compra.TipoPagamento> getTiposPagamento() {
        return tiposPagamento;
    }

    public void setTiposPagamento(List<Compra.TipoPagamento> tiposPagamento) {
        this.tiposPagamento = tiposPagamento;
    }

    //</editor-fold>
    public String adicionarProduto() {
        produtosSelecionados.add(produto);
        total = total.add(produto.getPreco());
        return "caixa?faces-redirect=true";
    }

    public String removerProduto(Produto produto) {
        produtosSelecionados.remove(produto);
        total = total.subtract(produto.getPreco());

        return "caixa?faces-redirect=true";
    }

    public String salvar() {

        Compra c = new Compra();
        Cliente cliente1 = clienteService.localizarPorId(cliente.getId());

        c.setDia(LocalDate.now());
        c.setPago(pago);
        c.setProdutos(produtosSelecionados);
        c.setTipoPagamento(tipoPagamentoSelecionado);
        c.setTotal(total);
        c.setCliente(cliente1);
//        cliente1.getCompras().add(c);
//        clienteService.salvar(cliente1);

        if (c.getTipoPagamento() == Compra.TipoPagamento.SALDO) {
            c.getCliente().cobrar(total);
            clienteService.salvar(c.getCliente());
        }
        compraService.salvar(c);
        reset();

        return "caixa?faces-redirect=true";
    }

    @PostConstruct
    public void cargaProdutos() {
        produtos = produtoService.localizarTodos();
        clientes = clienteService.localizarTodosComCompras();
//        System.out.println(clientes);

    }


    private void reset() {
        produtosSelecionados.clear();
        pago = false;
        produto = new Produto();
        total = BigDecimal.ZERO;
        cliente = new Cliente();
        tipoPagamentoSelecionado = null;
    }

}
