var numberx = 0;
$(function() {
	var url_host = window.sessionStorage.getItem('host');
	var urls = [url_host + "/plantlet/nursery/province/find/seedling.action.do", url_host + '/plantlet/nursery/outside/find/seedling.action.do'];
	var companys = GetQueryString('nums')
	var isabroad = GetQueryString('key')

	$.ajax({
		type: "get",
		url: urls[isabroad],
		data: {
			num: numberx,
			comp: companys
		},
		async: true,
		success: function(result) {
			if(result.errorCode == 0) {
				var data = result.data[numberx];
				if(isabroad == 1) {
					$('#nurseryName').html(data['company'] == null ? '' : data['company'])
					$('#nurseryAdd').html(data['address'] == null ? '' : data['address'])
					//$('#nurseryIntro').html(data['nurseryIntro'])
					$('#p_d_c').html(data['province'] == null ? '' : data['province'])
					$('#area').html(data['area'] == 0 ? '' : data['area'])
					var nurseryName = data['company'] == null ? '' : data['company'];
					/*********/
					$('#nurseryAddx').html(data['nurseryAdd'] == null ? '' : data['nurseryAdd'])
					$('#postCode').html(data['postCode'] == 0 ? '' : data['postCode'])
					$('#emailx').html(data['email'])
					$('#contact').html(data['contact'] == null ? '' : data['contact'])
					$('#tels').html(data['tel'] == 0 ? '' : data['tel'])
					$('#fax').html(data['fax'] == 0 ? '' : data['fax'])
					getmore(urls[isabroad], nurseryName, isabroad);

				} else {
					var img = data['picture'] == null ? '' : ' <img class="MXi_L_img" src="' + data['picture'] + '" />';
					$('#imgx').html(img)
					$('#nurseryName').html(data['nurseryName'] == null ? '' : data['nurseryName'])
					$('#nurseryAdd').html(data['nurseryAdd'] == null ? '' : data['nurseryAdd'])
					$('#nurseryIntro').html(data['nurseryIntro'] == null ? '' : data['nurseryIntro'])
					$('#p_d_c').html(data['province'] + '&nbsp;' + data['districts'] + '&nbsp;' + data['county'])
					$('#area').html(data['area'] == 0 ? '' : data['area'])
					var nurseryName = data['nurseryName'] == null ? '' : data['nurseryName'];
					/*********/
					$('#nurseryAddx').html(data['nurseryAdd'] == null ? '' : data['nurseryAdd'])
					$('#postCode').html(data['postCode'] == 0 ? '' : data['postCode'])
					$('#emailx').html(data['email'] == null ? '' : data['email'])
					$('#contact').html(data['contact'] == null ? '' : data['contact'])
					$('#tels').html(data['tel'] == 0 ? '' : data['tel'])
					$('#fax').html(data['fax'] == 0 ? '' : data['fax'])
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
							var specs = data[i]['spec'] == '' ? '' : data[i]['specMin'] == 0 ? '' : data[i]['specMax'] == 0 ? data[i]['spec'] + data[i]['specMin'] : data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'];
							var numsx = data[i]['num'] == 0 ? '' : data[i]['num'];
							html += '<tr class="MX_mc_tr2"><td class="MX_mc_td1">' + data[i]['seedlingName'] + '</td><td class="MX_mc_td2">' + specs + '</td><td class="MX_mc_td3">' + numsx + '</td></tr>';
						});
					} else {

						$.each(data, function(i) {
							var specs = data[i]['spec'] == '' ? '' : data[i]['specMin'] == 0 ? '' : data[i]['specMax'] == 0 ? data[i]['spec'] + data[i]['specMin'] : data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'];
							var numsx = data[i]['num'] == 0 ? '' : data[i]['num'];
							var price = data[i]['dprice']==0?'':data[i]['price'];
							html += '<tr class="MX_mc_tr2"><td class="MX_mc_td1">' + data[i]['plantName'] + '</td><td class="MX_mc_td2">' + specs + '</td><td class="MX_mc_td3">' + numsx + '</td><td class="MX_mc_td4">' + price + '</td></tr>';
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
		if(r != null) return decodeURI(r[2]);
		return null;
	}

})