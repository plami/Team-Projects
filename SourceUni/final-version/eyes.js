/**
 * Created by GLAVUN on 18.6.2014 г..
 */


var createEye = function () {
    var eyes = [];
    var eye;
    var lens;


    eye = document.createElement("div");
    eye.classList.add("eye");
    lens = document.createElement("span");
    eye.appendChild(lens);

    document.getElementById('guz4').appendChild(eye);
    eyes.push({
    iris: eye,
    lens: lens
    });

    return eyes;

    };
var createEye2 = function () {
    var eyes2 = [];
    var eye2;
    var lens2;


    eye2 = document.createElement("div");
    eye2.classList.add("eye2");
    lens2 = document.createElement("span");
    eye2.appendChild(lens2);
    document.getElementById('guz4').appendChild(eye2);
    eyes2.push({
    iris: eye2,
    lens: lens2
    });

    return eyes2;

    };

var eyes = createEye();
var eyes2 = createEye2();


document.addEventListener("mousemove", function (event) {

    var x = event.pageX;
    var y = event.pageY;

    eyes.forEach(function (eye) {

    var offsets = eye.lens.getBoundingClientRect();
    var left = (offsets.left - x);
    var top = (offsets.top - y);
    var rad = Math.atan2(top, left);

    eye.iris.style.webkitTransform = "rotate(" + rad + "rad)";
        eye.iris.style.MozTransform = "rotate(" + rad + "rad)";
        eye.iris.style.msTransform = "rotate(" + rad + "rad)";
    });
    eyes2.forEach(function (eye2) {

    var offsets = eye2.lens.getBoundingClientRect();
    var left = (offsets.left - x);
    var top = (offsets.top - y);
    var rad = Math.atan2(top, left);

    eye2.iris.style.webkitTransform = "rotate(" + rad + "rad)";
        eye2.iris.style.MozTransform = "rotate(" + rad + "rad)";
        eye2.iris.style.msTransform = "rotate(" + rad + "rad)";
    });

 });



