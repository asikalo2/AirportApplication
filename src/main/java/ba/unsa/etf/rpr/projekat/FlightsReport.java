package ba.unsa.etf.rpr.projekat;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightsReport extends JFrame {

    public void showReport(List<Flight> flightList) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/FlightsReport.jrxml").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();

        JRBeanCollectionDataSource jrBeanList = new
                JRBeanCollectionDataSource(flightList);

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
