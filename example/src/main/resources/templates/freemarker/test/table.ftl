<#include '../common/style.ftl'>
<#include '../common/macro.ftl'>
<#include '../common/tables.ftl'>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>

Table test data:

<div class="table-block ui-table testdata">
    <div class="table-row first">
        <@startColumn tableHeader, 'checkboxColumn'/>
            <label class="checkbox-label">
                <input data-all-check class="checkbox" type="checkbox">
                <span class="checkbox-label-text"></span>
            </label>
        <@endColumn tableHeader, 'checkboxColumn'/>

        <@startColumn tableHeader, "id"/>
            Идентификатор
        <@endColumn tableHeader, "id"/>

        <@startColumn tableHeader, "id"/>
            Логин пользователя
        <@endColumn tableHeader, "userId"/>

        <@startColumn tableHeader, "valInt"/>
            Числовое значение
        <@endColumn tableHeader, "valInt"/>

        <@startColumn tableHeader, "valString"/>
            Строковое значение
        <@endColumn tableHeader, "valString"/>

        <@startColumn tableHeader, "valInt"/>
            Числовое значение(обработанное)
        <@endColumn tableHeader, "valInt"/>

        <@startColumn tableHeader, "valString"/>
            Строковое значение(обработанное)
        <@endColumn tableHeader, "valString"/>
    </div>

    <#list testData.content as data>
        <div class="table-row">

            <div class="table-cell">
                <span>
                    <label class="checkbox-label">
                        <input id="${data.id}" data-list-check class="checkbox" type="checkbox" data-selection-stage-id="${data.userId}">
                        <span class="checkbox-label-text"></span>
                    </label>
                </span>
            </div>

            <div class="table-cell">
                <span>
                    ${data.id}
                </span>
            </div>

            <div class="table-cell">
                <span>
                    ${data.userId}
                </span>
            </div>

            <div class="table-cell">
                <span>
                    ${data.valInt}
                </span>
            </div>

            <div class="table-cell">
                <span>
                    ${data.valString}
                </span>
            </div>

            <div class="table-cell">
                <span>
                    ${data.resultInt}
                </span>
            </div>

            <div class="table-cell">
                <span>
                    ${data.resultString}
                </span>
            </div>
        </div>
    </#list>
</div>

<#if (userIdFilter??)>
    <#assign userIdF = '&userId=' + userIdFilter>
</#if>

<#if (lessValueIntFilter??)>
    <#assign lessValueIntF = '&lessValueInt=' + lessValueIntFilter>
</#if>

<@paginate "/test/table?sort=${sortProperty!},${sortDirection!}${userIdF}${lessValueIntF}", testData.number, testData.totalPages, testData.size/>
</body>
</html>