package com.mojaafar.volleylab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class DevelopersAdapter<holder> extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder> {
    //declare DeveloperList private member variable
    private List<DeveloperList> developerList;
    //context variable
    private Context mContext;
    //keys for our intents
    public static  final String KEY_NAME = "name";
    public static  final String KEY_IMAGE = "image";
    public static  final String KEY_URL = "url";


    @NonNull
    @Override
    public DevelopersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.developers_list,parent,false));

    }

    @Override
    //this method connects data to the view holder
    public void onBindViewHolder(@NonNull DevelopersAdapter.ViewHolder holder, final int position) {
//create a variable that gets the current intance of the developer in the list
        final DeveloperList currentDeveloper=developerList.get(position);
        //populate the text view and image view with data
        holder.login.setText(currentDeveloper.getLogin());
        holder.html_url.setText(currentDeveloper.getHtml_url());
        //use the library picasso to load images to prevent crashing...loading images is resource intensive
        Picasso.with(mContext).load(currentDeveloper.getAvatar_url()).into(holder.avatar_url);


        //set onclick listener to handle click events
        holder.linearLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            //ensure you override the onclick method
            public void onClick(View v) {
                //create an instance of the devloper list and initilialize it
                DeveloperList developerList1 = developerList.get(position);
                //create an intent and specify the target class profile activitty
                Intent skipIntent =  new Intent(v.getContext(), ProfileActivity.class);
                //use intent EXTRA to pass data from Main Activity to Profile Activity
                skipIntent.putExtra(KEY_NAME, developerList1.getLogin());
                skipIntent.putExtra(KEY_URL, developerList1.getHtml_url());
                skipIntent.putExtra(KEY_IMAGE, developerList1.getAvatar_url());
                v.getContext().startActivity(skipIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //return the size of the developer list
        return developerList.size();
    }

    //1.viewholder inner class
    public class ViewHolder extends RecyclerView.ViewHolder{
        //define the view objects
        public TextView login;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializes view objects
            login = itemView.findViewById(R.id.username);
            avatar_url=itemView.findViewById(R.id.imageView);
            html_url=itemView.findViewById(R.id.html_url);
            linearLayout=itemView.findViewById(R.id.linearLayout);

        }
    }

    //3. Implement a constructor with a context parameter and a list of developers that'll be fetched remotely
    public DevelopersAdapter(List<DeveloperList>developerList, Context context){
        this.developerList=developerList;
        this.mContext=context;
    }

}