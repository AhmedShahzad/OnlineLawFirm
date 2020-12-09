package myapp.admin.example.com.onlinelawfirm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.hdodenhof.circleimageview.CircleImageView;

public class LawyerAdapter extends ArrayAdapter {
    Activity thecontext;
    ArrayList<LawyerData> thedata;
    String abc;
    public LawyerAdapter(@NonNull Activity thecontext, ArrayList<LawyerData> thedata ) {

        super(thecontext, R.layout.customlawyerlist , thedata);
        this.thecontext = thecontext ;
        this.thedata = thedata;


    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = thecontext.getLayoutInflater();

         View rowview;

            rowview = inflater.inflate(R.layout.customlawyerlist, null , false);

            TextView name = rowview.findViewById(R.id.name);
            TextView email  = rowview.findViewById(R.id.email);
            TextView contact   = rowview.findViewById(R.id.contact);
            TextView lawyer_type   = rowview.findViewById(R.id.lawyer_type);
            TextView designation  = rowview.findViewById(R.id.designation);
            TextView description  = rowview.findViewById(R.id.description);
            TextView paybtn = rowview.findViewById(R.id.paynow);
            TextView chat = rowview.findViewById(R.id.chat);
        TextView user_case = rowview.findViewById(R.id.user_case);
            name.setText(thedata.get(position).getName().toString());
            email.setText(thedata.get(position).getEmail().toString());
            contact.setText(thedata.get(position).getContact().toString());
            lawyer_type.setText(thedata.get(position).getLawyer_type().toString());
            designation.setText(thedata.get(position).getDesignation().toString());
            description.setText(thedata.get(position).getDescription().toString());
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thecontext , ActivityPayment.class);
                intent.putExtra("key", thedata.get(position).getId());
                thecontext.startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thecontext , ActivityPayment.class);
                intent.putExtra("key", thedata.get(position).getId());
                thecontext.startActivity(intent);
            }
        });
        user_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thecontext , ActivityPayment.class);
                intent.putExtra("key", thedata.get(position).getId());
                thecontext.startActivity(intent);
            }
        });





        return  rowview;
    }
}
