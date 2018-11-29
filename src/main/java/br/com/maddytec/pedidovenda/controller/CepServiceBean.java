package br.com.maddytec.pedidovenda.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.maddytec.pedidovenda.model.Endereco;
import br.com.maddytec.pedidovenda.service.CepWebService;



@ManagedBean
@RequestScoped
public class CepServiceBean {

    private Endereco endereco;
    
    public Endereco encontraCep(String cep) {
        
		try {
			CepWebService cws = new CepWebService(cep);
			endereco = new Endereco();
			 if (cws.getResultado() == 1) {
				    endereco.setCep(cep);
				    //endereco.setTipoLogradouro(cws.getTipoLogradouro());
				    endereco.setLogradouro(cws.getTipoLogradouro() + " " + cws.getLogradouro());
		            endereco.setUf(cws.getEstado());
		            endereco.setCidade(cws.getCidade());
		            endereco.setBairro(cws.getBairro());
		        } else {
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cep não encontrado!", "Cep não encontrado!"));
		        }
			 
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servidor não está respondendo!", "Servidor não está respondendo"));
		}
		
		return endereco;
    }

}
