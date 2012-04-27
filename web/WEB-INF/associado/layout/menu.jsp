<%@taglib uri="/struts-tags" prefix="s" %>

<s:url id="inicio" action="index" namespace="/associado/index" />
<s:url id="titulos" action="index" namespace="/associado/titulo" />
<s:url id="visitas" action="index" namespace="/associado/visita" />
<s:url id="graos" action="index" namespace="/associado/graos" />
<s:url id="produtos" action="index" namespace="/associado/produto" />
<s:url id="orcamentos" action="index" namespace="/associado/orcamento" />
<s:url id="mensagens" action="index" namespace="/associado/mensagem" />

<jsp:useBean id="url" scope="request" type="String" />

<div id="mainMenu">
    <ul id="mainMenuInner">
        <li>
            <div id="menu-inicio"<%=(url.contains("/associado/index")?"class=\"selecionado\"":"")%>>
                <s:a href="%{inicio}" title="%{getText('menu.inicio.desc')}"><strong><s:text name="menu.inicio" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-titulos"<%=(url.contains("/associado/titulo")?"class=\"selecionado\"":"")%>>
                <s:a href="%{titulos}" title="%{getText('menu.titulos.desc')}"><strong><s:text name="menu.titulos" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-graos"<%=(url.contains("/associado/graos")?"class=\"selecionado\"":"")%>>
                <s:a href="%{graos}" title="%{getText('menu.graos.desc')}"><strong><s:text name="menu.graos" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-visitas"<%=(url.contains("/associado/visita")?"class=\"selecionado\"":"")%>>
                <s:a href="%{visitas}" title="%{getText('menu.visitas.desc')}"><strong><s:text name="menu.visitas" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-produtos"<%=(url.contains("/associado/produto")?"class=\"selecionado\"":"")%>>
                <s:a href="%{produtos}" title="%{getText('menu.produtos.desc')}"><strong><s:text name="menu.produtos" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-orcamentos"<%=(url.contains("/associado/orcamento")?"class=\"selecionado\"":"")%>>
                <s:a href="%{orcamentos}" title="%{getText('menu.orcamentos.desc')}"><strong><s:text name="menu.orcamentos" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-mensagens"<%=(url.contains("/associado/mensagem")?"class=\"selecionado\"":"")%>>
                <s:a href="%{mensagens}" title="%{getText('menu.mensagens.desc')}"><strong><s:text name="menu.mensagens" /></strong></s:a>
            </div>
        </li>
    </ul>
</div>