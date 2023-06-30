/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lucas Flavio<lucasfgm at ifnmg.edu.br>
 */
@Stateless
public class PratoService implements PratoServiceLocal {

   @PersistenceContext
    private EntityManager entityManager;
   
   @Override
    public void save(Prato prato) {
        entityManager.persist(prato);
    }
   
    @Override
    public List<Prato> findAll() {
        return entityManager.createQuery("SELECT DISTINCT p FROM Prato p", 
                Prato.class).getResultList();
    }

    @Override
    public Prato localizarPorId(Long id) {
        return entityManager.find(Prato.class, id);
    }
    
    
    
    
}
