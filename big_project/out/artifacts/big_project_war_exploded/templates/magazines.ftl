<!DOCTYPE html>
<#include "header_menu.ftl">
<#include "stars.ftl">
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

<@header_menu two=true></@header_menu>

<div id="content" align="center">

    <div id="heading">
        <h1>Журналы</h1>
    </div>
    <div align="center">
        <ul class="list_limiter">
            <li><a id="show-20" href="/magazines?page=${page}&showby=20">20</a></li>
            <li><a id="show-40" href="/magazines?page=${page}&showby=40">40</a></li>
            <li><a id="show-100" href="/magazines?page=${page}&showby=60">100</a></li>
            <li><a id="show-all" href="/magazines?page=${page}&showby=all">все</a></li>
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
        <li><a href="/magazines?page=${i}&showby=${count}"> ${i} </a></li>
    </#list>
    </div>

</div>
</body>
<footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>