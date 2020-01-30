package ba.unsa.etf.rpr.projekat;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PassengerListReport extends JFrame {
    public void showReport( List<Passenger> passengerList, Flight flight) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/PassengerReportList.jrxml").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("flightName", flight.getAirlineName() + " "+ flight.getCode());

        JRBeanCollectionDataSource jrBeanList = new
                JRBeanCollectionDataSource(passengerList);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, jrBeanList);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void showReport( List<Passenger> passengerList) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/PassengersReport.jrxml").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("flightName", "All Flights");

        JRBeanCollectionDataSource jrBeanList = new
                JRBeanCollectionDataSource(passengerList);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, jrBeanList);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
