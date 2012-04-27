<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<!-- Action URLs -->
<s:url id="marcarComoLidaURL" action="marcarComoLida" namespace="/funcionario/mensagem" />
<s:url id="addURL" action="add" namespace="/funcionario/mensagem" />
<s:url id="countNaoLidasByFuncionarioURL" action="countNaoLidasByFuncionario" namespace="/funcionario/mensagem" />
<s:url id="trocarSenhaURL" action="trocarSenha" namespace="/funcionario/usuario" />
<s:url id="addTecnicoURL" action="addTecnico" namespace="/funcionario/tecnico" />
<s:url id="criarContaURL" action="criarConta" namespace="/funcionario/associado" />

<script type="text/javascript">
var loaderDiv = '<div id="loader"><img src="${root}/images/loader.gif" alt="loader" /></div>';

// Funções comuns
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

// Funções de mensagens
function carregarMensagensNaoLidas(update) {
    var url = "<s:property value="#countNaoLidasByFuncionarioURL"/>";
    $.post(url, function(data) {
       if (data > 0) {
           var msg = (data == 1) ? " não lida" : " não lidas";
           $(".tableHeader .msgNaoLida").html("(" + data + msg + ")");
       } else if (update) {
            $(".tableHeader .msgNaoLida").html("");
       }
    });
}

function addEventoAbrirMensagem(e) {
    if ($(e).hasClass("nlidamsg")) {
        var id = $(e).attr("id").substring(3);
        var url = "<s:property value="#marcarComoLidaURL"/>";
        var msg = $(e);
        $.post(url, { mensagemId: id }, function() {
           msg.removeClass("nlidamsg");
           carregarMensagensNaoLidas(true);
        });
    }

    var full = $(e).children('.conteudoFull');

    if (full.css('display') == 'none') {
        full.show();
        $(e).children('.conteudo').hide();
    }
}

function addEventoFecharMensagem(e) {
    $(e).parent().parent().hide();
    $(e).parent().parent().parent().children('.conteudo').show();
}

function addEventoEnviarMensagem(e) {
    var textArea    = $(e).parent().children('.messageTextArea');
    var message     = textArea.val();
    var assocInput  = $(e).parent().children('.associadoId');
    var assocId     = assocInput.val();

    if (message == '') {
        alert("Você precisa escrever uma mensagem!");
        return false;
    }

    if (assocId == '') {
        alert("Um associado precisa receber a mensagem!");
        return false;
    }

    textArea.val("");
    assocInput.val("");
    var url = "<s:property value="#addURL"/>";
    $('#loadingBar').dialog('open');
    $.post(url, { 'conteudo': message, 'paraAssociadoId': assocId }, function(data) {
        var novaMsg = $(data);
        $(".tableHeader").after(novaMsg.hide().fadeIn(1000));
        novaMsg.css("display", "table-row");

        novaMsg.click(function() {
            addEventoAbrirMensagem(this);
            return false;
        });

        var respElem = novaMsg.children(".conteudoFull").children(".responder");

        respElem.children(".fechar").click(function() {
            addEventoFecharMensagem(this);
            return false;
        });

        respElem.children(".enviar").click(function() {
            addEventoEnviarMensagem(this);
            return false;
        });

        $('#loadingBar').dialog('close');
    });

    return true;
}

function openCloseNovaSenha(el) {
    var e;
    if ($(el).hasClass('novaSenha')) {
        e = $(el);
        el = $(e).parent().children('.trocarSenha').children('a');
    }
    else e = $(el).parent().parent().children('.novaSenha');
    
    if (e.css('display') == 'none') {
        e.fadeIn(1000);
        $(el).html('Cancelar');
    } else {
        e.fadeOut(1000);
        $(el).html('Trocar Senha');
    }
}

function trocarSenha(e) {
    var passEl  = $(e).parent().children('.password');
    var pass    = passEl.val();
    var id      = $(e).parent().parent().children('.id').val();

    if (id == '') return false;

    if (pass == '') {
        alert('A senha não pode ser vazia!');
        return false;
    }

    var url = "<s:property value="#trocarSenhaURL"/>";
    passEl.val('');
    $('#loadingBar').dialog('open');
    $.post(url, { id: id, senha: pass }, function(data) {
        $('#loadingBar').dialog('close');
        if (data == '0') {
            openCloseNovaSenha($(e).parent().parent());
            alert("Senha trocada com sucesso");
        } else {
            $(e).parent().parent().fadeOut(1000);
            alert("Ocorreu um erro ao trocar a senha");
        }
    });
}

function addTecnico() {
    var e       = $('#novoTecnicoForm');
    var nome    = $(e).children('.nomeContainer').children('.value').children('.nome').val();
    var email   = $(e).children('.emailContainer').children('.value').children('.email').val();
    var pass    = $(e).children('.passwordContainer').children('.value').children('.password').val();

    $('#loadingBar').dialog('open');
    var url = "<s:property value="#addTecnicoURL"/>";
    $.post(url, { nome:nome, email:email, pass:pass }, function(data) {
        $('#loadingBar').dialog('close');

        var tecnicoHtml = $(data);
        $(".tableHeader").after(tecnicoHtml.hide().fadeIn(1000));
        tecnicoHtml.css("display", "table-row");

        if (data == '1') {
            alert("Ocorreu um erro ao salvar o técnico.");
        } else {
            $('#novoTecnicoDialog').dialog('close');
            $('#tecnicoSalvoDialog').dialog('open');
        }
    });
}

function criarConta() {
    var e       = $('#criarContaForm');
    var codigo  = $(e).children('.associadoContainer').children('.value').children('.codigo').val();
    var email   = $(e).children('.emailContainer').children('.value').children('.email').val();
    var pass    = $(e).children('.passwordContainer').children('.value').children('.password').val();

    $('#loadingBar').dialog('open');
    var url = "<s:property value="#criarContaURL"/>";
    $.post(url, { codigo:codigo, email:email, pass:pass }, function(data) {
        $('#loadingBar').dialog('close');

        if (data == '1') {
            alert("O associado provavelmente já possui uma conta.");
        } else {
            $('#novaContaAssociadoDialog').dialog('close');
            $('#associadoSalvoDialog').dialog('open');
        }
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