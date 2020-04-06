package com.example.smarthome2020.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome2020.BR;

import java.util.List;

public class BaseBindingAdapter<T> extends RecyclerView.Adapter<BaseBindingAdapter.ViewHolder> {
    private List<T> data;
    private LayoutInflater inflater;
    private @LayoutRes
    int resId;

    public BaseBindingAdapter(Context context, @LayoutRes int resId) {
        inflater = LayoutInflater.from(context);
        this.resId = resId;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    @NonNull
    @Override
    public BaseBindingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(inflater, resId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingAdapter.ViewHolder holder, int position) {
        T item = data.get(position);
        holder.binding.setVariable(BR.item_device, item);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public ViewHolder(ViewDataBinding inflate) {
            super(inflate.getRoot());
            this.binding = inflate;
        }
    }
}

