/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.ifnmg.edu;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lucas
 */
@Local
public interface CompraServiceLocal {

    void salvar(Compra c);

    List<Compra> localizarCompraPorCliente(Cliente cliente);
    
}
