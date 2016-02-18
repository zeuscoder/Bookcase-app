package com.zeus.bookcase.app.cabinet.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.adapter.FilmRoomAdapter;
import com.zeus.bookcase.app.cabinet.adapter.HeaderAndFooterFileRoomAdapter;
import com.zeus.bookcase.app.cabinet.utils.DeviceUtils;
import com.zeus.bookcase.app.cabinet.utils.FilmLinearLayoutManager;
import com.zeus.bookcase.app.cabinet.utils.FilmViewUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zeus_coder on 2015/12/13.
 */
public class FilmRoomActivity extends BaseActivity {

    private int item_normal_height;
    private int item_max_height;
    private float item_normal_alpha;
    private float item_max_alpha;
    private float alpha_d;
    private float item_normal_font_size;
    private float item_max_font_size;
    private float font_size_d;

    private List<Integer> walls = Arrays.asList(R.mipmap.wall01,
            R.mipmap.wall02, R.mipmap.wall03, R.mipmap.wall04, R.mipmap.wall05,
            R.mipmap.wall06, R.mipmap.wall07, R.mipmap.wall08, R.mipmap.wall09,
            R.mipmap.wall10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_film);
        initTopButton(R.string.activity_film, R.mipmap.app__top_bar_arrow_back, 0);

        item_max_height = (int) getResources().getDimension(R.dimen.item_max_height);
        item_normal_height = (int) getResources().getDimension(R.dimen.item_normal_height);
        item_normal_font_size = getResources().getDimension(R.dimen.item_normal_font_size);
        item_max_font_size = getResources().getDimension(R.dimen.item_max_font_size);
        item_normal_alpha = getResources().getFraction(R.fraction.item_normal_mask_alpha, 1, 1);
        item_max_alpha = getResources().getFraction(R.fraction.item_max_mask_alpha, 1, 1);

        font_size_d = item_max_font_size - item_normal_font_size;
        alpha_d = item_max_alpha - item_normal_alpha;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.video_list);

        recyclerView.setLayoutManager(new FilmLinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        FilmRoomAdapter myAdapter = new FilmRoomAdapter(this, walls);
        HeaderAndFooterFileRoomAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterFileRoomAdapter(myAdapter);
        recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
        View moreView = getLayoutInflater().inflate(R.layout.case__activity_film_footer_more, null);
        TextView more = (TextView) moreView.findViewById(R.id.more);
        more.getLayoutParams().height = DeviceUtils.getScreenHeight(this) -
                item_max_height;
        FilmViewUtils.setFooterView(recyclerView, moreView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                RecyclerView.ViewHolder firstViewHolder = recyclerView
                        .findViewHolderForLayoutPosition(linearLayoutManager.findFirstVisibleItemPosition());
                RecyclerView.ViewHolder secondViewHolder = recyclerView
                        .findViewHolderForLayoutPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition());
                RecyclerView.ViewHolder threeViewHolder = recyclerView
                        .findViewHolderForLayoutPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                RecyclerView.ViewHolder lastViewHolder = recyclerView
                        .findViewHolderForLayoutPosition(linearLayoutManager.findLastVisibleItemPosition());
                if (firstViewHolder != null && firstViewHolder instanceof FilmRoomAdapter.ViewHolder) {
                    FilmRoomAdapter.ViewHolder viewHolder = (FilmRoomAdapter.ViewHolder) firstViewHolder;
                    if (viewHolder.itemView.getLayoutParams().height - dy <= item_max_height
                            && viewHolder.itemView.getLayoutParams().height - dy >= item_normal_height) {
                        viewHolder.itemView.getLayoutParams().height = viewHolder.itemView.getLayoutParams().height - dy;
                        viewHolder.mark.setAlpha(viewHolder.mark.getAlpha() - dy * alpha_d / item_normal_height);
                        viewHolder.text.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                viewHolder.text.getTextSize() - dy * font_size_d / item_normal_height);
                        viewHolder.itemView.setLayoutParams(viewHolder.itemView.getLayoutParams());
                    }
                }
                if (secondViewHolder != null && secondViewHolder instanceof FilmRoomAdapter.ViewHolder) {
                    FilmRoomAdapter.ViewHolder viewHolder = (FilmRoomAdapter.ViewHolder) secondViewHolder;
                    if (viewHolder.itemView.getLayoutParams().height + dy <= item_max_height
                            && viewHolder.itemView.getLayoutParams().height + dy >= item_normal_height) {
                        viewHolder.itemView.getLayoutParams().height = viewHolder.itemView.getLayoutParams().height + dy;
                        viewHolder.mark.setAlpha(viewHolder.mark.getAlpha() + dy * alpha_d / item_normal_height);
                        viewHolder.text.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                viewHolder.text.getTextSize() + dy * font_size_d / item_normal_height);
                        viewHolder.itemView.setLayoutParams(viewHolder.itemView.getLayoutParams());
                    }
                }

                if (threeViewHolder != null && threeViewHolder instanceof FilmRoomAdapter.ViewHolder) {
                    FilmRoomAdapter.ViewHolder viewHolder = (FilmRoomAdapter.ViewHolder) threeViewHolder;
                    viewHolder.mark.setAlpha(item_normal_alpha);
                    viewHolder.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, item_normal_font_size);
                    viewHolder.itemView.getLayoutParams().height = item_normal_height;
                    viewHolder.itemView.setLayoutParams(viewHolder.itemView.getLayoutParams());
                }
                if (lastViewHolder != null && lastViewHolder instanceof FilmRoomAdapter.ViewHolder) {
                    FilmRoomAdapter.ViewHolder viewHolder = (FilmRoomAdapter.ViewHolder) lastViewHolder;
                    viewHolder.mark.setAlpha(item_normal_alpha);
                    viewHolder.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, item_normal_font_size);
                    viewHolder.itemView.getLayoutParams().height = item_normal_height;
                    viewHolder.itemView.setLayoutParams(viewHolder.itemView.getLayoutParams());
                }
            }
        });
    }
}
