/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.produto.Produto;
import br.ifnmg.edu.produto.ProdutoServiceLocal;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Lucas
 */
@FacesConverter(value = "clienteConverter", managed = true)
@ApplicationScoped
public class ClienteConverter implements javax.faces.convert.Converter<Cliente> {

    @Inject
    private ClienteServiceLocal clienteService;

    public ClienteConverter() {
    }

    @Override
    public Cliente getAsObject(
            FacesContext context,
            UIComponent component,
            String id) {
        if (id == null) {
            return null;
        }
        return clienteService
                .localizarPorId(Long.valueOf(id));
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        
        return cliente.getId().toString();
    }

}
