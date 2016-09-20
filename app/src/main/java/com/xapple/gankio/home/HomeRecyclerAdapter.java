package com.xapple.gankio.home;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xapple.gankio.APP;
import com.xapple.gankio.R;
import com.xapple.gankio.data.model.GankIO;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 14:40
 * 修改人：wengyiming
 * 修改时间：2016/8/26 14:40
 * 修改备注：
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements HomeFragment.GetResult {


    private List<GankIO> mGankIOList;


    public HomeRecyclerAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ac_rv_home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            GankIO article = mGankIOList.get(position);
            final String url = article.getUrl();
            if (url.endsWith(".jpg") || url.endsWith(".png")) {
                ((ViewHolder) holder).mHomeImg.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView.getContext())
                        .load(url)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .into(((ViewHolder) holder).mHomeImg);
            } else {
                ((ViewHolder) holder).mHomeImg.setVisibility(View.GONE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        APP.getInstance().startActivity(intent);
                    }
                });
            }
            ((ViewHolder) holder).mDesc.setText(article.getDesc());
            ((ViewHolder) holder).mPublishedAt.setText(article.getPublishedAt());
        }
    }

    @Override
    public int getItemCount() {
        return mGankIOList == null ? 0 : mGankIOList.size();
    }

    @Override
    public void onArticleResult(List<GankIO> mGankIOList) {
        if (this.mGankIOList == null) {
            this.mGankIOList = new ArrayList<>();
            this.mGankIOList.addAll(mGankIOList);
            notifyDataSetChanged();
        }else {
            ArrayList<GankIO> alls = (ArrayList<GankIO>) mGankIOList;
            for (GankIO all : alls) {
                this.mGankIOList.add(all);
                notifyItemChanged(this.mGankIOList.size() - 1);
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_img)
        ImageView mHomeImg;
        @BindView(R.id.desc)
        TextView mDesc;
        @BindView(R.id.publishedAt)
        TextView mPublishedAt;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
