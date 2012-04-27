<%@page import="com.strutstool.currency.CurrencyUtils"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<jsp:useBean id="saldo" scope="request" type="Double" />

<div class="widget">
    <div class="widgetHeader">Saldo Cartão</div>

    <s:if test="%{saldo > 0}">
        <div class="widgetContent positive"><%= CurrencyUtils.formatToReais(saldo) %></div>
    </s:if>
    <s:else>
        <div class="widgetContent nagetive"><%= CurrencyUtils.formatToReais(saldo) %></div>
    </s:else>
</div>