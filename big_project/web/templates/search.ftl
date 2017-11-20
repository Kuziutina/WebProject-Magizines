<!DOCTYPE html>
<#include "header_menu.ftl">
<#include "stars.ftl">
<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/bootstrap-grid.css">
    <link href="/css/magazine_site.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>MagazineSite</title>
</head>
<body>

<@header_menu></@header_menu>

<div id="content" align="center">
    <div align="center" id="search_bar">
        <form name="search" action="/search" method="get">
            <input type="text" name="query" value="<#if query??>${query}</#if>" placeholder="Поиск" class="col-md-7">
            <input type="radio" name="object" value="magazine" id="mag" <#if !people??>checked</#if><label for="mag">Magazine</label>
            <input type="radio" name="object" value="people" id="peo" <#if people??>checked</#if>><label for="peo">People</label>
            <button type="submit">Искать</button>
        </form>
    </div>
    <#if magazines??>
    <#if magazines?size == 0>
    <p>К сожалению, по вашему запросу ничего не найдено</p>
    <#else>
        <div align="center">
            <ul class="list_limiter">
                <li><a id="show-20" href="/search?query=${query}&page=${page}&showby=20">20</a></li>
                <li><a id="show-40" href="/search?query=${query}&page=${page}&showby=40">40</a></li>
                <li><a id="show-100" href="/search?query=${query}&page=${page}&showby=60">100</a></li>
                <li><a id="show-all" href="/search?query=${query}&page=${page}&showby=all">все</a></li>
            </ul>
        </div>
        <div id="result_list">
            <#list magazines as sub>
                <div class="result_item">
                    <h3><a href="/magazine/${sub.id}">${sub.name}</a></h3>
                    <a href="/magazine/${sub.id}" class="thumbnail">
                        <img class="result_img" src="/load/${sub.picture_path}">
                    </a>
                    <div class="rating_bar" align="center">
                        <@stars count=sub.int_score></@stars>
                    </div>
                </div>
            </#list>
        </div>
        <div>
            <#list 1..max_count as i>
                <li><a href="/search?query=${query}&page=${i}&showby=${count}"> ${i} </a></li>
            </#list>
        </div>
    </#if>
    <#else >
        <ul id="myfriends" class="friend_list text-center">

            <#if users?size == 0>
                <li>К сожалению, по вашему запросу ничего не найдено</li>
            <#else>
                <#list users as friend>
                    <li>${friend.name}
                        <a href="/user/${friend.id}"><button class="friend_profile">Перейти к профилю</button></a>

                    </li>
                </#list>
            </#if>
        </ul>
    </#if>

</div>
</body>
<footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>