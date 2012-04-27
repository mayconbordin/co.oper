<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#usernameField").focus();
    });
</script>

<div class="centro">
    <s:url id="cadastrarURL" action="cadastrar" namespace="/login" />
    <s:a href="%{cadastrarURL}" title="Cadastre-se"><img src="${root}/images/cadastrar-se1.png" alt="" /></s:a>

    <s:include value="form.jsp" />

    <!--<img src="${root}/images/recuperar-senha1.png" class="recuperar" alt="" />-->
</div>