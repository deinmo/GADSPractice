package com.alc.gadspractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GADSRecycclerAdapter extends RecyclerView.Adapter<GADSRecycclerAdapter.ViewHolder> {
    private final Context context;
    private final LeadersClass[] leadersClasses;
    private final LayoutInflater layoutInflater;

    public GADSRecycclerAdapter(Context context, LeadersClass[] leadersClasses) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.leadersClasses = leadersClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeadersClass one = leadersClasses[position];
        holder.textView.setText(one.getName());
        holder.textView2.setText(one.getCountry());
        holder.textView3.setText(one.getSkillhours());
    }

    @Override
    public int getItemCount() {
        return leadersClasses.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final TextView textView2;
        private final TextView textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.textView3);
            textView2 = (TextView)itemView.findViewById(R.id.textView4);
            textView3 = (TextView)itemView.findViewById(R.id.textView5);
        }
    }
}
