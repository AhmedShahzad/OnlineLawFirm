package myapp.admin.example.com.onlinelawfirm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LawAdapter extends ArrayAdapter {
    ArrayList<LawData> thedata;
    Activity thecontext;

    public LawAdapter(@NonNull Activity thecontext, ArrayList<LawData> thedata ) {
        super(thecontext, R.layout.customlaw , thedata);
        this.thedata = thedata;
        this.thecontext = thecontext;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = thecontext.getLayoutInflater();

        View rowview = inflater.inflate(R.layout.customlaw, null , false);
        TextView title = rowview.findViewById(R.id.title);
        TextView description = rowview.findViewById(R.id.description);
        TextView pdf = rowview.findViewById(R.id.pdf);
        title.setText(thedata.get(position).getTitle().toString());
        description.setText(thedata.get(position).getDescription().toString());
        pdf.setText(thedata.get(position).getPdf().toString());

        return rowview;
    }
}
