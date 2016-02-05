package com.vagnnermartins.irregularverbs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.bean.Verb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vagnnermartins on 03/02/16.
 */
public class VerbAdapter extends ArrayAdapter<Verb> {

    private List<Verb> original;

    public VerbAdapter(Context context, int resource, List<Verb> objects) {
        super(context, resource, objects);
        this.original = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = parent.inflate(getContext(), R.layout.item_verb, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Verb item = getItem(position);
        holder.infinitive.setText(item.getInfinitive());
        holder.sp.setText(item.getSp());
        holder.pp.setText(item.getPp());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                List<Verb > values = (List<Verb>) results.values;
                if(values == null){
                    values = original;
                }
                for (Verb item : values) {
                    add(item);
                }
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    List<Verb> founded = new ArrayList<>();
                    for(Verb item: original){
                        if(item.toString().toLowerCase().contains(constraint)){
                            founded.add(item);
                        }
                    }
                    result.values = founded;
                    result.count = founded.size();
                }
                return result;
            }
        };
    }

    class ViewHolder{
        TextView infinitive;
        TextView sp;
        TextView pp;

        public ViewHolder(View view){
            this.infinitive = (TextView) view.findViewById(R.id.item_verb_infinitive);
            this.sp = (TextView) view.findViewById(R.id.item_verb_sp);
            this.pp = (TextView) view.findViewById(R.id.item_verb_pp);
        }
    }
}
