<#macro stars count=0>

    <fieldset class="rating">
        <#if count != 0>
        <#list 1..count as i>
            <label contenteditable="false" class="full checked"></label>
        </#list>
        </#if>
        <#if count!= 5>
        <#list 1..5-count as j>
            <label contenteditable="false" class="full"></label>
        </#list>
        </#if>
    </fieldset>

</#macro>