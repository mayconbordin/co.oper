<%@page import="com.strutstool.currency.CurrencyUtils"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<jsp:useBean id="total" scope="request" type="Double" />

<div class="widget">
    <div class="widgetHeader">Saldo Títulos</div>

    <s:if test="%{total > 0}">
        <div class="widgetContent positive"><%= CurrencyUtils.formatToReais(total) %></div>
    </s:if>
    <s:else>
        <div class="widgetContent nagetive"><%= CurrencyUtils.formatToReais(total) %></div>
    </s:else>
</div>