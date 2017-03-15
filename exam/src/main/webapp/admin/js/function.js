$(document).ready(function() {
			initZTree();
			$('#icon_picker').iconpicker({
						align : 'center', // Only in div tag
						arrowClass : 'btn-danger',
						arrowPrevIconClass : 'glyphicon glyphicon-chevron-left',
						arrowNextIconClass : 'glyphicon glyphicon-chevron-right',
						cols : 10,
						footer : true,
						header : true,
						icon : 'fa-',
						iconset : 'fontawesome',
						placement : 'bottom', // Only in button tag
						rows : 5,
						search : false,
						selectedClass : 'btn-success',
						unselectedClass : ''
					}).on('change', function(e) {
						var icon = e.icon;
						if (icon == 'empty') {
							icon = '';
						}
						$('#icon').val(icon);
					});
		});
function zTreeOnClick(event, treeId, treeNode) {
	var selectedNode = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0];
	$('#id').val(selectedNode.id);
	$('#showId').text(selectedNode.id);
	$('#pid').val(selectedNode.pid);
	$('#title').val(selectedNode.title);
	$('#description').val(selectedNode.description);
	$('#icon').val(selectedNode.icon2);
	if(selectedNode.icon2==''){
		$('#icon_picker').iconpicker('setIcon', 'fa-');
	}
	else{
		$('#icon_picker').iconpicker('setIcon', selectedNode.icon2);
	}
	if ($('#group-id').css('display') == 'none') {
		$('#group-id').css({
					'display' : 'block'
				});
	}
}
function initZTree() {
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		view : {
			selectedMulti : false,
			expandSpeed : "200"
		},
		callback : {
			onClick : zTreeOnClick
		}
	};
	var url = basePath + "/function/list.json";
	var options = {
		pid : 0
	};
	var callback = function(data) {
		console.log(data);
		if(data.msg=='OK'){
			var nodes = data.functions;
			var ztreeNode = [];
			if (nodes.length > 0) {
				for (var i = 0; i < nodes.length; i++) {
					ztreeNode.push({
								'id' : nodes[i].id,
								'name' : nodes[i].funcName,
								'funcName':nodes[i].funcName,
								'url2' : nodes[i].url,
								'status' : nodes[i].status,
								'icon2' : nodes[i].icon
							});
				}
			}
			else{
				$('#treeTip')
				.html('<font style="font-style:italic;font-size:16px;">还没有添加数据代码</font>');
			}
		}
		else{
			$('#treeTip')
			.html('<font style="font-style:italic;font-size:16px;">还没有添加数据代码</font>');
		}
		$.fn.zTree.init($("#treeDemo"), setting, ztreeNode).expandAll(true);
	}
	$.post(url, options, callback);
}
function addNode(data) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	var pid = $('#pid').val();
	if(!pid || pid==0 || pid==''){
		treeNode=null;
	}
	zTree.addNodes(treeNode, {
		id : data.id,
		pId : data.pid,
		name : data.funcName,
		funcName : data.funcName,
		url2:data.url,
		status:data.status,
		icon2:data.icon
	});
}
function updateNode(data){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	treeNode.id =data.id;
	treeNode.pId = data.pid;
	treeNode.name = data.funcName;
	treeNode.funcName = data.funcName;
	treeNode.url2=data.url;
	treeNode.icon2=data.icon;
	treeNode.status=data.status;
	zTree.updateNode(treeNode);
}
function zTreeOnClick(event, treeId, treeNode) {
	var selectedNode = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes()[0];
	$('#id').val(selectedNode.id);
	$('#showId').text(selectedNode.id);
	$('#pid').val(selectedNode.pid);
	$('#url').val(selectedNode.url2);
	$('#funcName').val(selectedNode.funcName);
	var status = selectedNode.status;
	if(status==0){
		$('#statusOff').prop('checked',true);;
	}
	else{
		$('#statusOn').prop('checked',true);;
	}
	$('#icon').val(selectedNode.icon2);
	if(selectedNode.icon2==''){
		$('#icon_picker').iconpicker('setIcon', 'fa-');
	}
	else{
		$('#icon_picker').iconpicker('setIcon', selectedNode.icon2);
	}
	if ($('#group-id').css('display') == 'none') {
		$('#group-id').css({
					'display' : 'block'
				});
	}
}
function zTreeBeforeRemove(treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getNodes();
	if(nodes.length<=0){
		$('#treeTip')
		.html('<font style="font-style:italic;font-size:16px;">还没有添加数据代码</font>');
	}
}
function clearForm(){
	$('#pid').val('');
	$('#id').val('');
	$('#funcName').val('');
	$('#url').val('');
	$('#statusOn').prop('checked',true);;
	$('#icon_picker').iconpicker('setIcon', 'fa-');
}
function addFunction(){
	if ($('#group-id').css('display') == 'block') {
		$('#group-id').css({
					'display' : 'none'
				});
	}
	clearForm();
	$("#addChildFunctionBtn").removeClass('active');
	$("#addFunctionBtn").addClass('active');
	$('#funcName').focus();
	
}
function addChildFunction(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getSelectedNodes();
	if(nodes.length<=0){
		layer.msg("请选择一个上级项");
		return;
	}
	treeNode = nodes[0];
	var pid = nodes[0].id;
	if(!pid || pid==''){
		layer.msg("请选择一个上级项");
		return;
	}
	if ($('#group-id').css('display') == 'block') {
		$('#group-id').css({
					'display' : 'none'
				});
	}
	clearForm();
	$("#addFunctionBtn").removeClass('active');
	$("#addChildFunctionBtn").addClass('active');
	$('#pid').val(pid);	
	$('#funcName').focus();
}
function add(){
	// 如果pid 为空则为添加顶级代码，如果pid不为空则添加子级代码，这两个添加动作一样！如果id为空则为添加， id不为空则为修改
	if (!$("#functionForm").validate(getValidationRules()).form()) {
		return;
	}
	var id = $('#id').val();
	if (id == '') {
		// 添加
		doAdd();
	} else {
		// 修改
		doEdit();
	}
}
function doAdd(){
	var url = basePath + '/function/add.json';
	var data = $("form").serialize();
	var success = function(data) {
		if(data.msg=="OK"){
			addNode(data.function);
			var pid  = $('#pid').val();
			clearForm();
			$('#pid').val(pid);
			layer.msg("添加成功");
			$('#treeTip').html('');
		}
	}
	$.post(url, data, success);
}
function doEdit(){
	var url = basePath + '/function/edit.json';
	var data = $("form").serialize();
	var success = function(data) {
		if(data.msg=="OK"){
			updateNode(data.function);
			layer.msg("修改成功");
		}
	}
	$.post(url, data, success);
}
function deleteFunction(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getSelectedNodes();
	if(nodes.length<=0){
		layer.msg("请选择要删除的项");
		return;
	}
	treeNode = nodes[0];
	var id = nodes[0].id;
	if(id==''){
		layer.msg("请选择要删除的项");
		return;
	}
	var url = basePath + '/function/delete.json';
	var data = {id:id};
	var success = function(data) {
		if(data.msg=="OK"){
			zTree.removeChildNodes(treeNode);
			zTree.removeNode(treeNode);
			layer.msg("删除成功");
			zTreeBeforeRemove();
			$('#addFunctionBtn').trigger('click');
		}
	}
	$.post(url, data, success);
}
function getValidationRules() {
	return {
		rules : {
			funcName: {
				required : true,
				maxlength : 20
			},
			url : {
				required : true,
				maxlength : 20
			},
			status:{
				required:true
			},
			icon : {
				maxlength : 20
			},
			id : {
				maxlength : 20
			},
			pid : {
				maxlength : 20
			}
		},
		messages : {
			funcName : {
				required : '请输入标题',
				maxlength : '长度最大不超过20'
			},
			url : {
				maxlength : '长度最大不超过20'
			},
			status:{
				required:'状态不能为空'
			},
			icon : {
				maxlength : '长度最大不超过20'
			},
			id : {
				maxlength : '长度最大不超过20'
			},
			pid : {
				maxlength : '长度最大不超过20'
			}
		}
	};
}