var html = '';
var numberx = 0;
$(function() {
	var url_gx = window.sessionStorage.getItem('host') + '/plantlet/plant/find/supply/seedling.action.do';
	var mydata = {
		num: numberx
	}
	var show_page = '.gx-content';

	getdata(url_gx, mydata, show_page, false);
	//加载更多
	$('.jzm-center').click(function() {
		mydata.num = mydata.num + 1;
		getdata(url_gx, mydata, show_page, true);
	})

	function getdata(url_, mydata, showpage, ismore) {

		$.ajax({
			type: "get",
			url: url_,
			data: mydata,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data
					$.each(data, function(i) {
						var img = data[i]['picture']==null?'':data[i]['picture'][0];
						html += '<div class="gxc-text">' +
							'<div class="gxc-left"><img src="' + img + '"/></div>' +
							'<div class="gxc-right">' +
							'<div class="gr-title">' +
							'<a href="#0">' + data[i]['sdName'] + '</a>' +
							'</div>' +
							'<div class="gr-text">' +
							'<p>' + data[i]['seedlingIntro'] + '</p>' +
							'</div>' +
							'</div>' +
							'</div>';

					});
					$(showpage).html(html);
					$('.gxc-text').click(function() {
						var num = $(this).index();
						var id = num < 20 ? num : num / 20;
						var index = 0;
						if((num + 1) < 20) {
							index = 0;
						} else {
							index = Math.ceil((num - (num % 20)) / 20);
						}

						var test = index + ',' + id;
						window.sessionStorage.setItem('gx_detail', test);
						//console.log(test)
						window.open('gongxu-details.html');

					})
				} else {
					if(ismore) {
						alert('已经没有更多数据了')
					} else {
						$(showpage).html('非常抱歉，暂时没此项数据');
					}
				}

			},
			error: function(e) {
				console.log(e.status)
			}
		});
	}

})