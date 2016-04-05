package com.zeus.bookcase.app.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.user.model.Address;

import java.util.List;

/**
 * Created by zeus_coder on 2016/3/31.
 */
public class AddressAdapter extends BaseSwipListAdapter {

    private Context context;
    private List<Address> addresses;

    public AddressAdapter(Context context,List<Address> addresses) {
        this.context = context;
        this.addresses = addresses;
    }

    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int i) {
        return addresses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Address address = addresses.get(i);
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.user__item_address, null);
            viewHolder = new ViewHolder();
            viewHolder.address = (TextView) view.findViewById(R.id.user_address_detail);
            viewHolder.name = (TextView) view.findViewById(R.id.user_address_name);
            viewHolder.phone = (TextView) view.findViewById(R.id.user_address_phone);
            view.setTag(viewHolder);

        }
        viewHolder.address.setText(address.getAddress());
        viewHolder.name.setText(address.getName());
        viewHolder.phone.setText(address.getPhone());
        return view;
    }

    class ViewHolder {
        private TextView address;
        private TextView phone;
        private TextView name;
    }
}
