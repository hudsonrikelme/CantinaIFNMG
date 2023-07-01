/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lucas Flavio<lucasfgm at ifnmg.edu.br>
 */
@Stateless
public class CardapioAssistenciaService implements CardapioAssistenciaServiceLocal {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void save (CardapioAssistencia cardapioAssistencia){
        entityManager.persist(cardapioAssistencia);
    }

    @Override
    public List<CardapioAssistencia> findAll() {
        return entityManager.createQuery("SELECT DISTINCT ca FROM CardapioAssistencia ca",
                CardapioAssistencia.class).getResultList();
    }

    @Override
    public CardapioAssistencia localizarPorId(Long id) {
        return entityManager.find(CardapioAssistencia.class, id);
    }

    @Override
    public List<Componente> localizarPorData(LocalDate dia) {
        CardapioAssistencia card;
         card = entityManager.createQuery("SELECT DISTINCT c FROM CardapioAssistencia c LEFT JOIN FETCH c.prato WHERE c.dia = :dia", 
                CardapioAssistencia.class).setParameter("dia", dia).getSingleResult();
         Prato p = entityManager.createQuery("SELECT p FROM Prato p LEFT JOIN FETCH p.componentes WHERE p.id = :pid", 
                 Prato.class).setParameter("pid", card.getPrato().getId()).getSingleResult();
         return p.getComponentes();
    }
    
    
    
    
    
    
    
}
