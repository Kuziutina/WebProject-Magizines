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
        <h1>Настройка аккаунта</h1>
    </div>

    <div class="user_data">
        <div>
            <div id="error_username"></div>
            <form action="#">
                <input type="text" id="user_name_change" placeholder="Введите новое имя пользователя">
                <button onclick="changeUsername()" type="button">Изменить имя пользователя</button>
            </form>
            <div id="error-email"></div>
            <form name="email_change">
                <input type="text" id="email_change" placeholder="Введите новый email" name="un">
                <button onclick="change_email()" id="email_change_btn" type="button">Изменить email</button>
            </form>
            <div id="error-password"></div>
            <form name="password_change">
                <input type="password" id="password_change" name="un" placeholder="Введите новый пароль">
                <input type="password" id="last_change" name="un" placeholder="Введите старый пароль">
                <button onclick="change_password()" id="password_change_btn" type="button">Изменить пароль</button>
            </form>

        </div>
    </div>
</div>

<script type="application/javascript">
    var changeUsername = function () {
        console.log("i start uodate username");
        var text = $("#user_name_change").val();
        $("#error_username").html("");
        if (text == "") {
            $("#error_username").append("Это поле не может быть пустым");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/ajax_change_account",
                data: {"username" : $("#user_name_change").val()},
                dataType: "json",
                success: function () {
                    alert("Ваше имя было изменено");
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
            $("#top-ref-user-profile").html(text);
            $("#user_name_change").val("");
        }

    }

    var change_email = function () {
        var text = $("#email_change").val();
        $("#error_email").html("");
        if (text == "") {
            $("#error-email").append("Это поле не может быть пустым");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/ajax_change_account",
                data: {"email" : $("#email_change").val()},
                dataType: "json",
                success: function (result) {
                    if (result.errors) {
                        $("#error-email").append("used");
                    }
                    else {
                        alert("jtgktng");
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
            $("#email_change").val("");
        }
    }


    var change_password = function () {
        var text = $("#password_change").val();
        var text2 = $("#last_change").val();
        $("#error_password").html("");
        if (text == "" || text2 == "") {
            $("#error-email").append("Оба поля должны быть заполнены");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/ajax_change_account",
                data: {
                    "new_password": $("#password_change").val(),
                    "last_password": $("#last_change").val()
                },
                dataType: "json",
                success: function (result) {
                    if (result.errors) {
                        $("#error-password").append("not true");
                    }
                    else {
                        alert("your password was change");
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

        }
    };

</script>


</body>
<footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>