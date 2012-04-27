<%@page import="com.cooper.model.entity.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cooper.model.entity.Mensagem"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

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
        });
    });

    $('#maisMensagens').click(function() {
        var count = 0;
        $('.msg').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });

    $('#novaMensagem a').click(function() {
        $('.messageSendBox').fadeIn(1000);
        return false;
    });

     // Fecha as mensagens ao clicar no botão
    $('.messageSendBox .fechar').each(function() {
        $(this).click(function() {
            $(this).parent().fadeOut(1000);
            $(this).parent().children('.messageTextArea').val("");
            return false;
        });
    });

    // Envia a mensagem
    $('.messageSendBox .enviar').each(function() {
        $(this).click(function() {
            if (addEventoEnviarMensagem(this)) {
                $(this).parent().fadeOut(1000);
            }
            return false;
        });
    });

});
</script>

<div id="messageBar">
    <div id="novaMensagem">
        <a href="" title="Nova Mensagem"><img src="${root}/images/nova_msg_btn.png" alt="nova mensagem" /></a>
    </div>

    <s:url id="mensagemIndexURL" action="index" namespace="/funcionario/mensagem" />
    <div id="messageFilters">
        <s:if test="%{list == 'todas'}">
            <s:a href="%{mensagemIndexURL}?list=todas" title="" cssClass="selecionado">Todas</s:a>
        </s:if>
        <s:else>
            <s:a href="%{mensagemIndexURL}?list=todas" title="">Todas</s:a>
        </s:else>

        <s:if test="%{list == 'recebidas'}">
            <s:a href="%{mensagemIndexURL}?list=recebidas" title="" cssClass="selecionado">Recebidas</s:a>
        </s:if>
        <s:else>
            <s:a href="%{mensagemIndexURL}?list=recebidas" title="">Recebidas</s:a>
        </s:else>
            
        <s:if test="%{list == 'enviadas'}">
            <s:a href="%{mensagemIndexURL}?list=enviadas" title="" cssClass="selecionado">Enviadas</s:a>
         </s:if>
        <s:else>
            <s:a href="%{mensagemIndexURL}?list=enviadas" title="">Enviadas</s:a>
        </s:else>
    </div>
    
    <div class="messageSendBox">
        <strong>Para:</strong> <sj:autocompleter
                                list="%{associadoList}"
                                listValue="nome"
                                listKey="codigo"
                                cssClass="associadoId"
                                />
        <br/>
        <strong>Mensagem:</strong><br/>
        <textarea class="messageTextArea" rows="10" cols="50"></textarea>
        <br/>
        <a class="fechar" href="" title="Cancelar"><img src="${root}/images/cancel_send_msg.png" alt="cancelar" /></a>
        <a class="enviar" href="" title="Enviar"><img src="${root}/images/send_msg.png" alt="enviar" /></a>
    </div>
</div>

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
                <a id="maisMensagens" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>
    </table>
</div>