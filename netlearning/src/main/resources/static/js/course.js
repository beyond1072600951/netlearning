
var courseManage = new Vue({
    el: "#courseManage",
    data: {

        course: true,
        courseList:[],
        selectCourse:"",

        addCourseBase:false,
        addCourseBasetitle:"添加课程",
        currentUserName: "",
        picpath:"",
        courseBaseName:"",
        description:"",
        courseStatus:"可用",

        courseId:"",
        coursePlanId:"",
        chapterId:"",
        coursePlan:false,
        byCourseIdList:[],
        // coursePlanSectionList:[],

        addCoursePlanView:false,
        addCoursePlantitle:"添加课程章节",
        coursePlanName:"",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        chapterDescription:"",
        coursePlanStatus:"可用",

        updateCoursePlantitle:"编辑课程章节",
        updateCoursePlan:false,

    },
    methods: {
        /**
         * 添加课程
         */
        addCourseClick: function () {
            this.course = false;
            this.addCourseBase = true;
        },
        /**
         * 确定添加课程
         */
        makeSureCourseBase: function () {
            var t = this;
            var params;
            if (t.courseBaseName && t.courseBaseName && t.courseStatus && t.picpath) {
                params = {
                    name: t.courseBaseName,
                    description: t.description,
                    status:t.courseStatus,
                    picpath:t.picpath,

                };
                globalvm.ajaxPost("/courseBase/saveCourseBase", params, function (data) {
                    t.course = true;
                    t.addCourseBase = false;
                    t.initCourseList();
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
         * 课程章节目录
         * @param event
         */
        interCourse:function (event) {
            var t = this;
            t.course = false;
            t.coursePlan = true;
            t.addCourseBase = false;
            var courseId = $(event.currentTarget).attr("courseId");
            globalvm.ajaxGet("/coursePlan/findAllChapterOrderByOrderby", {courseId: courseId,parentId:null}, function (data) {
                t.byCourseIdList = data;
            })
        },

        /**
         * 返回课程列表
         */
        returnCourseBase:function () {
            this.course = true;
            this.coursePlan = false;
            this.addCourseBase = false;
        },
        /**
         * 添加课程章节
         */
        addChapter:function () {
            this.addCoursePlanView = true;
            this.coursePlan = false;
        },
        makeSureCoursePlan:function () {
            var  t = this;
            var params;
            if (t.coursePlanName && t.coursePlanStatus && t.chapterDescription){
                params = {
                    name:t.coursePlanName,
                    description:t.chapterDescription,
                    status:t.coursePlanStatus,
                }
            }

            globalvm.ajaxPost("/coursePlan/addCoursePlan", params, function () {
                t.addCoursePlanView = false;
                t.coursePlan = true;
                t.interCourse();
            })
        },
        cancelEditCoursePlan:function () {
            this.addCoursePlanView = false;
            this.coursePlan = true;
        },


        /**
         * 删除课程章节
         */
        deletChapter:function (event) {
            var t = this;
            var chapterId = $(event.currentTarget).attr("chapterId");
            globalvm.ajaxGet("/coursePlan/deletChapter",{id:chapterId},function () {
                t.interCourse();
            })
        },

        /**
         * 修改课程章节内容
         */
        updateChapter:function (event) {
            this.coursePlan = false;
            this.updateCoursePlan = true;
        },
        makeSureUpdateCoursePlan:function () {

        },
        cancelEditUpdateCoursePlan:function () {
            this.coursePlan = true;
            this.updateCoursePlan = false;
        },
        /**
         * 课程列表
         */
        initCourseList:function () {
            var t = this;
            globalvm.ajaxGet("/courseBase/courseBaseList", {}, function (data) {
                t.courseList = data;
                console.log("---",t.courseList)
            });
        },
        createCourse:function () {
            this.initCourseList();
        },

        selectCourseClick:function () {
            var t = this;
            var name = t.selectCourse;

            globalvm.ajaxGet("/courseBase/findByNameContaining", {name: name}, function (data) {
                t.courseList = data;
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



