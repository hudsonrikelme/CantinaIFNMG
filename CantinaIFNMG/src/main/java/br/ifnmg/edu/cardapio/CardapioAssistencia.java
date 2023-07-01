/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu.cardapio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gustavo Rafael Nunes Durães &lt;grnd at aluno.ifnmg.edu.br&gt;
 */
@Entity
public class CardapioAssistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dia;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "prato_id")
    private Prato prato;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    

    public CardapioAssistencia() {
    }

    public CardapioAssistencia(LocalDate dia, Prato prato) {
        this.dia = dia;
        this.prato = prato;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gets/Sets">
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Hashcode/Equals/toString">
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "CardapioAssistencia{" + "id=" + id + ", dia=" + dia + ", prato=" + prato + '}';
    }

    //</editor-fold>
}
