/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.produto.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author @Daniel Alves F.N.&lt;Daniel Aluno do IFNMG&gt;
 */
@Entity
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 6, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    //cliente
    @ManyToOne(cascade = CascadeType.ALL,
             fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //produtos
    @ManyToMany(fetch = FetchType.LAZY,
             cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    private Boolean pago = false;

    @Column(columnDefinition = "DATE")
    private LocalDate dia;

    @Enumerated(EnumType.ORDINAL)
    private TipoPagamento tipoPagamento;

    public Compra() {

        produtos = new ArrayList();
    }

    //<editor-fold defaultstate="collapsed" desc="get e set">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoLogradouro) {
        this.tipoPagamento = tipoLogradouro;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="HashCode / Equals / ToString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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

        return "Compra{" + "id=" + id
                + ", total=" + total
                + ", clientes=" + cliente.getNome()
                + ", produtos=" + produtos
                + ", pago=" + pago
                + ", dia=" + dia
                + ", tipoLogradouro=" + tipoPagamento
                + '}';
    }

    //</editor-fold>
    public void adicionarProduto(Produto p) {
        total = total.add(p.getPreco());
        produtos.add(p);
    }

    public enum TipoPagamento {
        CARTAO("Cartão"),
        DINHEIRO("Dinheiro"),
        PIX("Pix"),
        SALDO("Saldo"),
        AUXILIO("Auxílio");

        private String rotulo;

        TipoPagamento(String rotulo) {
            this.rotulo = rotulo;
        }

        public String getRotulo() {
            return rotulo;
        }
    }

}
