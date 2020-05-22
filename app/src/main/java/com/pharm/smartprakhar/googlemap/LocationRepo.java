package com.pharm.smartprakhar.googlemap;

import com.google.android.gms.maps.model.LatLng;

public   class LocationRepo {
    //Singleton class for keeping a track of locations
    public LatLng getLatLng() {
        return latLng;
    }

    public   void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    private LocationRepo() {
    }

     LatLng latLng;

    public static LocationRepo getObj() {
        if(obj==null)
        {
            obj=new  LocationRepo();
            return obj;
        }
        else
        {
            return obj;

        }

    }

    static LocationRepo obj;
}
