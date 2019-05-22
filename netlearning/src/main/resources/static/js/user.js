
var userManage = new Vue({
    el: "#userManage",
    data: {

        userList: [],

        show: true,
        addView: false,
        addtitle: "添加用户",

        id:"",
        userName: "",
        password: "123456",
        name: "",
        education: "本科在读",
        phone: "",
        ispost: "1",
        isreply: "1",
        select: "",
        userRole:"",

        currentUserName:"admin"
    },
    methods: {
        addClick: function () {
            var t = this;
            t.show = false;
            t.addView = true;
        },
        makeSure: function () {
            var t = this;
            var params;
            if (t.userName && t.password && t.name && t.education && t.phone && t.userRole) {
                params = {
                    userName: t.userName,
                    passWord: t.password,
                    name: t.name,
                    education: t.education,
                    phone: t.phone,
                    ispost: t.ispost,
                    isreply: t.isreply,
                    level: t.userRole,
                };
                globalvm.ajaxPost("/user/saveUser", params, function (data) {
                    t.show = true;
                    t.addView = false;
                    t.initList();
                    t.userName = "";
                    t.password = "";
                    t.name = "";
                    t.phone = "";
                });
            } else {

            }
        },
        cancelEdit: function () {
            var t = this;
            t.show = true;
            t.addView = false;
            t.initList();
            t.userName = "";
            t.password = "";
            t.name = "";
            t.phone = "";
        },
        deletClick: function (event) {
            var t = this;
            var userId = $(event.currentTarget).attr("userId");
            globalvm.ajaxGet("/user/deletUser", {id: userId}, function () {
                t.initList();
            })
        },
        isPostClick: function (event) {
            var t = this;
            var userId = $(event.currentTarget).attr("userId");
            var ispost = $(event.currentTarget).attr("ispost");
            ispost = ispost == 0 ? 1 : 0;
            globalvm.ajaxPost("/user/updataIsPost", {id: userId, ispost: ispost}, function () {
                t.initList();
            })
        },
        isReplyClick: function (event) {
            var t = this;
            var userId = $(event.currentTarget).attr("userId");
            var isreply = $(event.currentTarget).attr("isreply");
            isreply = isreply == 0 ? 1 : 0;
            globalvm.ajaxPost("/user/updataIsReply", {id: userId, isreply: isreply}, function () {
                t.initList();
            })
        },
        selectClick: function () {
            var t = this;
            var userName = t.select;
            globalvm.ajaxGet("/user/findByNameContaining", {userName: userName}, function (data) {
                t.userList = data;
            })
        },

        initList: function () {
            var t = this;
            globalvm.ajaxGet("/user/userList", {}, function (data) {
                t.userList = data;
            })
        },
    },
    created: function () {
        this.initList();
    },



});
userManage.$watch('select', function (nval, oval) {
    userManage.selectClick();
})



