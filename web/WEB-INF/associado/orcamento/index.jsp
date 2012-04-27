<%@page import="com.strutstool.currency.CurrencyUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cooper.model.entity.Orcamento"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<s:url id="detalhesURL" action="detalhes" namespace="/associado/orcamento" />

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

    $('#orcamentosTable .orcamento').each(function() {
        $(this).click(function() {
            var full = $(this).children('.conteudoFull');

            if (full.css('display') == 'none') {
                full.show();
                $(this).children('.conteudo').hide();
            } else {
                full.hide();
                $(this).children('.conteudo').show();
            }
            return false;
        });
    }).children().click(function() {
        if ($(this).children().attr("class") == 'verProdutos') {
            window.location.href = $(this).children().attr("href");
            return false;
        }
    });

    
    $('#orcamentosTable .options a').each(function() {
        var url = '<s:property value="#detalhesURL"/>';
            url = url + '?orcamentoId='
                + $(this).parent().parent().attr("id").replace("orcamento", "");
        $(this).attr("href", url);
    });
    
});
</script>

<div class="tableHeaderCorner"></div>
<div id="orcamentosTableContainer" class="tableContainer">
    <table id="orcamentosTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="15%" class="tableFirstCol">Data de Envio</td>
            <td width="15%">Status</td>
            <td width="40%">Comentários</td>
            <td width="15%">Total</td>
            <td width="15%" class="tableLastCol" align="center">Opções</td>
        </tr>

        <jsp:useBean id="orcamentoList" scope="request" type="List<Orcamento>" />
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int count = 0;
        for (Orcamento orcamento : orcamentoList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>
            <tr class="orcamento tableRecord" id="orcamento<%= orcamento.getId() + "\"" + display %>>
                <td width="15%"><%= sdf.format(orcamento.getData()) %></td>

                <%
                String statusClass = (orcamento.getStatus().equals(Orcamento.STATUS_PENDENTE)) ? "red" : "green";
                %>
                <td width="15%" class="<%= statusClass %>"><%= orcamento.getStatus() %></td>

                <%
                String coment = orcamento.getComentario();
                if (coment == null) coment = "...";
                int limiteConteudo = (coment.length() > 100) ? 100 : coment.length();
                String cont = (coment.length() > 100) ? "..." : "";
                %>
                <td class="conteudo" width="40%"><%= coment.substring(0, limiteConteudo) + cont %></td>
                <td class="conteudoFull" width="40%">
                    <div><p><%= coment %></p></div>
                </td>

                <td width="15%"><%= CurrencyUtils.formatToReais(orcamento.getTotal()) %></td>

                <td width="15%" class="options" align="center"><a class="verProdutos" href="" title="Ver produtos deste orçamento">Ver produtos</a></td>
            </tr>
        <%
            count++;
        }
        %>
        <tr>
            <td colspan="3" align="center" class="tableLastCol">
                <a id="maisOrcamentos" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>
    </table>
</div>