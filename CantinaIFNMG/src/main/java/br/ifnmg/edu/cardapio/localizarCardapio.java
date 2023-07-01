/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author gusta
 */
@Named(value = "localizarCardapioBean")
@RequestScoped
public class localizarCardapio {


    @Inject
    private CardapioAutoServicoServiceLocal autoServicoService;
    
    @Inject
    private CardapioAssistenciaServiceLocal assistenciaService;
    
    private CardapioAutoServico cardapioDia;
    private CardapioAssistencia cardapioDiaAssistencia;
   
    
    public localizarCardapio() {
        this.cardapioDia = new CardapioAutoServico();
        this.cardapioDiaAssistencia = new CardapioAssistencia();
    }

    //<editor-fold defaultstate="collapsed" desc="Gets/Sets">
    

    public CardapioAssistencia getCardapioDiaAssistencia() {
        return cardapioDiaAssistencia;
    }

    public void setCardapioDiaAssistencia(CardapioAssistencia cardapioDiaAssistencia) {
        this.cardapioDiaAssistencia = cardapioDiaAssistencia;
    }

    
    public CardapioAutoServico getCardapioDia() {
        return cardapioDia;
    }

    public void setCardapioDia(CardapioAutoServico CardapioDoDia) {
        this.cardapioDia = CardapioDoDia;
    }
    //</editor-fold>
    
    @PostConstruct
    public void localizarCardapio(){
        System.out.println("###############################################\n");
        System.out.println(autoServicoService.localizarPorData(LocalDate.now()));
        //System.out.println(assistenciaService.localizarPorData(LocalDate.now()));
        this.cardapioDia = autoServicoService.localizarPorData(LocalDate.now());
        //this.cardapioDiaAssistencia.getPrato().setComponentes(assistenciaService.localizarPorData(LocalDate.now()));
        System.out.println("Cardapio do dia: " + cardapioDia);
        //System.out.println("Cardapio Assistencia: " + cardapioDiaAssistencia);
    }
    
    
    
}
