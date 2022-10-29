package com.hospital.booking.crud;

public class AppointmentModel {
    private String id;
    private String doctorName;
    private String doctorEmail;
    private String doctorAddress;
    private String phoneNo;
    private String Age;
    private String addDate;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getdoctorEmail() {
        return doctorEmail;
    }

    public void setdoctorEmail(String patientName) {
        this.doctorEmail = patientName;
    }

    public String getdoctorAddress() {
        return doctorAddress;
    }

    public void setdoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getaddDate() {
        return addDate;
    }

    public void setaddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AppointmentModel(String doctorEmail,
                            String doctorAddress,
                            String phoneNo,
                            String Age,
                            String addDate){
        this.Age = Age;
        this.doctorEmail = doctorEmail;
        this.doctorAddress = doctorAddress;
        this.phoneNo = phoneNo;
        this.addDate = addDate;
    }

    public AppointmentModel(){

    }
}
