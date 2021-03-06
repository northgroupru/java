<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spring Security</title>

<#include '../common/style.ftl'>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div class="container" style="width: 300px;">
        <form action="/registration" method="post" >
            <h2 class="form-signin-heading">Registration form</h2>
            <input type="text" class="form-control" name="login" placeholder="Login" required autofocus>
            <input type="text" class="form-control" name="firstName" placeholder="First name" required>
            <input type="text" class="form-control" name="lastName" placeholder="Last name">
            <input type="text" class="form-control" name="email" placeholder="Email">

            <select class="form-control" name="roles">
                <#list enumRoles as enumRole>
                    <option value="${enumRole.getCode()}">${enumRole.getName()}</option>
                </#list>
            </select>

            <input type="password" class="form-control" name="password" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="button" onclick="AuthManager.registr()">Зарегистрироваться</button>
        </form>
    </div>
</body>
</html>