
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
        return entityManager.createQuery("SELECT DISTINCT p FROM Prato p LEFT JOIN FETCH p.componentes", 
                Prato.class).getResultList();
    }

    @Override
    public Prato localizarPorId(Long id) {
        return entityManager.find(Prato.class, id);
    }
    
    
    
    
}
