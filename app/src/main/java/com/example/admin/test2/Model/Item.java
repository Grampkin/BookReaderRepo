package com.example.admin.test2.Model;



public class Item {
    private String bookName;
    private String authorName;
//    private Boolean isFavorite;





    public Item(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
//        this.isFavorite = isFavorite;

    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }



    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


//    public Boolean getIsFavorite() {
//        return isFavorite;
//    }
//
//    public void setIsFavorite(Boolean isFavorite) {
//        this.isFavorite = isFavorite;
//    }


}
