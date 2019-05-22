var courseRecordManage = new Vue({
    el: "#courseRecordManage",
    data: {
        record:true,
        selectRecord:"",
        recordMapList:[],
    },
    methods: {

        selectRecordClick: function () {
            var t = this;
            var userName = t.selectRecord;
            if (userName != ""){
                globalvm.ajaxGet("/courseRecord/findByNameContaining", {userName: userName}, function (data) {
                    t.recordMapList = data;
                })
            }else{
                t.initRecordList();
            }
        },

        initRecordList: function () {
            var t = this;
            globalvm.ajaxGet("/courseRecord/recordList", {}, function (data) {
                t.recordMapList = data;
            });
        },
        createdRecord: function () {
            this.initRecordList();
        },


    },
    created: function () {
        this.initRecordList();
    },



});
courseRecordManage.$watch('selectRecord', function (nval, oval) {
    courseRecordManage.selectRecordClick();
})
