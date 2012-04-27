<script type="text/javascript">
$(document).ready(function() {
    $('.trocarSenha a').each(function() {
        $(this).click(function() {
            openCloseNovaSenha(this);
            return false;
        });
    });

    $('.novaSenha .submit').each(function() {
        $(this).click(function() {
            trocarSenha(this);
            return false;
        });
    });
});
</script>
<div class="detailedDialog">
    <p>
        <span class="label">Email:</span>
        <span class="value">${usuario.email}</span>
    </p>
    <p class="trocarSenha"><a href="" title="">Trocar Senha</a></p>
    <p class="novaSenha">
        <input class="id" type="hidden" value="${usuario.codigo}" />
        <span class="label">Nova senha:</span>
        <span class="value">
            <input class="password" type="password" />
            <br />
            <input class="submit" type="submit" value="Salvar" />
        </span>
    </p>
</div>