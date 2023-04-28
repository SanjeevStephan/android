package com.sanjeev.stephan.murmu.recyclerapp;

public class ExampleItem
{
    private int mImageResource;
    private String mText1;
    private String mText2;
    //private static boolean state = false;

    public ExampleItem(int ImageResource,String text1,String text2)
    {
        this.mImageResource = ImageResource;
        this.mText1 = text1;
        this.mText2 = text2;
    }

    public void changeText1(String text)
    {
        mText2 = text;
    }
    public void changeImage(int image)
    {
        mImageResource = image;
    }

    public int getImageResource()
    {
        return mImageResource;
    }

    public String getText1()
    {
        return mText1;
    }

    public String getText2()
    {
        return mText2;
    }


}
