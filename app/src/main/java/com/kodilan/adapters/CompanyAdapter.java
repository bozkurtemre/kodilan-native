package com.kodilan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kodilan.R;
import com.kodilan.models.CompanyInfo;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private Context context;
    private List<CompanyInfo> companyInfoList;

    public CompanyAdapter(Context context, List<CompanyInfo> companyInfoList) {
        this.context = context;
        this.companyInfoList = companyInfoList;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.company_rv_item, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.companyNameTxt.setText(companyInfoList.get(0).name);
                holder.widgetLogo.setBackgroundResource(R.drawable.ic_briefcase);
                break;
            case 1:
                holder.companyNameTxt.setText(companyInfoList.get(0).location);
                holder.widgetLogo.setBackgroundResource(R.drawable.ic_map_pin);
                break;
            case 2:
                holder.companyNameTxt.setText(companyInfoList.get(0).updatedAt);
                holder.widgetLogo.setBackgroundResource(R.drawable.ic_clock);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class CompanyViewHolder extends ViewHolder {

        private TextView companyNameTxt;
        private ImageView widgetLogo;

        public CompanyViewHolder(View itemView) {
            super(itemView);

            widgetLogo = itemView.findViewById(R.id.widgetLogo);
            companyNameTxt = itemView.findViewById(R.id.companyNameTxt);
        }
    }

}
