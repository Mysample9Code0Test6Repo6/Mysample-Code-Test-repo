/*  ::

    :: Theme        : Jets
    :: Theme URI    : http://labs.funcoders.com/html/Jets

    :: File         : home.js
    :: About        : Scripts for Home page
    :: Version      : 1.3.1

::  */
var Home = {
    init: function() {

        this.slider();

    },
    slider : function () {
        $(window).on('load', function () {

            setTimeout(function() {

                var revolutionSlider = $('#page-slider'),
                    startwidth = (revolutionSlider.parents('body.w970').length) ? 960 : 1170;

                revolutionSlider.revolution({
                    delay           : 9000,
                    startheight     : 500,
                    hideThumbs      : 10,

                    touchenabled    : 'on',
                    onHoverStop     : 'on',
                    startwidth      : startwidth,

                    navOffsetHorizontal : 0,
                    navOffsetVertical   : 0,

                    stopAtSlide     : -1,
                    stopAfterLoops  : -1,

                    fullWidth       : 'on'
                });

                revolutionSlider.bind('revolution.slide.onloaded', function (e, data) {
                    $(this).parent().css({
                        background  : 'transparent',
                        height      : 'auto',
                        overflow    : 'visible'
                    }).children().animate({
                        opacity     : 1
                    }, 500);
                });

            }, 100);
        });
    }
};

Home.init();