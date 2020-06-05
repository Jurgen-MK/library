package kz.lib_mob_client.entity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.io.IOException;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.MainActivity;
import kz.lib_mob_client.R;
import kz.lib_mob_client.fragments.WebViewDialogFragment;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;
import kz.lib_mob_client.utils.FileUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News extends AbstractFlexibleItem<News.ViewHolder> {
	
	int id;
	String header;
	String dateUpdated;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof SearchRespond) {
			News inItem = (News) inObject;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_news;
	}

	@Override
	public News.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new News.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
		holder.dateTV.setText(dateUpdated);
		holder.headerTV.setText(header);
		holder.headerTV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				NetworkServiceResource.getInstance().getJSONApi().getNewsText("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(), id).enqueue(new Callback<ResponseBody>() {
					@Override
					public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
						FragmentManager manager = ((MainActivity) v.getContext()).getSupportFragmentManager();
						DialogFragment newFragment = null;
						try {
							newFragment = WebViewDialogFragment.newInstance(response.body().string().toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
						newFragment.show(manager, "dialog");
					}
					@Override
					public void onFailure(Call<ResponseBody> call, Throwable t) {
						t.printStackTrace();
						Toast.makeText(v.getContext(), "Error", Toast.LENGTH_LONG).show();
					}
				});
			}
		});
	}

	public class ViewHolder extends FlexibleViewHolder {
		public TextView dateTV;
		public TextView headerTV;


		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			dateTV = view.findViewById(R.id.dateTV);
			headerTV = view.findViewById(R.id.headerTV);

		}
	}

}
