<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#voltar").click(function(){
            history.go(-1);
        })
    });
</script>

<div class="centro">
    <h2>N�o foi poss�vel efetuar o cadastro.</h2>
    <h3>Poss�veis causas:</h3>
    <ul>
        <li>C�digo do associado n�o existe.</li>
        <li>O CPF/CNPJ vinculado a conta do associado n�o est� correto.</li>
    </ul>

    <a id="voltar" href="">Voltar</a>
</div>