<#macro startColumn tableHeader, columnName, customHandler = false>
    <#assign column = tableHeader.getColumn(columnName)!'null'>

<div class="table-cell line">
<#-------для сортировки:-->
<#------------вниз .sort присваиваем .bottom-->
<#------------вверх .sort присваиваем .top-->

    <#if (column != 'null' && column.sortable)>
        <#if (tableHeader.urlParams?has_content)>
            <#assign urlParams = '&' + tableHeader.urlParams>
        </#if>

        <#if (column.additionalUrlParams?has_content)>
            <#assign columnParams = '&' + column.additionalUrlParams>
        </#if>

        <#if ((column.sortDirection!'') == 'asc')>
            <#assign nextDirection = 'desc'>
        <#elseif ((column.sortDirection!'') == 'desc')>
            <#assign nextDirection = 'asc'>
        <#else>
            <#assign nextDirection = 'desc'>
        </#if>

    <div data-sorting-column data-sort-property="${column.sortProperty}" data-sort-direction="${nextDirection}" class="sort <#if ((column.sortDirection!'asc') == 'asc')> top <#elseif ((column.sortDirection!'asc') == 'desc')> bottom </#if>" <#if (!customHandler)> onclick="location.href='${tableHeader.pageUrl}?sort=${column.sortProperty},${nextDirection}${urlParams!''}${columnParams!''}${onlineParams!''}'"</#if>>
    <span>
    </#if>
</#macro>

<#macro endColumn tableHeader, columnName>
    <#assign column = tableHeader.getColumn(columnName)!'null'>

    <#if (column != 'null' && column.sortable)>
    </span>
        <a><div class="column-ico"></div></a>
    </div>
    </#if>

</div>
</#macro>