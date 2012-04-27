<%@taglib uri="/struts-tags" prefix="s" %>

<s:url id="conta" action="index" namespace="/associado/conta" />
<s:url id="sair"value="/j_spring_security_logout" />

<script type="text/javascript">
$(document).ready(function() {
    loadContaIfInside();
});
</script>

<div id="accountBox">
    <s:a cssClass="minhaConta" href="%{conta}" title="%{getText('menu.minhaconta.desc')}"><s:text name="menu.minhaconta" /></s:a><s:a cssClass="sair" href="%{sair}" title="%{getText('menu.sair.desc')}"><s:text name="menu.sair" /></s:a>
</div>