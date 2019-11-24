package com.example.app50001;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;

public class backendCustomExpandabaleListView extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listTitle;
    private HashMap<String, List<String>> listDetail;

    //constructor
    public backendCustomExpandabaleListView(Context context,
                                            List<String> listTitle,
                                            HashMap<String, List<String>> listDetail){
        this.context = context;
        this.listTitle = listTitle;
        this.listDetail = listDetail;
    }


    @Override
    public int getGroupCount() {
        return this.listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDetail.get(this.listTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDetail.get(this.listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = getGroup(groupPosition).toString();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.backend_list_group,null);
        }
        TextView listheader = (TextView) convertView.findViewById(R.id.list_group);
        listheader.setTypeface(null, Typeface.BOLD);
        listheader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childtext = (String) getChild(groupPosition,childPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.backend_list_item,null);
        }

        TextView listchild = (TextView) convertView.findViewById(R.id.list_item);

        listchild.setText(childtext);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

