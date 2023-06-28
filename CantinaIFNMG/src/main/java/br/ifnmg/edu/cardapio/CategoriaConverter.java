/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu.cardapio;


import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author gusta
 */
@FacesConverter(value = "categoriaConverter", managed = true)
@ApplicationScoped
public class CategoriaConverter implements javax.faces.convert.Converter<Categoria> {
    
    @Inject
    private CategoriaServiceLocal ctService;
    
    public CategoriaConverter(){
    }
    
    @Override
    public Categoria getAsObject(
            FacesContext context,
            UIComponent component,
            String id) {
        if (id == null) {
            return null;
        }
        return ctService.localizarPorId(Long.valueOf(id));
    }
    
    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        //System.out.println(categoria.getId().toString());
        return categoria.getNome();
    }
}
