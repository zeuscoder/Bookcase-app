package com.zeus.bookcase.app.home.widget;

/**
 * Constants that indicates the scroll state of the Scrollable widgets.
 * Created by zeus_coder on 2016/1/29.
 */
public enum  ScrollState {
    /**
     * Widget is stopped.
     * This state does not always mean that this widget have never been scrolled.
     */
    STOP,

    /**
     * Widget is scrolled up by swiping it down.
     */
    UP,

    /**
     * Widget is scrolled down by swiping it up.
     */
    DOWN,
}
