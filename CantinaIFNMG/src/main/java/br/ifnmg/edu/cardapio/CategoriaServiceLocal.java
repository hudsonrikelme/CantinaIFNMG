/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lucas Flavio<lucasfgm at ifnmg.edu.br>
 */
@Local
public interface CategoriaServiceLocal {

    public void save(Categoria categoria);

    public Categoria localizarPorId(Long id);

    List<Categoria> findAll();


}
