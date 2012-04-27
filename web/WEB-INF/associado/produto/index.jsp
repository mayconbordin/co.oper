<%@page import="com.cooper.model.utils.ProdutoNaLojaList"%>
<%@page import="com.cooper.model.utils.ProdutoDoOrcamentoList"%>
<%@page import="com.strutstool.currency.CurrencyUtils"%>
<%@page import="com.cooper.model.entity.Produto"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisProdutos').click(function() {
        var count = 0;
        $('#produtosTableContainer .produto').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 10);
        });
        return false;
    });

    $('#produtosTableContainer .addToOrcamento').each(function(){
        $(this).click(function(){
            addToOrcamento(this);
            return false;
        });
    });

    $('#produtosTableContainer .remover').each(function(){
        $(this).click(function(){
            removeOfOrcamento(this);
            return false;
        });
    });

    $('.searchBox .clear').click(function() {
        window.location.reload();
        return false;
    });
});
</script>

<div id="produtoSearch" class="searchBox">
    <s:form action="index" namespace="/associado/produto" cssClass="searchInput"><s:textfield type="text" name="searchInput" /> <input type="submit" value="Buscar" /> <input class="clear" type="submit" value="Limpar" /></s:form>
</div>

<div class="tableHeaderCorner"></div>
<div id="produtosTableContainer" class="tableContainer">
    <table id="produtosTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="15%" class="tableFirstCol">Código</td>
            <td width="35%">Descrição</td>
            <td width="15%" align="center">Qtde. Disp.</td>
            <td width="15%" align="center">Preço</td>
            <td width="25%" class="tableLastCol" align="center">Opções</td>
        </tr>

        <jsp:useBean id="produtoList" scope="request" type="List<Produto>" />
        <jsp:useBean id="orcamento" scope="request" type="ProdutoDoOrcamentoList" />
        <jsp:useBean id="produtoNaLojaList" scope="request" type="ProdutoNaLojaList" />
        <%
        int count = 0;
        for (Produto produto : produtoList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
            int codigo = produto.getCodigo();
        %>
            <tr class="produto tableRecord ref<%= codigo %>" id="produto<%= codigo + "\"" + display %>>
                <td width="15%"><%= produto.getCodigo() %></td>

                <%
                int limiteConteudo = (produto.getDescricao().length() > 50) ? 50 : produto.getDescricao().length();
                String cont = (produto.getDescricao().length() > 50) ? "..." : "";
                %>
                <td class="conteudo" width="35%"><%= produto.getDescricao().substring(0, limiteConteudo) + cont %></td>

                <td width="15%" align="center"><%= produtoNaLojaList.getQtdeTotalProduto(produto.getCodigo()) %></td>

                <td width="15%" align="center"><%= CurrencyUtils.formatToReais(produto.getPreco()) %></td>

                <%
                if (orcamento != null) {
                    if (orcamento.containsProduto(produto.getCodigo())) {
                %>
                    <td width="25%" class="options" align="center"><a class="addToOrcamento adicionado" href="" title="Adicione este produto ao orçamento">Adicionado</a> <a class="remover" href="" title="">Remover</a></td>
                <%
                    } else {
                %>
                    <td width="25%" class="options" align="center"><a class="addToOrcamento" href="" title="Adicione este produto ao orçamento">Adicionar ao Orçamento</a></td>
                <%
                    }
                }
                %>
            </tr>
        <%
            count++;
        }
        %>

        <!-- footer da tabela da lista de msges -->
        <tr>
            <td colspan="5" align="center" class="tableLastCol">
                <a id="maisProdutos" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>
    </table>
</div>