// 默认datatables 的配置
$.extend( $.fn.dataTable.defaults, {
    ordering:  false,
    lengthChange:false,//选择一页显示的记录数
    info : false,
    searching: false,
    ordering:  false,
    info : false,
    paging: true,
    pagingType: "full_numbers",
    serverSide: true,   //启用服务器端分页
    processing:true,
    language: {
        "decimal":        "",
        "emptyTable":     "没有记录",
        "info":           "Showing _START_ to _END_ of _TOTAL_ entries",
        "infoEmpty":      "Showing 0 to 0 of 0 entries",
        "infoFiltered":   "(filtered from _MAX_ total entries)",
        "infoPostFix":    "",
        "thousands":      ",",
        "lengthMenu":     "",
        "loadingRecords": "正在加载...",
        "processing":     "正在处理...",
        "search":         "搜索:",
        "zeroRecords":    "没有找到记录",
        "paginate": {
            "first":      "首页",
            "last":       "末页",
            "next":       "下一页",
            "previous":   "上一页"
        },
        "aria": {
            "sortAscending":  ": 顺序排序",
            "sortDescending": ": 倒序排序"
        }
    }
} );
$(document).ready(function(){
	
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
	$('#'+id).html('');
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
									func(data);
								}
							}
						}
					}, 'json')
}
/**
 * 设置bootstrap-switch 插件状态  0  和 1
 * @param selector      已经初始化为switch的jQuery选择器
 * @param status  要设置的状态  0 off，1 on
 * @returns
 */
function setSwitchState(selector,status){
	var $obj = $(selector);
	if(1==status){
		if (!$obj.bootstrapSwitch('state')) {
			$obj.bootstrapSwitch('toggleState');
		}
	}
	else{
		if ($obj.bootstrapSwitch('state')) {
			$obj.bootstrapSwitch('toggleState');
		}
	}
}
function getSwitchState(selector){
	var status ;
	if($(selector).bootstrapSwitch('state')){
		status=1;
	}
	else{
		status=0;
	}
	return status;
}