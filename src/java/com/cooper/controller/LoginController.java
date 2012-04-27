package com.cooper.controller;

import com.cooper.model.entity.Usuario;
import com.cooper.model.service.UsuarioService;
import com.strutstool.repository.RepositoryException;
import com.strutstool.struts.StrutsController;
import com.strutstool.validator.ValidatorException;
import org.springframework.security.core.GrantedAuthority;

public class LoginController extends StrutsController {
    private UsuarioService usuarioService;

    private String codigo;
    private String email;
    private String senha;
    private String cpfCnpj;
    private boolean saveUsuario = false;

    private String error;
	
    // Actions =================================================================

    public String index() {
        return SUCCESS;
    }

    public String accessDenied() {
        return SUCCESS;
    }

    public String redirect() {
        for (GrantedAuthority ga : getUserAuth().getAuthorities()) {
            if (ga.getAuthority().equals("NIVEL_ASSOCIADO")) {
                return "associado";
            } else if (ga.getAuthority().equals("NIVEL_FUNCIONARIO")) {
                return "funcionario";
            } else if (ga.getAuthority().equals("NIVEL_TECNICO")) {
                return "tecnico";
            }
        }
        return ERROR;
    }

    public String cadastrar() {
        if (isSaveUsuario()) {
            try {
                if (usuarioService.cadastrarAssociado(codigo, email, senha, cpfCnpj)) {
                    return SUCCESS_SAVE;
                }
            } catch (RepositoryException ex) {
                errorHandler(ex);
            } catch (ValidatorException ex) {
                errorHandler(ex);
            }
            return ERROR;
        } else {
            return SUCCESS;
        }
    }
    
    // Getters and Setters =====================================================
    public boolean isSaveUsuario() {
        return saveUsuario;
    }

    public void setSaveUsuario(boolean saveUsuario) {
        this.saveUsuario = saveUsuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
