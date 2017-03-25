var table;
//试题类型
var type = 'singleChoice';//multipleChoice,judgement,essay,program
//data存放试题的json格式数据
var data = {};
//记录当前已添加的选项数量 ， 如添加了A选项和B选项之后，count=2
var count = 0;
//记录选项
var optionAlias = ['A','B','C','D','E','F','G','H'];

var typeIds={
		singleChoice:6,
		multipleChoice:7,
		judgement:8,
		essay:9,
		program:10
}
//重置全局变量
function resetVar() {
	type = 'singleChoice';
	data = {};
	count = 0;
}
$('#singleChoiceForm').delegate(".addBtn", "click", function(){
	if(7==count){
		layer.alert("选项过多！");
		return;
	}
	count++;
	var $div = $("#singleChoiceOption").clone();
	$div.find('.addBtn').hide();
	$div.find(".removeBtn").show();
	$div.find(".optionLabel").html('');
	$div.find('.singleChoiceOptionAlias').val(optionAlias[count]);
	$div.find('.singleChoiceOptionAlias').prop('checked',false);
	$div.find('.singleChoiceOptionText').val('');
	$div.find('.alias').html(optionAlias[count]);
	$div.addClass('singleOptionCopy');
	$("#singleChoiceOption:last-child").after($div);
});
$('#singleChoiceForm').delegate(".removeBtn", "click", function(){
	if(0==count){
		layer.alert("选项过少！");
		return;
	}
	count--;
	$(this).parent().parent().parent().remove();
});
$('#multipleChoiceForm').delegate(".addBtn", "click", function(){
	if(7==count){
		layer.alert("选项过多！");
		return;
	}
	count++;
	var $div = $("#multipleChoiceOption").clone();
	$div.find('.addBtn').hide();
	$div.find(".removeBtn").show();
	$div.find(".optionLabel").html('');
	$div.find('.multipleChoiceOptionAlias').val(optionAlias[count]);
	$div.find('.multipleChoiceOptionAlias').prop('checked',false);
	$div.find('.multipleChoiceOptionText').val('');
	$div.find('.alias').html(optionAlias[count]);
	$div.addClass('multipleOptionCopy');
	$("#multipleChoiceOption:last-child").after($div);
});
$('#multipleChoiceForm').delegate(".removeBtn", "click", function(){
	if(0==count){
		layer.alert("选项过少！");
		return;
	}
	count--;
	$(this).parent().parent().parent().remove();
});
loadQuestionTypeMenu();
$(document).ready(function() {
	layui.use([ 'layedit','form', 'layer' ], function() {
//		var layedit = layui.layedit;
//		layedit.build('singleChoiceContent',layeditOptions);	
//		layedit.build('multipleChoiceContent',layeditOptions);	
//		layedit.build('judgementContent',layeditOptions);	
//		layedit.build('essayContent',layeditOptions);	
//		layedit.build('programContent',layeditOptions);	
		
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
function loadQuestionTypeMenu(){
	var html = '';
	var url = basePath+'/admin/datacode/getQuestionTypeSelector.json';
	var param={};
	$.post(url,param,function(rs){
		if('FOUND'==rs.STATUS){
			var data = rs.data;
			for(var i = 0;i<data.length;i++){
				html+="<li><a href=\"javascript:addQuestion('"+data[i].description+"')\"><i class='fa "+data[i].icon+"'></i>"+data[i].title+"</a></li>";
			}
		}
		else{
			html="<li><a href=\"javascript:addQuestion('singleChoice')\"><i class=\"fa fa-check-circle-o\"></i>单选题</a></li>"
					+"<li><a href=\"javascript:addQuestion('multipleChoice')\"><i class=\"fa fa-check-square-o\"></i>多选题</a></li>"
					+"<li><a href=\"javascript:addQuestion('judgement')\"><i class=\"fa fa-check\"></i>判断题</a></li>"
					+"<li><a href=\"javascript:addQuestion('essay')\"><i class=\"fa fa-pencil-square-o\"></i>简述题</a></li>"
					+"<li><a href=\"javascript:addQuestion('program')\"><i class=\"fa fa-code\"></i>编程题</a></li>";
		}
		$('#addQuestionDropDownMenu').html(html);
	});
}
function list(param){
	return $('#data_table').DataTable({
		ajax:{
			url:basePath+"/admin/question/list.json",
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
			{data:'content',title:'题目内容'},
			{data:'type',title:'类型',render:typeRender},
			{data:'status',title:'状态',render:statusRender},
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
	var editHtml = '<a class="operationBtn" style="color:#00c0ef;" href="javascript:void(0);" onclick="editQuestion('+row.id+')"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;';
	var deleteHtml = '<a class="operationBtn" style="color:#00c0ef;" href="javascript:void(0);" onclick="deleteQuestion('+row.id+')"><i class="fa fa-remove"></i>删除</a>';
	return editHtml+deleteHtml;
}
function switchStatus(question,state){
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
function addQuestion(questionType){
	resetVar();
	clearForm();
	loadSelector(basePath+'/admin/datacode/getQuestionLevelSelector.json','levelId','data','title','id',{},function(data){
		console.log(data);
		if(data.STATUS!='FOUND'){
			layer.alert("请先添加数据代码！");
			return;
		}
	},'');
	loadSelector(basePath+'/admin/questiondb/getSelector.json','questiondbId','data','name','id',{},function(data){
		console.log(data);
		if(data.STATUS!='FOUND'){
			layer.alert("请先添加题库！");
			return;
		}
	},'');
	var title  = '';
	if('singleChoice' == questionType){
		title  = '单选题';
		
		$('#commonDiv').show();
		$('#singleChoiceForm').prepend($('#commonDiv'));
	}
	else if('multipleChoice'==questionType){
		title  = '多选题';
		$('#commonDiv').show();
		$('#multipleChoiceForm').prepend($('#commonDiv'));
	}
	else if('judgement' == questionType){
		title  = '判断题';
		$('#commonDiv').show();
		$('#judgementForm').prepend($('#commonDiv'));
		
	}
	else if('essay' == questionType){
		title  = '简述题';
		$('#commonDiv').show();
		$('#essayForm').prepend($('#commonDiv'));
	}
	else if('program' == questionType){
		title  = '编程题';
		$('#commonDiv').show();
		$('#programForm').prepend($('#commonDiv'));
	}
	else{
		return;
	}
	$('#typeName').val(questionType);
	layer.open({
        type : 1,
        title : "添加"+title,
        shadeClose : true, //点击遮罩关闭
        maxmin: true,
        area : [ '55%', '70%' ],
        content : $('#'+questionType+'Container'),      
        resize:false,
        btnAlign: 'c',
        zIndex:1000,
    	btn : ['保存','取消'],
		yes: function(index){
			saveQuestion(index,questionType);        	
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
function saveQuestion(index,questionType){
//	var questionType = $('#typeName').val();
	var questiondbId = $('#questiondbId').val();
	$('#typeId').val(typeIds[questionType])
	if(null==questiondbId||''==questiondbId||''==questiondbId.trim()){
		layer.msg("请选择题库");
		return;
	}
	
	var score = $('#score').val();
	if(isNaN(parseFloat(score))){
		layer.msg("请输入正确的分数");
		$('#score').focus().select();
		return;
	}
	var typeId = $('#typeId').val();
	if(null==typeId || ''==typeId||''==typeId.trim()){
		layer.msg("请选择试题类型");
		return;
	}
	var levelId = $('#levelId').val();
	if(null==levelId || ''==levelId||''==levelId.trim()){
		layer.msg("请选择试题难度");
		return;
	}
	var key = '';
	var content='';
	if('singleChoice' == questionType){
		content = $('#singleChoiceContent').val();
		if(null==content||''==content||''==content.trim()){
			layer.msg("题干不能为空");
			$('#singleChoiceContent').focus();
			return;
		}
		$('.singleChoiceOptionText').each(function(){
			var text = $(this).val();
			if(null==text || ''==text ||''==text.trim()){
				layer.msg('请输入选项内容');
				$(this).focus();
				return;
			}
		})
		var checkedAlias = $('.singleChoiceOptionAlias:checked');
		if(checkedAlias.length<=0){
			layer.msg('请选择一个答案');
			return;
		}
		var options=[];
		$('.singleChoiceOption').each(function(){
			var alias = $(this).find('.singleChoiceOptionAlias').val();
			var text = $(this).find('.singleChoiceOptionText').val();
			options.push({alias:alias,text:text});
		})
		key = checkedAlias.val();
		data=
		{
			type:"singleChoice",
			singleChoice:{
				content:content,
				key:key,
				score:score,
				options:options
			}
		}
	}
	else if('multipleChoice'==questionType){
		content = $('#multipleChoiceContent').val();
		if(null==content||''==content||''==content.trim()){
			layer.msg("题干不能为空");
			$('#multipleChoiceContent').focus();
			return;
		}
		$('.multipleChoiceOptionText').each(function(){
			var text = $(this).val();
			if(null==text || ''==text ||''==text.trim()){
				layer.msg('请输入选项内容');
				$(this).focus();
			}
		})
		var checkedAlias = $('.multipleChoiceOptionAlias:checked');
		if(checkedAlias.length<=0){
			layer.msg('请至少选择一个答案');
			return;
		}
		checkedAlias.each(function(){
			console.log("check:");
			console.log($(this).prop('checked'));
			if($(this).prop('checked')){
				key=key+''+$(this).val();
			}
		})
		console.log(key);
		
		var options=[];
		$('.multipleChoiceOption').each(function(){
			var alias = $(this).find('.multipleChoiceOptionAlias').val();
			var text = $(this).find('.multipleChoiceOptionText').val();
			options.push({alias:alias,text:text});
		})
		data=
		{
			type:"multipleChoice",
			multipleChoice:{
				content:content,
				key:key,
				score:score,
				options:options
			}
		}
	}
	else if('judgement' == questionType){
		content = $('#judgementContent').val();
		if(null==content||''==content||''==content.trim()){
			layer.msg("题干不能为空");
			$('#judgementContent').focus();
			return;
		}
		
		var checkedAlias = $('.judgementOptionAlias:checked');
		if(checkedAlias.length<=0){
			layer.msg("请选择一个答案");
			return;
		}
		key = checkedAlias.val();
		data=
		{
			type:"judgement",
			judgement:{
				content:content,
				key:key,
				score:score
			}
		}
		console.log(data);
	}
	else if('essay' == questionType){
		content = $('#essayContent').val();
		if(null==content||''==content||''==content.trim()){
			layer.msg("题干不能为空");
			$('#essayContent').focus();
			return;
		}
		key = $('#essayKey').val();
		if(null==key||''==key||''==key.trim()){
			layer.msg("答案不能为空");
			$('#essayKey').focus();
			return;
		}
		data=
		{
			type:"essay",
			essay:{
				content:content,
				key:key,
				score:score
			}
		}
	}
	else if('program' == questionType){
		content = $('#programContent').val();
		if(null==content||''==content||''==content.trim()){
			layer.msg("题干不能为空");
			$('#programContent').focus();
			return;
		}
		key = $('#programKey').val();
		if(null==key||''==key||''==key.trim()){
			layer.msg("答案不能为空");
			$('#programKey').focus();
			return;
		}
		var output = $('#programOutput').val();
//		if(null==output||''==output||''==output.trim()){
//			layer.msg("输出结果不能为空");
//			$('#programOutput').focus();
//			return;
//		}
		data=
		{
			type:"program",
			program:{
				content:content,
				key:key,
				output:output,
				score:score,
			}
		}
	}
	else{
		return;
	}
	
	var url=basePath+'/admin/question/add.json'
	var param={
			questionDBId:questiondbId,
			typeId:typeId,
			levelId:levelId,
			status:1,
			content:content,
			answer:key,
			score:score,
			data:JSON.stringify(data)
	};
	$.post(url,param,function(data){
		layer.msg(data.MSG);
		if('ADD_SUCCESS'==data.STATUS){
			layer.close(index);
		}
		else{
			
		}
	})
}
function clearForm(){
	$('#score').val('');
	
	$('#singleChoiceContent').val('');
	$('.singleOptionCopy').remove();
	$('.singleChoiceOptionText').val('');
	
	$('#multipleChoiceContent').val('');
	$('.multipleOptionCopy').remove();
	$('.multipleChoiceOptionText').val('');
	
	$('#judgementContent').val('');
	
	$('#essayContent').val('');
	
	$('#programContent').val('');
}

function saveSingleChoice(index){
	var questiondbId = $('#questiondbId').val();
	if(null==questiondbId||''==questiondbId||''==questiondbId.trim()){
		layer.msg("请选择题库");
		return;
	}
	var content = $('#singleChoiceContent').val();
	if(null==content||''==content||''==content.trim()){
		layer.msg("题干不能为空");
		$('#singleChoiceContent').focus();
		return;
	}
	var score = $('#score').val();
	if(isNaN(parseFloat(score))){
		layer.msg("请输入正确的分数");
		$('#score').focus().select();
		return;
	}
	$('.singleChoiceOptionText').each(function(){
		var text = $(this).val();
		if(null==text || ''==text ||''==text.trim()){
			layer.msg('请输入选项内容');
			$(this).focus();
		}
	})
	var checkedAlias = $('.singleChoiceOptionAlias:checked');
	if(checkedAlias.length<=0){
		layer.msg('请选择一个答案');
	}
	var options=[];
	$('.singleChoiceOption').each(function(){
		var alias = $(this).find('.singleChoiceOptionAlias').val();
		console.log($(this).find('.singleChoiceOptionAlias'));
		var text = $(this).find('.singleChoiceOptionText').val();
		console.log($(this).find('.singleChoiceOptionAlias'));
		options.push({alias:alias,text:text});
	})
	var key = checkedAlias.val();
	data=
	{
		type:"singleChoice",
		singleChoice:{
			content:content,
			key:key,
			score:score,
			options:options
		}
	}
	var url=basePath+'/admin/question/add.json'
	var param={
			questionDBId:questiondbId,
			typeId:$('#typeId').val(),
			levelId:$('#levelId').val(),
			status:1,
			content:content,
			answer:key,
			score:score,
			data:JSON.stringify(data)
	};
	$.post(url,param,function(data){
		layer.msg(data.MSG);
		if('ADD_SUCCESS'==data.STATUS){
			layer.close(index);
		}
		else{
			
		}
	})
}
function getSingleChoiceValidationRules(){
	return {
		rules : {
			singleChoiceContent : {
				required : true,
				maxlength : 255,
			},
			singleChoiceScore:{
				required:true
			}
		},
		messages : {
			singleChoiceContent : {
				required : '请输入题干内容',
				maxlength : '长度最大不超过255个字符'
			},
			singleChoiceScore:{
				required:'请输入分数'
			}
		}
	};
}
