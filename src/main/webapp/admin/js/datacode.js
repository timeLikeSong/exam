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

function clearForm() {
	$('#id').val('');
	$('#showId').text('');
	$('#pid').val('');
	$('#title').val('');
	$('#description').val('');
	$('#icon_picker').iconpicker('setIcon', 'fa-');
}
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
function zTreeBeforeRemove(treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getNodes();
	if(nodes.length<=0){
		$('#treeTip')
		.html('<font style="font-style:italic;font-size:16px;">还没有添加数据代码</font>');
	}
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
		name : data.title,
		title : data.title,
		description:data.description,
		icon2:data.icon
	});
}
function updateNode(data){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	treeNode.id =data.id;
	treeNode.pId = data.pid;
	treeNode.name = data.title;
	treeNode.title = data.title;
	treeNode.description=data.description;
	treeNode.icon2=data.icon;
	zTree.updateNode(treeNode);
}
function onNodeChanged() {

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
			onClick : zTreeOnClick,
			beforeRemove: zTreeBeforeRemove
		}
	};
	var url = basePath + "/admin/datacode/list.json";
	var options = {
		pid : 0
	};
	var callback = function(data) {
		var nodes = data.nodes;
		var ztreeNode = [];
		if (nodes.length > 0) {
			for (var i = 0; i < nodes.length; i++) {
				ztreeNode.push({
							'id' : nodes[i].id,
							'name' : nodes[i].title,
							'pId' : nodes[i].pid,
							'title' : nodes[i].title,
							'description' : nodes[i].description,
							'icon2' : nodes[i].icon
						});
			}
		} else {
			$('#treeTip')
					.html('<font style="font-style:italic;font-size:16px;">还没有添加数据代码</font>');
		}
		$.fn.zTree.init($("#treeDemo"), setting, ztreeNode).expandAll(true);
	}
	$.post(url, options, callback);
}
function addTopDataCode() {
	if ($('#group-id').css('display') == 'block') {
		$('#group-id').css({
					'display' : 'none'
				});
	}
	clearForm();
	$("#addChildBtn").removeClass('active');
	$("#addTopBtn").addClass('active');
	$('#title').focus();
}
function addChildDataCode(){
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
	$("#addTopBtn").removeClass('active');
	$("#addChildBtn").addClass('active');
	$('#pid').val(pid);	
	$('#title').focus();
}
function addDataCode() {
	// 如果pid 为空则为添加顶级代码，如果pid不为空则添加子级代码，这两个添加动作一样！如果id为空则为添加， id不为空则为修改
	if (!$("#dataCodeForm").validate(getValidationRules()).form()) {
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
function deleteDataCode(){
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
	var url = basePath + '/admin/datacode/delete.json';
	var data = {id:id};
	var success = function(data) {
		if(data.msg=="OK"){
			zTree.removeChildNodes(treeNode);
			zTree.removeNode(treeNode);
			layer.msg("删除成功");
			zTreeBeforeRemove();
			$('#addTopBtn').trigger('click');
		}
	}
	$.post(url, data, success);
}
function doAdd() {
	var url = basePath + '/admin/datacode/add.json';
	var data = $("form").serialize();
	var success = function(data) {
		if(data.msg=="OK"){
			addNode(data.dataCode);
			var pid  = $('#pid').val();
			clearForm();
			$('#pid').val(pid);
			layer.msg("添加成功");
			$('#treeTip').html('');
		}
	}
	$.post(url, data, success);
}
function doEdit() {
	var url = basePath + '/admin/datacode/edit.json';
	var data = $("form").serialize();
	var success = function(data) {
		if(data.msg=="OK"){
			updateNode(data.dataCode);
			layer.msg("修改成功");
		}
	}
	$.post(url, data, success);
}
function getValidationRules() {
	return {
		rules : {
			title : {
				required : true,
				maxlength : 20
			},
			description : {
				maxlength : 20
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
			title : {
				required : '请输入标题',
				maxlength : '长度最大不超过20'
			},
			description : {
				maxlength : '长度最大不超过20'
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