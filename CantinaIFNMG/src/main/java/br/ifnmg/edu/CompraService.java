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
        
        List<Compra> compras = 
                em.createQuery("SELECT DISTINCT c FROM Compra c "
                        + "left join fetch c.produtos"
                + " WHERE c.cliente = :cliente",Compra.class)
                .setParameter("cliente", cliente)
                .getResultList();
        
        compras = em.createQuery("SELECT DISTINCT c FROM Compra c "
                        + "left join fetch c.produtos"
                + " WHERE c IN :compra",
                Compra.class)
                .setParameter("compra", compras)
                .getResultList();
        
        return compras;
    }

}
