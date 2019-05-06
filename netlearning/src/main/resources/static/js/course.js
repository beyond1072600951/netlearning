
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
        courseStatus:"可用"
    },
    methods: {

        addCourseClick: function () {
            this.course = false;
            this.addCourseBase = true;
        },
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
                    t.initNewsList();
                });
            } else {

            }
        },
        cancelEditCourseBase: function () {
            this.course = true;
            this.addCourseBase = false;
            this.initCourseList();
        },
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

    },



});
courseManage.$watch('selectCourse', function (nval, oval) {
    courseManage.selectCourseClick();
})



