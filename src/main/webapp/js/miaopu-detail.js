$(function() {
	var mp_detail = window.sessionStorage.getItem('mp_detail');
	var datax = mp_detail.split(',');
	$.ajax({
		type: "get",
		url: window.sessionStorage.getItem('host') + "/plantlet/nursery/province/find/seedling.action.do",
		data: {
			num: datax[0],
		},
		async: true,
		success: function(result) {
			if(result.errorCode == 0) {
				var data = result.data[datax[1]];
                var img = data['picture']==null?'':' <img class="MXi_L_img" src="'+data['picture']+'" />';
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

				getmore(nurseryName);
			}
		},
		error: function(e) {
			console.log(e.status);
		}
	});

	function getmore(nurseryName) {
		$.ajax({
			type: "get",
			url: window.sessionStorage.getItem('host') + "/plantlet/nursery/province/find/seedling.action.do",
			data: {
				keyWord: nurseryName
			},
			async: true,
			success: function(result) {
				console.log(result)
				if(result.errorCode == 0) {
					var data = result.data;
					var html = '';
					$.each(data, function(i) {
						html += '<div class="MX_mc_line">' +
							'<div class="MX_mc_line_one"><span>' + data[i]['plantName'] + '</span></div>' +
							'<div class="MX_mc_line_two"><span>' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</span></div>' +
							'<div class="MX_mc_line_three"><span>' + data[i]['num'] + '</span></div>' +
							'</div>';
					});
					$('#show_mp').html(html);
				}
			},
			error: function(e) {
				console.log(e.status);
			}
		});

	}

})