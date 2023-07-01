/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.br.login.DataServiceBeanLocal;
import br.ifnmg.edu.produto.Produto;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lucas
 */
@Singleton
@Startup
public class CargaDados implements CargaDadosServiceLocal1 {

    @PersistenceContext
    EntityManager em;

    @Inject
    DataServiceBeanLocal dataService;

    @Override
    @PostConstruct
    public void cargaDados() {

        Produto p1 = new Produto();
        p1.setDescricao("Coxinha grande");
        p1.setNome("Coxinha");
        p1.setPreco(BigDecimal.valueOf(3.5));

        Produto p2 = new Produto();
        p2.setDescricao("Refrigerante lata 350 ml");
        p2.setNome("Refrigerante lata");
        p2.setPreco(BigDecimal.valueOf(3.0));

        Produto p3 = new Produto();
        p3.setDescricao("Bolo no pote 300 gramas");
        p3.setNome("Bolo no pote");
        p3.setPreco(BigDecimal.valueOf(8.0));

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);


    }
}
