package com.ss.common.utils;

import org.springframework.data.domain.Sort;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 
	private int offset;
	// 每页条数
	private int limit;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		this.offset = Integer.parseInt(params.get("offset").toString());
		this.limit = Integer.parseInt(params.get("limit").toString());
		this.put("offset", offset);
		this.put("page", offset / limit + 1);
		this.put("limit", limit);
		this.put("delFlag","0");
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.put("offset", offset);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

    /**
     * 从此映射中删除指定键的映射（如果存在）
     * @param key
     */
	public void delete(String key){
        this.remove(key);
    }

    /**
     * 清除默认的delFlag
     */
    public void clearFlag(){
        this.remove("delFlag");
    }

	/**
	 * 排序
	 * 拼接[`]反单引号 可能出现关键字 防止 sql注入违规
	 * @param direction
	 * @param sort
	 */
    public void sort(Sort.Direction direction, String sort){
    	if(direction.isAscending()){
			this.put("order","ASC");
		}else {
			this.put("order","DESC");
		}
		//拼接[`]反单引号 可能出现关键字 防止 sql注入违规
		this.put("sort", "`"+sort+"`");
	}

}
