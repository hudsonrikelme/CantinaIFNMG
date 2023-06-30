/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.produto.Produto;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Lucas
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    @Inject
    private ClienteServiceLocal clienteService;

    @Inject
    private CompraServiceLocal compraService;

    private Cliente cliente;
    private List<Compra> compras;
    private List<Compra.TipoPagamento> tiposPagamento;
    private Compra.TipoPagamento tipoPagamentoSelecionado;
    private BigDecimal valor;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }

    @PostConstruct
    public void recuperarCliente() {
        //Arrays.asList(Compra.TipoPagamento.values());
        tiposPagamento = new ArrayList<>(Arrays.asList(Compra.TipoPagamento.values()));
        tiposPagamento.remove(Compra.TipoPagamento.AUXILIO);
        tiposPagamento.remove(Compra.TipoPagamento.DINHEIRO);

        cliente = clienteService.localizarPorId(1L);
        compras = compraService.localizarCompraPorCliente(cliente);
    }

    public BigDecimal totalAPagar() {
        BigDecimal total = BigDecimal.ZERO;
        for (Compra compra : compras) {
            if (!compra.getPago()) {
                total = total.add(compra.getTotal());
            }
        }
        return total;
    }

    public String adicionarSaldo() {
        cliente.adicionarSaldo(valor);
        clienteService.salvar(cliente);
        valor = BigDecimal.ZERO;
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Saldo adicionado!"));
        return "cliente?faces-redirect=true";
    }

    public String pagar() {
        if (tipoPagamentoSelecionado == Compra.TipoPagamento.SALDO) {
            if (cliente.getSaldo().compareTo(totalAPagar()) == -1) {
                FacesContext
                        .getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "SALDO INSUFICIENTE"));
                return null;
            }
            cliente.cobrar(totalAPagar());
            clienteService.salvar(cliente);
        }

        for (Compra compra : compras) {
            compra.setPago(true);
            compraService.salvar(compra);
        }
        return "cliente?faces-redirect=true";
    }

    public Boolean ispago() {
        return !(totalAPagar().compareTo(BigDecimal.ZERO) == 0);
    }

    public Compra.TipoPagamento getTipoPagamentoSelecionado() {
        return tipoPagamentoSelecionado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public String produtos(Compra c) {
        String produtos = new String();
        for (Produto produto : c.getProdutos()) {
            produtos += produto.getNome();
            produtos += ", ";
        }
        return produtos;
    }
}
