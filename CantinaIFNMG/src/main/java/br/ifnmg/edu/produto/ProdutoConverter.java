/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu.produto;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Lucas
 */
@FacesConverter(value = "produtoConverter", managed = true)
@ApplicationScoped
public class ProdutoConverter implements javax.faces.convert.Converter<Produto> {

    @Inject
    private ProdutoServiceLocal produtoService;

    public ProdutoConverter() {
    }

    @Override
    public Produto getAsObject(
            FacesContext context,
            UIComponent component,
            String id) {
        if (id == null) {
            return null;
        }
        System.out.println("TESTE: " + (produtoService == null));
        return produtoService
                .localizarPorId(Long.valueOf(id));
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Produto produto) {
        if (produto == null) {
            return null;
        }
        System.out.println(produto.getId().toString());
        return produto.getId().toString();
    }

}
