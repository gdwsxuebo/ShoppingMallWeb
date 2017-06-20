package com.smw.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smw.dao.XfTillidStateDao;
import com.smw.pojo.XfTillidState;
import com.smw.service.XfTillidStateService;

/**
 * 平板收银机在线服务层实现接口
 * @author suen
 * @date 2016年5月19日-下午2:33:33
 * @version 1.0
 */
@Service
public class XfTillidStateServiceImpl implements XfTillidStateService {

	@Resource
	private XfTillidStateDao xfTillidStateDao;
	
	@Override
	public XfTillidState selectByStrId(String tillid) {
		return xfTillidStateDao.selectByStrId(tillid);
	}

	/**
	 * 更新或者保存平板收银机在线
	 * @param xfTillidState 平板收银机在线
	 * @return
	 */
	@Override
	public void saveOrUpdate(XfTillidState xfTillidState) {
		xfTillidStateDao.saveOrUpdateXFTS(xfTillidState);
	}

	/**
	 * 查询收银机集合
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public Integer getCount(String storeCode, String key) {
		return xfTillidStateDao.getCount(storeCode,key);
	}

	/**
	 * 查询收银机集合
	 * @param pageIndex 页码
	 * @param pageSiz 每页显示的行数
	 * @param storeCode 店铺号
	 * @param key 搜索条件
	 * @return
	 */
	@Override
	public List<XfTillidState> getXfTillidList(Integer pageIndex, Integer pageSiz, String storeCode, String key) {
		return xfTillidStateDao.getXfTillidList(pageIndex,pageSiz,storeCode,key);
	}

	/**
	 * 根据店铺编号和以平板编号进行分组统计平板数
	 * @param xfStorecode 店铺编号
	 * @return
	 */
	@Override
	public int getCountByXfSCodeGRBYId(String xfStorecode) {
		return xfTillidStateDao.getCountByXfSCodeGRBYId(xfStorecode);
	}

	/**
	 * 根据店铺编号删除平板收银机
	 * @param xfStorecode 店铺编号
	 */
	@Override
	public void deleteXFTSByStCod(String xfStorecode) {
		xfTillidStateDao.deleteXFTSByStCod(xfStorecode);
	}

	@Override
	public Integer getTillidCountByStoreId(String storeid) {
		return xfTillidStateDao.getTillidByStoreId(storeid);
	}

	@Override
	public Integer getTillidCountByCoreStoreId(String storeid) {

		return xfTillidStateDao.getTillidCountByCoreStoreId(storeid);
	}

	@Override
	public List<XfTillidState> getTillidListBy(String storeid) {
		// TODO Auto-generated method stub
		return xfTillidStateDao.getTillidListBy(storeid);
	}

	@Override
	public List<XfTillidState> getAllTillids() {
		// TODO Auto-generated method stub
		return xfTillidStateDao.getAllTillids();
	}

	@Override
	public XfTillidState getXfTillidStateBytillid(String tillids) {
		// TODO Auto-generated method stub
		return xfTillidStateDao.getXfTillidStateBytillid(tillids);
	}

	@Override
	public XfTillidState getXfTillidStateBystaffCode(String staffCode) {
		// TODO Auto-generated method stub
		return xfTillidStateDao.getXfTillidStateBystaffCode(staffCode);
	}

	@Override
	public void delTillidStateTabe() {
		// TODO Auto-generated method stub
		xfTillidStateDao.delTillidStateTabe();
	}




}
