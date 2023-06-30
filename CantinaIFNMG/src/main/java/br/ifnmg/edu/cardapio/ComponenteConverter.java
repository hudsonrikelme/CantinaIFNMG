/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.cardapio;


import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Gustavo Rafael Nunes Durães &lt;grnd at aluno.ifnmg.edu.br&gt;
 */
@FacesConverter(value = "componenteConverter", managed = true)
@ApplicationScoped
public class ComponenteConverter implements javax.faces.convert.Converter<Componente> {

    @Inject
    private ComponenteServiceLocal componenteService;
    
    public ComponenteConverter() {
    }
    
    
    @Override
    public Componente getAsObject(
            FacesContext context,
            UIComponent component,
            String id) {
        if (id == null) {
            return null;
        }
        System.out.println(componenteService.localizarPorID(Long.valueOf(id)));
        return componenteService.localizarPorID(Long.valueOf(id));
    }
    
    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Componente comp) {
        if (comp == null) {
            return null;
        }
        System.out.println(comp.getId().toString());
        return comp.getId().toString();
    }
}

