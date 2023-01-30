package com.ssafy.realcart.data.dao.inter;

import java.util.List;

import com.ssafy.realcart.data.entity.BoardReport;

public interface IBoardReportDAO {
	boolean saveReport(BoardReport boardReport);
    List<BoardReport> getBoardReportAll();
    BoardReport getBoardReport(int id);
	boolean deleteReport(int id);
}
