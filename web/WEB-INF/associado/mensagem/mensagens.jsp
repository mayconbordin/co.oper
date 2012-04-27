<%@page import="com.cooper.model.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cooper.model.entity.Mensagem"%>

<jsp:useBean id="usuarioId" scope="request" type="String" />
<jsp:useBean id="mensagemList" scope="request" type="List<Mensagem>" />
<%

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

int count = 0;
for (Mensagem msg : mensagemList) {
    String usuario = "";
    if (usuarioId.equals(msg.getEnviadoPor()) && msg.getEnviadoPorFonte() == Usuario.NIVEL_ASSOCIADO) {
        usuario = "voc�";
    } else {
        usuario = "Cooperativa";
    }

    String display = (count > 8) ? " style=\"display:none;\"" : "";
    if (msg.isDestinoLeu() || (msg.isFonteLeu() && msg.getEnviadoPor().equals(usuarioId))) {
%>
    <tr class="msg tableRecord" id="msg<%= msg.getId() + "\"" + display %>>
<% } else { %>
    <tr class="msg nlidamsg tableRecord" id="msg<%= msg.getId() + "\"" + display %>>
<% } %>
        <td width="25%">
            Enviada por <%= usuario %>
            <p class="data">em <%= sdf.format(msg.getData()) %></p>
        </td>

        <%
        int limiteConteudo = (msg.getConteudo().length() > 100) ? 100 : msg.getConteudo().length();
        String cont = (msg.getConteudo().length() > 100) ? "..." : "";
        %>
        <td class="conteudo" width="75%"><%= msg.getConteudo().substring(0, limiteConteudo) + cont %></td>

        <td class="conteudoFull" width="75%">
            <div><p><%= msg.getConteudo() %></p></div>
            <% if (!usuario.equals("voc�")) { %>
            <div class="responder">
                <h3>Responder</h3>
                <textarea class="messageTextArea" rows="10" cols="50"></textarea>

                <a class="fechar" href="" title="Fechar"><img src="${root}/images/cancel_send_msg.png" alt="cancelar" /></a>
                <a class="enviar" href="" title="Enviar mensagem"><img src="${root}/images/send_msg.png" alt="enviar" /></a>
            </div>
            <% } %>
        </td>
    </tr>
<%
    count++;
}
%>