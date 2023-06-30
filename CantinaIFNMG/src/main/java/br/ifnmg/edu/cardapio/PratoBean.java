/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author gusta
 */
@Named(value = "pratoBean")
@RequestScoped
public class PratoBean {

    @Inject
    private PratoServiceLocal pratoService;
    
    @Inject
    private ComponenteServiceLocal componenteService;

    private List<Componente> componentes; 
    
    private Prato novoPrato;

    /**
     * Creates a new instance of PratoBean
     */
    public PratoBean() {
        componentes = new ArrayList<>();
        novoPrato = new Prato();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getteres/Setteres">
    

    public Prato getNovoPrato() {
        return novoPrato;
    }

    public void setNovoPrato(Prato novoPrato) {
        this.novoPrato = novoPrato;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }
    
    
    //</editor-fold>
    
    @PostConstruct
    public void carregarComponentes(){
        System.out.println(componenteService.findAll());
        this.componentes = componenteService.findAll();
    }
    
    public String salvarPrato(){
        System.out.println(novoPrato.toString());
        pratoService.save(novoPrato);
        reset();
        return "cadastroPrato?faces-redirect=true";
    }
    
    public void reset(){
        componentes = new ArrayList<>();
        novoPrato = new Prato();
    }

}
