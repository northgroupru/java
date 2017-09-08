<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>Базовый ссылки</h1>

<ul>
    <li><a href="/login" >Авторизация</a></li>
    <li><a href="/registration" >Регистрация</a></li>

</ul>

<#if (user??)>
    Dear ${user}, you are successfully logged into this application.
</#if>


</body>
</html>