<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#usernameField").focus();
    });
</script>

<div class="centro">
    <h2>Seu cadastro foi efetuado com sucesso!</h2>

    <s:url id="loginURL" action="index" namespace="/login" />
    <s:a href="%{loginURL}" title="">Entrar no Portal do Associado</s:a>
</div>