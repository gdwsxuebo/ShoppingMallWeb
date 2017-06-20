;(function () {
    var pos = {
        setPageHeight: function () {
            var clientWidth = document.documentElement.clientWidth,
                clientHeight = document.documentElement.clientHeight;
            $("body").height(clientHeight);
        }()
    };
    window.pos = pos;
    window.onresize = function () {
        var clientWidth = document.documentElement.clientWidth,
            clientHeight = document.documentElement.clientHeight;
        $("body").height(clientHeight);
    }
})(jQuery);
