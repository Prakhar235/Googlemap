package com.pharm.smartprakhar.googlemap;

public class Presenter {
  private  View view;
     Presenter(View view) {
        this. view=view;
    }

    public void setlocation()
    {
        view.displaylocation();

    }


}
