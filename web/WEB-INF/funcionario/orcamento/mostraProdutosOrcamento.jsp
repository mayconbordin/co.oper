<%@page trimDirectiveWhitespaces="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Set"%>
<%@page import="com.cooper.model.entity.ProdutoDoOrcamento"%>
<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>

<compress:html enabled="true"
               compressJavaScript="true"
               jsCompressor="closure"
               removeComments="true"
               compressCss="true"
               removeIntertagSpaces="true"
               >
<div class="tableHeaderCorner"></div>
<div id="produtosDoOrcamentoTableContainer" class="tableContainer">
    <table id="produtosDoOrcamentoTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="33%" class="tableFirstCol">Cod. Produto</td>
            <td width="33%">Desc. Produto</td>
            <td width="33%" class="tableLastCol">Qtde.</td>
        </tr>
        <jsp:useBean id="produtoDoOrcamentoList" scope="request" type="Set<ProdutoDoOrcamento>" />
        <%
        for (ProdutoDoOrcamento pdo : produtoDoOrcamentoList) {
        %>
            <tr class="produtoDoOrcamento tableRecord" id="produtoDoOrcamento<%= pdo.getPrimaryKey().getProduto().getCodigo() + "\"" %>>
                <td width="33%"><%= pdo.getPrimaryKey().getProduto().getCodigo() %></td>
                <td class="conteudo" width="33%"><%= pdo.getPrimaryKey().getProduto().getDescricao() %></td>
                <td width="33%"><%= pdo.getQuantidade() %></td>
            </tr>
        <%
        }
        %>
        <tr>
            <td colspan="3" align="center" class="tableLastCol">
                <a id="maisOrcamentos" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>
    </table>
</div>
</compress:html>