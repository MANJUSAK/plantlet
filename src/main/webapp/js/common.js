/***************页面头部的当前时间*****************/
setInterval("domes()", 1000);
function domes() {
	var time = new Date();
	var full = time.getFullYear() + '';
	var month = time.getMonth() + 1;
	var dates = time.getDate();
	var hourss = time.getHours();
	var minutess = time.getMinutes();
	var secondss = time.getSeconds();
	var days = time.getDay();
	var weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
	if(hourss<10){
		hourss="0"+hourss;
	}
	if(minutess<10){
		minutess="0"+minutess;
	}
	if(secondss<10){
		secondss="0"+secondss;
	}
	var yier = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'];
	var naian = yier[full.charAt(0)] + yier[full.charAt(1)] + yier[full.charAt(2)] + yier[full.charAt(3)];
	str = naian + "-" + month + "-" + dates + " " + hourss + ":" + minutess + ":" + secondss + "  " + weeks[days];
	document.getElementById("Span").innerHTML = str;
	
}
/***************页面头部的当前时间  结束*****************/





