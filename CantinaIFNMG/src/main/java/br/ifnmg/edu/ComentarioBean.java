/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.br.login.ControladorCredencial;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Index;

/**
 *
 * @author Lucas
 */
@Named(value = "comentarioBean")
@RequestScoped
public class ComentarioBean {

    @Inject
    ControladorCredencial controlador;
    
    @Inject
    ComentarioServiceLocal comentarioService;
    
    @Inject
    ClienteServiceLocal clienteService;
    
    
    private String texto;
    private Cliente cliente;
    private Integer avaliacao;

    /**
     * Creates a new instance of ComentarioBean
     */
    public ComentarioBean() {
    }
    
    @PostConstruct
    public void carregarInformacoes()
    {
        cliente = controlador.getCurrentCliente();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    public String salvar(){
        Comentario c = new Comentario();
        Cliente cl = clienteService.localizarPorId(cliente.getId());
//        clienteService.salvar(cl);
        c.setAvaliacao(avaliacao);
        c.setCliente(cl);
        c.setTexto(texto);
        comentarioService.salvar(c);
        return "comentarios?faces-redirect=true";
    }

}
