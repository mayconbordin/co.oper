<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<!-- Action URLs -->
<s:url id="salvarRelatorioVisitaURL" action="salvarRelatorioVisita" namespace="/tecnico/visita" />

<script type="text/javascript">
var loaderDiv = '<div id="loader"><img src="${root}/images/loader.gif" alt="loader" /></div>';

// Funções comuns
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

function addOnClickRelatoriVisita(e) {
    $(e).click(function() {
        var id = '#' + $(this).attr('id').replace('visita', 'visitaDialog');
        $(id).dialog('open');
        return false;
    }).children().click(function() {
        if ($(this).children().attr("class") == 'editar') {
            var id = '#' + $(this).parent().attr('id').replace('visita', 'editarDialog');
            $(id).dialog('open');
            return false;
        }
    });
}

function saveRelatorioVisita(d, f, t) {
    if (t == null) t = 'editar';
    var dataEl      = $(f).children('.dataContainer').children('.value').children('.data');
    var descricaoEl = $(f).children('.descricaoContainer').children('.value').children('.descricao');
    var associadoEl = $(f).children('.associadoContainer').children('.value').children('.associado');

    var data        = dataEl.val();
    var descricao   = descricaoEl.val();
    var associado   = associadoEl.val();
    var id          = $(f).children('.id').val();
    var tecnico     = $(f).children('.tecnico').val();
    
    var url = "<s:property value="#salvarRelatorioVisitaURL"/>";
    $(d).dialog('close');
    $('#loadingBar').dialog('open');
    $.post(url, { id:id, data:data, descricao:descricao, associado:associado, tecnico:tecnico }, function(data) {
        $('#loadingBar').dialog('close');
        $('#relatorioVisitaSalvoDialog').dialog('open');
    });
}

</script>

<sj:dialog
    id="loadingBar"
    autoOpen="false"
    modal="true"
    title="Carregando..."
    height="100"
    width="250"
>
    <div id="loader"><img src="${root}/images/loader.gif" alt="loader" /></div>
</sj:dialog>