package br.ifnmg.edu.br.login;

import br.ifnmg.edu.Cliente;
import br.ifnmg.edu.Credencial;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hudson Rikelme <hudson.rikelme at ifnmg.edu.br>
 */
@Named
@RequestScoped
public class ControladorNovaCredencial {
    @Inject
    DataServiceBeanLocal dataService;
    
    private Cliente cliente;
    
    private Credencial credencial;

    private List<Credencial.TipoCliente> tipocliente;
    
    private Credencial.TipoCliente credencialselecionada;
    

    
    
    
    
    public ControladorNovaCredencial() {
        cliente = new Cliente();
        credencial = new Credencial();
//        credencial.setTipoCliente(Credencial.TipoCliente.CLIENTE);
        credencial.setCliente(cliente);
        tipocliente = Arrays.asList(Credencial.TipoCliente.values());
    }

    
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public List<Credencial.TipoCliente> getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(List<Credencial.TipoCliente> tipocliente) {
        this.tipocliente = tipocliente;
    }

    public Credencial.TipoCliente getCredencialselecionada() {
        return credencialselecionada;
    }

    public void setCredencialselecionada(Credencial.TipoCliente credencialselecionada) {
        this.credencialselecionada = credencialselecionada;
    }
    

    
    public String save() {
        credencial = dataService.criaCredencial(
                cliente,
                credencial.getEmail(), 
                credencial.getSenha(),
                Credencial.TipoCliente.CLIENTE);
        
        
        return "app/index?faces-redirect=true";
    }
    
     public String salvarAdministrativo() {
        credencial = dataService.criaCredencial(
                cliente,
                credencial.getEmail(), 
                credencial.getSenha(),
                credencialselecionada);
        
        
        
        return "restrito/cadastroCliente?faces-redirect=true";
    }
}
