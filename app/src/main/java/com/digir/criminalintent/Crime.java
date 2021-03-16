package com.digir.criminalintent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
//    private boolean mRequiresPolice;

    public Crime() {
        //UUID sharing simple methods generating different IDs
        mId = UUID.randomUUID();

        //Date with default constructor assigns current date
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

//    public boolean isRequiresPolice() {
//        return mRequiresPolice;
//    }
//
//    public void setRequiresPolice(boolean requiresPolice) {
//        mRequiresPolice = requiresPolice;
//    }
}
