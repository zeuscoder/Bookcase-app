package com.zeus.bookcase.app.home.data;

import java.util.ArrayList;

/**
 * Created by zeus_coder on 2015/12/1.
 */
public class BookDiscountSampleData {

    public static final int SAMPLE_DATA_ITEM_COUNT = 30;

    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            data.add("SAMPLE #");
        }

        return data;
    }
}
