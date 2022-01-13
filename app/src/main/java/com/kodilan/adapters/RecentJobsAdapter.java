package com.kodilan.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.gravity.IChildGravityResolver;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kodilan.R;
import com.kodilan.models.CompanyInfo;
import com.kodilan.models.Data;
import com.kodilan.models.Tag;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecentJobsAdapter extends RecyclerView.Adapter<RecentJobsAdapter.RecentJobsViewHolder> {

    private Context context;
    private List<Data> dataList;
    private ArrayList<Tag> tagArrayList;
    private TagsAdapter tagsAdapter;
    private CompanyAdapter companyAdapter;

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public boolean showShimmer = true;

    public RecentJobsAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecentJobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_rv_item, parent, false);
        return new RecentJobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentJobsViewHolder holder, int position) {
        Data data = dataList.get(position);
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer();
        } else {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            holder.positionTxt.setBackground(null);
            holder.typeTxt.setBackground(null);
            holder.companyLogo.setBackground(null);

            List<CompanyInfo> companyInfos = new ArrayList<CompanyInfo>();
            companyInfos.add(new CompanyInfo(data.company.name, data.location, formatDate(data.updated_at)));

            ChipsLayoutManager companyLayout = ChipsLayoutManager.newBuilder(context)
                    .setChildGravity(Gravity.TOP)
                    .setScrollingEnabled(false)
                    .setMaxViewsInRow(3)
                    .setGravityResolver(new IChildGravityResolver() {
                        @Override
                        public int getItemGravity(int position) {
                            return Gravity.CENTER;
                        }
                    })
                    .setOrientation(ChipsLayoutManager.HORIZONTAL)
                    .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                    .withLastRow(true)
                    .build();

            holder.companyRv.setLayoutManager(companyLayout);
            companyAdapter = new CompanyAdapter(context, companyInfos);
            holder.companyRv.setAdapter(companyAdapter);

            holder.positionTxt.setText(data.position);

            ChipsLayoutManager tagLayout = ChipsLayoutManager.newBuilder(context)
                    .setChildGravity(Gravity.TOP)
                    .setScrollingEnabled(false)
                    .setMaxViewsInRow(5)
                    .setGravityResolver(new IChildGravityResolver() {
                        @Override
                        public int getItemGravity(int position) {
                            return Gravity.CENTER;
                        }
                    })
                    .setOrientation(ChipsLayoutManager.HORIZONTAL)
                    .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                    .withLastRow(true)
                    .build();
            holder.tagsRv.setLayoutManager(tagLayout);
            holder.tagsRv.setHasFixedSize(false);

            if (data.type == 1) {
                holder.typeTxt.setBackground(ContextCompat.getDrawable(context, R.drawable.badge_button_blue));
                holder.typeTxt.setTextColor(context.getResources().getColor(R.color.blue));
                holder.typeTxt.setText(context.getResources().getString(R.string.full_time));
            } else {
                holder.typeTxt.setBackground(ContextCompat.getDrawable(context, R.drawable.badge_button_orange));
                holder.typeTxt.setTextColor(context.getResources().getColor(R.color.orange));
                holder.typeTxt.setText(context.getResources().getString(R.string.half_time));
            }

            Uri uri = Uri.parse(data.company.logo);
            Glide.with(context).load(uri).centerCrop().placeholder(R.drawable.empty_company_logo).error(R.drawable.empty_company_logo).into(holder.companyLogo);

            tagArrayList = data.tags;
            tagsAdapter = new TagsAdapter(context, tagArrayList);
            holder.tagsRv.setAdapter(tagsAdapter);
            holder.tagsRv.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        }
    }

    public String formatDate(String dateTime) {
        try {
            Date date = sdf.parse(dateTime);
            PrettyTime prettyTime = new PrettyTime("tr");
            return prettyTime.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "hesaplanamadı";
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class RecentJobsViewHolder extends ViewHolder {

        private final TextView positionTxt, typeTxt;
        private final ImageView companyLogo;
        private RecyclerView tagsRv, companyRv;
        private ShimmerFrameLayout shimmerFrameLayout;

        public RecentJobsViewHolder(View itemView) {
            super(itemView);

            positionTxt = itemView.findViewById(R.id.positionTxt);
            typeTxt = itemView.findViewById(R.id.typeTxt);
            companyLogo = itemView.findViewById(R.id.companyLogo);

            companyRv = itemView.findViewById(R.id.companyRv);
            tagsRv = itemView.findViewById(R.id.tagsRv);

            shimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout);
        }
    }

}
