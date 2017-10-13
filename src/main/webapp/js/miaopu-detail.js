var numberx = 0;
$(function() {
	//获取同意地址
	var url_host = window.sessionStorage.getItem('host');
	//拼接请求路径
	var urls = [url_host + "/plantlet/nursery/province/find/seedling.action.do", url_host + '/plantlet/nursery/outside/find/seedling.action.do'];
	//获取浏览器地址栏参数	传入 参数名称即可（支持中文参数值
	//此参数为苗圃名称
	var companys = GetQueryString('nums')
	//	此参数判断是否为身外	1为省外	0为省内
	var isabroad = GetQueryString('key')
	//ajax请求数据
	$.ajax({
		type: "get",
		url: urls[isabroad],
		data: {
			num: numberx,
			comp: companys
		},
		async: true,
		success: function(result) {
			console.log(result)
			//判断数据是否正确
			if(result.errorCode == 0) {
				//提取数据集合第一个数据
				var data = result.data[numberx];
				//判断是否为省外	
				if(isabroad == 1) {
					$('#nurseryName').html(data['company'] == null ? '' : data['company'])
					$('#nurseryAdd').html(data['address'] == null ? '' : data['address'])
					//$('#nurseryIntro').html(data['nurseryIntro'])
					$('#p_d_c').html(data['province'] == null ? '' : data['province'])
					$('#area').html(data['area'] <2 ? '' : data['area']*667)
					//提取苗木企业名称
					var nurseryName = data['company'] == null ? '' : data['company'];
					/*********/
					$('#nurseryAddx').html(data['nurseryAdd'] == null ? '' : data['nurseryAdd'])
					$('#postCode').html(data['postCode'] == 0 ? '' : data['postCode'])
					$('#emailx').html(data['email'])
					$('#contact').html(data['contact'] == null ? '' : data['contact'])
					$('#tels').html(data['tel'] == 0 ? '' : data['tel'])
					$('#fax').html(data['fax'] == 0 ? '' : data['fax'])
					//根据苗木企业名称查询企业下的苗圃信息
					getmore(urls[isabroad], nurseryName, isabroad);

				} else {
					var img = data['picture'] == null ? '' : ' <img class="MXi_L_img" src="' + data['picture'] + '" />';
					$('#imgx').html(img)
					$('#nurseryName').html(data['nurseryName'] == null ? '' : data['nurseryName'])
					$('#nurseryAdd').html(data['nurseryAdd'] == null ? '' : data['nurseryAdd'])
					$('#nurseryIntro').html(data['nurseryIntro'] == null ? '' : data['nurseryIntro'])
					$('#p_d_c').html(data['province'] + '&nbsp;' + data['districts'] + '&nbsp;' + data['county'])
					$('#area').html(data['area']<2 ? '' : data['area']*667)
					//提取苗木企业名称
					var nurseryName = data['nurseryName'] == null ? '' : data['nurseryName'];
					/*********/
					$('#nurseryAddx').html(data['nurseryAdd'] == null ? '' : data['nurseryAdd'])
					$('#postCode').html(data['postCode'] == 0 ? '' : data['postCode'])
					$('#emailx').html(data['email'] == null ? '' : data['email'])
					$('#contact').html(data['contact'] == null ? '' : data['contact'])
					$('#tels').html(data['tel'] == 0 ? '' : data['tel'])
					$('#fax').html(data['fax'] == 0 ? '' : data['fax'])
					//根据苗木企业名称查询企业下的苗圃信息
					getmore(urls[isabroad], nurseryName);
				}
			}
		},
		error: function(e) {
			console.log(e.status);
		}
	});
	/**
	 * 获取苗圃企业下的苗圃信息
	 * @param {Object} urlx	请求地址	
	 * @param {Object} nurseryName	苗木企业名称
	 * @param {Object} isabroad	是否省内
	 */
	function getmore(urlx, nurseryName, isabroad) {
		$.ajax({
			type: "get",
			url: urlx,
			data: {
				comp: nurseryName
			},
			async: true,
			success: function(result) {
				console.log(result)
				if(result.errorCode == 0) {
					var data = result.data;
					var html = '';
					if(isabroad == 1) {
						//循环省外数据
						$.each(data, function(i) {
							var specs = data[i]['spec'] == '' ? '' : data[i]['specMin'] == 0 ? '' : data[i]['specMax'] == 0 ? data[i]['spec'] + data[i]['specMin'] : data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'];
							var numsx = data[i]['num'] == 0 ? '' : data[i]['num'] + data[i]['unit'];
							var price = data[i]['dprice'] == 0 ? '' : data[i]['price'] + '元';
							html += '<tr class="MX_mc_tr2"><td class="MX_mc_td1">' + data[i]['seedlingName'] + '</td><td class="MX_mc_td2">' + specs + '</td><td class="MX_mc_td3">' + numsx + '</td><td class="MX_mc_td4">' + price + '</td></tr>';
						});
					} else {
						//循环省内数据
						$.each(data, function(i) {
							var specs = data[i]['spec'] == '' ? '' : data[i]['specMin'] == 0 ? '' : data[i]['specMax'] == 0 ? data[i]['spec'] + data[i]['specMin'] : data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'];
							var numsx = data[i]['num'] == 0 ? '' : data[i]['num'] + data[i]['unit'];
							var price = data[i]['dprice'] == 0 ? '' : data[i]['price'] + '元';
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
	//获取地址栏	参数 	传入参数名称即可获取参数值（支持中文）
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return decodeURI(r[2]);
		return null;
	}

})