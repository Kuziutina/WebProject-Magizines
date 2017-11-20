<!DOCTYPE html>
<#include "header_menu.ftl">
<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link href="/css/magazine_site.css" rel="stylesheet">
    <title>MagazineSite</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>

<@header_menu one=true></@header_menu>

<div id="content" align="center">
    <div id="heading">
        <h1>Добро пожаловать на siteName</h1>
    </div>
    <div>
        <p id="description">
            Описательное описание функционального функционала.
        </p>
    </div>
    <#if user??>
    <div class="container">
        <div id="subscribes" class="carousel slide">
            <#if user.subscriptions?size != 0>
            <ol class="carousel-indicators">
                <li class="active" data-target="#subscribes" data-slide-to="0"></li>
                <#if 0 < count>
                <#list 1..count as i>
                    <li><li data-target="#subscribes" data-slide-to="${i}"></li></li>
                </#list>
                </#if>
            </ol>
                </#if>

            <div class="featurette-heading">
                <p>Ваши подписки</p>
            </div>

            <#if user.subscriptions?size == 0>
                <p class="text-center">К сожалению, вы пока ни на что не подписаны</p>
            <#else >

            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="row">
                        <#list 1..3 as i>
                        <#if i <= user.subscriptions?size>
                        <div class="col-md-4">
                            <a href="/magazine/${user.subscriptions[i-1].id}" class="thumbnail">
                                <img src="/load/${user.subscriptions[i-1].picture_path}" alt="MagazineName">
                            </a>
                            <p>${user.subscriptions[i-1].name}</p>
                        </div>
                        </#if>
                        </#list>
                    </div>
                </div>
                <#if 1 <= count >
                <#list 1..count as i>
                <div class="carousel-item">
                    <div class="row">
                        <#list 0..2 as j>
                        <div class="col-md-4">
                            <a href="/magazine/${user.subscriptions[i*3 + j].id}" class="thumbnail">
                                <img src="/load/${user.subscriptions[i*3 + j].picture_path}" alt="MagazineName">
                            </a>
                            <p>${user.subscriptions[i*3 + j].name}</p>
                        </div>
                        </#list>
                    </div>
                </div>
                </#list>
                </#if>
            </div>
            </#if>
            <#if 0 < count>
            <a data-slide="prev" href="#subscribes" class="carousel-control-prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </a>
            <a data-slide="next" href="#subscribes" class="carousel-control-next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>

            </a>
            </#if>
        </div>
    </div>
    </#if>
    <div class="container">
        <div id="new_magazines" class="carousel slide">
            <ol class="carousel-indicators">
                <li class="active" data-target="#new_magazines" data-slide-to="0"></li>
                <#assign count_new = newer?size / 3>
                <#if 0 < count_new-1>
                    <#list 1..count_new as i>
                        <li><li data-target="#new_magazines" data-slide-to="${i}"></li></li>
                    </#list>
                </#if>
            </ol>

            <div class="featurette-heading">
                <p>Новые журналы</p>
            </div>

            <#if 0 < count_new>

            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="row">
                    <#list 1..3 as i>
                        <#if i <= newer?size>
                            <div class="col-md-4">
                                <a href="/magazine/${newer[i-1].id}" class="thumbnail">
                                    <img src="/load/${newer[i-1].picture_path}" alt="MagazineName">
                                </a>
                                <p>${newer[i-1].name}</p>
                            </div>
                        </#if>
                    </#list>
                    </div>
                </div>

                <#if 1 <= count_new >
                    <#list 1..count_new as i>
                        <div class="carousel-item">
                            <div class="row">
                                <#list 0..2 as j>
                                    <#if i*3+j < newer?size>
                                    <div class="col-md-4">
                                        <a href="/magazine/${newer[i*3 + j].id}" class="thumbnail">
                                            <img src="/load/${newer[i*3 + j].picture_path}" alt="MagazineName">
                                        </a>
                                        <p>${newer[i*3 + j].name}</p>
                                    </div>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
            </#if>
            <#if 0 < count_new - 1>
            <a data-slide="prev" href="#new_magazines" class="carousel-control-prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </a>
            <a data-slide="next" href="#new_magazines" class="carousel-control-next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </a>
            </#if>
        </div>
    </div>
    <div class="container">
        <div id="popular_magazines" class="carousel slide">
            <ol class="carousel-indicators">
                <li class="active" data-target="#popular_magazines" data-slide-to="0"></li>
                <#assign count_pop = popular?size / 3>
            <#if 0 < count_pop - 1>
                <#list 1..count_pop as i>
                    <li data-target="#popular_magazines" data-slide-to="${i}"></li>
                </#list>
            </#if>
            </ol>

            <div class="featurette-heading">
                <p>Популярные журналы</p>
            </div>

            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="row">
                        <#list 1..3 as i>
                            <#if i <= popular?size>
                                <div class="col-md-4">
                                    <a href="/magazine/${popular[i-1].id}" class="thumbnail">
                                        <img src="/load/${popular[i-1].picture_path}" alt="MagazineName">
                                    </a>
                                    <p>${popular[i-1].name}</p>
                                </div>
                            </#if>
                        </#list>
                    </div>
                </div>

                <#if 0 < count_pop - 1 >
                    <#list 1..count_pop as i>
                        <div class="carousel-item">
                            <div class="row">
                                <#list 0..2 as j>
                                    <#if i*3 + j < popular?size>
                                    <div class="col-md-4">
                                        <a href="/magazine/${popular[i*3 + j].id}" class="thumbnail">
                                            <img src="/load/${popular[i*3 + j].picture_path}" alt="MagazineName">
                                        </a>
                                        <p>${popular[i*3 + j].name}</p>
                                    </div>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        <#if 0 < count_pop - 1>
            <a data-slide="prev" href="#popular_magazines" class="carousel-control-prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </a>
            <a data-slide="next" href="#popular_magazines" class="carousel-control-next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </a>
        </#if>
            </div>

        </div>

</div>
</body>
<footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/carousel.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>