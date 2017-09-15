<#import "/spring.ftl" as spring/>

<#function pageLink base, page, size>
    <#return buildUriMethod(base, "page=${page}", "size=${size}")>
</#function>

<#macro paginate pageUrl, page, pageCount, pageSize, showPageCount = true>
    <#if (pageCount > 1)>
    <div id="pagination">
        <a href="${pageLink(pageUrl, 0, pageSize)}" <#if page == 0> class="selected" </#if>>1</a>

        <#if (pageCount > 4 && page > 3)>
            <span>&hellip;</span>
        </#if>

        <#assign range = page - 2 .. page + 2>

        <#list range as p>
            <#if (p > 0 && p < (pageCount - 1))>
                <a href="${pageLink(pageUrl, p, pageSize)}" <#if (p == page)> class="selected" </#if>>${p + 1}</a>
            </#if>
        </#list>

        <#if ((pageCount > 4) && page < (pageCount - 4))>
            <span>&hellip;</span>
        </#if>

        <#if (showPageCount || page > pageCount - 4)>
            <a href="${pageLink(pageUrl, pageCount - 1, pageSize)}" <#if (page == (pageCount - 1))> class="selected" </#if>>${pageCount}</a>
        </#if>
    </div>
    </#if>
</#macro>