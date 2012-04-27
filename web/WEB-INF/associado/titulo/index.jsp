<%@page import="com.strutstool.currency.CurrencyUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cooper.model.entity.Titulo"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisTitulos').click(function() {
        var count = 0;
        $('.titulo').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });
});
</script>

<s:action name="mostraTotal" namespace="/associado/titulo" executeResult="true" />

<div class="tableHeaderCorner"></div>
<div id="titulosTableContainer" class="tableContainer">
    <table id="titulosTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="20%" class="tableFirstCol">Efetuado em</td>
            <td width="20%">Vencimento</td>
            <td width="20%">Status</td>
            <td width="20%">Tipo</td>
            <td width="20%" class="tableLastCol">Valor</td>
        </tr>

        <jsp:useBean id="tituloList" scope="request" type="List<Titulo>" />
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;
        
        for (Titulo titulo : tituloList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>

        <tr class="titulo tableRecord"<%= display %>>
            <td width="20%" class="tableFirstCol"><%= sdf.format(titulo.getDataCriacao()) %></td>
            <td width="20%"><%= sdf.format(titulo.getDataVencimento()) %></td>
            <td width="20%"><%= titulo.getStatus() %></td>
            <td width="20%"><%= titulo.getTipo() %></td>
            <td width="20%" class="tableLastCol"><%= CurrencyUtils.formatToReais(titulo.getValor()) %></td>
        </tr>

        <%
            count++;
        }
        %>

        <!-- footer da tabela da lista de msges -->
        <tr>
            <td colspan="5" align="center" class="tableLastCol">
                <a id="maisTitulos" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>

    </table>
</div>