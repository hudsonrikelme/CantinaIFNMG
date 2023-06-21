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
 * @author Lucas
 */
@Stateless
public class ClienteService implements ClienteServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Cliente c) {
        if (em.contains(c)) {
            em.persist(c);
        } else if(c.getId() != null){
            em.merge(c);
        } else {
            em.merge(c);
        }
    }

    @Override
    public List<Cliente> localizarTodos() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    @Override
    public Cliente localizarPorId(Long id) {
        return em.find(Cliente.class, id);
    }

}
