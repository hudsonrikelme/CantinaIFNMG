
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
public class CardapioAutoServicoService implements CardapioAutoServicoServiceLocal {

   @PersistenceContext
   private EntityManager entityManager;
   
   @Override
    public void save (CardapioAutoServico cardapioAutoServico){
       entityManager.persist(cardapioAutoServico);
   }

    @Override
    public List<CardapioAutoServico> findAll() {
        return entityManager.createQuery("SELECT DISTINCT cd FROM CardapioAutoServico cd",
                CardapioAutoServico.class).getResultList() ;
    }

    @Override
    public CardapioAutoServico localizarPorId(Long id) {
        return entityManager.find(CardapioAutoServico.class, id);
    }

    @Override
    public CardapioAutoServico localizarPorData(LocalDate dia) {
        return entityManager.createQuery("SELECT DISTINCT c FROM CardapioAutoServico c LEFT JOIN FETCH c.componentes WHERE c.dia = :dia", 
                CardapioAutoServico.class).setParameter("dia", dia).getSingleResult();
    }
    
    
    
    
    
    
   
   
}
