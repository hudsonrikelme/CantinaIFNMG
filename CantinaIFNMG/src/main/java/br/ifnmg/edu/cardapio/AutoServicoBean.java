/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author gusta
 */
@Named(value = "autoServicoBean")
@RequestScoped
public class AutoServicoBean {

    @Inject
    private ComponenteServiceLocal componenteService;
    
    @Inject
    private CardapioAutoServicoServiceLocal autoServicoService;
    
    private CardapioAutoServico novoCardapio;
    private List<Componente> componentes;
    private LocalDate minDate;
    
    public AutoServicoBean() {
        componentes = new ArrayList<>();
        novoCardapio = new CardapioAutoServico();
        minDate = LocalDate.now();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getteres/Setteres">
    

    public CardapioAutoServico getNovoCardapio() {
        return novoCardapio;
    }

    public void setNovoCardapio(CardapioAutoServico novoCardapio) {
        this.novoCardapio = novoCardapio;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }
    
    //</editor-fold>
    
    @PostConstruct
    public void carregarComponentes(){
        System.out.println(componenteService.findAll());
        this.componentes = componenteService.findAll();
    }
    
    public String salvarCardapio(){
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
        System.out.println(novoCardapio.toString());
        autoServicoService.save(novoCardapio);
        reset();
        return "cadastroCardapioSelfService?faces-redirect=true";
    }
    
    public void reset() {
        componentes = new ArrayList<>();
        novoCardapio = new CardapioAutoServico();
        minDate = LocalDate.now();
    }
    
}
