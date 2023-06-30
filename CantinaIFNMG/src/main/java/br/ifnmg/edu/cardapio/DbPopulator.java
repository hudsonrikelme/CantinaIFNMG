/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author gusta
 */
@Singleton
@Startup
public class DbPopulator {
    
    @Inject
    private CategoriaServiceLocal ctService;
    
    @Inject
    private ComponenteServiceLocal cpService;
    
    @PostConstruct
    public void populator(){
        Categoria c1 = new Categoria("FEIJAO");
        Categoria c2 = new Categoria("ARROZ");
        Categoria c3 = new Categoria("CARNE");
        Categoria c4 = new Categoria("ACOMPANHAMENTO");
        Categoria c5 = new Categoria("SALADA1");
        Categoria c6 = new Categoria("SALADA2");
        Categoria c7 = new Categoria("SALADA3");
        Categoria c8 = new Categoria("FRUTA/SUCO");
    
        ctService.save(c1);
        ctService.save(c2);
        ctService.save(c3);
        ctService.save(c4);
        ctService.save(c5);
        ctService.save(c6);
        ctService.save(c7);
        ctService.save(c8);
        
        Componente cp1 = new Componente("Feijão Tropeiro", (float)123, c1);
        Componente cp2 = new Componente("Feijão de Caldo", (float)456, c1);
        Componente cp3 = new Componente("Arroz branco", (float)789, c2);
        Componente cp4 = new Componente("Filé de lombo", (float)987, c3);
        Componente cp5 = new Componente("Costelinha frita", (float)654, c3);
        Componente cp6 = new Componente("Frango empanado", (float)321, c3);
        Componente cp7 = new Componente("Macarrão com bacon", (float)147, c4);
        Componente cp8 = new Componente("Purê de Abóbora", (float)258, c4);
        Componente cp9 = new Componente("Lasanha", (float)369, c4);
        Componente cp10 = new Componente("Salada de Alface", (float)963, c5);
        Componente cp11 = new Componente("Batata doce", (float)852, c6);
        Componente cp12 = new Componente("Salpicão", (float)741, c7);
        Componente cp13 = new Componente("Melancia", (float)159, c8);
        Componente cp14 = new Componente("Suco de Tomate", (float)753, c8);
        
        cpService.salvar(cp1);
        cpService.salvar(cp2);
        cpService.salvar(cp3);
        cpService.salvar(cp4);
        cpService.salvar(cp5);
        cpService.salvar(cp6);
        cpService.salvar(cp7);
        cpService.salvar(cp8);
        cpService.salvar(cp9);
        cpService.salvar(cp10);
        cpService.salvar(cp11);
        cpService.salvar(cp12);
        cpService.salvar(cp13);
        cpService.salvar(cp14);
        
    }
    
    
    
    
    
    
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
