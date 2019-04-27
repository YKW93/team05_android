package com.yapp14th.yappapp.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yapp14th.yappapp.R;
import com.yapp14th.yappapp.model.GroupCardInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class GroupCardAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GroupCardInfo> modelList;
    private Context mContext;
    private int viewType;
    private final int VIEW_TYPE_NEAR = 0 , VIEW_TYPE_REAL_TIME = 1;
    private ItemOnCickListener mItemOnClickListener;

    public GroupCardAdpater(Context mContext, ArrayList<GroupCardInfo> modelList, int viewType){

        this.mContext = mContext;
        this.modelList = modelList;
        this.viewType = viewType;

    }

    public interface ItemOnCickListener{

        void onClickListener(GroupCardInfo model, View sharedView);

    }

    public void setOnItemClickListener(ItemOnCickListener mItemOnClickListener){

        this.mItemOnClickListener = mItemOnClickListener;

    }

    @Override
    public int getItemViewType(int position) {
        return this.viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_NEAR){

            return new GroupCardViewHolder(LayoutInflater.from(mContext).inflate(R.layout.model_near_group, parent, false));

        }else{

            return new GroupCardViewHolder(LayoutInflater.from(mContext).inflate(R.layout.model_realtime_group, parent, false));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

            GroupCardViewHolder holder = (GroupCardViewHolder) viewHolder;

            GroupCardInfo model = modelList.get(position);

            holder.imgCardBgr.setTransitionName(model.getTitle());

            holder.itemView.setOnClickListener(onClickListener(modelList.get(position), holder.imgCardBgr));

            //TODO
            //set View Data

    }

    private View.OnClickListener onClickListener(final GroupCardInfo model, final ImageView sharedImage){

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOnClickListener.onClickListener(model, sharedImage);
            }
        };

        return listener;

    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    private class GroupCardViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCardBgr, imgUser1, imgUser2, imgUser3, imgUser4;
        private TextView txtCardTitle, txtDate, txtTime, txtLocName, txtDist, txtUsersCnt;
        private ArrayList<ImageView> imgGroups;

        public GroupCardViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCardBgr = itemView.findViewById(R.id.img_card_bgr);

            imgGroups = new ArrayList<>();

            imgGroups.add(itemView.findViewById(R.id.img_user_group_1));
            imgGroups.add(itemView.findViewById(R.id.img_user_group_2));
            imgGroups.add(itemView.findViewById(R.id.img_user_group_3));
            imgGroups.add(itemView.findViewById(R.id.img_user_group_3));

//            imgUser1 = itemView.findViewById(R.id.img_user_group_1);
//            imgUser2 = itemView.findViewById(R.id.img_user_group_2);
//            imgUser3 = itemView.findViewById(R.id.img_user_group_3);
//            imgUser4 = itemView.findViewById(R.id.img_user_group_4);

            txtCardTitle = itemView.findViewById(R.id.txt_home_card_title);

            txtDate = itemView.findViewById(R.id.txt_home_card_date);

            txtTime = itemView.findViewById(R.id.txt_home_card_time);

            txtLocName = itemView.findViewById(R.id.txt_home_card_loc_name);

            txtUsersCnt = itemView.findViewById(R.id.txt_user_group_count);

        }

    }

}
