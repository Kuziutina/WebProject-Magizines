<#include "header_menu.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link href="/css/magazine_site.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>MagazineSite</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>

<@header_menu></@header_menu>

<div id="content" align="center">

    <div id="heading">
        <#if user?? && user.id = another_user.id>
            <h1>Личный кабинет</h1>
        <#else>
            <h1>Профиль пользователя</h1>
        </#if>
    </div>


        <#if user?? && user.id == another_user.id>
        <div class="user_data">
        <h3>
            ${user.name}
        </h3>
        <div>
            <a href="/edit"><button  id="user_settings">Настройки аккаунта</button></a>
        </div>
        <div>
            <a href="#"><button href="#" id="my_subscriptions">Мои подписки</button></a>
        </div>
        <div>
            <a href="#"><button id="my_friends">Мои друзья</button></a>
        </div>
        <div>
            <a href="#"><button id="my_conversations">Мои беседы</button></a>
        </div>
        </div>
        <#else >
            <div class="user_profile">
                <div><a class="username">UserName</a></div>
                <div><a href="#"><button id="user_subscriptions">Подписки пользователя</button></a></div>
                <div><a href="#"><button id="send_msg">Отправить сообщение</button></a></div>
            </div>
        </#if>


</div>
</body>
<footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>