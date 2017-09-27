var numberx=0;
$(function() {
	var url_host = window.sessionStorage.getItem('host');
	var urls = [url_host + "/plantlet/nursery/province/find/seedling.action.do", url_host + '/plantlet/nursery/outside/find/seedling.action.do'];
	var id = GetQueryString('nums')
	var isabroad = GetQueryString('key')
	$.ajax({
		type: "get",
		url: urls[isabroad],
		data: {
			num: numberx,
			id: id
		},
		async: true,
		success: function(result) {
			if(result.errorCode == 0) {
				var data = result.data[numberx];
				if(isabroad == 1) {
					$('#nurseryName').html(data['company'])
					$('#nurseryAdd').html(data['address'])
					//$('#nurseryIntro').html(data['nurseryIntro'])
					$('#p_d_c').html(data['province'])
					$('#area').html(data['area'])
					var nurseryName = data['company'];
					/*********/
					$('#nurseryAddx').html(data['nurseryAdd'])
					$('#postCode').html(data['postCode'])
					$('#emailx').html(data['email'])
					$('#contact').html(data['fax'])
					$('#tels').html(data['tel'])
					$('#fax').html(data['fax'])
					getmore(urls[isabroad], nurseryName, isabroad);

				} else {
					var img = data['picture'] == null ? '' : ' <img class="MXi_L_img" src="' + data['picture'] + '" />';
					$('#imgx').html(img)
					$('#nurseryName').html(data['nurseryName'])
					$('#nurseryAdd').html(data['nurseryAdd'])
					$('#nurseryIntro').html(data['nurseryIntro'])
					$('#p_d_c').html(data['province'] + '&nbsp;' + data['districts'] + '&nbsp;' + data['county'])
					$('#area').html(data['area'])
					var nurseryName = data['nurseryName'];
					/*********/
					$('#nurseryAddx').html(data['nurseryAdd'])
					$('#postCode').html(data['postCode'])
					$('#emailx').html(data['email'])
					$('#contact').html(data['fax'])
					$('#tels').html(data['tel'])
					$('#fax').html(data['fax'])
					getmore(urls[isabroad], nurseryName);
				}
			}
		},
		error: function(e) {
			console.log(e.status);
		}
	});

	function getmore(urlx, nurseryName, isabroad) {
		$.ajax({
			type: "get",
			url: urlx,
			data: {
				comp: nurseryName
			},
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					var html = '';
					if(isabroad == 1) {
						//省外
						$.each(data, function(i) {
							html += '<div class="MX_mc_line">' +
								'<div class="MX_mc_line_one"><span>' + data[i]['seedlingName'] + '</span></div>' +
								'<div class="MX_mc_line_two"><span>' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</span></div>' +
								'<div class="MX_mc_line_three"><span>' + data[i]['num'] + '</span></div>' +
								'</div>';
						});
					} else {

						$.each(data, function(i) {
							html += '<div class="MX_mc_line">' +
								'<div class="MX_mc_line_one"><span>' + data[i]['plantName'] + '</span></div>' +
								'<div class="MX_mc_line_two"><span>' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</span></div>' +
								'<div class="MX_mc_line_three"><span>' + data[i]['num'] + '</span></div>' +
								'</div>';
						});
					}

					$('#show_mp').html(html);
				} else {
					$('#show_mp').html(result.msg);
				}
			},
			error: function(e) {
				console.log(e.status);
			}
		});

	}

	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return unescape(r[2]);
		return null;
	}

})