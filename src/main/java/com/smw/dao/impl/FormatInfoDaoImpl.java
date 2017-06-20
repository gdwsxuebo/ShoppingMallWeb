package com.smw.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.smw.dao.FormatInfoDao;
import com.smw.dao.BaseDao_suen.BaseDaoSupport;
import com.smw.pojo.FormatInfo;

@Repository
public class FormatInfoDaoImpl extends BaseDaoSupport<FormatInfo> implements FormatInfoDao {

	public void saveFormatInfo(List<FormatInfo> formatInfo) {
		for (FormatInfo fi : formatInfo) {
			saveOrUpdate(fi);
		}
	}
	
	/**
	 *  查询符合业态的集合
	 */
	public List<FormatInfo> getFormatInfoListByIdAndFid(Integer pageIndex, Integer pageSize,String id){
		List<FormatInfo> formatInfos;
		if(!"".equals(id)){
			formatInfos = getList(pageIndex, pageSize, " id in ("+id+") or fid in ("+id+") ");
		}else{
			formatInfos = getList(pageIndex, pageSize," state=1 ");
		}
		return formatInfos;
	}
	

	public Integer getCount(String key) {
		String count;
		if (key == null) {
			count = executeHQL("select count(1) from FormatInfo where fid = 0").toString();
		} else {
			count = executeHQL("select count(1) from FormatInfo x where fid = 0 and x.name like ? or x.bm like ", "%" + key + "%",
					"%" + key + "%").toString();
		}
		return count == null ? 0 : Integer.parseInt(count);
	}

	public List<FormatInfo> getFormatInfoList(Integer pageIndex, Integer pageSize, String key) {
		List<FormatInfo> formatInfos;
		if (key == null) {
			formatInfos = getList(pageIndex, pageSize, " fid=0 and state = 1 ");
		} else {
			formatInfos = getList(pageIndex, pageSize, " fid=0 and name like ? or bm like ? ", "%" + key + "%", "%" + key + "%");
		}
		return formatInfos;
	}
	
	public void delFormat(){
		String sql = "TRUNCATE TABLE format_info";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

}
