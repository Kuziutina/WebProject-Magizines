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

<div class="modal fade" id="leave_review">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="form-group">
                <div align="center">
                    <h2 id="modal_title">Оставить отзыв</h2>
                </div>
                <form class="form-review" action="#">
                    <fieldset>
                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea title="Отзыв" class="form-control" id="review_area_create"
                                          rows="7" placeholder="Оставьте отзыв..."></textarea>
                            </div>
                        </div>
                        <div class="form-group" align="center">
                            <div id="error-score"></div>
                            <a>Поставьте оценку:</a>
                            <fieldset class="rating_vote">
                                <input type="radio" id="star5" name="rating" value="5"/><label class="full"
                                                                                               for="star5"></label>
                                <input type="radio" id="star4" name="rating" value="4"/><label class="full"
                                                                                               for="star4"></label>
                                <input type="radio" id="star3" name="rating" value="3"/><label class="full"
                                                                                               for="star3"></label>
                                <input type="radio" id="star2" name="rating" value="2"/><label class="full"
                                                                                               for="star2"></label>
                                <input type="radio" id="star1" name="rating" value="1"/><label class="full"
                                                                                               for="star1"></label>
                            </fieldset>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <button class="btn btn-lg btn-block purple-bg" type="button" onclick="create_review()">
                        Отправить
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var create_review = function () {
        $("#error-score").html("");
        console.log("i start create review");
        console.log($("textarea#review_area_create").val());
        var text = $("input[name='rating']:checked").val();
        if (text == null) {
            $("#error-score").append("please choose");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/ajax_magazine_review",
                data: {
                    "magazine_id": ${magazine.id},
                    "review": $("textarea#review_area_create").val(),
                    "score": $("input[name='rating']:checked").val()
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

            $("#reviews").append("<div class='review'>" +
                    "<#if user??><a href='/user/${user.id}' class='review_author'>${user.name}</a></#if>" +
                    "<p class='review_text'>"+$("#review_area_create").val()+"</p>" +
                    "<fieldset class='review_rating'>" +
                    "<label contenteditable='false'></label>" +
                    "<label contenteditable='false' class='full'></label>" +
                    "<label contenteditable='false' class='full checked'></label>" +
                    "<label contenteditable='false' class='full checked'></label>" +
                    "<label contenteditable='false' class='full checked'></label>" +
                    "</fieldset></div>");
            console.log("i end");
        }
    }


</script>
<div class="modal fade" id="add_issue">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="form-group">
                <div align="center">
                    <h2 class="modal_title">Добавить выпуск</h2>
                </div>
                <form class="form-review" id="create-copy" action="/create-magazine-copy">
                    <fieldset>
                        <div class="form-group">
                            <div class="col-md-12">
                                <input id="copy-name" type="text" class="form-control" placeholder="Название" required autofocus/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea id="copy-description" title="Описание" required class="form-control"
                                          rows="7" placeholder="Описание..."></textarea>
                            </div>
                        </div>

                        <div class="form-group" align="center">
                            <h5>Обложка</h5>
                            <input type="file" id="cover">
                        </div>
                        <div class="form-group" align="center">
                            <h5>Файл выпуска</h5>
                            <input type="file" id="magazine_file">
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <button form="create-copy" class="btn btn-lg btn-block purple-bg" type="button">
                        Добавить
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>



<div id="content" align="center">

    <div id="heading">
        <h1>${magazine.name}</h1>
    </div>
    <div class="row align-items-center">
        <div class="col-md-5 order-md-1">
            <img id="magazine_image" src="/load/${magazine.picture_path}">
        </div>

        <div class="col-md-5 order-md-2">
            <p id="magazine_descr">${magazine.description}</p>
        </div>
        <div class="col-md-2 order-md-3">
            <div class="rating_bar" align="center">
                <h3>Рейтинг</h3>
                <fieldset class="rating">
                    <label contenteditable="false" class="checked"></label>
                    <label contenteditable="false" class="full"></label>
                    <label contenteditable="false" class="full checked"></label>
                    <label contenteditable="false" class="full checked"></label>
                    <label contenteditable="false" class="full checked"></label>
                </fieldset>
            </div>
        </div>
    </div>
    <div class="horizontal_tab">
        <button class="tablink" id="onDefault" onclick="openTab(event,'reviews')">Отзывы</button>
        <button class="tablink" onclick="openTab(event, 'issues')">Выпуски</button>
    </div>
    <div class="tabcontent" id="reviews">
        <#if magazine.reviews?size == 0>
            <p>К данному журналу пока нет отзывов</p>
        <#else >
        <#list magazine.reviews as review>
        <div class="review">
            <a href="/user/${review.user_id}" class="review_author">${review.user.name}</a>
            <p class="review_text">${review.review}</p>
            <fieldset class="review_rating">
                <label contenteditable="false"></label>
                <label contenteditable="false" class="full"></label>
                <label contenteditable="false" class="full checked"></label>
                <label contenteditable="false" class="full checked"></label>
                <label contenteditable="false" class="full checked"></label>
            </fieldset>
        </div>
        </#list>
        </#if>
    </div>

    <div class="tabcontent" id="issues">
        <ul id="issue_list">
            <#if magazine.copies?size == 0>
                <p> данном разделе пока нет журналов</p>
                <#else >
            <#list magazine.copies as copy>
                <li><a href="#">copy.name</a></li>
                </#list>
            </#if>
        </ul>
    </div>
    <button class="auth" id="write_review" onclick="showReview()">Оставить отзыв</button>
    <button class="auth" id="add_issue">Добавить выпуск</button>

</div>
</body>
<footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/horizontal_tab.js"></script>
    <script src="/js/module_wind.js"></script>
</footer>
</html>