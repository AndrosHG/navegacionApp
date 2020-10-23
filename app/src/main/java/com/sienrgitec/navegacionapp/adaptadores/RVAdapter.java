package com.sienrgitec.navegacionapp.adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sienrgitec.navegacionapp.modelos.ctProveedor_;
import com.sienrgitec.navegacionapp.modelos.opPedidoDet;
import com.sienrgitec.navegacionapp.modelos.opPedidoDet_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RVAdapter<T> extends RecyclerView.Adapter<RVAdapter.ListViewHolder> {

    private List<T> list;
    private Context context;
    private OnViewHolderClick<T> listener;

    //protected abstract void bindView(ctProveedor_ item, ListViewHolder viewHolder);

    public interface OnViewHolderClick<T> {
        void onClick(View view, int position, T item);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private Map<Integer, View> views;

        public ListViewHolder(View view, OnViewHolderClick listener) {
            super(view);
            views = new HashMap<>();
            views.put(0, view);

            if (listener != null)
                view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClick(v, getAdapterPosition(), getItem(getAdapterPosition()));
        }

        public void initViewList(int[] idList) {
            for (int id : idList)
                initViewById(id);
        }

        public void initViewById(int id) {
            View view = (getView() != null ? getView().findViewById(id) : null);

            if (view != null)
                views.put(id, view);
        }

        public View getView() {
            return getView(0);
        }

        public View getView(int id) {
            if (views.containsKey(id))
                return views.get(id);
            else
                initViewById(id);

            return views.get(id);
        }
    }

    protected abstract View createView(Context context, ViewGroup viewGroup, int viewType);

    protected abstract void bindView(T item, RVAdapter.ListViewHolder viewHolder);

    public RVAdapter(Context context){
        this(context, null);
    }

    public RVAdapter(Context context, OnViewHolderClick<T> listener) {
        super();
        this.context = context;
        this.listener = listener;
        list = new ArrayList<>();
    }

    public RVAdapter(Context context, OnViewHolderClick<T> listener, List<T> list) {
        super();
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ListViewHolder(createView(context, viewGroup, viewType), listener);
    }

    @Override
    public void onBindViewHolder(RVAdapter.ListViewHolder holder, int position) {
        bindView(getItem(position), holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public T getItem(int index) {
        return ((list != null && index < list.size()) ? list.get(index) : null);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public OnViewHolderClick<T> getListener() {
        return listener;
    }

    public void setListener(OnViewHolderClick<T> listener) {
        this.listener = listener;
    }

    public void addAll(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void reset() {
        this.list.clear();
        notifyDataSetChanged();
    }
}

