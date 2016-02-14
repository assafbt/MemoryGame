/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

/**
 * Created by assafbt on 12/02/2016.
 */
public class Card {

    private boolean faceUp;
    private String imgUp;
    int quantity;

//    private String imgDown;
//    private String fileName;


    public boolean getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public String getImgUp() {
        return imgUp;
    }

    public void setImgUp(String imgUp) {
        this.imgUp = imgUp;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


/*
    public String getImgDown() {
        return imgDown;
    }

    public void setImgDown(String imgDown) {
        this.imgDown = imgDown;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    */





}
