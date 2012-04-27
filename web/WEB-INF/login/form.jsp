<%@taglib uri="/struts-tags" prefix="s"%>

<s:actionerror />

<s:set name="error">${error}</s:set>
<s:if test="%{#error=='invalid'}">
    <div id="loginError">Código ou senha inválidos</div>
</s:if>
<s:elseif test="%{#error!=''}">
    <div id="loginError">Ocorreu um erro na autenticação</div>
</s:elseif>

<s:url id="formSubmit" value='/j_spring_security_check' />

<s:form action="%{formSubmit}" method="post">
    <div class="formlogin">

        <div class="legendas">
            <div class="labelfor"><s:text name="label.username" /></div>
            <div class="labelfor"><s:text name="label.password" /></div>
        </div>

        <div class="entradas">
            <input id="usernameField" type="text" name="j_username" class="inptform"/>
            <input id="passwordField" type="password" name="j_password" class="inptform"/>
        </div>

        <input type="submit" class="btnentrar" value="Entrar"/>
    </div>
</s:form>