<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript" src="${root}/js/jquery/jquery.password_strength.js"></script>
<script type="text/javascript" src="${root}/js/emailCheck.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#password').password_strength();

        $('#minhaConta').submit(function() {
            var email = $('#email').val();
            if (email != '') {
                if (!emailCheck(email)) {
                    $('#email').parent().children('.info')
                        .html("<span style='color:red;'>Este email não é válido</span>");
                    return false;
                }
            }
        });
    });
</script>

<h1>Minha Conta</h1>

<div id="messages">
    <s:actionerror theme="jquery" />
    <s:actionmessage theme="jquery" />
</div>

<s:form id="minhaConta" action="save" namespace="/tecnico/conta">
    <p>
        <s:label key="label.nome" />
        <s:textfield name="tecnico.nome" disabled="true" />
        <span class="info"></span>
    </p>
    <p>
        <s:label key="label.senha" />
        <s:password name="senha" id="password" />
        <span class="info"></span>
    </p>
    <p>
        <s:label key="label.email" />
        <s:textfield name="email" id="email" />
        <span class="info"></span>
    </p>
    <p>
        <s:submit key="label.save" name="" />
    </p>
</s:form>
