<%@page import="com.cooper.model.entity.ProdutoDoOrcamento"%>
<%@page import="com.cooper.model.entity.Orcamento"%>
<%@page import="com.strutstool.currency.CurrencyUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisOrcamentos').click(function() {
        var count = 0;
        $('.orcamento').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });

    $('.orcamento').click(function() {
        var id = '#' + $(this).attr('id').replace('orcamento', 'orcamentoDialog');
        $(id).dialog('open');
    }).children().click(function() {
        if ($(this).children().attr("class") == 'responderOrcamento' ||
            $(this).children().attr("class") == 'respondidoOrcamento') {
            var id = '#' + $(this).parent().attr('id').replace('orcamento', 'responderOrcamento');
            $(id).dialog('open');
            return false;
        }
    });
});
</script>

<sj:dialog
    id="orcamentoRespondidoDialog"
    autoOpen="false"
    modal="true"
    title="Orçamento Respondido!"
    buttons="{'OK':function() { $(this).dialog('close'); }}"
>
    Orçamento Respondido!
</sj:dialog>
    
<div class="tableHeaderCorner"></div>
<div id="graosTableContainer" class="tableContainer">
    <table id="graosTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="30%" class="tableFirstCol">Data</td>
            <td width="40%">Associado</td>
            <td width="20%">Status</td>
            <td width="10%" class="tableLastCol" align="center">Opções</td>
        </tr>

        <jsp:useBean id="orcamentoList" scope="request" type="List<Orcamento>" />
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;

        for (Orcamento orcamento : orcamentoList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>

        <tr id="orcamento<%= orcamento.getId() %>" class="orcamento tableRecord"<%= display %> title="Clique para ver mais detalhes">
            <td width="30%" class="tableFirstCol"><%= sdf.format(orcamento.getData()) %></td>
            <td width="40%"><%= orcamento.getAssociado().getNome() %></td>

            <%
            String statusClass = (orcamento.getStatus().equals(Orcamento.STATUS_PENDENTE)) ? "red" : "green";
            %>
            <td width="20%" class="<%= statusClass %>"><%= orcamento.getStatus() %></td>
            
            <% if (orcamento.getStatus().equals(Orcamento.STATUS_PENDENTE)) { %>
            <td width="10%" class="options" align="center"><a class="responderOrcamento" href="" title="">Responder</a></td>
            <% } else { %>
            <td width="10%" class="options" align="center"><a class="respondidoOrcamento" href="" title="">Respondido</a></td>
            <% } %>
        </tr>


        <s:set name="id"><%= orcamento.getId() %></s:set>
        <s:set name="data"><%= sdf.format(orcamento.getData()) %></s:set>
        <sj:dialog
            id="orcamentoDialog%{#id}"
            autoOpen="false"
            modal="true"
            title="Orçamento de %{#data}"
            width="500"
            buttons="{'Fechar':function() { $(this).dialog('close'); }}"
        >
            <div class="detailedDialog">
            <p>
                <span class="label">Data:</span>
                <span class="value"><%= sdf.format(orcamento.getData()) %></span>
            </p>
            <p>
                <span class="label">Associado:</span>
                <span class="value"><%= orcamento.getAssociado().getNome() %></span>
            </p>
            <p>
                <span class="label">Comentários:</span>
                <% String coment = (orcamento.getComentario() == null) ? "" : orcamento.getComentario(); %>
                <span class="value"><%= coment %></span>
            </p>
            <p>
                <span class="label">Status:</span>
                <span class="value"><%= orcamento.getStatus() %></span>
            </p>
            <p>
                <span class="label">Total:</span>
                <span class="value"><%= CurrencyUtils.formatToReais(orcamento.getTotal()) %></span>
            </p>
            <p>
                <script type="text/javascript">
                    $(document).ready(function() {
                        $('#openOrcamentoProdutosDialog<%= orcamento.getId() %>').click(function(){
                            $('#orcamentoProdutosDialog<%= orcamento.getId() %>').dialog('open');
                            return false;
                        });
                        
                    });
                </script>
                <a class="buttonLink" id="openOrcamentoProdutosDialog<%= orcamento.getId() %>" href="" title="">Produtos</a>
            </p>
            </div>
        </sj:dialog>

        <s:url id="mostraProdutosOrcamentoURL" action="mostraProdutosOrcamento" namespace="/funcionario/orcamento">
            <s:param name="orcamentoId" value="%{id}" />
        </s:url>
        <sj:dialog
            id="orcamentoProdutosDialog%{#id}"
            href="%{mostraProdutosOrcamentoURL}"
            autoOpen="false"
            modal="true"
            title="Produtos do orçamento de %{#data}"
            width="700"
            buttons="{'Fechar':function() { $(this).dialog('close'); }}"
        >
            <div id="loader"><img src="${root}/images/loader.gif" alt="loader" /></div>
        </sj:dialog>

        <s:url id="saveRespostaOrcamentoURL" action="saveRespostaOrcamento" namespace="/funcionario/orcamento" />
        <sj:dialog
            id="responderOrcamento%{#id}"
            autoOpen="false"
            modal="true"
            title="Responder Orçamento cod. %{#id}"
            width="600"
            buttons="{
                'Fechar':function() { $(this).dialog('close'); },
                'Enviar':function(e) {
                    var comentario = $('#orcamentoResposta%{#id}')
                        .children('.comentarioContainer')
                        .children('.value').children('.comentario').val();

                    var total = $('#orcamentoResposta%{#id}')
                        .children('.totalContainer')
                        .children('.value').children('.total').val();

                    var url = '%{#saveRespostaOrcamentoURL}';

                    if (comentario == '' || total == '' || !isNumber(total)) {
                        alert('Preencha todos os campos!');
                    } else {
                        $(this).dialog('close');
                        $('#loadingBar').dialog('open');
                        $.post(url, {orcamentoId:%{#id}, comentario:comentario, total:total}, function() {
                            var linkAvaliadr = $('#orcamento%{#id}').children('.options').children('.responderOrcamento');
                            linkAvaliadr.html('Respondido');
                            linkAvaliadr.removeClass('responderOrcamento');
                            linkAvaliadr.addClass('respondidoOrcamento');
                            linkAvaliadr.attr('title', 'Orçamento respondido');

                            //Modifica o status
                            var status = $('#orcamento%{#id}').children('.red');
                            status.html('respondido');
                            status.removeClass('red');
                            status.addClass('green');

                            $('#loadingBar').dialog('close');
                            $('#orcamentoRespondidoDialog').dialog('open');
                        });
                    }
                }
            }"
        >
            <div class="detailedDialog" id="orcamentoResposta<%= orcamento.getId() %>">
                <p class="comentarioContainer">
                    <span class="label">Comentário:</span>
                    <% String comentario = (orcamento.getComentario() != null) ? orcamento.getComentario() : ""; %>
                    <span class="value"><textarea class="comentario"><%= comentario %></textarea></span>
                </p>
                <p class="totalContainer">
                    <span class="label">Total:</span>
                    <span class="value">
                        R$ <input type="text" class="total" value="<%= orcamento.getTotal() %>" />
                    </span>
                </p>
            </div>
        </sj:dialog>

        <%
            count++;
        }
        %>

        <!-- footer da tabela da lista de msges -->
        <tr>
            <td colspan="5" align="center" class="tableLastCol">
                <a id="maisOrcamentos" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>

    </table>
</div>