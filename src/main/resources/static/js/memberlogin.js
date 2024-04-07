$().ready(function () {
    $("#login-btn").on("click", function () {
        $.post(
            "/ajax/member/login",
            {
                email: $("#employeeid").val(),
                password: $("#password").val(),
                nextUrl: $("#next").val(),
            },
            function (response) {


                var next = response.data.next;

                if (next) {
                    location.href = next;
                }
            }
        );
    });
});