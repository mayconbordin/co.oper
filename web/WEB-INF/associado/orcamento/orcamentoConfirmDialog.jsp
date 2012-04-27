<%@page import="com.cooper.model.entity.ProdutoDoOrcamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cooper.model.utils.ProdutoDoOrcamentoList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<s:url id="saveOrcamentoURL" action="saveOrcamento" namespace="/associado/orcamento" />

<script type="text/javascript">
$(document).ready(function() {
    $('#maisProdutosDoOrcamento').click(function() {
        var count = 0;
        $('#produtoDoOrcamentoTableContainer .produto').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 10);
        });
        return false;
    });

    $('#produtoDoOrcamentoTableContainer .addToOrcamento').each(function(){
        $(this).click(function(){
            addToOrcamento(this);
            return false;
        });
    });

    $('#produtoDoOrcamentoTableContainer .remover').each(function(){
        $(this).click(function(){
            removeOfOrcamento(this);
            return false;
        });
    });

    $('#produtoDoOrcamentoButtons .enviar').click(function() {
        enviarOrcamento(this);
    });
    
    $('#produtoDoOrcamentoButtons .cancelar').click(function() {
        $('#orcamentoConfirmDialog').dialog('close');
    });
});
</script>

<sj:dialog
    id="orcamentoEnviadoDialog"
    autoOpen="false"
    modal="true"
    title="Orçamento Enviado"
    buttons="{'OK':function() { $(this).dialog('close'); }}"
>
    Orçamento Enviado!
</sj:dialog>

<div class="tableHeaderCorner"></div>
<div id="produtoDoOrcamentoTableContainer" class="tableContainer">
    <table id="produtoDoOrcamentoTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="15%" class="tableFirstCol">Código</td>
            <td width="35%">Descrição</td>
            <td width="15%">Qtde.</td>
            <td width="20%" class="tableLastCol" align="center">Opções</td>
        </tr>

        <jsp:useBean id="orcamento" scope="request" type="ProdutoDoOrcamentoList" />
        <%
        int count = 0;

        for (ProdutoDoOrcamento pdo : orcamento) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
            int codigo = pdo.getPrimaryKey().getProduto().getCodigo();
        %>

        <tr class="produto tableRecord ref<%= codigo %>" id="produto<%= codigo + "\"" + display %>>
            <td width="15%" class="tableFirstCol"><%= pdo.getPrimaryKey().getProduto().getPreco() %></td>
            <%
            String desc =  pdo.getPrimaryKey().getProduto().getDescricao();
            int limiteConteudo = (desc.length() > 50) ? 50 : desc.length();
            String cont = (desc.length() > 50) ? "..." : "";
            %>
            <td width="35%"><%= desc.substring(0, limiteConteudo) + cont %></td>
            <td width="15%" class="tableLastCol"><%= pdo.getQuantidade() %></td>
            <td width="25%" class="options" align="center"><a class="addToOrcamento adicionado" href="" title="Adicione este produto ao orçamento">Adicionado</a> <a class="remover" href="" title="">Remover</a></td>
        </tr>

        <%
            count++;
        }
        %>

        <!-- footer da tabela da lista de msges -->
        <tr>
            <td colspan="5" align="center" class="tableLastCol">
                <a id="maisProdutosDoOrcamento" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>
    </table>
</div>

<div id="produtoDoOrcamentoButtons" class="buttons">
    <div class="right">
        <input class="cancelar" type="submit" value="Cancelar" />
        <input class="enviar" type="submit" value="Enviar »" />
    </div>
</div>