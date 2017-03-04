package com.lx.exam.vo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoFunction;
import com.lx.exam.po.PoRole;
import com.lx.exam.util.DateUtil;

public class Role {
	private Long id;
	private String roleName;
	private Integer status;
	private String createDate;
	private Set<Function> functions;
	private Long[] functionIds;
	public Role(){}
	public Role(PoRole poRole){
		BeanUtils.copyProperties(poRole, this);
		if(poRole.getPoFunctions()!=null && poRole.getPoFunctions().size()>0){
			functions = new HashSet<Function>();
			for(PoFunction poFunction:poRole.getPoFunctions()){
				functions.add(new Function(poFunction));
			}
		}
		this.createDate=DateUtil.format(poRole.getCreateDate());
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", status=" + status + ", createDate=" + createDate
				+ ", functions=" + functions + ", functionIds=" + Arrays.toString(functionIds) + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Set<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}
	public Long[] getFunctionIds() {
		return functionIds;
	}
	public void setFunctionIds(Long[] functionIds) {
		this.functionIds = functionIds;
	}
	
	
}
