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
 * @author Lucas Flavio<lucasfgm at ifnmg.edu.br>
 */
@Stateless
public class CategoriaService implements CategoriaServiceLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Categoria categoria) {
        entityManager.persist(categoria);
    }

    @Override
    public Categoria localizarPorId(Long id) {

        return entityManager.find(Categoria.class, id);
    }

    @Override
    public List<Categoria> findAll() {
        return entityManager.createNamedQuery("SELECT DISTINCT c FROM Categoria c",
                Categoria.class).getResultList();
    }

}
