var table;
$(document).ready(function() {
	layui.use([ 'form', 'layer' ], function() {
	});
	table = list({});
	$("#status").bootstrapSwitch({
		'onText' : '开启',
		'offText' : '关闭'
	});
	$('.form_datetime').datetimepicker({
		language : 'cn',
		format : "yyyy-mm-dd hh:ii:ss",
		value:"2017-3-20 00:00:00",
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left",
		'z-index':999999
	});
	$('#addGroupRuleBtn').click(function() {
		var groupName = $('#groupName').val();
		var groupCount = $('#groupCount').val();
		if (null == groupName || '' == groupName) {
			layer.msg('请输入组别名称');
			$('#groupName').focus();
			$('#groupName').select();
			return;
		}
		if (null == groupCount || '' == groupCount) {
			layer.msg('请输入组别人数');
			$('#groupCount').focus();
			$('#groupCount').select();
			return;
		}
		if (isNaN(parseInt(groupCount))) {
			layer.msg('请输入正确的人数');
			$('#groupCount').focus();
			$('#groupCount').select();
			return;
		}
		addGroupRule(groupName,groupCount);
	})
	$('#addStepBtn').click(function(){
		var stepName = $('#stepName').val();
		if (null == stepName || '' == stepName) {
			layer.msg('请输入阶段名称');
			$('#stepName').focus();
			$('#stepName').select();
			return;
		}
		addStep(stepName);
	})
	$('#searchBtn').click(function(){
		table.ajax.reload();
	})
})
function list(param){
	return $('#data_table').DataTable({
		ajax:{
			url:basePath+"/event/list.json",
			data:function(d){
				var searchText = $('#searchText').val();
				if(null!=searchText && searchText.trim()!=''){
					d.sm_like_name=searchText;
				}
				d.sm_orderby='order by create_time desc';
				return d;
			}
		},
		columns: [
			{data:'id',title:'ID'},
			{data:'name',title:'赛事名称'},
			{data:'description',title:'赛事描述'},
			{data:'enrollStartTime',title:'报名开始时间'},
			{data:'enrollEndTime',title:'报名结束时间'},
			{data:'status',title:'状态',render:statusRender},
			{data:'operation',title:'操作',render:operationRender}
		],
		columnDefs: [
		    {
		      "data": null,
		      "defaultContent": operationRender,
		      "targets": -1
		    }
		  ],
		createdRow: function (row, data, dataIndex) {
			var param ={
					'onText' : '开启',
					'offText' : '关闭'
				};
			$(row).find('.switch').bootstrapSwitch({
				'onSwitchChange':switchStatus
			});
		},
		searching: false,
	    ordering:  false,
	    info : false,
	    paging: true,
	    pagingType: "full_numbers",
	    serverSide: true,   //启用服务器端分页
	    ordering:true,
	    processing:true
	});
}

function statusRender(data, type, row, meta ){
	return '<input type="checkbox" data-id="'+row.id+'" class="switch" '+(row.status==1?'checked':'')+' ) />';
}
function operationRender(data,type,row,meta){
	console.log(this);
	console.log("data");
	console.log(data);
	console.log("type");
	console.log(type);
	console.log("row");
	console.log(row);
	console.log("meta");
	console.log(meta);
	var editHtml = '<a style="color:#00c0ef;" href="javascript:void(0);" onclick="editEvent('+row.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;';
	var deleteHtml = '<a style="color:#00c0ef;" href="javascript:void(0);" onclick="deleteEvent('+row.id+')"><i class="fa fa-remove"></i>删除</a>';
	return editHtml+deleteHtml;
}
function switchStatus(event,state){
	var id = $(this).attr('data-id');
	var url = basePath+'/event/switchStatus.json';
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
function addEvent(){
	clearForm();
	
	layer.open({
        type : 1,
        title : "添加赛事",
        shadeClose : true, //点击遮罩关闭
        maxmin: true,
        area : [ '55%', '70%' ],
        content : $('#eventContainer'),      
        resize:false,
        btnAlign: 'c',
        zIndex:1000,
    	btn : ['保存','取消'],
		yes: function(index){
			saveEvent(index);        	
		}
    });
}
function deleteEvent(id){
	layer.confirm("确定要删除该赛事吗？", {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		var url = basePath+'/event/delete.json';
		var param={'sm_eq_id':id};
		var callback=function(e){
			if('DEL_SUCCESS'==e.STATUS){
				removeRow(id);
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
function editEvent(id){
	clearForm();
	var url = basePath+'/event/get.json';
	var param={'sm_eq_id':id};
	var callback=function(e){
		if('FOUND'==e.STATUS){
			$('#id').val(e.event.id);
			$('#name').val(e.event.name);
			$('#description').val(e.event.description);
			groupRules = eval(e.event.groupRule);
			console.log(groupRules);
			steps= eval(e.event.steps);
			console.log(steps);
			$('#enrollStartTime').val(e.event.enrollStartTime);
			$('#enrollEndTime').val(e.event.enrollEndTime);
			if(1==e.event.status){
				if (!$('#status').bootstrapSwitch('state')) {
					$('#status').bootstrapSwitch('toggleState');
				}
				
			}
			else{
				if ($('#status').bootstrapSwitch('state')) {
					$('#status').bootstrapSwitch('toggleState');
				}
			}
						//此方法会报错！！！！！
						//$('#status').bootstrapSwitch('setState', true);
			refreshGroupRules();
			refreshSteps();
			
			layer.open({
		        type : 1,
		        title : "修改赛事",
		        shadeClose : true, //点击遮罩关闭
		        maxmin: true,
		        area : [ '55%', '70%' ],
		        content : $('#eventContainer'),      
		        resize:false,
		        btnAlign: 'c',
		        zIndex:1000,
		    	btn : ['保存','取消'],
				yes: function(index){
					saveEvent(index);        	
				}
		    });
		}
		else{
			layer.msg('未找到');
		}
	}
	$.post(url,param,callback);
	
}
function saveEvent(index){
	var name = $('#name').val();
	if (!$("#eventForm").validate(getValidationRules()).form()) {
		return;
	}
	var enrollStartTime = $('#enrollStartTime').val();
	if(null==enrollStartTime || ''==enrollStartTime){
		layer.msg('请选择开始报名时间');
		return;
	}
	var enrollEndTime = $('#enrollEndTime').val();
	if(null==enrollEndTime || ''==enrollEndTime){
		layer.msg('请选择结束报名时间');
		return;
	}
	if(groupRules.length==0){
		layer.msg('请添加赛事规则');
		$('#groupName').focus();
		$('#groupName').select();
		return;
	}
	if(steps.length==0){
		layer.msg('请添加赛事阶段');
		$('#stepName').focus();
		$('#stepName').select();
		return;
	}
	var status = $('#status').attr('checked');
	if($('input[name="status"]').bootstrapSwitch('state')){
		status=1;
	}
	else{
		status=0;
	}
	var param ={};
	
	var id = $('#id').val();
	var url = basePath+'/event/add.json';
	//判断是更新还是添加
	if(null!=id && ''!=id){
		param['id']=id;
		url = basePath+'/event/edit.json';
	}
	param.name=name;
	param.description=$('#description').val();
	param.enrollStartTime=enrollStartTime;
	param.enrollEndTime=enrollEndTime;
	param.status=status;
	param.groupRule=JSON.stringify(groupRules);
	param.steps=JSON.stringify(steps);
	param.currentStep=0;
	var callback=function(e){
		layer.msg(e.MSG);
		//判断返回值
		if('ADD_SUCCESS'==e.STATUS){
			//更新表格
			table.ajax.reload();
		}
		else if('EDIT_SUCCESS'==e.STATUS){
			
		}
		else{
			return;
		}
		layer.close(index);
	}
	$.post(url,param,callback);
}
function refreshGroupRules() {
	var html = '';
	for (var i = 0; i < groupRules.length; i++) {
		html+='<div class="form-group groupRule">'+
			'<label for="" class="col-sm-2 control-label"'+
				'style="margin-right: 1em;"></label>'+
			'<div class="form-inline">'+
				'<span>'+groupRules[i].name+'</span>组&nbsp;&nbsp;&nbsp;&nbsp;<span>'+groupRules[i].count+'</span>人'+
				'<button type="button" onclick="deleteGroupRule(\''+groupRules[i].name+'\')" class="btn btn-danger btn-sm" title="删除此规则">'+
					'<i class="fa fa-minus"></i>'+
				'</button>'+
			'</div>'+
		'</div>';
	}
	$('#groupRules').html(html);
}
function addGroupRule(groupName,groupCount){
	var i = atGroupRules(groupName);
	if(-1==i){
		var rule = {
			'name' : groupName,
			'count' : groupCount
		};
		groupRules.push(rule);
		//刷新赛事规则
		refreshGroupRules();
	}
	else{
		layer.msg("该组别已经存在");
	}
	
}
function clearForm(){
	$('#eventForm input').each(function(){
		$(this).val('');
	})
	groupRules=[];
	steps=[];
	refreshGroupRules();
	refreshSteps();
}
function deleteGroupRule(groupName){
	var i = atGroupRules(groupName);
	if(-1!=i){
		groupRules.splice(i,1);
		refreshGroupRules();
	}
}
function atGroupRules(groupName){
	for (var i = 0; i < groupRules.length; i++) {
		if(groupRules[i].name==groupName){
			return i;
		}
	}
	return -1;
}

function addStep(stepName){
	var i = atSteps(stepName);
	if(-1==i){
		var step = {
			'name' : stepName
		};
		steps.push(step);
		//刷新赛事规则
		refreshSteps();
	}
	else{
		layer.msg("该赛事阶段已经存在");
	}
}
function deleteStep(stepName){
	var i = atSteps(stepName);
	if(-1!=i){
		steps.splice(i,1);
		refreshSteps();
	}
}
function atSteps(stepName){
	for (var i = 0; i < steps.length; i++) {
		if(steps[i].name==stepName){
			return i;
		}
	}
	return -1;
}
function refreshSteps(){
	var html = '';
	for (var i = 0; i < steps.length; i++) {
		html+='<div class="form-group step">'+
			'<label for="" class="col-sm-2 control-label"'+
				'style="margin-right: 1em;"></label>'+
			'<div class="form-inline">'+
				'<span>'+steps[i].name+'</span>'+
				'<button type="button" onclick="deleteStep(\''+steps[i].name+'\')" class="btn btn-danger btn-sm" title="删除此阶段">'+
					'<i class="fa fa-minus"></i>'+
				'</button>'+
			'</div>'+
		'</div>';
	}
	$('#steps').html(html);
}

function getValidationRules(){
	return {
		rules : {
			name : {
				required : true,
				maxlength : 50
			},
			description : {
				maxlength : 200
			}
		},
		messages : {
			name : {
				required : '请输入赛事名称',
				maxlength : '长度最大不超过50'
			},
			description : {
				maxlength : '长度最大不超过200'
			}
		}
	};
}
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