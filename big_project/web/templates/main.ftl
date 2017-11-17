<!DOCTYPE html>
<#include "header_menu.ftl">
<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link href="/css/magazine_site.css" rel="stylesheet">
    <title>MagazineSite</title>
</head>
<body>

<@header_menu></@header_menu>

<div id="content" align="center">
    <div id="heading">
        <h1>Добро пожаловать на siteName</h1>
    </div>
    <div>
        <p id="description">
            Описательное описание функционального функционала.
        </p>
    </div>
    <div class="container">
        <div id="subscribes" class="carousel slide">
            <ol class="carousel-indicators">
                <li class="active" data-target="#subscribes" data-slide-to="0"></li>
                <#if 0 < count>
                <#list 1..count as i>
                    <li><li data-target="#subscribes" data-slide-to="${i}"></li></li>
                </#list>
                </#if>
            </ol>

            <div class="featurette-heading">
                <p>Ваши подписки</p>
            </div>

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
    <div class="container">
        <div id="new_magazines" class="carousel slide">
            <ol class="carousel-indicators">
                <li class="active" data-target="#new_magazines" data-slide-to="0"></li>
                <li data-target="#new_magazines" data-slide-to="1"></li>
                <li data-target="#new_magazines" data-slide-to="2"></li>
            </ol>

            <div class="featurette-heading">
                <p>Новые журналы</p>
            </div>

            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>
                    </div>
                </div>
            </div>
            <a data-slide="prev" href="#new_magazines" class="carousel-control-prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </a>
            <a data-slide="next" href="#new_magazines" class="carousel-control-next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>

            </a>
        </div>
    </div>
    <div class="container">
        <div id="popular_magazines" class="carousel slide">
            <ol class="carousel-indicators">
                <li class="active" data-target="#popular_magazines" data-slide-to="0"></li>
                <li data-target="#popular_magazines" data-slide-to="1"></li>
                <li data-target="#popular_magazines" data-slide-to="2"></li>
            </ol>

            <div class="featurette-heading">
                <p>Популярные журналы</p>
            </div>

            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>

                        <div class="col-md-4">
                            <a href="#" class="thumbnail">
                                <img src="images/magazines.png" alt="MagazineName">
                            </a>
                            <p>Hi!</p>
                        </div>
                    </div>
                </div>
            </div>
            <a data-slide="prev" href="#popular_magazines" class="carousel-control-prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </a>
            <a data-slide="next" href="#popular_magazines" class="carousel-control-next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>

            </a>
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