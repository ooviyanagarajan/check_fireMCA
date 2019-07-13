package com.example.check;

public class adminproduct {



        String name;
        String email;
        String latitude;
        String longitude;
        String imageurl;
        String information;
        String date_time;

        public adminproduct(String name, String email, String latitude, String longitude, String imageurl, String information, String date_time) {
            this.name=name;
            this.email=email;
            this.latitude=latitude;
            this.longitude=longitude;
            this.imageurl=imageurl;
            this.information=information;
            this.date_time=date_time;
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }
        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }
    }



