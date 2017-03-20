$(document).ready(function(){
	// 默认禁用搜索和排序
//	$.extend( $.fn.dataTable.defaults, {
//	    searching: false,
//	    ordering:  false,
//	    info : false,
//	    language: {
//            "lengthMenu": "每页 _MENU_ 条记录",
//            "zeroRecords": "没有找到记录",
//            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
//            "infoEmpty": "无记录",
//            "infoFiltered": "(从 _MAX_ 条记录过滤)"
//        }
//	} );
})
function getCurrentTime() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
/**
 * 加载数据到select，并且选中某个值
 * @param url 	数据地址
 * @param id	select的id值
 * @param objName 返回的对象名称 如：data
 * @param textName	设置为显示的 属性 如：eventName
 * @param valueName	设置为
 * @param config 请求参数
 * @param func	回调函数
 * @param selValue	需要设置选中的值
 * @returns
 */
function loadSelector(url, id, objName, textName, valueName, config,func,selValue) {
	$.post(url,config,function(data) {
						if ('FOUND'==data.STATUS) {
							if (data[objName] && data[objName].length > 0) {
								var $selector = $('#'+id);
								var rows = data;
								if(objName!=null){
									rows=data[objName];
								}
								for ( var row in data[objName]) {
									$selector
											.append('<option value="'+rows[row][valueName]+'" '+(selValue==(rows[row][valueName]+'')?'selected':'')+'>'
													+ rows[row][textName]
													+ '</option>');
								}
								if(func && func!=undefined){
									func();
								}
							}
						}
					}, 'json')
}