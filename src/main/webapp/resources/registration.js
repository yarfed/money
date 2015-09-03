/**
 * Created by User on 03.06.2015.
 */
function init(context) {
    $("#submitRegistration").click(function() {

        var username = $("#usernameRegistration").val();
        var password = $("#passwordRegistration").val();

        if (username==''||password=='') {
            alert('can\'t be empty');
        }else {
            $.ajax({
                type: "POST",
                url: context + '/registration',
                data: { 'username': encodeURIComponent(username),
                        'password': encodeURIComponent(password)
                },
                success: function (data) {
                   $("#username").val(username);
                   $("#password").val(password);
                   $("#submit").click();

                },
                error: function (err) {
                    $("#message").text("something  wrong");
                }

            })
        }
    });
    $("#demo").click(function(){

        $("#username").val('user');
        $("#password").val('user');
        $("#submit").click();
    });

}