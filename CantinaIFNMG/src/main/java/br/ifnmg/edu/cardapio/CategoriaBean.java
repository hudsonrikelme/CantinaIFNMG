/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author gusta
 */
@Named(value = "categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable {

    @Inject
    private CategoriaServiceLocal ctService;

    private Categoria ct;

    /**
     * Creates a new instance of CantinaBean
     */
    public CategoriaBean() {
        ct = new Categoria();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Categoria getCt() {
        return ct;
    }

    public void setCt(Categoria ct) {
        this.ct = ct;
    }

    //</editor-fold>
    
    public String salvarCategoria() {
        ctService.save(ct);
        System.out.println(ct.toString());
        reset();
        return "cadastroComponente?faces-redirect=true";
    }

    public void reset() {
        ct = new Categoria();
    }

}
