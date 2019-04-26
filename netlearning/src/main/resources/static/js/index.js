var navigation = new Vue({
    el: "#navigation",
    data: {},
    created: function () {
        this.initMyInfo();
    },
    methods: {
        initMyInfo: function () {
            var t = this;
            globalvm.ajaxGet("/user/findById", {}, function (data) {
                stuManage.currentUserName = data.name;
            });
        },
        userClick: function (index) {
            if (0 == index) {
                stuManage.show = true;
            } else {
                stuManage.show = false;
            }
            if (3 == index) {
                stuManage.news = true;
                stuManage.createdNews();
            } else {
                stuManage.news = false;
            }
        }
    }
});
var stuManage = new Vue({
    el: "#userManage",
    data: {
        //user
        userList: [],

        show: true,
        addView: false,
        addtitle: "添加学生",

        userName: "",
        password: "123456",
        name: "",
        education: "本科在读",
        phone: "",
        ispost: "1",
        isreply: "1",
        select: "",

        //news
        news: false,
        newsList: [],
        addNews: false,
        addNewstitle: "添加新闻通知",

        newsName: "",
        content: "",
        selectNews: "",

        currentUserName : ""
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
            if (t.userName && t.password && t.name && t.education && t.phone) {
                params = {
                    userName: t.userName,
                    passWord: t.password,
                    name: t.name,
                    education: t.education,
                    phone: t.phone,
                    ispost: t.ispost,
                    isreply: t.isreply
                };
                globalvm.ajaxPost("/user/saveUser", params, function (data) {
                    t.show = true;
                    t.addView = false;
                    t.initList();
                });
            } else {

            }
        },
        cancelEdit: function () {
            var t = this;
            t.show = true;
            t.addView = false;
            t.initList();
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

        addNewsClick: function () {
            var t = this;
            t.news = false;
            t.addNews = true;
        },
        makeSureNews: function () {
            var t = this;
            var params;
            if (t.newsName && t.content) {
                params = {
                    name: t.newsName,
                    content: t.content
                };
                globalvm.ajaxPost("/news/saveNews", params, function (data) {
                    t.news = true;
                    t.addNews = false;
                    t.initNewsList();
                });
            } else {

            }
        },
        cancelEditNews: function () {
            this.news = true;
            this.addNews = false;
            this.initNewsList();
        },

        deletNewsClick: function (event) {
            var t = this;
            var newsId = $(event.currentTarget).attr("newsId");
            globalvm.ajaxGet("/news/deletNews", {id: newsId}, function () {
                t.initNewsList();
            })
        },

        selectNewsClick: function () {
            var t = this;
            var name = t.selectNews;

            globalvm.ajaxGet("/news/findByNameContaining", {name: name}, function (data) {
                t.newsList = data;
            })
        },


        initNewsList: function () {
            var t = this;
            globalvm.ajaxGet("/news/newsList", {}, function (data) {
                t.newsList = data;
            });
        },
        createdNews: function () {
            this.initNewsList();
        }
    },
    created: function () {
        this.initList();
    },


});
stuManage.$watch('select', function (nval, oval) {
    stuManage.selectClick();
})



