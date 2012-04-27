<%@page import="java.text.SimpleDateFormat"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@page import="com.cooper.model.entity.RelatorioVisita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>

<compress:html enabled="true"
               compressJavaScript="true"
               jsCompressor="closure"
               removeComments="true"
               compressCss="true"
               removeIntertagSpaces="true"
               >
<jsp:useBean id="relatorioVisita" scope="request" type="RelatorioVisita" />
<% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); %>

<tr id="visita<%= relatorioVisita.getId() %>" class="visitas tableRecord" title="Clique para ver mais detalhes">
    <td width="25%" class="tableFirstCol"><%= sdf.format(relatorioVisita.getData()) %></td>
    <td width="45%"><%= relatorioVisita.getDescricao() %></td>
    <td width="20%"><%= relatorioVisita.getAssociado().getNome() %></td>

    <td width="15%" class="options" align="center"><a class="editar" href="" title="Avalie esta visita técnica">Editar</a></td>
</tr>
</compress:html>