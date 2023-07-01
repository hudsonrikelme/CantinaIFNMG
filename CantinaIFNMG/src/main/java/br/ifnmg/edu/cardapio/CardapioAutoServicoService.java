
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
    
    
    
    
   
   
}
