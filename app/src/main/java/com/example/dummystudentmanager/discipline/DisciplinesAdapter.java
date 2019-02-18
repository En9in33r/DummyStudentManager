package com.example.dummystudentmanager.discipline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dummystudentmanager.R;

import java.util.ArrayList;

public class DisciplinesAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<DisciplineItem> disciplineItems;
    View view;

    public DisciplinesAdapter(Context context, ArrayList<DisciplineItem> disciplineItems) {
        this.context = context;
        this.disciplineItems = disciplineItems;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return disciplineItems.size();
    }

    @Override
    public Object getItem(int position) {
        return disciplineItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    DisciplineItem getDisciplineItem(int position)
    {
        return ((DisciplineItem)getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        if (view == null)
        {
            view = inflater.inflate(R.layout.discipline_item, parent, false);
        }

        final DisciplineItem d = getDisciplineItem(position);

        ((TextView)view.findViewById(R.id.nameTextViewItem)).setText(d.name);
        ((TextView)view.findViewById(R.id.teacherTextViewItem)).setText(d.teacher);
        ((TextView)view.findViewById(R.id.descriptionTextViewItem)).setText(d.description);

        if (d.debt.equals("1"))
        {
            ((TextView)view.findViewById(R.id.debtTextViewItem)).setText("долг");
        }
        else if (d.debt.equals("0"))
        {
            ((TextView)view.findViewById(R.id.debtTextViewItem)).setText("текущий");
        }
        //((TextView)view.findViewById(R.id.debtTextViewItem)).setText(d.debt);

        return view;
    }
}
