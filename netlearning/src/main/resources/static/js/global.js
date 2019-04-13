var token = $.cookie("token");
// $.removeCookie("token");
var startUrl = "/sc";
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
        }
    }
});
var loading = new Vue({
    el: '#login'
})
