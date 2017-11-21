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
        <h1>Мои беседы</h1>
    </div>

    <form name="message" class="col-md-12">
        <div>
            <label>Получатель:
                <select title="Получатель" id="recipient" type="text">
                    <#if another_user??>
                    <option value="${another_user.id}">${another_user.name}</option>
                        <#else >
                    <option value="-1"></option>
                        </#if>
                    <#--<#list conversations as con>-->
                    <#--<option value="${con.id}">${con.name}</option>-->
                    <#--</#list>-->
                    <#list friends as friend>
                        <option value="${friend.id}">${friend.name}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div><input type="text" id="header" placeholder="Тема"></div>
        <div>
            <textarea id="mes" placeholder="Сообщение" rows="6"></textarea>
        </div>
        <button id="send_msg" onclick="send_message()" type="button">Отправить</button>
    </form>

    <div style="display: inline-block">
        <#if conversations?size != 0>
        <div class="vertical_tab">
            <button class="tablink" id="onDefault" onclick="openTab(event,'${conversations[0].id}')">${conversations[0].name}</button>
            <#if 1 < conversations?size>
            <#list 2..conversations?size as i>
            <button class="tablink" onclick="openTab(event, '${conversations[i-1].id}')">${conversations[i-1].name}</button>
            </#list>
            </#if>
        </div>

            <#list 1..conversations?size as i>

        <div class="tabcontent vert" id="${conversations[i-1].id}">
            <#list letters[i-1] as letter>
                <#--<#list letters_c as letter>-->
            <div class="message ${i-1}">

                <a class="message_author">${letter.sender.name}</a>
                <div class="message_theme" style="font-size: 15px;">${letter.header}</div>
                <p class="message_text">${letter.body}</p>
            </div>
                <#--</#list>-->
            </#list>

        </div>
            </#list>
            <#else >
                <div class="empty-conversation" id="-1">
                        <p>У вас пока нет бесед</p>
                </div>
            </#if>



    </div>
    
    <script>
        var send_message = function () {
            var text = $("#mes").val();
            var recipient = $("#recipient option:selected").val();
            var header = $("#header").val();
            console.log(text + "  " + recipient);
            if (text != "" && recipient != -1 ){
                $.ajax({
                type: "GET",
                url: "/ajax_message",
                data: {
                    "recipient_id": recipient,
                    "header": header,
                    "message": text
                },
                dataType: "json",
                success: function (result) {
                    var has = result.has;
                    console.log("i'm here");
                    if (has) {
                        $("#" + recipient).append("<div class='message'>" +
                                "                <a class='message_author'>${user.name}</a>" +
                                "                <div class='message_theme' style='font-size: 15px:'>" + header + "</div>" +
                                "                <p class='message_text'>" + text + "</p>" +
                                "            </div>");
                    }
                    else {
                        window.location.reload();
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
                $("#header").val("");
                $("#mes").val("");
        }
        else {

            }

        }
    </script>


</div>
</body>
<footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/horizontal_tab.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>