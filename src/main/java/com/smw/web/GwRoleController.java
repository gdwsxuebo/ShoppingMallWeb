package com.smw.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gws.util.JqGridData;
import com.smw.common.util.BaseResultVo;
import com.smw.common.util.DateUtil;
import com.smw.common.util.ResponseCode;
import com.smw.common.util.SetEnum;
import com.smw.common.util.Status;
import com.smw.common.util.paging.Paging;
import com.smw.pojo.GwMenuTree;
import com.smw.pojo.GwRole;
import com.smw.pojo.GwRoleGroup;
import com.smw.pojo.PosConfigModel;
import com.smw.pojo.XfStaff;
import com.smw.service.GwMenuTreeService;
import com.smw.service.GwRoleGroupService;
import com.smw.service.GwRoleService;
import com.smw.service.XfStaffService;

/**
 * 
 * 
 * GwRoleController:角色控制层
 *
 * @author  shengjinpeng
 * @date    2017年2月21日
 * @version jdk1.8
 *
 */
@Controller
@RequestMapping("web/gwRole")
public class GwRoleController {
	Logger logger = LoggerFactory.getLogger(GwRoleController.class);
	
	@Resource
	private GwRoleService gwRoleService;
	
	@Resource
	private GwMenuTreeService gwMenuTreeService;
	
	@Resource
	private GwRoleGroupService gwRoleGroupService;
	
	@Resource
	private XfStaffService xfStaffServiceImpl;
	
	/**
	 * 
	 * 获取角色列表
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的条数
	 * @param key 搜索关键字
	 * @return
	 */
	@RequestMapping("getRole")
	public Object getRole(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key){
		
		try {
			if (pageIndex == null) {
				pageIndex = Integer.parseInt(SetEnum.pageIndex.getValue());
			}
			if (pageSize==null){
	            pageSize=Integer.parseInt(SetEnum.pageSize.getValue());
	        }
			if (key!=null) {
				key= new String(key.getBytes("iso-8859-1"),"utf-8");
		        key=key.trim();
		        request.setAttribute("key", key);
	        }
			//角色总数
			Integer totalCount=0;
			//角色集合
			List<GwRole> gwRoles;
			totalCount = gwRoleService.getCount(key);
			gwRoles = gwRoleService.selectPageList(pageIndex, pageSize, key);
			Paging<GwRole> paging=new Paging<>(pageIndex, pageSize, totalCount, gwRoles);
			request.getSession().setAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("select", "role");
		return "role/roleList";
	}
	
	/**
	 * 获取角色
	 * @param request
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的行数
	 * @return
	 */
	@RequestMapping("getRolePageList")
	@ResponseBody
	public JqGridData getRolePageList(HttpServletRequest request,Integer page,Integer rows){
		JqGridData jqGrid=new JqGridData();
		List<GwRole> gwRoleList = gwRoleService.selectPageList(page, rows);
		int count = gwRoleService.getCount();
		jqGrid.setRows(gwRoleList);
		jqGrid.setPageSize(rows);
		jqGrid.setTotalRecords(count);
		return jqGrid;
	}
	
	@RequestMapping("addRole")
	public String addRole(HttpServletRequest request) {
		List<GwRole> roleList = gwRoleService.selectAllList();
		request.setAttribute("roleList", roleList);
		return "role/addRole";
	}
	/**
	 * 添加或者修改角色
	 * @return
	 */
	@RequestMapping("addOrUpdateRole")
	public Object addOrUpdateRole(HttpServletRequest request, Integer pageIndex, Integer pageSize, String key,GwRole role){
		try {
			String time = DateUtil.getCurrentDatetime("-");
			role.setTime(time);
			if(role.getId()!=null && role.getId()==1 && gwRoleService.selectById(1)!=null) {
				return BaseResultVo.responseFail("该角色不能修改");
			}
			gwRoleService.saveOrupdateGwRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/web/gwRole/getRole";
//		return getRole(request,pageIndex,pageSize,key);
	}
	
	/**
	 * 根据id获得角色
	 * @param id
	 * @return
	 */
	@RequestMapping("getRoleById")
	@ResponseBody
	public Object getRoleById(HttpServletRequest request,Integer id, Model model) {
		GwRole role = gwRoleService.selectById(id);
		return role;
	}

	/**
	 * 根据id删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteGwRoleById")
	@ResponseBody
	public BaseResultVo deleteGwRoleById(Integer id){
		
		List<XfStaff> xfStaffList = xfStaffServiceImpl.getListByRoleId(id);
		if(xfStaffList!=null && xfStaffList.size()>0) {
			return BaseResultVo.responseFail("该角色正在使用中,无法删除"); 
		}
		
		BaseResultVo resultVo = gwRoleService.deleteGwRoleById(id);
		return resultVo;
	}
	
	@RequestMapping("getRoleGroupById")
	public String getRoleGroupById(HttpServletRequest request,Integer id, Model model) {
		model.addAttribute("roleId", id);
		return "role/setRoleGroup";
	}
	
	@RequestMapping("/setRoleGroupByRoleId")
	@ResponseBody
	public BaseResultVo getRoleGroupsByRoleId(Integer roleId){
		System.out.println("权限获取=============================================");
		if(roleId==0){
			return BaseResultVo.responseFail("数据异常");
		}
		
		//获取所有菜单
		List<GwMenuTree> menuTreeList= gwMenuTreeService.selectAllList();
		
		//获取角色的权限菜单
		List<GwRoleGroup> roleGroupList=gwRoleGroupService.getRoleGroupsByRoleId(roleId);
		List<HashMap<String, Object>> treeList=new ArrayList<HashMap<String,Object>>();
		for (GwMenuTree menuTree : menuTreeList) {
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("id", menuTree.getId());//菜单id
			map.put("pid", menuTree.getFid());//父级菜单id
			map.put("text", menuTree.getName());//菜单名称
			map.put("ischecked", checkIsHasMenuId(menuTree.getId(),roleGroupList));//菜单是否有该权限
			map.put("isExpand", true);
			treeList.add(map);
		}
		
		BaseResultVo baseResultVo=new BaseResultVo();
		Status status=new Status();
		status.setCode(ResponseCode.OPR_SUCCESS);
		status.setMsg("获取权限成功");
		baseResultVo.setStatus(status);
		baseResultVo.setData(treeList);
		return baseResultVo;
	}
	
	@SuppressWarnings("unused")
	private boolean checkIsHasMenuId(int menuId,List<GwRoleGroup>roleGroupList){
		for (GwRoleGroup gwRoleGroupModel : roleGroupList) {
			if(menuId==gwRoleGroupModel.getGwMenuTreeId().getId()){
				return true;
			}
		}
		
		return false;
	}
	
	@RequestMapping("/saveRoleGroups")
	@ResponseBody
	public Object saveRoleGroups(HttpServletRequest request,int roleId,String addIds,String delIds,Integer pageIndex, Integer pageSize, String key){
		if(roleId==0){
			return BaseResultVo.responseFail("角色编号为空");
		}
		//添加权限
		if(addIds.length()>0){
			String[] addMenuIdStr=addIds.split(",");
			List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
			for (int i = 0; i < addMenuIdStr.length; i++) {
				/*HashMap<String, Object> map=new HashMap<String,Object>();
				//角色id
				map.put("roleId", roleId);
				//菜单id
				map.put("menuId", addMenuIdStr[i]);
				//时间
				map.put("time", DateUtil.getCurrentDatetime("-"));
				list.add(map);*/
				GwRoleGroup roleGroup = new GwRoleGroup();
				GwRole gwRole = gwRoleService.selectById(roleId);
				GwMenuTree gwMenuTree = gwMenuTreeService.selectById(Integer.valueOf(addMenuIdStr[i]));
				roleGroup.setGwMenuTreeId(gwMenuTree);
				roleGroup.setGwRoleId(gwRole);
				roleGroup.setTime(DateUtil.getCurrentDatetime("-"));
				gwRoleGroupService.saveOrUpdate(roleGroup);
			}
			//插入权限
			//gwRoleGroupServiceImpl.insertRoleGroups(list);
		} 
		//删除权限
		if(delIds.length()>0){
			String[] delMenuIdStr=delIds.split(",");
			Integer[] delMenuIds=new Integer[delMenuIdStr.length];
			//生成删除的菜单id
			for (int i = 0; i < delMenuIdStr.length; i++) {
				delMenuIds[i]=Integer.parseInt(delMenuIdStr[i]);
			}
			//删除权限
			gwRoleGroupService.deleteListBy(roleId, delMenuIds);
		}
		return getRole(request,pageIndex,pageSize,key);
	}
	
}
