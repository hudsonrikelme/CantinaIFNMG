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
public class ComentarioService implements ComentarioServiceLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void salvar(Comentario comentario) {
        em.persist(comentario);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Comentario> localizarTodos() {
        return em.createQuery("SELECT c FROM Comentario c", Comentario.class).getResultList();
    }
}
