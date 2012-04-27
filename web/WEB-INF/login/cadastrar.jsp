<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<script type="text/javascript">
//mascara generica em javascript
function mascaraTexto(evento, mascara){
   var campo, valor, i, tam, caracter;

   if (document.all) // Internet Explorer
      campo = evento.srcElement;
   else // Nestcape, Mozzila
       campo= evento.target;

   valor = campo.value;
   tam = valor.length;
   
   for(i=0;i<mascara.length;i++){
      caracter = mascara.charAt(i);
      if(caracter!="9")
         if(i<tam & caracter!=valor.charAt(i))
            campo.value = valor.substring(0,i) + caracter + valor.substring(i,tam);
    }

}


$(document).ready(function() {
    $('#cadastrarForm .cpfcnpj').keyup(function() {
        Mascara(this,Cpf);
    });

    $('#cadastrarForm .input select').change(function() {
        if ($(this).val() == 'cpf') {
            $('#cadastrarForm .cpfcnpj').keyup(function() {
                $(this).attr("maxLength", 14);
                Mascara(this,Cpf);
            });
        } else {
            $('#cadastrarForm .cpfcnpj').keyup(function() {
                $(this).attr("maxLength", 21);
                Mascara(this,Cnpj);
            });
        }

        $('#cadastrarForm .cpfcnpj').val("");
    });

    $("#cadastrarForm").submit(function() {
        if ($('#cadastrarForm .codigo').val() == '') {
            alert("Informa seu código do associado.");
            return false;
        }

        if ($('#cadastrarForm .senha').val() == '') {
            alert("Informe uma senha.");
            return false;
        }

        if ($('#cadastrarForm .cpfcnpj').val() == '') {
            alert("Informe o seu CPF ou CNPJ.");
            return false;
        }
    });
});
</script>

<div class="centro">
    <h2>Cadastre-se</h2>
    <s:form id="cadastrarForm" action="cadastrar" namespace="/login">
        <s:hidden name="saveUsuario" value="true" />
        <div class="input">
            <s:label key="usuario.codigo" />
            <s:textfield cssClass="codigo" name="codigo" />
        </div>
        <div class="input">
            <s:label key="usuario.email" />
            <s:textfield cssClass="email" name="email" />
        </div>
        <div class="input">
            <s:label key="usuario.senha" />
            <s:password cssClass="senha" name="senha" />
        </div>
        <div class="input">
            <label><select>
                <option value="cpf">CPF</option>
                <option value="cnpj">CNPJ</option>
            </select></label>
            <s:textfield cssClass="cpfcnpj" name="cpfCnpj" maxLength="14" />
        </div>
        <div class="input">
            <s:submit key="salvar" />
        </div>
    </s:form>
</div>