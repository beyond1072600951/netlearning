var navigation = new Vue({
   el: "#navigation",
   data :{

   },
    methods:{
        userClick:function (index) {
            if(0==index){
                stuManage.show = true;
            }else {
                stuManage.show = false;
            }
            if(3==index){
                stuManage.news = true;
            }else {
                stuManage.news = false;
            }
        }
    }
});
var stuManage = new Vue({
    el: "#userManage",
    data: {
        userList: [],

        show: true,
        news :false,
        addView: false,
        addtitle: "添加学生",

        userName: "",
        password: "123456",
        name: "",
        education: "本科在读",
        phone: "",
        ispost: "1",
        isreply: "1",
        select: ""
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
            globalvm.ajaxGet("/user/findByNameContaining",{userName: userName}, function (data) {
                t.userList = data;
            })
        },
        cancelEdit: function () {

        },
        initList: function () {
            var t = this;
            globalvm.ajaxGet("/user/userList", {}, function (data) {
                console.log(data);
                t.userList = data;
            });
        },

    },
    created: function () {
        this.initList();
    }
});
stuManage.$watch('select', function(nval, oval) {
    stuManage.selectClick();
});
/*var courseManage;
courseManage = new Vue({
    e1:"#courseManage",
    data:{

    },
    methods:{
        addClick: function () {
            var t = this;
            t.show = false;
            t.courseManage = true;
        },
    },
})*/