var forumManage = new Vue({
    el: "#forumManage",
    data: {
        postView:true,
        selectPost:"",
        postMapList:[],
        postId:"",
        currentUserName:"",
        userId:"",

        addPost:false,
        addPostTitle:"发帖",
        postTitle:"",
        postContent:"",

        replyView:false,
        byPostIdList:[],
        addReply:false,
        addReplyTitle:"回复",
        replyContent:"",
    },
    methods: {
        addPostClick: function () {
            this.postView = false;
            this.addPost = true;
            this.initMyInfo();
        },
        makeSurePost: function () {
            var t = this;
            t.initMyInfo();
            t.findUserId();
            var params;
            if (t.postTitle && t.postContent){
                params = {
                    title: t.postTitle,
                    content: t.postContent,
                    userId: t.userId,
                };
                globalvm.ajaxPost("/post/savePost", params,function () {
                    t.postView = true;
                    t.addPost = false;
                    t.initPostList();
                    t.postTitle = "";
                    t.postContent = "";
                });
            }else{

            }
        },
        cancelEditPost: function () {
            this.postView = true;
            this.addPost = false;
            this.initPostList();
        },

        deletPost: function () {
            var t = this;
            var postId = $(event.currentTarget).attr("postId");
            globalvm.ajaxGet("/post/deletPost", {id:postId}, function () {
                t.initPostList();
            })
        },

        selectPostClick: function () {
            var t = this;
            var title = t.selectPost;
            globalvm.ajaxGet("/post/findByTitleContaining", {title: title}, function (data) {
                t.postMapList = data;
            })
        },

        returnPost: function () {
            this.replyView = false;
            this.postView = true;
        },
        addReplyClick:function () {
            this.replyView = false;
            this.addReply = true;
            this.initMyInfo();
        },
        makeSureReply: function () {
            var t = this;
            t.initMyInfo();
            t.findUserId();
            var params;
            if (t.replyContent){
                params = {
                    content: t.replyContent,
                    userId: t.userId,
                    postId: t.postId,
                };
                globalvm.ajaxPost("/reply/saveReply", params,function () {
                    t.replyView = true;
                    t.addReply = false;
                    t.interReply(t.postId);
                    t.replyContent = "";
                });
            }else{

            }
        },
        cancelEditReply: function () {
            this.replyView = true;
            this.addReply = false;
            this.interReply(this.postId);
        },
        deletReply: function () {
          var t = this;
          var replyId = $(event.currentTarget).attr("replyId");
          globalvm.ajaxGet("/reply/deletReply", {id:replyId}, function () {
              t.interReply(t.postId);
          })
        },
        obtainReply: function (event) {
            this.postId = $(event.currentTarget).attr("postId");
            this.interReply(this.postId);
        },
        interReply: function (postId) {
            var t = this;
            t.postView = false;
            t.replyView = true;
            globalvm.ajaxGet("/reply/replyList", {postId: postId}, function (data) {
                t.byPostIdList = data;
            })
        },

        initPostList: function () {
            var t = this;
            globalvm.ajaxGet("/post/postList", {}, function (data) {
                t.postMapList = data;
            })
        },
        findUserId:function () {
            var t = this;
            t.initMyInfo();
            globalvm.ajaxGet("/user/findUserId",{name: t.currentUserName}, function (data) {
                forumManage.userId = data.id;
            })
        },
        initMyInfo: function () {
            var t = this;
            globalvm.ajaxGet("/user/findById", {}, function (data) {
                forumManage.currentUserName = data.name;
            });
        },
    },
    created: function () {
        this.initPostList();
    },
});
forumManage.$watch('selectPost', function (nval, oval) {
    forumManage.selectPostClick();
})
