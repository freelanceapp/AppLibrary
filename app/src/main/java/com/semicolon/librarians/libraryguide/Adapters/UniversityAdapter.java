package com.semicolon.librarians.libraryguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.semicolon.librarians.libraryguide.Activities.Activity_Profile;
import com.semicolon.librarians.libraryguide.Activities.Chat_Activity;
import com.semicolon.librarians.libraryguide.Activities.HomeActivity;
import com.semicolon.librarians.libraryguide.Models.UniversityModel;
import com.semicolon.librarians.libraryguide.R;
import com.semicolon.librarians.libraryguide.Services.Tags;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import java.util.List;

/**
 * Created by Delta on 22/01/2018.
 */

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ViewHolder>  {

    List<UniversityModel> universityModelList;
    Context context;
    HomeActivity homeActivity;
    Target target;

    public UniversityAdapter(List<UniversityModel> universityModelList, Context context) {
        this.universityModelList = universityModelList;
        this.context = context;
        homeActivity = (HomeActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.uni_recyclerview_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        UniversityModel universityModel = universityModelList.get(position);
        holder.BindData(universityModel);
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.rec_anim);
        holder.itemView.startAnimation(animation);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final UniversityModel universityModel2 = universityModelList.get(holder.getAdapterPosition());


                View custom_view = LayoutInflater.from(context).inflate(R.layout.custom_alert_msg_profile,null);
                TextView open_profile = (TextView) custom_view.findViewById(R.id.open_profile_tv);
                TextView send_msg = (TextView) custom_view.findViewById(R.id.send_msg_tv);
                Button cancelBtn = (Button) custom_view.findViewById(R.id.cancelBtn);

                final LovelyCustomDialog dialog = new LovelyCustomDialog(context);
                dialog.setCancelable(true);
                dialog.setTopTitle(context.getString(R.string.sel_opt));
                dialog.setTopColor(ActivityCompat.getColor(context,R.color.centercolor));
                dialog.setTopTitleColor(ActivityCompat.getColor(context,R.color.base));

                open_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context,Activity_Profile.class);
                        intent.putExtra("who_visit_myProfile","visitor");
                        intent.putExtra("universityData",universityModel2);
                        context.startActivity(intent);

                        dialog.dismiss();

                    }
                });
                send_msg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (homeActivity.user_Data!=null)
                        {
                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.user_Data.getUserId(),universityModel2.getUni_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","user");
                            intent.putExtra("chat_userType","university");
                            intent.putExtra("curr_user",homeActivity.user_Data);
                            intent.putExtra("chat_user",universityModel2);
                            context.startActivity(intent);
                            dialog.dismiss();
                        }else if (homeActivity.publisher_Model!=null)
                        {
                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.publisher_Model.getPub_username(),universityModel2.getUni_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","publisher");
                            intent.putExtra("chat_userType","university");
                            intent.putExtra("curr_user",homeActivity.publisher_Model);
                            intent.putExtra("chat_user",universityModel2);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                        else if (homeActivity.library_Model!=null)
                        {
                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.library_Model.getLib_username(),universityModel2.getUni_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","library");
                            intent.putExtra("chat_userType","university");
                            intent.putExtra("curr_user",homeActivity.library_Model);
                            intent.putExtra("chat_user",universityModel2);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                        else if (homeActivity.university_Model!=null)
                        {
                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.university_Model.getUni_username(),universityModel2.getUni_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","university");
                            intent.putExtra("chat_userType","university");
                            intent.putExtra("curr_user",homeActivity.university_Model);
                            intent.putExtra("chat_user",universityModel2);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                        else if (homeActivity.company_Model!=null)
                        {
                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.company_Model.getComp_username(),universityModel2.getUni_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","company");
                            intent.putExtra("chat_userType","university");
                            intent.putExtra("curr_user",homeActivity.company_Model);
                            intent.putExtra("chat_user",universityModel2);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setView(custom_view);
                dialog.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return universityModelList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView uni_image;
        TextView uni_name,more;
        public ViewHolder(View itemView) {
            super(itemView);

            uni_image       = (ImageView) itemView.findViewById(R.id.university_image_item);
            uni_name        = (TextView) itemView.findViewById(R.id.university_name_item);
            more            = (TextView) itemView.findViewById(R.id.more);


        }



        public void BindData(UniversityModel universityModel)
        {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),Tags.font);

            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    uni_image.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            if (!universityModel.getUser_photo().equals("0"))
            {
                Picasso.with(context).load(Tags.image_path+universityModel.getUser_photo()).placeholder(R.drawable.user_profile).into(target);

            }else
            {
                Picasso.with(context).load(R.drawable.user_profile).into(target);

            }


            uni_name.setTypeface(typeface);
            more.setTypeface(typeface);
            uni_name.setText(universityModel.getUni_name());

        }
    }


    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}

