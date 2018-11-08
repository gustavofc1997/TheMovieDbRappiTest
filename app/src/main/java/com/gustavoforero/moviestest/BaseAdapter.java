package com.gustavoforero.moviestest;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public abstract class BaseAdapter<Type extends RecyclerView.ViewHolder, Data> extends RecyclerView.Adapter<Type>{

    public abstract void setData(List<Data> data);
}
