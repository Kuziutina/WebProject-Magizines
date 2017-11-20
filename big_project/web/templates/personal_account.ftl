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

<#if user?? && user.id = another_user.id>
    <@header_menu five=true></@header_menu>
<#else >
    <@header_menu></@header_menu>
</#if>

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
            <a href="/user/${user.id}/subscriptions"><button id="my_subscriptions">Мои подписки</button></a>
        </div>
        <div>
            <a href="/myfriends"><button id="my_friends">Мои друзья</button></a>
        </div>
        <div>
            <a href="/conversation"><button id="my_conversations">Мои беседы</button></a>
        </div>
        </div>
        <#else >
            <div class="user_profile">
                <div><a class="username">${another_user.name}</a></div>
                <div><a href="/user/${another_user.id}/subscriptions"><button id="user_subscriptions">Подписки пользователя</button></a></div>
                <#if user??>
                <div>
                    <form method="post" action="/conversation?an_id=${another_user.id}">
                        <button type="submit">Отправить сообщение</button>
                    </form>
                </div>
                <div><a><button onclick="friend()" id="add_friend"><#if !has>Добавить в друзья<#else >Убрать из друзей</#if></button></a></div>
                </#if>
            </div>
        </#if>
<script>
    var friend = function () {
        var text = $("#add_friend").text();
        var has;
        console.log("i start and has " + text);
        if (text == 'Добавить в друзья') {
            has = true;
            $("#add_friend").text('Убрать из друзей');
        }
        else {
            has = false;
            $("#add_friend").text("Добавить в друзья");
        }
        $.ajax({
            type: "POST",
            url: "/ajax_change_friend",
            data: {
                "friend_id": ${another_user.id},
                "has" : has
            },
            dataType: "json",
            success: function (result) {

            },
            error: function (jqXHR, exception) {

                if (jqXHR.status === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.status == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.status == 500) {
                    alert('Internal Server Error [500].');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error.\n' + jqXHR.responseText);
                }
            },
        });

        console.log("i end");

    }
</script>




</div>
</body>
<footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>