$(document).ready(function() {
	layui.use([ 'form', 'laypage', 'layer' ], function() {
	});
	$('#data_table').DataTable({
		ajax: basePath+"/event/list.json",
		columns: [
			{data:'id',title:'ID'},
			{data:'name',title:'竞赛名称'},
			{data:'description',title:'竞赛描述'},
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
			console.log(data);//,
			var param ={
					'onText' : '开启',
					'offText' : '关闭'
				};
			if(data.status==1){
				param['']
			}
			$(row).find('.switch').bootstrapSwitch({
				'onSwitchChange':switchStatus
			});
		}
	});
	$("[name='status']").bootstrapSwitch({
		'onText' : '开启',
		'offText' : '关闭'
	});
	$('.form_datetime').datetimepicker({
		language : 'cn',
		format : "yyyy-mm-dd hh:ii:ss",
		value:new Date(),
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left"
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
})
function statusRender(data, type, row, meta ){
	return '<input type="checkbox" data-id="'+row.id+'" class="switch" '+(row.status==1?'checked':'')+' ) />';
}
function operationRender(data,type,row,meta){
	var editHtml = '<a style="color:#00c0ef;" href="javascript:void()" onclick="editEvent('+row.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;';
	var deleteHtml = '<a style="color:#00c0ef;" href="javascript:void()" onclick="deleteEvent('+row.id+')"><i class="fa fa-remove"></i>删除</a>';
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
		layer.msg('请添加竞赛规则');
		$('#groupName').focus();
		$('#groupName').select();
		return;
	}
	if(steps.length==0){
		layer.msg('请添加竞赛阶段');
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
	param.name=name;
	param.description=$('#description').val();
	param.enrollStartTime=enrollStartTime;
	param.enrollEndTime=enrollEndTime;
	param.status=status;
	param.groupRule=JSON.stringify(groupRules);
	param.steps=JSON.stringify(steps);
	param.currentStep=0;
	console.log(param);
	var url = basePath+'/event/add.json';
	var callback=function(e){
		//更新表格
		layer.close(index);
		layer.msg('添加成功');
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
		//刷新竞赛规则
		refreshGroupRules();
	}
	else{
		layer.msg("该组别已经存在");
	}
	
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
		//刷新竞赛规则
		refreshSteps();
	}
	else{
		layer.msg("该竞赛阶段已经存在");
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
				required : '请输入竞赛名称',
				maxlength : '长度最大不超过50'
			},
			description : {
				maxlength : '长度最大不超过200'
			}
		}
	};
}