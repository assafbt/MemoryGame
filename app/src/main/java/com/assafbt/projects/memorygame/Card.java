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


    public Card(){
        faceUp =false;
        imgUp ="";
        quantity =0;
    }


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




}
