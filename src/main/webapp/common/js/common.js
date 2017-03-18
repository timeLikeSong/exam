var basePath = '${basePath}';
$(document).ready(function(){
	// 默认禁用搜索和排序
	$.extend( $.fn.dataTable.defaults, {
	    searching: false,
	    ordering:  false
	} );
})