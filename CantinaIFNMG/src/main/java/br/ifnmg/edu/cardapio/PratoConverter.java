/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author gusta
 */
@Named(value = "pratoConverter")
@ApplicationScoped
public class PratoConverter implements javax.faces.convert.Converter<Prato>{

    @Inject
    private PratoServiceLocal pratoService;
    
    public PratoConverter() {
    }
    
    @Override
    public Prato getAsObject(
            FacesContext context,
            UIComponent component,
            String id) {
        if (id == null) {
            return null;
        }
        System.out.println(pratoService.localizarPorId(Long.valueOf(id)));
        return pratoService.localizarPorId(Long.valueOf(id));
    }
    
    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Prato prato) {
        if (prato == null) {
            return null;
        }
        System.out.println(prato.getId().toString());
        return prato.getId().toString();
    }
    
    
}
