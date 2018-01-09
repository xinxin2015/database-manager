$(function () {

    var pageFun = {

        bindEvent : function () {
            $("#submit_line").click(function () {
                pageFun.submit_line();
            });
            $("#submit_change").click(function () {
                pageFun.submit_change();
            });
            $("#submit_station").click(function () {
                pageFun.submit_station();
            });
        },
        submit_line : function () {
            alert("submit_line");
        },
        submit_change : function () {
            alert("submit_change");
        },
        submit_station : function () {
            alert("submit_station");
        }
    };

    pageFun.bindEvent();
});