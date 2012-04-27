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
    <h2>Não foi possível efetuar o cadastro.</h2>
    <h3>Possíveis causas:</h3>
    <ul>
        <li>Código do associado não existe.</li>
        <li>O CPF/CNPJ vinculado a conta do associado não está correto.</li>
    </ul>

    <a id="voltar" href="">Voltar</a>
</div>