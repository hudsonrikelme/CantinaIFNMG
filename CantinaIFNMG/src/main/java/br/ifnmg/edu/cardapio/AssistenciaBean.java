/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.io.Serializable;
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
@Named(value = "assistenciaBean")
@RequestScoped
public class AssistenciaBean implements Serializable{

    @Inject
    private PratoServiceLocal pratoService;

    @Inject
    private CardapioAssistenciaServiceLocal assistService;

    private CardapioAssistencia novoCardapio;
    private List<Prato> pratos;
    private LocalDate minDate;

    public AssistenciaBean() {
        novoCardapio = new CardapioAssistencia();
        pratos = new ArrayList<>();
        minDate = LocalDate.now();
        
    }

    //<editor-fold defaultstate="collapsed" desc="Gets/Sets">
    

    public CardapioAssistencia getNovoCardapio() {
        return novoCardapio;
    }

    public void setNovoCardapio(CardapioAssistencia novoCardapio) {
        this.novoCardapio = novoCardapio;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }
    //</editor-fold>
    
    @PostConstruct
    public void carregarPratos(){
        System.out.println(pratoService.findAll());
        this.pratos = pratoService.findAll();
    }
    
    public String salvar(){
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
        System.out.println(novoCardapio.toString());
        assistService.save(novoCardapio);
        reset();
        return "cadastroCardapioAssistencia?faces-redirect=true";
    }
    
    public void reset() {
        novoCardapio = new CardapioAssistencia();
        pratos = new ArrayList<>();
        minDate = LocalDate.now();
    }

}
