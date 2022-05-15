package com.example.projectandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Body_Part_RV_Adapter extends RecyclerView.Adapter<Body_Part_RV_Adapter.MyViewHolder> {

    private Context context;
    private List<Integer> images;
    private List<String> bodyparts;
    private int idPerson;

    public Body_Part_RV_Adapter(Context context, List<Integer> images, List<String> bodyparts, int idPerson) {
        this.context = context;
        this.images = images;
        this.bodyparts = bodyparts;
        this.idPerson= idPerson;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.body_part, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.body_part_img.setImageResource(images.get(position));
        holder.bodypart.setText(bodyparts.get(position));
        holder.body_part_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CoachesActivity.class);
                i.putExtra("bodyPart", bodyparts.get(position));
                i.putExtra("idPerson", idPerson);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView body_part_img;
        TextView bodypart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            body_part_img = itemView.findViewById(R.id.bodyPartImgID);
            bodypart = itemView.findViewById(R.id.bodypart);

        }
    }
}
