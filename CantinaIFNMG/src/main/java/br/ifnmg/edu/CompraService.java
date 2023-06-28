/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ifnmg.edu;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Lucas
 */
@Stateless
public class CompraService implements CompraServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Compra c) {
        if (em.contains(c)) {
            em.persist(c);
        } else if (c.getId() != null) {
            em.merge(c);
        } else {
            em.merge(c);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Compra> localizarCompraPorCliente(Cliente cliente) {
        TypedQuery tq = em.createNamedQuery("Compra.findByCliente", Compra.class);
        tq.setParameter("cliente", cliente);
        return tq.getResultList();
    }

}
