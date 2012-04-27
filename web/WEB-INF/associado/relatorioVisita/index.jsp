<%@page import="com.cooper.model.entity.RelatorioVisita"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
$(document).ready(function() {
    $('#maisVisitas').click(function() {
        var count = 0;
        $('.visitas').each(function() {
            if ($(this).css('display') == 'none') {
                $(this).fadeIn(1000);
                count++;
            }

            return (count < 5);
        });
        return false;
    });

    $('#visitasTable .visitas').click(function() {
        var id = '#' + $(this).attr('id').replace('visita', 'visitaDialog');
        $(id).dialog('open');
        return false;
    }).children().click(function() {
        if ($(this).children().attr("class") == 'avaliar') {
            var id = '#' + $(this).parent().attr('id').replace('visita', 'avaliarDialog');
            $(id).dialog('open');
            return false;
        }
    });
});
</script>

<sj:dialog
    id="avaliacaoEnviadaDialog"
    autoOpen="false"
    modal="true"
    title="Avaliação Enviada!"
    buttons="{'OK':function() { $(this).dialog('close'); }}"
>
    Avaliação Enviada!
</sj:dialog>

<div class="tableHeaderCorner"></div>
<div id="visitasTableContainer" class="tableContainer">
    <table id="visitasTable" class="table" cellpadding="0" cellspacing="0">
        <tr class="tableHeader">
            <td width="25%" class="tableFirstCol">Data</td>
            <td width="45%">Descrição</td>
            <td width="20%">Técnico</td>
            <td width="15%" class="tableLastCol">Opções</td>
        </tr>

        <jsp:useBean id="relatorioVisitaList" scope="request" type="List<RelatorioVisita>" />
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;

        for (RelatorioVisita visita : relatorioVisitaList) {
            String display = (count > 8) ? " style=\"display:none;\"" : "";
        %>

        <tr id="visita<%= visita.getId() %>" class="visitas tableRecord"<%= display %> title="Clique para ver mais detalhes">
            <td width="25%" class="tableFirstCol"><%= sdf.format(visita.getData()) %></td>
            <td width="45%"><%= visita.getDescricao() %></td>
            <td width="20%"><%= visita.getTecnico().getNome() %></td>
            
            <% if (visita.getComentario() == null) { %>
            <td width="15%" class="options" align="center"><a class="avaliar" href="" title="Avalie esta visita técnica">Avaliar</a></td>
            <% } else { %>
            <td width="15%" class="options" align="center"><a class="avaliado" href="" title="Visita técnica avaliada">Avaliada</a></td>
            <% } %>
        </tr>
       
        <s:set name="id"><%= visita.getId() %></s:set>
        <s:set name="data"><%= sdf.format(visita.getData()) %></s:set>
        <sj:dialog
            id="visitaDialog%{#id}"
            autoOpen="false"
            modal="true"
            title="Relatório da Visita Técnica do dia %{#data}"
            width="500"
            buttons="{'Fechar':function() { $(this).dialog('close'); }}"
        >
        <div class="detailedDialog">
            <p>
                <span class="label">Data:</span>
                <span class="value"><%= sdf.format(visita.getData()) %></span>
            </p>
            <p>
                <span class="label">Descrição:</span>
                <span class="value"><%= visita.getDescricao() %></span>
            </p>
            <p>
                <span class="label">Técnico:</span>
                <span class="value"><%= visita.getTecnico().getNome() %></span>
            </p>
            <p>
                <span class="label">Seu comentário:</span>
                <% String comentario = (visita.getComentario() == null) ? "" : visita.getComentario(); %>
                <span class="value"><%= comentario %></span>
            </p>
            <p>
                <span class="label">Nota atribuída por você (0-10):</span>
                <% String nota = (visita.getNota() == null) ? "" : String.valueOf(visita.getNota()); %>
                <span class="value"><%= nota %></span>
            </p>
        </div>
        </sj:dialog>

        <s:url id="saveAvaliacaoURL" action="saveAvaliacao" namespace="/associado/visita">
            <s:param name="relatorioVisitaId" value="%{#id}" />
        </s:url>
        <sj:dialog
            id="avaliarDialog%{#id}"
            autoOpen="false"
            modal="true"
            title="Avalie a Visita Técnica do dia %{#data}"
            width="600"
            buttons="{
                'Fechar':function() { $(this).dialog('close'); },
                'Enviar':function(e) {
                    var comentario = $('#avaliacao%{#id}')
                        .children('.comentarioContainer')
                        .children('.value').children('.comentario').val();
                        
                    var nota = $('#avaliacao%{#id}')
                        .children('.notaContainer')
                        .children('.value').children('.nota').val();

                    var url = '%{#saveAvaliacaoURL}';

                    if (comentario == '' || nota == -1) {
                        alert('Preencha todos os campos!');
                    } else {
                        var dialog = $(this);
                        $.post(url, {comentario:comentario, nota:nota}, function() {
                            var linkAvaliadr = $('#visita%{#id}').children('.options').children('.avaliar');
                            linkAvaliadr.html('Avaliada');
                            linkAvaliadr.removeClass('avaliar');
                            linkAvaliadr.addClass('avaliado');
                            linkAvaliadr.attr('title', 'Visita técnica avaliada');
                            
                            dialog.dialog('close');
                            $('#avaliacaoEnviadaDialog').dialog('open');
                        });
                    }
                }
            }"
        >
            <div class="detailedDialog" id="avaliacao<%= visita.getId() %>">
                <p class="comentarioContainer">
                    <span class="label">Comentário:</span>
                    <span class="value"><s:textarea cssClass="comentario" /></span>
                </p>
                <p class="notaContainer">
                    <span class="label">Nota:</span>
                    <span class="value">
                        <s:select
                            headerKey="-1" headerValue="Dê sua nota"
                            list="#{'0':'0','1':'1', '2':'2', '3':'3', '4':'4', '5':'5', '6':'6', '7':'7', '8':'8', '9':'9', '10':'10'}"
                            cssClass="nota"
                            value="-1" />
                    </span>
                </p>
            </div>
        </sj:dialog>

        <%
            count++;
        }
        %>

        <!-- footer da tabela da lista de msges -->
        <tr>
            <td colspan="5" align="center" class="tableLastCol">
                <a id="maisVisitas" class="tableMoreLink" href="">mais ...</a>
            </td>
        </tr>

    </table>
</div>