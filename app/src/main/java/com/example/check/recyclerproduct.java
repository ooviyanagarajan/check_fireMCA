package com.example.check;

class Recycproduct {
    String name;
    String email;
    String area;
    String phno;
    public Recycproduct( String sname,String semail,String sarea,String sphno)
    {
        name=sname;
        email=semail;
        area=sarea;
        phno=sphno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
