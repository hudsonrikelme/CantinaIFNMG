/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.ifnmg.edu.produto;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lucas Freitas &lt;lpf1 at ifnmg.edu.br&gt;
 */
@Local
public interface ProdutoServiceLocal {

    void salvar(Produto p);

    List<Produto> localizarTodos();

    List<Produto> localizarParteNome(String busca);

    Produto localizarPorId(Long id);
    
}
