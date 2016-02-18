package com.zeus.bookcase.app.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.holder.ExpressTimeLineViewHolder;
import com.zeus.bookcase.app.user.model.Express;
import com.zeus.bookcase.app.user.view.ExpressTimelineView;

import java.util.List;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class ExpressTimeLineAdapter extends RecyclerView.Adapter<ExpressTimeLineViewHolder> {
    private List<Express> mFeedList;

    public ExpressTimeLineAdapter(List<Express> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return ExpressTimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public ExpressTimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.user__item_express_timeline, null);
        return new ExpressTimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ExpressTimeLineViewHolder holder, int position) {
        Express timeLineModel = mFeedList.get(position);
        holder.thing.setText(timeLineModel.getThing());
        holder.time.setText(timeLineModel.getTime());
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

}
