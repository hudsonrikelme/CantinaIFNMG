package br.ifnmg.edu.cardapio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Gustavo Rafael Nunes Durães &lt;grnd at aluno.ifnmg.edu.br&gt;
 */
@Named(value = "componenteBean")
@RequestScoped
public class ComponenteBean implements Serializable {

    @Inject
    private ComponenteServiceLocal componenteService;

    @Inject
    private CategoriaServiceLocal ctService;

    private Componente c;

    private List<Categoria> categorias;

    public ComponenteBean() {
        c = new Componente();
    }

    //<editor-fold defaultstate="collapsed" desc="Getteres/Setteres">
    public Componente getC() {
        return c;
    }

    public void setC(Componente c) {
        this.c = c;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    //</editor-fold>

   @PostConstruct
    public void carregarCategorias() {

        System.out.println(ctService.findAll());
        this.categorias = ctService.findAll();
    }

    public String salvarComponente() {
        System.out.println(c.toString());
        componenteService.salvar(c);
        reset();
        return "cadastroComponente?faces-redirect=true";
    }

    public void reset() {
        c = new Componente();
    }
    
}
