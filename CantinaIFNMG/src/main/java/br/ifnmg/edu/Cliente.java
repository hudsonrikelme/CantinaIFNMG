/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu;

import br.ifnmg.edu.compra.Compra;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Lucas Freitas &lt;lpf1 at ifnmg.edu.br&gt;
 */
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 65)
    private String nome;

    private String cpf;

    @OneToMany(mappedBy = "cliente",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Compra> compras;

    private BigDecimal saldo;

    private Boolean auxilio;

    @Enumerated(EnumType.ORDINAL)
    private TipoCliente tipoCliente;

    @OneToOne(cascade = CascadeType.ALL)
    private Credencial credencial;

    public Cliente() {
        compras = new ArrayList<>();
    }

    public Cliente(Credencial credencial) {
        this.credencial = credencial;
    }
    
    public void adicionarCompra(Compra c) {
        compras.add(c);
    }
    
    public void cobrar(BigDecimal valor)
    {
        saldo = saldo.subtract(valor);
    }
    
    public void adicionarSaldo(BigDecimal valor){
        saldo = saldo.add(valor);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter / Setter">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Boolean getAuxilio() {
        return auxilio;
    }

    public void setAuxilio(Boolean auxilio) {
        this.auxilio = auxilio;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Hash / Equals / ToString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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

        return obj.hashCode() == hashCode();
    }

    @Override
    public String toString() {
        return "Cliente{"
                + "id=" + id
                + ", nome=" + nome
                + ", cpf=" + cpf
                + ", compras=" + compras
                + ", saldo=" + saldo
                + ", auxilio=" + auxilio
                + ", tipoCliente=" + tipoCliente.getRotulo()
                + '}';
    }

//</editor-fold>
    
    public enum TipoCliente {
        CLIENTE("Cliente"),
        GERENTE("Gerente"),
        NUTRICIONISTA("Nutricionista"),
        CAIXA("Caixa");

        private String rotulo;

        private TipoCliente(String rotulo) {
            this.rotulo = rotulo;
        }

        public String getRotulo() {
            return rotulo;
        }
    }

}
