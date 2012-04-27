<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<s:url id="orcamentoConfirmDialogURL" action="orcamentoConfirmDialog" namespace="/associado/orcamento" />
<sj:dialog
    id="orcamentoConfirmDialog"
    autoOpen="false"
    modal="true"
    title="Confirmar Envio do Orçamento"
    width="900"
    minHeight="250"
/>

<s:url id="orcamentoDialogURL" action="orcamentoDialog" namespace="/associado/orcamento" />
<sj:dialog
    id="orcamentoDialog"
    autoOpen="false"
    modal="true"
    title="Detalhes do Orçamento"
    width="900"
    minHeight="250"
/>

<jsp:useBean id="produtosCount" scope="request" type="Integer" />
<% String visible = (produtosCount > 0) ? "" : "style='visibility:hidden;'"; %>

<div id="orcamentoBox"<%= visible %>>
    <sj:a cssClass="orcamento" openDialog="orcamentoDialog" href="%{orcamentoDialogURL}" title="">Meu Orçamento (<span>${produtosCount}</span>)</sj:a><sj:a cssClass="enviar" openDialog="orcamentoConfirmDialog" href="%{orcamentoConfirmDialogURL}" title="">Enviar »</sj:a>
</div>