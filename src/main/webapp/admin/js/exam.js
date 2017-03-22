var table;
$(document).ready(function() {
	layui.use([ 'form', 'layer' ], function() {
	});
	table = list({});
	$('.form_datetime').datetimepicker({
		language : 'cn',
		format : "yyyy-mm-dd hh:ii:ss",
		value:"2017-3-20 00:00:00",
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left",
		'z-index':999999
	});
	$('#searchBtn').click(function(){
		table.ajax.reload();
	})
	$('#eventId').change(function(){
		loadGroup($(this).val());
		loadSteps($(this).val());
	})
	$('#canInAct').change(function(){
		var act = $(this).val();
		if('enroll'==act){
			$('#canInObj').hide();
		}
		else if('pass'==act){
			$('#canInObj').show();
			loadExamSelector();
		}
	})
})
function list(param){
	return $('#data_table').DataTable({
		ajax:{
			url:basePath+"/admin/exam/list.json",
			data:function(d){
				var searchText = $('#searchText').val();
				if(null!=searchText && searchText.trim()!=''){
					console.log(searchText);
					d.sm_like_name=searchText;
				}
				d.sm_orderby='order by start_time desc';
				return d;
			}
		},
		columns: [
			{data:'id',title:'ID'},
			{data:'name',title:'竞赛名称'},
			{data:'eventName',title:'所属赛事'},
			{data:'faceGroup',title:'面向组别'},
			{data:'step',title:'当前阶段',render:stepRender},
			{data:'paperName',title:'试卷'},
			{data:'startTime',title:'开始时间'},
			{data:'endTime',title:'结束时间'},
			{data:'duration',title:'时长(分钟)'},
			{data:'operation',title:'操作',render:operationRender}
		],
		createdRow: function (row, data, dataIndex) {
			
		}
		
	});
}
function operationRender(data,type,row,meta){
	var editHtml = '<a class="operationBtn" style="color:#00c0ef;" href="javascript:void(0);" onclick="editExam('+row.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;';
	var deleteHtml = '<a class="operationBtn" style="color:#00c0ef;" href="javascript:void(0);" onclick="deleteExam('+row.id+')"><i class="fa fa-remove"></i>删除</a>';
	return editHtml+deleteHtml;
}
function loadGroup(eventId,selectValue){
	var url = basePath+'/admin/event/getGroupRuleById.json';
	if(null==eventId || ''==eventId){
		layer.alert("请先选择赛事！");
		return;
	}
	console.log(eventId);
	var param= {id:eventId};
	$.post(url,param,function(rs){
		if('FOUND'==rs.STATUS){
			var html  = '';
			for(var p in rs.data){
				html+='<option value="'+rs.data[p].name+'" '+(null!=selectValue&&selectValue==rs.data[p].name?'selected':'')+'>'+rs.data[p].name+'组  '+rs.data[p].count+'人</option>';
			}
			$('#faceGroup').html(html);
		}
	})
}
function loadSteps(eventId,selectValue){
	var url = basePath+'/admin/event/getStepsById.json';
	if(null==eventId || ''==eventId){
		layer.alert("请先选择赛事！");
	}
	var param= {id:eventId};
	$.post(url,param,function(rs){
		if('FOUND'==rs.STATUS){
			var html  = '';
			for(var p in rs.data){
				html+='<option value="'+p+'" '+(null!=selectValue&&selectValue==p?'selected':'')+'>'+rs.data[p].name+'</option>';
			}
			$('#step').html(html);
		}
	})
}
function loadExamSelector(selectValue){
	var url = basePath+'/admin/exam/getSelector.json';
	var param={};
	$.post(url,param,function(rs){
		if('FOUND'==rs.STATUS){
			var html  = '';
			for(var p in rs.data){
				html+='<option value="'+rs.data[p].id+'" '+(null!=selectValue&&selectValue==rs.data[p].id?'selected':'')+'>'+rs.data[p].name+'</option>';
			}
			$('#canInObj').html(html);
		}
	})
}
//加载赛事列表
function loadEventSelector(selectValue,type){
	loadSelector(basePath+'/admin/event/getSelector','eventId','data','name','id',{},function(data){
		if(data.STATUS!='FOUND'){
			layer.alert("请先添加赛事！");
			return;
		}
		var eventId = $('#eventId').val();
		if(0==type){
			loadGroup(eventId);
			loadSteps(eventId);
		}
	},selectValue);
}
//加载试卷列表
function loadPaperSelector(selectValue){
	loadSelector(basePath+'/admin/paper/getSelector','paperId','data','name','id',{},function(data){
		if(data.STATUS!='FOUND'){
			if(data.STATUS!='FOUND'){
				layer.alert("请先添加试卷！");
				return;
			}
		}
	},selectValue);
}
function addExam(){
	clearForm();
	loadEventSelector(null,0);
	loadPaperSelector();
	layer.open({
        type : 1,
        title : "添加竞赛阶段",
        shadeClose : true, //点击遮罩关闭
        maxmin: true,
        area : [ '55%', '70%' ],
        content : $('#examContainer'),      
        resize:false,
        btnAlign: 'c',
        zIndex:1000,
    	btn : ['保存','取消'],
		yes: function(index){
			saveExam(index);        	
		}
    });
}
function deleteExam(id){
	layer.confirm("确定要删除该阶段吗？", {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
		var url = basePath+'/admin/exam/delete.json';
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
function editExam(id){
	clearForm();
	var url = basePath+'/admin/exam/get.json';
	var param={'sm_eq_id':id};
	var callback=function(e){
		if('FOUND'==e.STATUS){
			$('#id').val(e.exam.id);
			$('#name').val(e.exam.name);
			loadEventSelector(e.exam.eventId);
			loadGroup(e.exam.eventId,e.exam.faceGroup);
			console.log(e.exam.faceGroup);
			loadSteps(e.exam.eventId,e.exam.step);
			canIn = JSON.parse(e.exam.canIn);
			console.log(canIn);
			if('enroll'==canIn.act){
				//loadExamSelector();
				$("#canInAct").find("option[value='enroll']").attr("selected",true);
				$("#canInAct").find("option[value='pass']").attr("selected",false);
				
				$('#canInObj').hide();
			}
			else{
				loadExamSelector(canIn.obj);
				$("#canInAct").find("option[value='pass']").attr("selected",true);
				$("#canInAct").find("option[value='enroll']").attr("selected",false);
				$('#canInObj').show();
			}
			loadPaperSelector(e.exam.paperId);
			$('#startTime').val(e.exam.startTime);
			$('#endTime').val(e.exam.endTime);
			$('#duration').val(e.exam.duration);
			layer.open({
		        type : 1,
		        title : "修改竞赛阶段",
		        shadeClose : true, //点击遮罩关闭
		        maxmin: true,
		        area : [ '55%', '70%' ],
		        content : $('#examContainer'),      
		        resize:false,
		        btnAlign: 'c',
		        zIndex:1000,
		    	btn : ['保存','取消'],
				yes: function(index){
					saveExam(index);        	
				}
		    });
		}
		else{
			layer.msg('未找到');
		}
	}
	$.post(url,param,callback);
	
}
function saveExam(index){
	var name = $('#name').val();
	if (!$("#examForm").validate(getValidationRules()).form()) {
		return;
	}
	var eventId = $('#eventId').val();
	if(null==eventId || ''==eventId){
		layer.msg('请选择所属赛事');
		return;
	}
	var step = $('#step').val();
	if(null==step || ''==step){
		layer.msg('请选择竞赛阶段');
	}
	var faceGroup = $('#faceGroup').val();
	if(null==faceGroup || ''==faceGroup){
		layer.msg('请选择面向的组别');
		return;
	}
	canIn.act=$('#canInAct').val();
	canIn.obj=$('#canInObj').val();
	if($.isEmptyObject(canIn)){
		layer.msg('请添加参赛条件');
		return;
	}
	if('enroll'==canIn.act){
		canIn.obj=eventId;
	}
	var paperId = $('#paperId').val();
	if(null==paperId || ''==paperId){
		layer.msg('请选择试卷');
		return;
	}
	var startTime = $('#startTime').val();
	if(null==startTime || ''==startTime){
		layer.msg('请选择开始时间');
		return;
	}
	var endTime = $('#endTime').val();
	if(null==endTime || ''==endTime){
		layer.msg('请选择结束时间');
		return;
	}
	
	var duration = $('#duration').val();
	if(isNaN(parseInt(duration))){
		layer.msg('请输入正确时长');
		return;
	}
	var param ={};
	
	var id = $('#id').val();
	var url = basePath+'/admin/exam/add.json';
	//判断是更新还是添加
	if(null!=id && ''!=id){
		param['id']=id;
		url = basePath+'/admin/exam/edit.json';
	}
	param.name=name;
	param.eventId=eventId;
	param.faceGroup=faceGroup;
	param.canIn=JSON.stringify(canIn);;
	param.paperId=paperId;
	param.startTime=startTime;
	param.endTime=endTime;
	param.duration=duration;
	param.step=step;
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
	$('#examForm input').each(function(){
		$(this).val('');
	})
	canIn={};
}
function stepRender(data, type, row, meta){
	if(0==row.step){
		return '初赛';
	}
	else if(1==row.step){
		return '复赛';
	}
	else if(2==row.step){
		return '决赛';
	}
}
function getValidationRules(){
	return {
		rules : {
			name : {
				required : true,
				maxlength : 50
			},
			eventId : {
				required : true,
			},
			faceGroup:{
				required : true,
				maxlength : 10
			},
			paperId:{
				required : true,
			},
			duration:{
				required : true,
				min : 1,
				max : 300
			}
		},
		messages : {
			name : {
				required : '请输入竞赛阶段名称',
				maxlength : '长度最大不超过50'
			},
			eventId : {
				required : '请选择所属赛事',
			},
			faceGroup:{
				required :'请选择所面向的组别',
				maxlength:'参数错误'
			},
			paperId:{
				required : '请选择试卷',
			},
			duration:{
				required : '请输入时长',
				min : '时长不能小于0',
				max : '请输入正常范围的时长'
			}
		}
	};
}
