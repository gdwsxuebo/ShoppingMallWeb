package com.smw.service;

import java.util.List;

import com.smw.pojo.XfTillidState;

/**
 * 平板收银机在线服务层接口
 * @author suen
 * @date 2016年5月19日-下午2:32:29
 * @version 1.0
 */
public interface XfTillidStateService{

	/**
	 * 根据收银机编号(ID)查询
	 * @param tillid id
	 * @return
	 */
	XfTillidState selectByStrId(String tillid);

	/**
	 * 更新或者保存平板收银机在线
	 * @param xfTillidState 平板收银机在线
	 * @return
	 */
	void saveOrUpdate(XfTillidState xfTillidState);

	/**
	 * 查询收银机总数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	Integer getCount(String storeCode, String key);

	/**
	 * 查询收银机集合
	 * @param pageIndex 页码
	 * @param pageSiz 每页显示的行数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	List<XfTillidState> getXfTillidList(Integer pageIndex, Integer pageSiz,String storeCode, String key);

	/**
	 * 根据店铺编号和以平板编号进行分组统计平板数
	 * @param xfStorecode 店铺编号
	 * @return
	 */
	int getCountByXfSCodeGRBYId(String xfStorecode);

	/**
	 * 根据店铺编号删除平板收银机
	 * @param xfStorecode 店铺编号
	 */
	void deleteXFTSByStCod(String xfStorecode);
	
	public Integer getTillidCountByStoreId(String storeid);

	public Integer getTillidCountByCoreStoreId(String storeid);

	List<XfTillidState> getTillidListBy(String storeid);

	List<XfTillidState> getAllTillids();

	XfTillidState getXfTillidStateBytillid(String tillids);

	XfTillidState getXfTillidStateBystaffCode(String staffCode);

	void delTillidStateTabe();



}
