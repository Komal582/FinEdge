package com.finedge.finedge.Service;
import java.util.List;

import com.finedge.finedge.DTO.MonthlyReport;
import com.finedge.finedge.Model.User;

public interface ReportService {
     
   List<MonthlyReport> generateReport(User user,int year);

}
