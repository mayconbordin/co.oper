<%@page trimDirectiveWhitespaces="true" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cooper.model.entity.Mensagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>

<compress:html enabled="true"
               compressJavaScript="true"
               jsCompressor="closure"
               removeComments="true"
               compressCss="true"
               removeIntertagSpaces="true"
               >

<jsp:useBean id="usuarioId" scope="request" type="String" />
<jsp:useBean id="mensagem" scope="request" type="Mensagem" />

<%
    String usuario = (usuarioId.equals(mensagem.getEnviadoPor())) ? "você" : "Cooperativa";
%>

<% if (mensagem.isFonteLeu()) { %>
    <tr class="msg tableRecord" id="msg<%= mensagem.getId() %>">
<% } else { %>
    <tr class="msg nlidamsg tableRecord" id="msg<%= mensagem.getId() %>">
<% } %>
        <td width="25%">
            Enviada por <%= usuario %>
            <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); %>
            <p class="data">em <%= sdf.format(mensagem.getData()) %></p>
        </td>

        <%
        int limiteConteudo = (mensagem.getConteudo().length() > 100) ? 100 : mensagem.getConteudo().length();
        String cont = (mensagem.getConteudo().length() > 100) ? "..." : "";
        %>
        <td class="conteudo" width="75%"><%= mensagem.getConteudo().substring(0, limiteConteudo) + cont %></td>

        <td class="conteudoFull" width="75%">
            <div><p><%= mensagem.getConteudo() %></p></div>
            <% if (!usuario.equals("você")) { %>
            <div class="responder">
                <h3>Responder</h3>
                <textarea class="messageTextArea" rows="10" cols="50"></textarea>

                <a class="fechar" href="" title="Fechar"><img src="${root}/images/cancel_send_msg.png" alt="cancelar" /></a>

                <a class="enviar" href="" title="Enviar mensagem"><img src="${root}/images/send_msg.png" alt="enviar" /></a>
            </div>
            <% } %>
        </td>
    </tr>
</compress:html>