<!DOCTYPE html>
<#include "header_menu.ftl">
<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link href="/css/magazine_site.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>MagazineSite</title>
</head>
<body>

<@header_menu></@header_menu>

<div id="content" align="center">

    <div id="heading">
        <h1>Мои беседы</h1>
    </div>

    <form name="message" class="col-md-12">
        <div>
            <label>Получатель:
                <select title="Получатель" type="text">
                    <option></option>
                    <#list conversation as con>
                    <option>${con.name}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div><input type="text" placeholder="Тема"></div>
        <div>
            <textarea placeholder="Сообщение" rows="6"></textarea>
        </div>
        <button id="send_msg">Отправить</button>
    </form>

    <div style="display: inline-block">
        <#if conversation?size != 0>
        <div class="vertical_tab">
            <button class="tablink" id="onDefault" onclick="openTab(event,'con[0].id')">${con.name}</button>
            <#list 2..con?size as i>
            <button class="tablink" onclick="openTab(event, 'con[i-1].id')">${con[i-1].name}</button>
            </#list>
        </div>
            </#if>
            <#if conversation?size != 0>
        <div class="tabcontent vert" id="${con[0].id}">
            <#list letters as letters>
            <div class="message">
                <div class="message_theme">${letters.header}</div>
                <a class="message_author">${letters.sender.name}</a>
                <p class="message_text">${letters.body}</p>
            </div>
                </#list>

        </div>
            </#if>


    </div>


</div>
</body>
<footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/horizontal_tab.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>