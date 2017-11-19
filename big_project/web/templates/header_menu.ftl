<#macro header_menu one=false two=false three=false four=false five=false six=false>



<div class="modal fade" id="sign_in">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="account-box">
                <div class="logo ">
                    <img src="/images/magazines.png" alt="logo"/>
                </div>
                <div id="errors"></div>
                <form class="form-signin" action="#">
                    <div class="form-group">
                        <input id="signin_email" type="text" class="form-control" placeholder="Email" required autofocus/>
                    </div>
                    <div class="form-group">
                        <input id="signin_password" type="password" class="form-control" placeholder="Пароль" required/>
                    </div>
                    <label class="checkbox">
                        <input id="remember_me" type="checkbox" value="remember-me"/>
                        Запомнить меня
                    </label>
                    <button onclick="signin()" class="btn btn-lg btn-block purple-bg" type="button">
                        Войти
                    </button>
                </form>
                <a class="forgotLnk" href="" data-dismiss="modal" onclick="showForgotPass()">Восстановление пароля</a>
                <div class="or-box row-block">
                    <div class="row">
                        <div class="col-md-12 row-block">
                            <a>Нет аккаунта?</a>
                            <button class="btn btn-primary btn-block" data-dismiss="modal" onclick="showSignUp()">
                                Зарегистрироваться
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="sign_up">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="account-box">
                <div class="logo ">
                    <img src="/images/magazines.png" alt="logo"/>
                </div>
                <form class="form-signup" method="post" action="/about">
                    <div id="empty_username"></div>
                    <div class="form-group">
                        <input oninput="username_checker()" type="text" id="username" class="form-control" placeholder="Имя пользователя" required autofocus name="username"/>
                    </div>
                    <div id="empty_email"></div>
                    <div class="form-group">
                        <input oninput="email_checker()" type="text" id="email" class="form-control" placeholder="Email" required name="mail"/>
                    </div>
                    <div id="short_password"></div>
                    <div class="form-group">
                        <input oninput="pas_checker()" type="password" id="password" class="form-control" placeholder="Пароль" required name="password"/>
                    </div>
                    <div id="dont-match"></div>
                    <div class="form-group">
                        <input oninput="repeate_pas_checker()" type="password" id="repeat-password" class="form-control" placeholder="Повторите пароль" required name="repeat_password"/>
                    </div>
                    <button type="button" class="btn btn-lg btn-block purple-bg" onclick="check_singup()">
                        Регистрация
                    </button>
                </form>
                <div class="or-box row-block">
                    <div class="row">
                        <div class="col-md-12 row-block">
                            <a>Уже зарегистрированы?</a>
                            <button class="btn btn-primary btn-block" data-dismiss="modal" onclick="showSignIn()">
                                Войти
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">

    var signin = function () {
        console.log("i start sign in");
        $.ajax({
            type: "GET",
            url: "/ajax_check_sign_up",
            data: {"email" : $("#signin_email").val(),
                "password" : $("#signin_password").val(),
                "remember_me": $("#remember_me").is(":checked")},
            dataType: "json",
            success: function (result) {
                console.log(result);
                $("#errors").html("");
                if (result.errors) {
                    $("#errors").append("<p>Логин или пароль введены не верно</p>");
                }
                else {
                    window.location.reload();
                    console.log("all good i reload");
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

    var username_checker = function () {
        $("#empty_username").html("");
    }

    var email_checker = function () {
        $("#empty_email").html("");
    }

    var pas_checker = function () {
        var text = $("#password").val();
        $("#short_password").html("");
        if (text == "") {
            $("#password").attr("style", "border: auto");
        }
        else if (text.length <= 4) {
            $("#short_password").append("<p>Слишком короткий пароль</p>");
            $("#password").attr("style", "border: 1px solid red");
        }
        else {
            $("#password").attr("style", "border: 1px solid green");
        }
    }

    var repeate_pas_checker = function () {
        var text = $("#repeat-password").val();
        var text1 = $("#password").val();
        $("#dont-match").html("");
        if (text == "") {
            $("#repeat-password").attr("style", "border: auto;")
        }
        else if (text == text1) {
            $("#repeat-password").attr("style", "border: 1px solid green");
        }
        else {
            $("#dont-match").append("<p>Пароли не совпадают</p>");
            $("#repeat-password").attr("style", "border: 1px solid red");
        }

    }

    function check_singup() {
        console.log("i start check");
        var errors = false;

        if ($("#username").val() == "") {
            $("#empty_username").html("").append("<p>Поле не может быть пустым</p>");
            errors = true;
        }

        if ($("#email").val() == "") {
            $("#empty_email").html("").append("<p>Поле не может быть пустым</p>");
            errors = true;
        }

        if ($("#password").val() == "") {
            $("#short_password").html("").append("<p>Поле не может быть пустым</p>");
            errors = true;
        }

        if (!errors) {
            $.ajax({
                type: "POST",
                url: "/ajax_check_sign_up",
                data: {
                    "username": $("#username").val(),
                    "email": $("#email").val(),
                    "password": $("#password").val(),
                    "repeate_password": $("#repeat-password").val()
                },
                dataType: "json",
                success: function (result) {
                    if (result.email_used != null) {
                        $("#empty_email").html("").append("<p>Данная почта уже используется</p>");
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
        }
    }

    var exit = function () {
        $.ajax({
            url: "/ajax_exit",
            data: {},
            dataType: "json",
            success: function (result) {
                window.location.reload();
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
</script>

<div class="modal fade" id="forgot_pass">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="account-box">
                <div class="logo ">
                    <img src="/images/magazines.png" alt="logo"/>
                </div>
                <div id="errors-restoring"></div>
                <form class="form-signup" action="#">
                <#--<div class="form-group">-->
                <#--<input id="username-restoring" type="text" class="form-control" placeholder="Имя пользователя" required autofocus/>-->
                <#--</div>-->
                    <div class="form-group">
                        <input id="email-restoring" type="text" class="form-control" placeholder="Email" required/>
                    </div>
                    <div class="form-group">
                        <button onclick="restoring_password()" class="btn btn-lg btn-block purple-bg" type="button">
                            Отправить
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    var restoring_password = function () {
        console.log("i start and click");
        $.ajax({
            url: "/ajax_restoring_password",
            data: {"email": $("#email-restoring").val()},
            dataType: "json",
            success: function (result) {
                console.log("i start restoring");
                $("#errors-restoring").html("");
                if (result.errors) {
                    $("#errors-restoring").append("<p>Пользователь с такой почтой не зарегестрирован</p>");
                }
                else {
                    $("#forgot_pass").modal('hide');
                    alert("на вашу почту был выслан новый пароль");
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
</script>

<div class="modal fade" id="add_magazine">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="form-group">
                <div align="center">
                    <h2 id="modal_title">Добавить журнал</h2>
                </div>
                <form method="post" class="form-review" action="/create-magazine" id="create" enctype="multipart/form-data">
                    <fieldset>
                        <div class="form-group">
                            <div class="col-md-12">
                                <input name="title" type="text" class="form-control" placeholder="Название" required autofocus/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea name="description" title="Описание" required class="form-control" id="review_area"
                                          rows="7" placeholder="Описание..."></textarea>
                            </div>
                        </div>

                        <div class="form-group" align="center">
                            <h5>Обложка</h5>
                            <input name="cover" type="file" id="cover">
                        </div>
                    </fieldset>
                  </form>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                <#--<button form="create" class="btn btn-lg btn-block purple-bg" type="submit">-->
                <#--Добавить-->
                <#--</button>-->

                    <input form="create" type="submit" value="ok">
                </div>

            </div>
        </div>
    </div>
</div>
    <#if user??>
        <#if user.confirmation != "">
        <div><p>Please podtverdite email</p></div>
        </#if>
    </#if>
<header>
    <div id="log_in">


        <#if user??>
            <a id="top-ref-user-profile" href="/user/${user.id}">${user.name}</a>
            <button onclick="exit()" id="exit">Выйти</button>
        <#else>
            <button id="sign_in_btn" onclick="showSignIn()">Войти</button>
            <button id="sign_up_btn" onclick="showSignUp()">Регистрация</button>
        </#if>
        <form name="search" action="/search" method="get">
            <input type="text" name="query" placeholder="Поиск">
            <button type="submit">Искать</button>
        </form>
    </div>
    <a href="/main" name="logo"><img src=/images/magazines.png alt="SiteName logo"></a>
</header>
<nav>
    <ul class="top-menu">
        <li class="<#if one>active </#if>"><a href="/main">Главная Страница</a></li>
        <li class="<#if two>active </#if>"><a href="/magazines">Журналы</a></li>
        <li class="<#if three>active </#if>"><a href="/newest_issues">Последние выпуски</a></li>

        <#if user??>
            <!-- Класс auth отображаются только у авторизованного юзера-->
            <li class="<#if four>active </#if>auth"><span class="add_magazine" onclick="showAddMagazine()">Добавить журнал</span></li>
            <li class="<#if five>active </#if>auth"><a href="/user/${user.id}">Личный кабинет</a></li>
        </#if>
        <li class="<#if six>active </#if>"><a href="/about" >О сайте</a></li>
    </ul>
</nav>
</#macro>