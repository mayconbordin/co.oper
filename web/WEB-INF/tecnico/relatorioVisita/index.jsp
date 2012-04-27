<%@page import="com.cooper.model.entity.RelatorioVisita"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisVisitas').click(function() {
        var count = 0;
        $('.visitas').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });

    addOnClickRelatoriVisita('#visitasTable .visitas');
    
    $('#novoRelatorioVisita a').click(function() {
        $('#novoRelatorioVisitaDialog').dialog('open');
        return false;
    });
});
</script>

<div id="novoRelatorioVisita">
    <a class="buttonLink" href="" title="Novo Relatório de Visita">+ Novo Relatório de Visita</a>
</div>
<sj:dialog
    id="novoRelatorioVisitaDialog"
    autoOpen="false"
    modal="true"
    title="Novo Relatório de Visita"
    width="600"
    buttons="{
        'Cancelar':function() { $(this).dialog('close'); },
        'Salvar':function() { saveRelatorioVisita('#novoRelatorioVisitaDialog', '#novoRelatorioVisitaForm', 'novo'); }
    }"
>
    <div id="novoRelatorioVisitaForm" class="detailedDialog">
        <input class="id" type="hidden" value="-1" />
        <input class="tecnico" type="hidden" value="${tecnicoId}" />
        <p class="dataContainer">
            <span class="label">Data:</span>
            <span class="value"><sj:datepicker cssClass="data" displayFormat="dd/mm/yy" /></span>
        </p>
        <p class="descricaoContainer">
            <span class="label">Descição:</span>
            <span class="value"><textarea class="descricao"></textarea></span>
        </p>
        <p class="associadoContainer">
            <span class="label">Associado:</span>
            <span class="value">
                <sj:autocompleter
                    list="associadoList"
                    listKey="codigo"
                    listValue="nome"
                    cssClass="associado"
                />
            </span>
        </p>
    </div>
</sj:dialog>

<sj:dialog
    id="relatorioVisitaSalvoDialog"
    autoOpen="false"
    modal="true"
    title="Relatório de Visita salvo"
    width="500"
    buttons="{'Fechar':function() {
        $(this).dialog('close');
        window.location.href=window.location.href;
    }}"
>
    Relatório de visita salvo com sucesso!
</sj:dialog>

<div class="tableHeaderCorner"></div>
<div id="visitasTableContainer" class="tableContainer">
    <table id="visitasTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="25%" class="tableFirstCol">Data</td>
            <td width="45%">Descrição</td>
            <td width="20%">Associado</td>
            <td width="15%" class="tableLastCol" align="center">Opções</td>
        </tr>

        <jsp:useBean id="relatorioVisitaList" scope="request" type="List<RelatorioVisita>" />
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;

        for (RelatorioVisita visita : relatorioVisitaList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>

        <tr id="visita<%= visita.getId() %>" class="visitas tableRecord"<%= display %> title="Clique para ver mais detalhes">
            <td width="25%" class="tableFirstCol"><%= sdf.format(visita.getData()) %></td>
            <td width="45%"><%= visita.getDescricao() %></td>
            <td width="20%"><%= visita.getAssociado().getNome() %></td>

            <td width="15%" class="options" align="center"><a class="editar" href="" title="Avalie esta visita técnica">Editar</a></td>
        </tr>

        <s:set name="id"><%= visita.getId() %></s:set>
        <s:set name="data"><%= sdf.format(visita.getData()) %></s:set>
        <s:set name="associado"><%= visita.getAssociado().getCodigo() %></s:set>
        <sj:dialog
            id="visitaDialog%{#id}"
            autoOpen="false"
            modal="true"
            title="Relatório da Visita Técnica do dia %{#data}"
            width="500"
            buttons="{'Fechar':function() { $(this).dialog('close'); }}"
        >
        <div class="detailedDialog">
            <p>
                <span class="label">Data:</span>
                <span class="value"><%= sdf.format(visita.getData()) %></span>
            </p>
            <p>
                <span class="label">Descrição:</span>
                <span class="value"><%= visita.getDescricao() %></span>
            </p>
            <p>
                <span class="label">Associado:</span>
                <span class="value"><%= visita.getAssociado().getNome() %></span>
            </p>
            <p>
                <span class="label">Comentário do Associado:</span>
                <% String comentario = (visita.getComentario() == null) ? "" : visita.getComentario(); %>
                <span class="value"><%= comentario %></span>
            </p>
            <p>
                <span class="label">Nota atribuída pelo Associado (0-10):</span>
                <% String nota = (visita.getNota() == null) ? "" : String.valueOf(visita.getNota()); %>
                <span class="value"><%= nota %></span>
            </p>
        </div>
        </sj:dialog>

        <sj:dialog
            id="editarDialog%{#id}"
            autoOpen="false"
            modal="true"
            title="Editar a Visita Técnica do dia %{#data}"
            width="600"
            buttons="{
                'Cancelar':function() { $(this).dialog('close'); },
                'Salvar':function() { saveRelatorioVisita('#editarDialog%{#id}', '#editarRelatorioVisitaForm%{#id}'); }
            }"
        >
            <div id="editarRelatorioVisitaForm<%= visita.getId() %>" class="detailedDialog">
                <input class="id" type="hidden" value="<%= visita.getId() %>" />
                <input class="tecnico" type="hidden" value="${tecnicoId}" />
                <p class="dataContainer">
                    <span class="label">Data:</span>
                    <span class="value"><sj:datepicker cssClass="data" displayFormat="dd/mm/yy" value="%{#data}" /></span>
                </p>
                <p class="descricaoContainer">
                    <span class="label">Descição:</span>
                    <span class="value"><textarea class="descricao"><%= visita.getDescricao() %></textarea></span>
                </p>
                <p class="associadoContainer">
                    <span class="label">Associado:</span>
                    <span class="value">
                        <sj:autocompleter
                            list="associadoList"
                            listKey="codigo"
                            listValue="nome"
                            cssClass="associado"
                            value="%{#associado}"
                        />
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
                <a id="maisVisitas" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>

    </table>
</div>