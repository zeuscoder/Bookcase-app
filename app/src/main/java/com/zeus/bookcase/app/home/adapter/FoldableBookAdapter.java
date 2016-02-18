package com.zeus.bookcase.app.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.ui.activity.BookDiscountActivity;
import com.zeus.bookcase.app.home.view.FoldableLayout;


import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by zeus_coder on 2015/11/20.
 *
 * FoldableLayout/FoldablePhotoAdapter
 */
public class FoldableBookAdapter extends RecyclerView.Adapter<FoldableBookAdapter.PhotoViewHolder> {

    private String[] mTextset = { "互联网",
            "心理学",
            "传记",
            "体育",
            "社会科学",
            "自然",
            "健身",
            "小说",
            "其他",};

    private String[] mDataSet;
    private Map<Integer, Boolean> mFoldStates = new HashMap<>();
    private Context mContext;

    public FoldableBookAdapter(String[] dataSet, Context context) {
        mDataSet = dataSet;
        mContext = context;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(new FoldableLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {
        final String path = "content://com.zeus.bookcase/demo-pictures/" + mDataSet[position];

        // Bind data
        Picasso.with(holder.mFoldableLayout.getContext()).load(path).into(holder.mImageViewCover);
        Picasso.with(holder.mFoldableLayout.getContext()).load(path).into(holder.mImageViewDetail);
        //holder.mTextViewCover.setText(mDataSet[position].replace(".jpg", ""));
        holder.mTextViewCover.setText(mTextset[position]);

        // Bind state
        if (mFoldStates.containsKey(position)) {
            if (mFoldStates.get(position) == Boolean.TRUE) {
                if (!holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.foldWithoutAnimation();
                }
            } else if (mFoldStates.get(position) == Boolean.FALSE) {
                if (holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.unfoldWithoutAnimation();
                }
            }
        } else {
            holder.mFoldableLayout.foldWithoutAnimation();
        }

        holder.mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/jpg");
                Uri uri = Uri.parse(path);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                mContext.startActivity(Intent.createChooser(shareIntent, "Share image using"));*/
                //mContext.startActivity(new Intent(mContext, BookKindListActivity.class));
                mContext.startActivity(new Intent(mContext, BookDiscountActivity.class));
            }
        });

        holder.mFoldableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.unfoldWithAnimation();
                } else {
                    holder.mFoldableLayout.foldWithAnimation();
                }
            }
        });
        holder.mFoldableLayout.setFoldListener(new FoldableLayout.FoldListener() {
            @Override
            public void onUnFoldStart() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(5);
                }
            }

            @Override
            public void onUnFoldEnd() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
                mFoldStates.put(holder.getAdapterPosition(), false);
            }

            @Override
            public void onFoldStart() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(5);
                }
            }

            @Override
            public void onFoldEnd() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
                mFoldStates.put(holder.getAdapterPosition(), true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    protected static class PhotoViewHolder extends RecyclerView.ViewHolder {

        protected FoldableLayout mFoldableLayout;

        @Bind(R.id.imageview_cover)
        protected ImageView mImageViewCover;

        @Bind(R.id.imageview_detail)
        protected ImageView mImageViewDetail;

        @Bind(R.id.textview_cover)
        protected TextView mTextViewCover;

        @Bind(R.id.share_button)
        protected Button mButtonShare;

        public PhotoViewHolder(FoldableLayout foldableLayout) {
            super(foldableLayout);
            mFoldableLayout = foldableLayout;
            foldableLayout.setupViews(R.layout.home__activity_book_list_item_cover, R.layout.home__activity_book_list_item_detail, R.dimen.card_cover_height, itemView.getContext());
            ButterKnife.bind(this, foldableLayout);
        }
    }
}
