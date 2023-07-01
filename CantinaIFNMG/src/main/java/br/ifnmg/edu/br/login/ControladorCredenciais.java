package br.ifnmg.edu.br.login;

import br.ifnmg.edu.Credencial;
import br.ifnmg.edu.br.login.DataServiceBeanLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hudson Rikelme <hudson.rikelme at ifnmg.edu.br>
 */
@Named
@RequestScoped
public class ControladorCredenciais {
    @Inject
    DataServiceBeanLocal dataService;
    
    private List<Credencial> allCredenciais;
    
    @PostConstruct
    public void initialize(){
        this.allCredenciais = dataService.getAllCredenciais();
    }
    
    public List<Credencial> getAllCredenciais(){
        return allCredenciais;
    }
}
