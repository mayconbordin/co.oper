<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

<div id="datePanel">
    <s:form action="index" namespace="/funcionario/acesso">
        Data inicial: <sj:datepicker name="dataInicial" cssClass="datePicker" displayFormat="dd/mm/yy" />
        Data final: <sj:datepicker name="dataFinal" cssClass="datePicker" displayFormat="dd/mm/yy" />
        <s:submit id="loadVisitas" value="Carregar" />
    </s:form>
</div>

<!--
<div id="chartsPanel">
    <a href="" title="">Visitas x Período</a>
    <a href="" title="">Visitas x Página x Período</a>
    <a href="" title="">Visitas x Associado</a>
</div>
-->

<sjc:chart
    id="chartPoints"
    cssStyle="width: 100%; height: 400px;"
    xaxisMode="time"
    xaxisTimeformat="%0d/%0m"
    xaxisMin="%{minTime}"
    xaxisMax="%{maxTime}"
    xaxisColor="#666"
    xaxisTickSize="[1, 'day']"
    xaxisTickColor="#aaa"
    xaxisPosition="bottom"
    yaxisPosition="left"
    yaxisTickSize="10"
>
    <sjc:chartData
        label="Visitas"
        list="acessosList"
        points="{ show: true }"
        lines="{ show: true }"
        clickable="true"
        hoverable="true"
    />
</sjc:chart>