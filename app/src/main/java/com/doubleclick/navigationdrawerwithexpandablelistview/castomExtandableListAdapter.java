package com.doubleclick.navigationdrawerwithexpandablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class castomExtandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listTitel;
    private Map<String,List<String>> listItem;

    public castomExtandableListAdapter(Context context, List<String> listTitel, Map<String, List<String>> listItem) {
        this.context = context;
        this.listTitel = listTitel;
        this.listItem = listItem;
    }


    @Override
    public int getGroupCount() {
        return listTitel.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItem.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTitel.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItem.get(listTitel.get(groupPosition)).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String titel = (String) getGroup(groupPosition);

        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_group, null);

        }
        TextView txtTitle =  convertView.findViewById(R.id.ExpandListTitle);
        txtTitle.setTypeface(null, Typeface.BOLD);
        txtTitle.setText(""+titel);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String titel = (String) getChild(groupPosition,childPosition);

        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);

        }
        TextView txtchild =  convertView.findViewById(R.id.ExpandListItem);
        txtchild.setTypeface(null, Typeface.BOLD);
        txtchild.setText(""+titel);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
