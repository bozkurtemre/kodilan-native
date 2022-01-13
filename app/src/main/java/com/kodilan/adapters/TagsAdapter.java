package com.kodilan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.kodilan.R;
import com.kodilan.models.Tag;

import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

    private Context context;
    private List<Tag> tagList;

    public TagsAdapter(Context context, List<Tag> tagList) {
        this.context = context;
        this.tagList = tagList;
    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tags_rv_item, parent, false);
        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsAdapter.TagsViewHolder holder, int position) {
        Tag tag = tagList.get(position);
        holder.tagNameTxt.setText(tag.name);
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    static class TagsViewHolder extends ViewHolder {

        private final TextView tagNameTxt;

        public TagsViewHolder(View itemView) {
            super(itemView);
            tagNameTxt = itemView.findViewById(R.id.tagNameTxt);
        }
    }
}
