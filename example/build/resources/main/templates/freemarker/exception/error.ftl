<!DOCTYPE HTML>
<html>
<head>
    <title>Пример системы</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/css/main.css" />
</head>
<body>
<div class="exception-content">
    <div class="exception-content-cell">
        <img src="/images/500.png" alt=""/>
        Что-то пошло не так!
        <br/>
    <#if (data.status)??>
        Статус: ${data.status}<br/>
    </#if>

    <#if (data.message)??>
        Сообщение: ${data.message}
    </#if>
    </div>
</div>
</body>
</html>
