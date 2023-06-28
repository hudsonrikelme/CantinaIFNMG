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
    
    }

    
    
    
    
    
    
    
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
