package com.pcg.service.impl;

import com.pcg.common.vo.SearchObject;
import com.pcg.common.vo.VisitInfoVo;
import com.pcg.dao.VisitInfoDAO;
import com.pcg.service.VisitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcg on 16/1/9.
 */
@Service
public class VisitInfoServiceImpl implements VisitInfoService {

    @Autowired
    private VisitInfoDAO visitInfoDAO;

    @Override
    public void insertVisitInfo(VisitInfoVo vo) {
        visitInfoDAO.insertVisitInfo(vo);
    }

    @Override
    public VisitInfoVo selectVisitInfoById(Long id) {
        return visitInfoDAO.selectVisitInfoById(id);
    }

    @Override
    public List<VisitInfoVo> selectAllVisitInfo(SearchObject seaObj) {
        return visitInfoDAO.selectAllVisitInfo(seaObj);
    }

    @Override
    public Integer selectAllVisitInfoCount(SearchObject seaObj) {
        return visitInfoDAO.selectAllVisitInfoCount(seaObj);
    }
}
