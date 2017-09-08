/**
 * Created by Gainutdinov on 07.09.17.
 */
function MainManager_F()
{
    this.ajaxJSON = function(url, dat)
    {
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(dat),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.log(data);
            }
        })
    }

}

var MainManager = new MainManager_F();