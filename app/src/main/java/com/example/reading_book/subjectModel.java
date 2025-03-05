package com.example.reading_book;

public class subjectModel {

    private String subjectName, pathImage;

    public subjectModel (String subjectName, String pathImage){
        this.subjectName = subjectName;
        this.pathImage = pathImage;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
