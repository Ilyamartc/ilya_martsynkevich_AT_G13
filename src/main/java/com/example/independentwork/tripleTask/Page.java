package com.example.independentwork.tripleTask;

public class Page {
    private int pageNumber;
    private String textPage;

    public Page(int pageNumber, String textPage) {
        this.pageNumber = pageNumber;
        this.textPage = textPage;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public String getTextPage() {
        return textPage;
    }
    public void setTextPage(String textPage) {
        this.textPage = textPage;
    }
    public void read() {
        System.out.println("Page " + pageNumber + ": " + textPage);
    }
}
