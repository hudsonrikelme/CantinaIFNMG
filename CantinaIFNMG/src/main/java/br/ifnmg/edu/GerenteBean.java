/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Named(value = "gerenteBean")
@SessionScoped
public class GerenteBean implements Serializable {

    @Inject
    ClienteServiceLocal clienteService;
    
    @Inject
    CompraServiceLocal compraService;

    private Cliente c;
    private Credencial cred;
    private List<Cliente.TipoCliente> tiposCliente;
    private Cliente.TipoCliente tipoClienteSelecionado;
    private List<Cliente> clientes;
    private List<Compra> compras;

    /**
     * Creates a new instance of GerenteBean
     */
    public GerenteBean() {
        c = new Cliente();
        tiposCliente = Arrays.asList(Cliente.TipoCliente.values());
        cred = new Credencial();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = recuperarTodosClientes();
        }
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public Credencial getCred() {
        return cred;
    }

    public void setCred(Credencial cred) {
        this.cred = cred;
    }

    public List<Cliente.TipoCliente> getTiposCliente() {
        return tiposCliente;
    }

    public void setTiposCliente(List<Cliente.TipoCliente> tiposCliente) {
        this.tiposCliente = tiposCliente;
    }

    public Cliente.TipoCliente getTipoClienteSelecionado() {
        return tipoClienteSelecionado;
    }

    public void setTipoClienteSelecionado(Cliente.TipoCliente tipoClienteSelecionado) {
        this.tipoClienteSelecionado = tipoClienteSelecionado;
    }
//</editor-fold>

    @PostConstruct
    public void CargaDados()
    {
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
        
        Produto p1 = new Produto();
        p1.setDescricao("Coxinha grande");
        p1.setNome("Coxinha");
        p1.setPreco(BigDecimal.valueOf(3.5));
        
        Produto p2 = new Produto();
        p2.setDescricao("Refrigerante lata 350 ml");
        p2.setNome("Refrigerante lata");
        p2.setPreco(BigDecimal.valueOf(3.0));
        
        Produto p3 = new Produto();
        p3.setDescricao("Bolo no pote 300 gramas");
        p3.setNome("Bolo no pote");
        p3.setPreco(BigDecimal.valueOf(8.0));
        
        
        Compra compra = new Compra();
        compra.setCliente(c1);
        compra.setDia(LocalDate.now());
        compra.setPago(true);
        compra.adicionarProduto(p1);
        compra.adicionarProduto(p2);
        compra.adicionarProduto(p3);
        compra.setTipoPagamento(Compra.TipoPagamento.DINHEIRO);
        compraService.salvar(compra);
    }
    
    public String carregarCompras(Cliente c)
    {
        compras = compraService.localizarCompraPorCliente(c);
        
        return "comprasCliente";
    }
    
    public String produtos(Compra c)
    {
        String produtos = new String();
        for (Produto produto : c.getProdutos()) {
            produtos += produto.getNome();
            produtos += ", ";
        }
        return produtos;
    }
    
    public String salvarCliente() {
        c.setCredencial(cred);
        c.setTipoCliente(tipoClienteSelecionado);
        clienteService.salvar(c);
        reset();
        recarregarClientes();

        return "editarAluno?faces-redirect=true";
    }

    public List<Cliente> recuperarTodosClientes() {
        return clienteService.localizarTodos();
    }

    public Cliente carregarCliente(Long id) {
        if (c != null) {
            Cliente cliente = clienteService.localizarPorId(id);
            c = cliente;
            cred = cliente.getCredencial();
            tipoClienteSelecionado = c.getTipoCliente();
            return c;
        } else {
            return null;
        }
    }

    private void reset() {
        c = new Cliente();
        cred = new Credencial();
    }

    private void recarregarClientes() {
        clientes = recuperarTodosClientes();
    }

}
