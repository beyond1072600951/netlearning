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

        fileName: "",

        courseId: "",
        coursePlanId: "",
        chapterId: "",
        coursePlanList: false,
        chapterList: [],
        coursePlanOrder:"",
        chapterNumList:[],
        //sectionList:[],
        // coursePlanSectionList:[],

        addChapterView: false,
        addChapterTitle: "添加课程章节",
        coursePlanName: "",
        chapterDescription: "",
        coursePlanStatus: "",

        addSectionView: false,
        addSectionTitle: "添加小节",

        updateCoursePlantitle: "编辑课程章节",
        updateCoursePlan: false,
        coursePlan:"",
        resourcesUrl:""

    },
    methods: {
        //文件上传
        uploadFile: function (file) {
                var t = this;
                var formData = new FormData();
                formData.append('upload', file.target.files[0]);
                globalvm.ajaxUploadFile("/file/upload", formData, function (data) {
                    console.log(data);
                    t.fileName=data;

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
                    picpath: t.fileName,
                    userId: t.userId
                };
                globalvm.ajaxPost("/courseBase/saveCourseBase", params, function (data) {
                    t.course = true;
                    t.addCourseBase = false;
                    t.initCourseList();
                    t.courseBaseName = "";
                    t.description = "";
                    t.courseStatus = "";
                    t.picpath = "";
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
            this.courseBaseName = "";
            this.description = "";
            this.courseStatus = "";
            this.picpath = "";
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
            this.interChapter(this.courseId);
        },
        /**
         * 课程章节目录
         * @param event
         */
        interChapter: function (courseId) {
            var t = this;
            t.course = false;
            t.coursePlanList = true;
            t.addCourseBase = false;
            globalvm.ajaxGet("/coursePlan/findAllChapterOrderByOrderby", {
                courseId: courseId,
                parentId: null
            }, function (data) {
                t.chapterList = data;
            })
        },
        // interSection: function (parent) {
        //   var t = this;
        //   globalvm.ajaxGet("/coursePlan/findAllByParentIdOrderByOrderby", {parent: parent}, function (data) {
        //       t.sectionList = data;
        //   })
        // },

        /**
         * 返回课程列表
         */
        returnCourseBase: function () {
            this.course = true;
            this.coursePlanList = false;
            this.addCourseBase = false;
        },
        /**
         * 添加课程章节
         */
        addChapter: function () {
            var t = this;
            t.addChapterView = true;
            t.coursePlanList = false;
            globalvm.ajaxGet("/chapterNum/chapterNumList", {level: 1}, function (data) {
                t.chapterNumList = data;
            })
        },


        makeSureChapter: function () {
            var t = this;
            var params;
            if (t.coursePlanName && t.coursePlanStatus && t.chapterDescription && t.courseId) {
                params = {
                    name: t.coursePlanName,
                    description: t.chapterDescription,
                    status: t.coursePlanStatus,
                    courseId: t.courseId,
                    //orderby: t.coursePlanOrder,
                }
            }

            globalvm.ajaxPost("/coursePlan/addCoursePlan", params, function () {
                t.addChapterView = false;
                t.coursePlanList = true;
                t.interChapter(t.courseId);
                t.coursePlanName = "";
                t.chapterDescription = "";
                t.coursePlanStatus = "";
            })
        },
        cancelEditChapter: function () {
            this.addChapterView = false;
            this.coursePlanList = true;
            this.coursePlanName = "";
            this.chapterDescription = "";
            this.coursePlanStatus = "";
        },

        /**
         * 上传资源
         */
        uploadSectionFile: function (file) {
            var t = this;
            var formData = new FormData();
            formData.append('upload', file.target.files[0]);
            globalvm.ajaxUploadFile("/file/upload", formData, function (data) {
                console.log(data);
                t.resourcesUrl=data;
            });
        },
        /**
         * 添加小节
         */
        addSection: function (event) {
            var t = this;
            t.addSectionView = true;
            t.coursePlan = false;
            t.parentId = $(event.currentTarget).attr("chapterId");
            globalvm.ajaxGet("/chapterNum/chapterNumList", {level: 2}, function (data) {
                t.chapterNumList = data;
            })
        },
        makeSureSection: function () {
            var t = this;
            var params;
            if (t.coursePlanName && t.coursePlanStatus && t.chapterDescription && t.courseId && t.parentId) {
                params = {
                    name: t.coursePlanName,
                    description: t.chapterDescription,
                    status: t.coursePlanStatus,
                    courseId: t.courseId,
                    //orderby: t.coursePlanOrder,
                    parentId: t.parentId,
                    url: t.resourcesUrl,
                }
            }

            globalvm.ajaxPost("/coursePlan/addCoursePlan", params, function () {
                t.addSectionView = false;
                t.coursePlan = true;
                t.interChapter(t.courseId);
                t.coursePlanName = "";
                t.chapterDescription = "";
                t.coursePlanStatus = "";
            })
        },
        cancelEditSection: function () {
            this.addSectionView = false;
            this.coursePlan = true;
            this.coursePlanName = "";
            this.chapterDescription = "";
            this.coursePlanStatus = "";
        },

        /**
         * 删除课程章节
         */
        deletChapter: function (event) {
            var t = this;
            var chapterId = $(event.currentTarget).attr("chapterId");
            globalvm.ajaxGet("/coursePlan/deletChapter", {id: chapterId}, function () {
                t.interChapter(t.courseId);
            })
        },

        /**
         * 修改课程章节内容
         */
        updateChapter: function (event) {
            var t = this;
            t.coursePlanList = false;
            t.updateCoursePlan = true;
            t.chapterId = $(event.currentTarget).attr("chapterId");
            globalvm.ajaxGet("/coursePlan/selectById", {id: t.chapterId}, function (data) {
                t.coursePlan = data;
            })
        },
        makeSureUpdateCoursePlan: function () {
            var t = this;
            var params;
            if (t.coursePlan.name && t.coursePlan.description && t.coursePlan.status) {
                params = {
                    id: t.coursePlan.id,
                    name: t.coursePlan.name,
                    description: t.coursePlan.description,
                    status: t.coursePlan.status,
                    //orderby: t.coursePlanOrder,
                }
            }

            globalvm.ajaxPost("/coursePlan/updateCoursePlan", params, function () {
                t.updateCoursePlan = false;
                t.coursePlanList = true;
                t.interChapter(t.courseId);
                t.coursePlanName = "";
                t.chapterDescription = "";
                t.coursePlanStatus = "";
            })
        },
        cancelEditUpdateCoursePlan: function () {
            this.coursePlanList = true;
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



