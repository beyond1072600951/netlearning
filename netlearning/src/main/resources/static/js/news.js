var newsManage = new Vue({
    el: "#newsManage",
    data: {
        news: true,
        newsMapList: [],
        addNews: false,
        addNewstitle: "添加新闻通知",

        newsName: "",
        content: "",
        selectNews: "",
        currentUserName : "",
        releaseTime:"",
        userId:"",
    },
    methods: {

        addNewsClick: function () {
            var t = this;
            t.news = false;
            t.addNews = true;
            t.initMyInfo();
        },
        makeSureNews: function () {
            var t = this;
            t.initMyInfo();
            t.findUserId();
            var params;
            if (t.newsName && t.content) {
                params = {
                    name: t.newsName,
                    content: t.content,
                    userId:t.userId,
                };
                globalvm.ajaxPost("/news/saveNews", params, function (data) {
                    t.news = true;
                    t.addNews = false;
                    t.initNewsList();
                    t.newsName = "";
                    t.content = "";
                });
            } else {
                alert("请填写所有信息！！");
            }
        },
        cancelEditNews: function () {
            this.news = true;
            this.addNews = false;
            this.initNewsList();
            this.newsName = "";
            this.content = "";
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
                t.newsMapList = data;
            })
        },

        findUserId:function () {
          var t = this;
          t.initMyInfo();
          globalvm.ajaxGet("/user/findUserId",{name: t.currentUserName}, function (data) {
              newsManage.userId = data.id;
          })
        },
        initMyInfo: function () {
            var t = this;
            globalvm.ajaxGet("/user/findById", {}, function (data) {
                newsManage.currentUserName = data.name;
            });
        },

        initNewsList: function () {
            var t = this;
            globalvm.ajaxGet("/news/newsList", {}, function (data) {
                t.newsMapList = data;
            });
        },
        createdNews: function () {
            this.initNewsList();
        },


    },
    created: function () {
        this.initNewsList();
    },



});
newsManage.$watch('selectNews', function (nval, oval) {
    newsManage.selectNewsClick();
})
