var navigation = new Vue({
    el: "#navigation",
    data: {
        currUserName: ""
    },
    methods: {
        userClick: function (index) {
            if (0 == index) {
                window.location.href = "/learning/index";
            }
            if (1 == index) {
                window.location.href = "/learning/course";
            }
            if (2 == index) {
                window.location.href = "/learning/courseRecord";
            }
            if (3 == index) {
                window.location.href = "/learning/news";
            }
            if (4 == index) {
                window.location.href = "/learning/forum";
            }
        },
        outLogin: function () {
            globalvm.outLogin();
        }
    },
    created: function () {
        var t = this;
        globalvm.ajaxGet("/user/getName", {}, function (data) {
            t.currUserName = data.userName;
        })
    },
});


