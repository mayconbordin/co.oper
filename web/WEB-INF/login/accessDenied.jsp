<%@taglib uri="/struts-tags" prefix="s"%>

<s:url id="loginURL" action="index" namespace="/login" />

<div class="centro">
    <div id="acessoNegado">
        <h1>Acesso Negado!</h1>
        <h3><s:a href="%{loginURL}" title="">Efetuar login</s:a></h3>
    </div>
</div>