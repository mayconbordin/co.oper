<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cooper.model.entity.Orcamento"%>
<%@page import="com.cooper.model.entity.ProdutoDoOrcamento"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<jsp:useBean id="orcamento" scope="request" type="Orcamento" />
<% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); %>
<h3 id="orcamentoTitle">Orçamento do dia <span class="data"><%= sdf.format(orcamento.getData()) %></span></h3>

<div class="tableHeaderCorner"></div>
<div id="produtosDoOrcamentoTableContainer" class="tableContainer">
    <table id="produtosDoOrcamentoTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="33%" class="tableFirstCol">Cod. Produto</td>
            <td width="33%">Desc. Produto</td>
            <td width="33%" class="tableLastCol">Qtde.</td>
        </tr>

        <jsp:useBean id="produtoDoOrcamentoList" scope="request" type="List<ProdutoDoOrcamento>" />
        <%

        int count = 0;
        for (ProdutoDoOrcamento pdo : produtoDoOrcamentoList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>
            <tr class="produtoDoOrcamento tableRecord" id="produtoDoOrcamento<%= pdo.getPrimaryKey().getProduto().getCodigo() + "\"" + display %>>
                <td width="33%"><%= pdo.getPrimaryKey().getProduto().getCodigo() %></td>

                <%
                String desc = pdo.getPrimaryKey().getProduto().getDescricao();
                if (desc == null) desc = "...";
                int limiteConteudo = (desc.length() > 100) ? 100 : desc.length();
                String cont = (desc.length() > 100) ? "..." : "";
                %>
                <td class="conteudo" width="33%"><%= desc.substring(0, limiteConteudo) + cont %></td>
                <td class="conteudoFull" width="33%">
                    <div><p><%= desc %></p></div>
                </td>

                <td width="33%"><%= pdo.getQuantidade() %></td>
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