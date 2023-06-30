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
 * @author Gustavo Rafael Nunes Durães &lt;grnd at aluno.ifnmg.edu.br&gt;
 */
@Stateless
public class ComponenteService implements ComponenteServiceLocal {
    
    @PersistenceContext
    private EntityManager en;

    @Override
    public void salvar(Componente componente) {
        en.persist(componente);
    }

    @Override
    public List<Componente> findAll() {
       return en.createQuery("SELECT DISTINCT c FROM Componente c",
                Componente.class).getResultList();
    }

    @Override
    public Componente localizarPorID(Long id) {
        return en.find(Componente.class, id);
    }
    
    
}
