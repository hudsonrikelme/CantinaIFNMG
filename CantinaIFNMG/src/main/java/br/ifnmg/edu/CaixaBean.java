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

        Cliente c1 = new Cliente();
        c1.setAuxilio(true);
        c1.setCpf("111.222.333-44");
        c1.setNome("Lucas");
        c1.setSaldo(BigDecimal.valueOf(50.00));
        c1.setTipoCliente(Cliente.TipoCliente.CLIENTE);
        Credencial cre = new Credencial();
        cre.setEmail("Lucas@gmail.com");
        cre.setSenha("123456");
        c1.setCredencial(cre);
        clienteService.salvar(c1);

        Cliente c2 = new Cliente();
        c2.setAuxilio(true);
        c2.setCpf("555.666.777-88");
        c2.setNome("Pedro");
        c2.setSaldo(BigDecimal.valueOf(50.00));
        c2.setTipoCliente(Cliente.TipoCliente.CLIENTE);
        Credencial cre1 = new Credencial();
        cre1.setEmail("Lucas@gmail.com");
        cre1.setSenha("123456");
        c2.setCredencial(cre);
        clienteService.salvar(c2);

        produtoService.salvar(p1);
        produtoService.salvar(p2);
        produtoService.salvar(p3);

        produtos = produtoService.localizarTodos();
        clientes = clienteService.localizarTodosComCompras();
        System.out.println(clientes);

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
