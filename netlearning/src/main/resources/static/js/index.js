var navigation = new Vue({
    el: "#navigation",
    data: {},
    methods: {
        userClick: function (index) {
            if (0 == index) {
                window.location.href="/learning/index";
            }
            if (1 == index) {
                window.location.href="/learning/course";
            }
            if (2 == index) {
                window.location.href="/learning/statistics";
            }
            if (3 == index) {
                window.location.href="/learning/news";

            }
            if(4 == index){
                window.location.href="/learning/forum";
            }
        }
    }
});


