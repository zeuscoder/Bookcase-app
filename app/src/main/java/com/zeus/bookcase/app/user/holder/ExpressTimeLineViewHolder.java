package com.zeus.bookcase.app.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.view.ExpressTimelineView;

/**
 * 快递详情
 * Created by zeus_coder on 2016/2/6.
 */
public class ExpressTimeLineViewHolder extends RecyclerView.ViewHolder {

    public TextView thing;
    public TextView time;
    public ExpressTimelineView mTimelineView;

    public ExpressTimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        thing = (TextView) itemView.findViewById(R.id.tx_thing);
        time = (TextView) itemView.findViewById(R.id.tx_time);
        mTimelineView = (ExpressTimelineView) itemView.findViewById(R.id.time_marker);
        mTimelineView.initLine(viewType);
    }
}
