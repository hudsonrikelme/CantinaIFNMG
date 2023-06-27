/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ifnmg.edu.cardapio;

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
    public void findAll() {
        en.createNamedQuery("Select distinct c from Componente c",Componente.class).getResultList();
    }
    
}
