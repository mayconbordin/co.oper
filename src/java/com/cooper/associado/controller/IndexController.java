package com.cooper.associado.controller;

import com.strutstool.struts.StrutsController;

public class IndexController extends StrutsController {
    private String usuarioId;
	
    // Actions =================================================================

    public String index() {
        usuarioId = getUserAuth().getName();
        return SUCCESS;
    }

    // Getters and Setters =====================================================
    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
