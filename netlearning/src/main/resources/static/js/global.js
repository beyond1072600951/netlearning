var token = $.cookie("token");
// $.removeCookie("token");
var startUrl = "/learning";
var globalvm = new Vue({
    methods: {
        ajaxGet: function (url, params, callback) {
            if (!params) {
                params = {};
            }
            this.$http.get(startUrl + url, {params: params, headers: {Authorization: token}}).then(function (res) {
                if (res.body.state == "success") {
                    callback(res.body.data);
                } else {
                    alert(res.message);
                }
            }, function (res) {
            });
        },
        ajaxPost: function (url, params, callback) {
            if (!params) {
                params = {};
            }
            this.$http.post(startUrl + url, params, {
                emulateJSON: true,
                headers: {Authorization: token}
            }).then(function (res) {
                if (res.body.state == "success") {
                    callback(res.body.data);
                } else {
                    alert(res.message);
                }
            }, function () {
            });
        },
        ajaxUpload: function (url, params, callback) {
            if (!params) {
                params = {};
            }
            this.$http.post(url, params, {
                headers: {Authorization: token},
                contentType: false,
                processData: false,
            }).then(function (res) {
                if (res.body.state == "success") {
                    callback(res.body.data);
                } else {
                    alert(res.message);
                }
            }, function () {
            });
        },
        ajaxUploadFile: function (url, formData, callback) {
            this.$http.post(startUrl + url, formData, {
                emulateJSON: true,
                headers: {Authorization: token, 'Content-Type': 'multipart/form-data'}
            }).then(function (res) {
                if (res.body.state == "success") {
                    callback(res.body.data);
                } else {
                    alert(res.message);
                }
            }, function () {
            });
        },
        outLogin: function () {
            $.removeCookie("token");
            window.location.href = "/learning";
        }
    }
});
var loading = new Vue({
    el: '#login'
})
