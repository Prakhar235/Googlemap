package com.pharm.smartprakhar.googlemap;

import dagger.Component;
import dagger.Provides;

@Component

public interface Presentationcomponent {

    //Builds up the Presenter by self initaiting the objects through dagger annotion processing

    Presenter getPresenter();





}
