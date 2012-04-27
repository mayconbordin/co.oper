<%@page import="com.cooper.model.entity.Associado"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisAssociados').click(function() {
        var count = 0;
        $('.associado').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });

    $('.associado').click(function() {
        var id = '#' + $(this).attr('id').replace('associado', 'associadoDialog');
        $(id).dialog('open');
    });

    $('#novoAssociado a').click(function(){
       $('#novaContaAssociadoDialog').dialog('open');
       return false;
    });
});
</script>

<div id="novoAssociado">
    <a class="buttonLink" href="" title="Criar conta para Associado">Criar Conta p/ Associado</a>
</div>
<sj:dialog
    id="novaContaAssociadoDialog"
    autoOpen="false"
    modal="true"
    title="Criar conta para Associado"
    width="500"
    buttons="{
        'Cancelar':function() { $(this).dialog('close'); },
        'Salvar':function() { criarConta() }
    }"
>
    <div id="criarContaForm" class="detailedDialog">
    <p class="associadoContainer">
        <span class="label">Associado:</span>
        <span class="value">
            <sj:autocompleter list="associadoList" listKey="codigo" listValue="nome" cssClass="codigo" />
        </span>
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
    id="associadoSalvoDialog"
    autoOpen="false"
    modal="true"
    title="Associado adicionado"
    width="500"
    buttons="{'Fechar':function() { $(this).dialog('close'); }}"
>
    Associado adicionado com sucesso!
</sj:dialog>

<div class="tableHeaderCorner"></div>
<div id="associadosTableContainer" class="tableContainer">
    <table id="associadosTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="10%" class="tableFirstCol">Código</td>
            <td width="30%">Nome</td>
            <td width="20%">CPF/CNPJ</td>
            <td width="20%">Data Cadastro</td>
            <td width="20%" class="tableLastCol">Tem conta</td>
        </tr>

        <jsp:useBean id="associadoList" scope="request" type="List<Associado>" />
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;

        for (Associado associado : associadoList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>

        <tr id="associado<%= associado.getCodigo() %>" class="associado tableRecord"<%= display %> title="Clique para ver mais detalhes">
            <td width="10%" class="tableFirstCol"><%= associado.getCodigo() %></td>
            <td width="30%"><%= associado.getNome() %></td>
            <td width="20%"><%= associado.getCpfcnpj() %></td>
            <td width="20%"><%= sdf.format(associado.getDataCadastro()) %></td>
            <td width="20%" class="tableLastCol"><%= (associado.isTemConta()) ? "Sim" : "Não" %></td>
        </tr>


        <s:set name="id"><%= associado.getCodigo() %></s:set>
        <s:set name="nome"><%= associado.getNome() %></s:set>
        <s:url id="usuarioURL" action="getUsuarioInfo" namespace="/funcionario/usuario">
            <s:param name="id" value="%{id}" />
        </s:url>
        <sj:dialog
            id="associadoDialog%{#id}"
            href="%{usuarioURL}"
            autoOpen="false"
            modal="true"
            title="Associado %{#nome}"
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
                <a id="maisAssociados" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>

    </table>
</div>