/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ifnmg.edu;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lucas Freitas &lt;lpf1 at ifnmg.edu.br&gt;
 */
@Stateless
public class ProdutoService implements ProdutoServiceLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void salvar(Produto p) {
        em.persist(p);
    }

    @Override
    public List<Produto> localizarTodos() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class)
                .getResultList();
    }
    
    

    
}
