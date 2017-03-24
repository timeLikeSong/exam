var table;
$(document).ready(function() {
	layui.use([ 'form', 'layer' ], function() {
	});
	$("#status").bootstrapSwitch({
		'onText' : '开启',
		'offText' : '关闭'
	});
	table = list({});
	$('#searchBtn').click(function(){
		table.ajax.reload();
	})
})
function list(param){
	return $('#data_table').DataTable({
		ajax:{
			url:basePath+"/admin/questiondb/list.json",
			data:function(d){
				var searchText = $('#searchText').val();
				if(null!=searchText && searchText.trim()!=''){
					d.sm_like_name=searchText;
				}
				d.sm_orderby='order by create_date desc';
				return d;
			}
		},
		columns: [
			{data:'id',title:'ID'},
			{data:'name',title:'题库名称'},
			{data:'status',title:'状态',render:statusRender},
			{data:'description',title:'描述'},
			{data:'posterName',title:'创建者'},
			{data:'createDate',title:'创建时间'},
			{data:'modifyorName',title:'修改者'},
			{data:'modifyDate',title:'修改时间'},
			{data:'operation',title:'操作',render:operationRender}
		],
		createdRow: function (row, data, dataIndex) {
			var param ={
					'onText' : '开启',
					'offText' : '关闭'
				};
			$(row).find('.switch').bootstrapSwitch({
				'onSwitchChange':switchStatus
			});
		}
	});
}
function statusRender(data, type, row, meta ){
	return '<input type="checkbox" data-id="'+row.id+'" class="switch" '+(row.status==1?'checked':'')+' ) />';
}
function operationRender(data,type,row,meta){
	var editHtml = '<a class="operationBtn" style="color:#00c0ef;" href="javascript:void(0);" onclick="editQuestiondb('+row.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;';
	var deleteHtml = '<a class="operationBtn" style="color:#00c0ef;" href="javascript:void(0);" onclick="deleteQuestiondb('+row.id+')"><i class="fa fa-remove"></i>删除</a>';
	return editHtml+deleteHtml;
}
function switchStatus(questiondb,state){
	var id = $(this).attr('data-id');
	var url = basePath+'/admin/questiondb/switchStatus.json';
	var param={id:id};
	var callback=function(data){
		if('EDIT_SUCCESS'==data.STATUS){
			layer.msg(data.MSG);
			return true;
		}
		else{
			layer.msg(data.MSG);
			return false;
		}
		layer.msg(data.MSG);
	}
	$.post(url,param,callback);
	return true;
}
function addQuestiondb(){
	clearForm();
	layer.open({
        type : 1,
        title : "添加题库",
        shadeClose : true, //点击遮罩关闭
        maxmin: true,
        area : [ '55%', '70%' ],
        content : $('#questiondbContainer'),      
        resize:false,
        btnAlign: 'c',
        zIndex:1000,
    	btn : ['保存','取消'],
		yes: function(index){
			saveQuestiondb(index);        	
		}
    });
}
function deleteQuestiondb(id){
	layer.confirm("确定要删除该题库吗？", {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		var url = basePath+'/admin/questiondb/delete.json';
		var param={'sm_eq_id':id};
		var callback=function(e){
			if('DEL_SUCCESS'==e.STATUS){
				//更新表格
				table.ajax.reload();
			}
			layer.msg(e.MSG);
		}
		$.post(url,param,callback);
	}, function(){
	});
}
function removeRow(id){
	table.ajax.reload();
}
function editQuestiondb(id){
	clearForm();
	var url = basePath+'/admin/questiondb/get.json';
	var param={'sm_eq_id':id};
	var callback=function(e){
		if('FOUND'==e.STATUS){
			$('#id').val(e.questiondb.id);
			$('#name').val(e.questiondb.name);
			$('#description').val(e.questiondb.description);
			setSwitchState('#status',e.questiondb.status);
			layer.open({
		        type : 1,
		        title : "修改竞赛阶段",
		        shadeClose : true, //点击遮罩关闭
		        maxmin: true,
		        area : [ '55%', '70%' ],
		        content : $('#questiondbContainer'),      
		        resize:false,
		        btnAlign: 'c',
		        zIndex:1000,
		    	btn : ['保存','取消'],
				yes: function(index){
					saveQuestiondb(index);        	
				}
		    });
		}
		else{
			layer.msg('未找到');
		}
	}
	$.post(url,param,callback);
}
function saveQuestiondb(index){
	if (!$("#questiondbForm").validate(getValidationRules()).form()) {
		return;
	}
	var name = $('#name').val();
	var status = getSwitchState('#status');
	var description = $('#description').val();
	
	var param ={};
	
	var id = $('#id').val();
	var url = basePath+'/admin/questiondb/add.json';
	//判断是更新还是添加
	if(null!=id && ''!=id){
		param['id']=id;
		url = basePath+'/admin/questiondb/edit.json';
	}
	param.name=name;
	param.status=status;
	param.description=description;
	var callback=function(e){
		layer.msg(e.MSG);
		//判断返回值
		if('ADD_SUCCESS'==e.STATUS){
			//更新表格
			table.ajax.reload();
		}
		else if('EDIT_SUCCESS'==e.STATUS){
			//更新表格
			table.ajax.reload();
		}
		else{
			return;
		}
		layer.close(index);
	}
	$.post(url,param,callback);
}
function clearForm(){
	$('#questiondbForm input').each(function(){
		$(this).val('');
	})
}
function getValidationRules(){
	return {
		rules : {
			name : {
				required : true,
				maxlength : 50
			}
		},
		messages : {
			name : {
				required : '请输入题库名称',
				maxlength : '长度最大不超过50'
			}
		}
	};
}
