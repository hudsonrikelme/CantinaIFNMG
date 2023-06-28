package br.ifnmg.edu.cardapio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Gustavo Rafael Nunes Durães &lt;grnd at aluno.ifnmg.edu.br&gt;
 */
@Named(value = "componenteBean")
@SessionScoped
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
        componenteService.salvar(c);
        System.out.println(c.toString());
        reset();
        return "cadastroComponente?faces-redirect=true";
    }

    public void reset() {
        c = new Componente();
    }

    /*@PostConstruct
    public void cargaDados(){
        //Criando categorias para teste
        Categoria a1 = new Categoria();
        a1.setNome("FEIJAO-1");
        
        Categoria a2 = new Categoria();
        a2.setNome("ARROZ-1");
        
        Categoria a3 = new Categoria();
        a3.setNome("SALADA-1");
        
        //Criando componentes de teste
        Componente c1 = new Componente();
        c1.setNome("Feijão tropeiro");
        c1.setCaloria((float)256);
        c1.setCategoria(a1);
        
        Componente c2 = new Componente();
        c2.setNome("Arroz");
        c2.setCaloria((float)326.5);
        c2.setCategoria(a2);
        
        Componente c3 = new Componente();
        c3.setNome("Repolho Refogado");
        c3.setCaloria((float)154);
        c3.setCategoria(a3);
        
        componenteService.salvar(c1);
        componenteService.salvar(c2);
        componenteService.salvar(c3);
    }*/
}
