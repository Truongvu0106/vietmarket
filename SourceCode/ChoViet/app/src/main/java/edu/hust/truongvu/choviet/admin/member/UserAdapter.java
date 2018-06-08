package edu.hust.truongvu.choviet.admin.member;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 5/29/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{
    public interface UserListener{
        void onClick(User user);
    }
    private ArrayList<User> data;
    private Context mContext;
    private UserListener mListener;

    public UserAdapter(Context context, ArrayList<User> data, UserListener listener){
        this.mContext = context;
        this.data = data;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        UserHolder holder = new UserHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = data.get(position);
        holder.setContent(user);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{
        private ImageView imgAvatar;
        private TextView tvUserName, tvFullName, tvPhone, tvAddress;
        public UserHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.avatar_user);
            tvUserName = itemView.findViewById(R.id.tv_username);
            tvFullName = itemView.findViewById(R.id.tv_fullname);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }

        public void setContent(final User user){
            MyHelper.loadImageUser(mContext, imgAvatar, Constants.MY_PATH + user.getAvatar());
            tvUserName.setText(user.getUsername());
            tvFullName.setText(user.getFullname());
            tvPhone.setText(mContext.getString(R.string.phone) + ": " + user.getPhone());
            tvAddress.setText(mContext.getString(R.string.address) + ": "
                    + MyHelper.getListSubString(user.getAddress()).get(0));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(user);
                }
            });
        }
    }
}
