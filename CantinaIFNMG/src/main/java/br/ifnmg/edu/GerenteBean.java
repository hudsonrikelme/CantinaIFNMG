/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.compra.CompraServiceLocal;
import br.ifnmg.edu.compra.Compra;
import br.ifnmg.edu.produto.Produto;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;

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

    public String carregarCompras(Cliente c) {

        compras = compraService.localizarCompraPorCliente(c);

        if (compras.isEmpty()) {
            addMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Cliente sem compras");
            return null;
        }
        
        return "comprasCliente?faces-redirect=true";

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public String produtos(Compra c) {
        String produtos = new String();
        for (Produto produto : c.getProdutos()) {
            produtos += produto.getNome();
            produtos += ", ";
        }
        return produtos;
    }

    public String salvarCliente() {
//        c.setCredencial(cred);
//        c.setTipoCliente(tipoClienteSelecionado);
        clienteService.salvar(c);
        reset();
        recarregarClientes();

        return "editarCliente?faces-redirect=true";
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
