<%@page import="com.cooper.model.entity.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cooper.model.entity.Mensagem"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<s:url id="maisMensagensURL" action="index" namespace="/associado/mensagem" />

<script type="text/javascript">
$(document).ready(function() {

    carregarMensagensNaoLidas();

    // Abre as mensagens e seta como lidas quando abertas
    $('#mensagensTable .msg').each(function() {
        $(this).click(function() {
            addEventoAbrirMensagem(this);
            return false;
        });
    });

    // Fecha as mensagens ao clicar no botão
    $('#mensagensTable .fechar').each(function() {
        $(this).click(function() {
            addEventoFecharMensagem(this);
            return false;
        });
    });

    // Envia a mensagem
    $('#mensagensTable .enviar').each(function() {
        $(this).click(function() {
            addEventoEnviarMensagem(this);
            return false;
        });
    });
    
});
</script>

<div class="tableHeaderCorner"></div>
<div id="mensagensTableContainer" class="tableContainer">
    <table id="mensagensTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td colspan="3" class="tableFirstCol">
                Mensagens <span class="msgNaoLida"></span>
            </td>
        </tr>

        <jsp:include page="mensagens.jsp" />

        <!-- footer da tabela da lista de msges -->
        <tr>
            <td colspan="3" align="center" class="tableLastCol">
                <s:a id="maisMensagens" cssClass="tableMoreLink" href="%{maisMensagensURL}" title="Ver todas mensagens">todas...</s:a>
            </td>
        </tr>
    </table>
</div>