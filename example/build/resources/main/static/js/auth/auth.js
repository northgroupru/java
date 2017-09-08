/**
 * Created by Gainutdinov on 07.09.17.
 */

function AuthManager_F()
{
    this.registr = function () {
        dat = {
            'login': $('input[name="login"]').val(),
            'firstName': $('input[name="firstName"]').val(),
            'lastName': $('input[name="lastName"]').val(),
            'email': $('input[name="email"]').val(),
            'password': $('input[name="password"]').val(),
            'roles':[$('input[name="roles"]').val()]
        }

        MainManager.ajaxJSON("/registration", dat);
    }
}

var AuthManager = new AuthManager_F();