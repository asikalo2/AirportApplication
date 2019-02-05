package ba.unsa.etf.rpr.projekat;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Reports extends JFrame {

    public void showBoadingPass(String passengerName, String airlineName, String flightNo,
                                String titleDate, String boardingTime) throws JRException {
        //iz predavanja
        String reportSrcFile = getClass().getResource("/reports/BoardingPass.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        parameters.put("passengerName", passengerName);
        parameters.put("airlineName", airlineName);
        parameters.put("flightNo", flightNo);
        parameters.put("titleDate", titleDate);
        parameters.put("boardingTime", boardingTime);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, AirportDAO.getConn());
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }
}
