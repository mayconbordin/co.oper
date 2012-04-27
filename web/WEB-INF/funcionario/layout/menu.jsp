<%@taglib uri="/struts-tags" prefix="s" %>

<s:url id="inicio" action="index" namespace="/funcionario/index" />
<s:url id="acessos" action="index" namespace="/funcionario/acesso" />
<s:url id="orcamentos" action="index" namespace="/funcionario/orcamento" />
<s:url id="mensagens" action="index" namespace="/funcionario/mensagem" />
<s:url id="associados" action="index" namespace="/funcionario/associado" />
<s:url id="tecnicos" action="index" namespace="/funcionario/tecnico" />
<s:url id="sair" action="index" namespace="/funcionario/mensagem" />

<jsp:useBean id="url" scope="request" type="String" />

<div id="mainMenu">
    <ul id="mainMenuInner">
        <li>
            <div id="menu-inicio"<%=(url.contains("/funcionario/index")?"class=\"selecionado\"":"")%>>
                <s:a href="%{inicio}" title="%{getText('menu.func.inicio.desc')}"><strong><s:text name="menu.func.inicio" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-acessos"<%=(url.contains("/funcionario/acesso")?"class=\"selecionado\"":"")%>>
                <s:a href="%{acessos}" title="%{getText('menu.func.acesso.desc')}"><strong><s:text name="menu.func.acesso" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-orcamentos"<%=(url.contains("/funcionario/orcamento")?"class=\"selecionado\"":"")%>>
                <s:a href="%{orcamentos}" title="%{getText('menu.func.orcamentos.desc')}"><strong><s:text name="menu.func.orcamentos" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-mensagens"<%=(url.contains("/funcionario/mensagem")?"class=\"selecionado\"":"")%>>
                <s:a href="%{mensagens}" title="%{getText('menu.func.mensagens.desc')}"><strong><s:text name="menu.func.mensagens" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-associados"<%=(url.contains("/funcionario/associado")?"class=\"selecionado\"":"")%>>
                <s:a href="%{associados}" title="%{getText('menu.func.associados.desc')}"><strong><s:text name="menu.func.associados" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-tecnicos"<%=(url.contains("/funcionario/tecnico")?"class=\"selecionado\"":"")%>>
                <s:a href="%{tecnicos}" title="%{getText('menu.func.tecnicos.desc')}"><strong><s:text name="menu.func.tecnicos" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-sair">
                <s:a href="%{sair}" title="%{getText('menu.func.sair.desc')}"><strong><s:text name="menu.func.sair" /></strong></s:a>
            </div>
        </li>
    </ul>
</div>