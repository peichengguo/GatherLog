package com.pcg.dao;

import com.pcg.common.vo.SearchObject;
import com.pcg.common.vo.VisitInfoVo;

import java.util.List;

/**
 * Created by pcg on 16/1/9.
 */
public interface VisitInfoDAO {

    public void insertVisitInfo(VisitInfoVo vo);

    public VisitInfoVo selectVisitInfoById(Long id);

    public List<VisitInfoVo> selectAllVisitInfo(SearchObject seaObj);

    public Integer selectAllVisitInfoCount(SearchObject seaObj);
}
