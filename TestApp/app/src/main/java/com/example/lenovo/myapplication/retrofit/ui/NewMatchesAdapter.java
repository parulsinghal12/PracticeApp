package com.example.lenovo.myapplication.retrofit.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.databinding.NewMatchRowItemBinding;
import com.example.lenovo.myapplication.retrofit.model.MatchData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NewMatchesAdapter extends RecyclerView.Adapter<NewMatchesAdapter.NewMatchViewHolder> {

    private List<MatchData> list_data;
    private Context context;

    public NewMatchesAdapter(List<MatchData> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }

    @NonNull
    @Override
    public NewMatchesAdapter.NewMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewMatchRowItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.new_match_row_item, parent, false);

        return new NewMatchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewMatchViewHolder holder, int position) {
        MatchData movie = list_data.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return list_data != null ? list_data.size() : 0;
    }

    public class NewMatchViewHolder extends RecyclerView.ViewHolder {

        public NewMatchRowItemBinding itemRowBinding;
        public NewMatchViewHolder(@NonNull NewMatchRowItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(MatchData match) {
            itemRowBinding.setModel(match);
            itemRowBinding.executePendingBindings();
        }
    }
}
