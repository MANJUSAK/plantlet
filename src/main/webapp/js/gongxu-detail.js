$(function() {
	var url_gx = window.sessionStorage.getItem('host') + '/plantlet/plant/find/supply/seedling.action.do';
	var detialx = window.sessionStorage.getItem('gx_detail');
	var datax =detialx.split(',') ;
	var mydatax={
		num:datax[0]
	};
getdetail(url_gx,mydatax);
	function getdetail(url_x, mydatax) {
		$.ajax({
			type: "get",
			url: url_x,
			data: mydatax,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data[datax[1]];
					var img = data['picture']==null?'':' <img src="'+data['picture']+'" />';
					$('#imgshow').html(img);
					$('#titlex').html(data['sdName']);
					$('#copmx').html(data['seedlingComp']);
					$('#spce').html(data['spec'] + data['specMax'] + '-' + data['specMin']);
					$('#numx').html(data['num']);
					$('#copms').html(data['seedlingComp']);
					$('#contact').html(data['contact']);
					$('#sdAdd').html(data['sdAdd']);
					$('#telx').html(data['tel']);
					$('#seedlingIntro').html(data['seedlingIntro']);
					$('#copmz').html(data['seedlingComp']);
					$('#telxx').html(data['tel']);
				}
			}
		});
	}
})