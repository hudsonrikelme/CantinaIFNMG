/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lucas Flavio<lucasfgm at ifnmg.edu.br>
 */
@Local
public interface CardapioAutoServicoServiceLocal {

public void save(CardapioAutoServico cardapioAutoServico);

    List<CardapioAutoServico> findAll();

    CardapioAutoServico localizarPorId(Long id);

    CardapioAutoServico localizarPorData(LocalDate dia);

}
