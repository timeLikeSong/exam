function addEvent(){
	layer.open({
        type : 1,
        title : "添加赛事",
        shadeClose : true, //点击遮罩关闭
        maxmin: true,
        area : [ '55%', '70%' ],
        content : $('#eventContainer'),      
        resize:false,
        btnAlign: 'c',
        zIndex:101,
    	btn : ['保存','取消'],
		yes: function(index){
			saveEvent(index);        	
		}
    });
}
function saveEvent(index){
	layer.close(index);
}