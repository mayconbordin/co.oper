package com.cooper.associado.interceptor;

import com.cooper.model.service.AcessoService;
import com.cooper.model.service.impl.AcessoServiceImpl;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.security.core.context.SecurityContextHolder;

public class AcessoInterceptor extends AbstractInterceptor implements StrutsStatics {
    private AcessoService acessoService;
    protected static final Logger logger = Logger.getLogger(AcessoInterceptor.class);

    public String intercept(ActionInvocation invocation) throws Exception {
        String pagina = ServletActionContext.getRequest().getRequestURI().toString();

        if (!pagina.toLowerCase().contains("web-inf")) {
            acessoService = new AcessoServiceImpl();
            String ip = ServletActionContext.getRequest().getRemoteAddr();
            String codAssociado = SecurityContextHolder.getContext().getAuthentication().getName();
            acessoService.newAcesso(ip, pagina, codAssociado);
        }

        String result = ActionSupport.SUCCESS;
        
        try {
            result = invocation.invoke();
        } catch(Exception ex) {
            logger.error("Erro: " + ex.getMessage() + "\n" + ex.getCause().toString());
        }
        return result;
    }

}
