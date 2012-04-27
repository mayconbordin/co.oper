<%@page import="com.cooper.model.entity.Tecnico"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisTecnicos').click(function() {
        var count = 0;
        $('.tecnico').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });

    $('.tecnico').click(function() {
        var id = '#' + $(this).attr('id').replace('tecnico', 'tecnicoDialog');
        $(id).dialog('open');
    });

    $('#novoTecnico a').click(function(){
       $('#novoTecnicoDialog').dialog('open');
       return false;
    });
});
</script>

<div id="novoTecnico">
    <a class="buttonLink" href="" title="Adicionar Técnico">+ Adicionar Técnico</a>
</div>
<sj:dialog
    id="novoTecnicoDialog"
    autoOpen="false"
    modal="true"
    title="Adicionar Técnico"
    width="500"
    buttons="{
        'Cancelar':function() { $(this).dialog('close'); },
        'Salvar':function() { addTecnico() }
    }"
>
    <div id="novoTecnicoForm" class="detailedDialog">
    <p class="nomeContainer">
        <span class="label">Nome:</span>
        <span class="value"><input type="text" class="nome" /></span>
    </p>
    <p class="emailContainer">
        <span class="label">E-mail:</span>
        <span class="value"><input type="text" class="email" /></span>
    </p>
    <p class="passwordContainer">
        <span class="label">Senha:</span>
        <span class="value"><input type="password" class="password" /></span>
    </p>
    </div>
</sj:dialog>

<sj:dialog
    id="tecnicoSalvoDialog"
    autoOpen="false"
    modal="true"
    title="Técnico adicionado"
    width="500"
    buttons="{'Fechar':function() { $(this).dialog('close'); }}"
>
    Técnico adicionado com sucesso!
</sj:dialog>

<div class="tableHeaderCorner"></div>
<div id="tecnicosTableContainer" class="tableContainer">
    <table id="tecnicosTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="30%" class="tableFirstCol">Código</td>
            <td width="70%">Nome</td>
        </tr>

        <jsp:useBean id="tecnicoList" scope="request" type="List<Tecnico>" />
        <%
        int count = 0;

        for (Tecnico tecnico : tecnicoList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>

        <tr id="tecnico<%= tecnico.getId() %>" class="tecnico tableRecord"<%= display %> title="Clique para ver mais detalhes">
            <td width="30%" class="tableFirstCol"><%= tecnico.getId() %></td>
            <td width="70%"><%= tecnico.getNome() %></td>
        </tr>


        <s:set name="id"><%= tecnico.getId() %></s:set>
        <s:set name="nome"><%= tecnico.getNome() %></s:set>
        <s:url id="usuarioURL" action="getUsuarioInfo" namespace="/funcionario/usuario">
            <s:param name="id" value="%{id}" />
        </s:url>
        <sj:dialog
            id="tecnicoDialog%{#id}"
            href="%{usuarioURL}"
            autoOpen="false"
            modal="true"
            title="Técnico %{#nome}"
            width="500"
            buttons="{'Fechar':function() { $(this).dialog('close'); }}"
        >
            <div id="loader"><img src="${root}/images/loader.gif" alt="loader" /></div>
        </sj:dialog>

        <%
            count++;
        }
        %>

        <!-- footer da tabela da lista de msges -->
        <tr>
            <td colspan="5" align="center" class="tableLastCol">
                <a id="maisTecnicos" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>

    </table>
</div>