package com.vagnnermartins.irregularverbs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.vagnnermartins.irregularverbs.enums.GameTypeEnum;
import com.vagnnermartins.irregularverbs.R;
import com.vagnnermartins.irregularverbs.pojo.ResultVerbPojo;
import com.vagnnermartins.irregularverbs.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vagnnermartins on 05/02/16.
 */
public class ItemResultAdapter extends ArrayAdapter<ResultVerbPojo> {

    private final GameTypeEnum gameTypeEnum;
    private List<ResultVerbPojo> original;

    public ItemResultAdapter(Context context, int resource, List<ResultVerbPojo> objects, GameTypeEnum gameTypeEnum) {
        super(context, resource, objects);
        this.gameTypeEnum = gameTypeEnum;
        this.original = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = parent.inflate(getContext(),R.layout.item_result, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ResultVerbPojo item = getItem(position);
        holder.infinitive.setText(item.getVerb().getInfinitive());
        holder.first.setText(item.getVerb().getSp());
        holder.firstResult.setText(item.getSp());
        holder.second.setText(item.getVerb().getPp());
        holder.secondResult.setText(item.getPp());
        checkSpell(holder.firstResult, item.getVerb().getSp(), item.getSp());
        checkSpell(holder.secondResult, item.getVerb().getPp(), item.getPp());
        checkVisibilitiesGame(holder, gameTypeEnum);
        return convertView;
    }

    public void checkVisibilitiesGame(ViewHolder holder, GameTypeEnum gameType){
        if(gameType != GameTypeEnum.BOTH && gameType != GameTypeEnum.SP){
            holder.firstContainer.setVisibility(View.GONE);
        }
        if(gameType != GameTypeEnum.BOTH && gameType != GameTypeEnum.PP){
            holder.secondContainer.setVisibility(View.GONE);
        }
    }

    private void checkSpell(TextView textView, String verb, String spell){
        if(StringUtils.compareVerb(verb, spell)){
            textView.setTextColor(Color.rgb(0, 127, 0));
        }else{
            textView.setTextColor(Color.RED);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                List<ResultVerbPojo> values = (List<ResultVerbPojo>) results.values;
                if(values == null){
                    values = original;
                }
                for (ResultVerbPojo item : values) {
                    add(item);
                }
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    List<ResultVerbPojo> founded = new ArrayList<>();
                    for(ResultVerbPojo item: original){
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
        View firstContainer;
        TextView first;
        TextView firstResult;
        View secondContainer;
        TextView second;
        TextView secondResult;

        public ViewHolder(View view){
            this.infinitive = (TextView) view.findViewById(R.id.item_result_infinitive);
            this.first = (TextView) view.findViewById(R.id.item_result_first);
            this.firstResult = (TextView) view.findViewById(R.id.item_result_first_result);
            this.firstContainer = view.findViewById(R.id.item_result_first_container);
            this.second = (TextView) view.findViewById(R.id.item_result_second);
            this.secondResult = (TextView) view.findViewById(R.id.item_result_second_result);
            this.secondContainer = view.findViewById(R.id.item_result_second_container);
        }
    }
}
