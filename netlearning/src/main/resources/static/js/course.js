var courseManage = new Vue({
    el: "#courseManage",
    data: {

        course: true,
        courseBaseMapList: [],
        selectCourse: "",

        userId: "",
        addCourseBase: false,
        addCourseBasetitle: "添加课程",
        currentUserName: "",
        picpath: "",
        courseBaseName: "",
        description: "",
        courseStatus: "",

        courseId: "",
        coursePlanId: "",
        chapterId: "",
        coursePlan: false,
        byCourseIdList: [],
        // coursePlanSectionList:[],

        addCoursePlanView: false,
        addCoursePlantitle: "添加课程章节",
        coursePlanName: "",
        chapterDescription: "",
        coursePlanStatus: "",

        updateCoursePlantitle: "编辑课程章节",
        updateCoursePlan: false,

    },
    methods: {
        //文件上传
        uploadFile: function (file) {
            var t = this;
            var formData = new FormData();
            formData.append('upload', file.target.files[0]);
            globalvm.ajaxUploadFile("/file/upload", formData, function (data) {
                console.log(data);
            });
        },
        /**
         * 添加课程
         */
        addCourseClick: function () {
            this.course = false;
            this.addCourseBase = true;
            this.initMyInfo();
        },
        /**
         * 确定添加课程
         */
        makeSureCourseBase: function () {
            var t = this;
            t.initMyInfo();
            t.findUserId();
            var params;
            if (t.courseBaseName && t.courseBaseName && t.courseStatus) {
                params = {
                    name: t.courseBaseName,
                    description: t.description,
                    status: t.courseStatus,
                    // picpath:t.picpath,
                    userId: t.userId,
                };
                globalvm.ajaxPost("/courseBase/saveCourseBase", params, function (data) {
                    t.course = true;
                    t.addCourseBase = false;
                    t.initCourseList();
                    t.courseBaseName = "";
                    t.description = "";
                    t.courseStatus = "";
                });
            } else {

            }
        },
        /**
         * 取消添加课程
         */
        cancelEditCourseBase: function () {
            this.course = true;
            this.addCourseBase = false;
            //this.initCourseList();
        },

        /**
         * 删除课程
         */
        deletCourseBase: function (event) {
            var t = this;
            var courseBaseId = $(event.currentTarget).attr("courseBaseId");
            globalvm.ajaxGet("/courseBase/deletCourseBase", {id: courseBaseId}, function () {
                t.initCourseList();
            })
        },
        /**
         * 获取选择课程的id，并调用获取课程目录的方法
         */
        obtainCourse: function (event) {
            this.courseId = $(event.currentTarget).attr("courseId");
            this.interCourse(this.courseId);
        },
        /**
         * 课程章节目录
         * @param event
         */
        interCourse: function (courseId) {
            var t = this;
            t.course = false;
            t.coursePlan = true;
            t.addCourseBase = false;
            globalvm.ajaxGet("/coursePlan/findAllChapterOrderByOrderby", {
                courseId: courseId,
                parentId: null
            }, function (data) {
                t.byCourseIdList = data;
            })
        },

        /**
         * 返回课程列表
         */
        returnCourseBase: function () {
            this.course = true;
            this.coursePlan = false;
            this.addCourseBase = false;
        },
        /**
         * 添加课程章节
         */
        addChapter: function () {
            this.addCoursePlanView = true;
            this.coursePlan = false;
            this.initMyInfo();
        },
        makeSureCoursePlan: function () {
            var t = this;
            t.initMyInfo();
            t.findUserId();
            var params;
            if (t.coursePlanName && t.coursePlanStatus && t.chapterDescription) {
                params = {
                    name: t.coursePlanName,
                    description: t.chapterDescription,
                    status: t.coursePlanStatus,
                    userId: t.userId,

                }
            }

            globalvm.ajaxPost("/coursePlan/addCoursePlan", params, function () {
                t.addCoursePlanView = false;
                t.coursePlan = true;
                t.interCourse();
            })
        },
        cancelEditCoursePlan: function () {
            this.addCoursePlanView = false;
            this.coursePlan = true;
        },


        /**
         * 删除课程章节
         */
        deletChapter: function (event) {
            var t = this;
            var chapterId = $(event.currentTarget).attr("chapterId");
            globalvm.ajaxGet("/coursePlan/deletChapter", {id: chapterId}, function () {
                t.interCourse(t.courseId);
            })
        },

        /**
         * 修改课程章节内容
         */
        updateChapter: function (event) {
            this.coursePlan = false;
            this.updateCoursePlan = true;
        },
        makeSureUpdateCoursePlan: function () {

        },
        cancelEditUpdateCoursePlan: function () {
            this.coursePlan = true;
            this.updateCoursePlan = false;
        },

        /**
         * 获取登录人id
         */
        findUserId: function () {
            var t = this;
            t.initMyInfo();
            globalvm.ajaxGet("/user/findUserId", {name: t.currentUserName}, function (data) {
                courseManage.userId = data.id;
            })
        },
        /**
         * 获取当前登录人名字
         */
        initMyInfo: function () {
            var t = this;
            globalvm.ajaxGet("/user/findById", {}, function (data) {
                courseManage.currentUserName = data.name;
            });
        },
        /**
         * 课程列表
         */
        initCourseList: function () {
            var t = this;
            globalvm.ajaxGet("/courseBase/courseBaseList", {}, function (data) {
                t.courseBaseMapList = data;
            });
        },
        createCourse: function () {
            this.initCourseList();
        },

        selectCourseClick: function () {
            var t = this;
            var name = t.selectCourse;
            globalvm.ajaxGet("/courseBase/findByNameContaining", {name: name}, function (data) {
                t.courseBaseMapList = data;
            })
        },

    },
    created: function () {
        this.initCourseList();
        //this.interCourse();
    },


});
courseManage.$watch('selectCourse', function (nval, oval) {
    courseManage.selectCourseClick();
})



