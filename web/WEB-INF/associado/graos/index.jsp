<%@page import="com.cooper.model.entity.Graos"%>
<%@page import="com.strutstool.currency.CurrencyUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisGraos').click(function() {
        var count = 0;
        $('.graos').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });

    $('.graos').click(function() {
        var id = '#' + $(this).attr('id').replace('romaneio', 'graos');
        $(id).dialog('open');
    });
});
</script>

<div class="tableHeaderCorner"></div>
<div id="graosTableContainer" class="tableContainer">
    <table id="graosTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="20%" class="tableFirstCol">Romaneio</td>
            <td width="20%">Data</td>
            <td width="20%">Produto</td>
            <td width="20%">Peso/Qtde.</td>
            <td width="20%" class="tableLastCol">Status</td>
        </tr>

        <jsp:useBean id="graosList" scope="request" type="List<Graos>" />
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;

        for (Graos graos : graosList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>

        <tr id="romaneio<%= graos.getRomaneio() %>" class="graos tableRecord"<%= display %> title="Clique para ver mais detalhes">
            <td width="20%" class="tableFirstCol"><%= graos.getRomaneio() %></td>
            <td width="20%"><%= sdf.format(graos.getData()) %></td>
            <td width="20%"><%= graos.getProduto() %></td>
            <td width="20%"><%= graos.getPesoQtde() %></td>
            <td width="20%" class="tableLastCol"><%= graos.getStatus() %></td>
        </tr>

        
        <s:set name="id"><%= graos.getRomaneio() %></s:set>
        <sj:dialog
            id="graos%{#id}"
            autoOpen="false"
            modal="true"
            title="Romaneio %{#id}"
            width="500"
            buttons="{'Fechar':function() { $(this).dialog('close'); }}"
        >
            <div class="detailedDialog">
            <p>
                <span class="label">Romaneio:</span>
                <span class="value"><%= graos.getRomaneio() %></span>
            </p>
            <p>
                <span class="label">Data:</span>
                <span class="value"><%= sdf.format(graos.getData()) %></span>
            </p>
            <p>
                <span class="label">Descontos:</span>
                <span class="value"><%= CurrencyUtils.formatToReais(graos.getDescontos()) %></span>
            </p>
            <p>
                <span class="label">Descrição:</span>
                <span class="value"><%= graos.getDescricao() %></span>
            </p>
            <p>
                <span class="label">Motorista:</span>
                <span class="value"><%= graos.getMotoristaCaminhao() %></span>
            </p>
            <p>
                <span class="label">Placa do Caminhão:</span>
                <span class="value"><%= graos.getPlacaCaminhao() %></span>
            </p>
            <p>
                <span class="label">Produto:</span>
                <span class="value"><%= graos.getProduto() %></span>
            </p>
            <p>
                <span class="label">Peso/Qtde.:</span>
                <span class="value"><%= graos.getPesoQtde() %></span>
            </p>
            <p>
                <span class="label">Status:</span>
                <span class="value"><%= graos.getStatus() %></span>
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
                <a id="maisGraos" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>

    </table>
</div>