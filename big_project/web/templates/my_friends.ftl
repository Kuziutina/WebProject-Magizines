<!DOCTYPE html>
<#include "header_menu.ftl">
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
        <h1 class="text-center">Мои друзья</h1>
        <ul id="myfriends" class="friend_list text-center">

        <#if friends?size == 0>
            <li>К сожалению, у вас нет друзей</li>
        <#else>
            <#list friends as friend>
                <li>${friend.name}<a href="/user/${friend.id}"><button class="friend_profile">Перейти к профилю</button></a><button id="${friend.id}" class="delete" type="button">Убрать из друзей</button> </li>
            </#list>
        </#if>
        </ul>
    </div>
    <script>

        $(".delete").bind("click", function () {
            var id = $(this).attr('id');
            var ob = $(this).parent('li');
            console.log("i start delete friend");
            $.ajax({
                type: "GET",
                url: "/ajax_change_friend",
                data: {"delete_id" : id},
                dataType: "json",
                success: function (result) {
                    console.log("i start remove parent friend");
                    ob.remove();
                    console.log("i end delete friend");
                    if (document.getElementById('myfriends').getElementsByTagName('li').length == 0) {
                        $("#myfriends").append("<li>К сожалению, у вас нет друзей</li>");
                    }
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

        })
    </script>


</div>
</body>
<footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>