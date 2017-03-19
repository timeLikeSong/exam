var basePath = '${basePath}';
$(document).ready(function(){
	// 默认禁用搜索和排序
	$.extend( $.fn.dataTable.defaults, {
	    searching: false,
	    ordering:  false,
	    info : false,
	    language: {
            "lengthMenu": "每页 _MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "infoFiltered": "(从 _MAX_ 条记录过滤)"
        }
	} );
})