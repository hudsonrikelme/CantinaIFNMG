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
    
}