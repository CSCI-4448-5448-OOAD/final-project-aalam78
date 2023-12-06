package com.scm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report {
    private Date startDate;
    private Date endDate;
    private List<String> reportBody;
    private List<Order> orderReport;
    private Stock stockReport;
    private int userID;
    private String reportType;
    private int reportID;

    // Constructor
    public Report(int userID, String reportType, int reportID) {
        this.userID = userID;
        this.reportType = reportType;
        this.reportID = reportID;
        this.startDate = new Date(); // Current date as the default start date
        this.endDate = null; // Not specified initially
        this.reportBody = new ArrayList<>();
        this.orderReport = new ArrayList<>();
        //this.stockReport = new Stock(); // Assuming Stock class exists
        // Assuming you have the required data for Stock creation

        //this.stockReport = new Stock(availableProducts, stockAmount, amountOfProduct);
    }

    // Method to initialize the report
    public void initReport(String reportType) {
        this.reportType = reportType;
        System.out.println("Report initialized. Type: " + reportType);
    }

    // Method to schedule a report
    public void scheduler(int intervalInDays) {
        // Logic to schedule the report based on the specified interval
        System.out.println("Report scheduled. Interval: " + intervalInDays + " days");
    }
}