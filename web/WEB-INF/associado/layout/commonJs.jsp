<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<!-- Action URLs -->
<s:url id="addProdutoURL" action="addProduto" namespace="/associado/orcamento" />
<s:url id="removeProdutoURL" action="removeProduto" namespace="/associado/orcamento" />
<s:url id="orcamentoBoxURL" action="orcamentoBox" namespace="/associado/orcamento" />
<s:url id="saveOrcamentoURL" action="saveOrcamento" namespace="/associado/orcamento" />
<s:url id="removeOrcamentoURL" action="removeOrcamento" namespace="/associado/orcamento" />

<s:url id="marcarComoLidaURL" action="marcarComoLida" namespace="/associado/mensagem" />
<s:url id="addURL" action="add" namespace="/associado/mensagem" />
<s:url id="countNaoLidasByUsuarioURL" action="countNaoLidasByUsuario" namespace="/associado/mensagem" />

<script type="text/javascript">
var loaderDiv = '<div id="loader"><img src="${root}/images/loader.gif" alt="loader" /></div>';

// Funções comuns
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

// Funções de Conta
function loadContaIfInside() {
    var url = window.location.href;
    if (url.indexOf('/associado/conta') != -1) {
        $('#accountBox .minhaConta').addClass('selecionado');
    }
}

// Funções de orçamento
function updateOrcamentoBox(plus) {
    var box = $("#orcamentoBox .orcamento");

    if ($("#orcamentoBox").css('visibility') == 'hidden') {
        $("#orcamentoBox").css('visibility','visible').hide().fadeIn('slow');
    }

    if (plus != '') {
        var num = box.children('span');
        var value = parseInt(num.html()) + plus;
        num.html(value);
    }
}

function removeOfOrcamento(e) {
    var url = "<s:property value="#removeProdutoURL"/>";
    var id = $(e).parent().parent().attr('id').replace('produto', '');

    var loader = $(loaderDiv);
    $(e).parent().append(loader);
    $.post(url, { produtoId: id }, function() {
        loader.remove();
        var num = $("#orcamentoBox .orcamento").children('span');
        var value = parseInt(num.html()) - 1;
        num.html(value);

        if (value < 1) {
            num.html(0);
            $("#orcamentoBox").fadeOut('slow', function() {
                $(this).css('display', 'inline-block');
                $(this).css('visibility','hidden');
            });
        }

        $('.ref' + id).children('.options').children('.remover').each(function(){
            $(this).parent().children('.adicionado').html('Adicionar ao Orçamento');
            $(this).parent().children('.adicionado').removeClass('adicionado');
            $(this).remove();
        });
    });
}

function addToOrcamento(e) {
    if (!$(e).hasClass("adicionado")) {
        var qtde = $('<div class="qtde">Quantidade: <input class="input" type="text" /> <input class="submit" type="submit" value="Ok" /> <input class="cancel" type="submit" value="Cancelar" /></div>');
        $(e).parent().append(qtde);
        qtde.fadeIn("slow");

        qtde.children('.submit').click(function() {
            var qtdeVal = $(this).parent().children(".input").val();
            if (qtdeVal == '' || !isNumber(qtdeVal)) {
                alert("A quantidade precisar ser um número.");
            } else {
                $(this).parent().fadeOut("slow", function() {
                    $(this).remove();

                    var loader = $(loaderDiv);
                    $(e).parent().append(loader);

                    var url = "<s:property value="#addProdutoURL"/>";
                    var id = $(e).parent().parent().attr('id').replace('produto', '');
                    $.post(url, { produtoId: id, qtde: qtdeVal }, function() {
                        updateOrcamentoBox(1);

                        var remover = $('<a class="remover" href="" title="">Remover</a>');
                        remover.click(function(){removeOfOrcamento(this);return false;});

                        $(e).hide();
                        loader.fadeOut("slow", function() {
                            $(e).show();
                            $(e).html("Adicionado");
                            $(e).addClass("adicionado");
                            $(e).parent().append(' ');
                            $(e).parent().append(remover);
                        });
                    });
                });
            }
        });

        qtde.children('.cancel').click(function() {
            $(this).parent().fadeOut("slow", function() {
                $(this).remove();
            });
        });
    }
}

function enviarOrcamento(e) {
    var url = "<s:property value="#saveOrcamentoURL"/>";
    $('#loadingBar').dialog('open');
    $.post(url, function() {
        $('#loadingBar').dialog('close');
        $("#orcamentoBox").fadeOut('slow', function() {
            $(this).css('display', 'inline-block');
            $(this).css('visibility','hidden');
        });

        $('.table .remover').each(function() {
            removeOfOrcamento(this);
        });

        $('#orcamentoConfirmDialog').dialog('close');
        $('#orcamentoDialog').dialog('close');
        $('#orcamentoEnviadoDialog').dialog('open');
    });
}

function removerOrcamento() {
    var url = "<s:property value="#saveOrcamentoURL"/>";
    $('#loadingBar').dialog('open');
    $.post(url, function() {
        $('#loadingBar').dialog('close');
        $("#orcamentoBox").fadeOut('slow', function() {
            $(this).css('display', 'inline-block');
            $(this).css('visibility','hidden');
        });

        $('.table .remover').each(function() {
            removeOfOrcamento(this);
        });

        $('#orcamentoConfirmDialog').dialog('close');
        $('#orcamentoDialog').dialog('close');
        $('#orcamentoRemovidoDialog').dialog('open');
    });
}

// Funções de mensagens
function carregarMensagensNaoLidas(update) {
    var url = "<s:property value="#countNaoLidasByUsuarioURL"/>";
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
    var textArea = $(e).parent().children('.messageTextArea');
    var message = textArea.val();
    textArea.val("");

    if (message == '') {
        alert("Você precisa escrever uma mensagem!");
    } else {
        var url = "<s:property value="#addURL"/>";
        $('#loadingBar').dialog('open');
        $.post(url, { 'conteudo': message }, function(data) {
            $('#loadingBar').dialog('close');
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
        });
    }
}

//
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