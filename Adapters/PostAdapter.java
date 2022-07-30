package com.example.bitsnews.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bitsnews.Model.Post;
import com.example.bitsnews.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends  RecyclerView.Adapter<PostAdapter.ViewHolder>{
    public Context mContext;
    public List<Post> mPostList;

    public PostAdapter(Context mContext,List<Post>mPostList){
        this.mContext=mContext;
        this.mPostList=mPostList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.post_retrieved_layout,parent,false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final  Post post = mPostList.get(position);
        holder.expandable_text.setText(post.getDescription());
        holder.headline.setText(post.getHeadline());
        holder.date.setText(post.getDate());
        holder.username.setText(post.getUsername());
        Glide.with(mContext).load(post.getPostImage()).into(holder.postImage);

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView profile;
        public TextView username,likes,dislikes,comments,headline,date;
        public ImageView more,postImage,like,dislike,comment,save;
        public ExpandableTextView expandable_text;




        public  ViewHolder(@NonNull View itemView){
            super(itemView);

            profile=itemView.findViewById(R.id.profile);
           username=itemView.findViewById(R.id.username);
           likes=itemView.findViewById(R.id.likes);
           dislikes=itemView.findViewById(R.id.dislikes);
           comments=itemView.findViewById(R.id.comments);
           headline=itemView.findViewById(R.id.headline);
           date=itemView.findViewById(R.id.date);
           more=itemView.findViewById(R.id.more);
           postImage=itemView.findViewById(R.id.post_image);
           like=itemView.findViewById(R.id.like);
           dislike=itemView.findViewById(R.id.dislike);
           comment=itemView.findViewById(R.id.comment);
           save=itemView.findViewById(R.id.save);
           expandable_text=itemView.findViewById(R.id.expandable_text);


        }
    }
}
