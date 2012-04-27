<%@page trimDirectiveWhitespaces="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>

<compress:html enabled="true"
               compressJavaScript="true"
               jsCompressor="closure"
               removeComments="true"
               compressCss="true"
               removeIntertagSpaces="true"
               >

<tr id="tecnico${tecnico.id}" class="tecnico tableRecord" title="Clique para ver mais detalhes">
    <td width="30%" class="tableFirstCol">${tecnico.id}</td>
    <td width="70%">${tecnico.nome}</td>
</tr>


<s:set name="id">${tecnico.id}</s:set>
<s:set name="nome">${tecnico.nome}</s:set>
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
</compress:html>