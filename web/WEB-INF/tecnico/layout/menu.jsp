<%@taglib uri="/struts-tags" prefix="s" %>

<s:url id="visitas" action="index" namespace="/tecnico/visita" />
<s:url id="conta" action="index" namespace="/tecnico/conta" />
<s:url id="sair"value="/j_spring_security_logout" />

<jsp:useBean id="url" scope="request" type="String" />

<div id="mainMenu">
    <ul id="mainMenuInner">
        <li>
            <div id="menu-inicio"<%=(url.contains("/tecnico/visita")?"class=\"selecionado\"":"")%>>
                <s:a href="%{visitas}" title="%{getText('menu.tecnico.visitas.desc')}"><strong><s:text name="menu.tecnico.visitas" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-conta"<%=(url.contains("/tecnico/conta")?"class=\"selecionado\"":"")%>>
                <s:a href="%{conta}" title="%{getText('menu.tecnico.conta.desc')}"><strong><s:text name="menu.tecnico.conta" /></strong></s:a>
            </div>
        </li>

        <li>
            <div id="menu-sair">
                <s:a href="%{sair}" title="%{getText('menu.tecnico.sair.desc')}"><strong><s:text name="menu.tecnico.sair" /></strong></s:a>
            </div>
        </li>
    </ul>
</div>