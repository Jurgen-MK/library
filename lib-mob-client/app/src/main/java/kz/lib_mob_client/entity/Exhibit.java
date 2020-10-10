package kz.lib_mob_client.entity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.R;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.network.ServiceAuth;

public class Exhibit extends AbstractFlexibleItem<Exhibit.ViewHolder> {
	
	private int id;
	private String link;
	private String name;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof Exhibit) {
			Exhibit inItem = (Exhibit) inObject;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_exhibit;
	}

	@Override
	public Exhibit.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new Exhibit.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, Exhibit.ViewHolder holder, int position, List<Object> payloads) {
		Context ctx = holder.eximg.getContext();
		TokenManager tokenManager = TokenManager.getInstance(ctx.getSharedPreferences("prefs", ctx.MODE_PRIVATE));
		GlideUrl glideUrl = new GlideUrl(ServiceAuth.RES_API_BASE_URL+"/files/getfilebyfullpath?fullpath="+link,
				new LazyHeaders.Builder()
						.addHeader("Authorization", "Bearer " + tokenManager.getToken().getAccess_token())
						.build());
		Glide.with(ctx)
				.load(glideUrl).placeholder(R.drawable.arrow).error(R.drawable.arrow)
				.into(holder.eximg);
		holder.descriptionTv.setText(description);
		holder.nameTv.setText(name);
	}



	public class ViewHolder extends FlexibleViewHolder {
		public TextView nameTv;
		public TextView descriptionTv;
		public ImageView eximg;
		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			nameTv = view.findViewById(R.id.nameTv);
			descriptionTv = view.findViewById(R.id.descriptionTv);
			eximg = view.findViewById(R.id.eximage);
		}
	}

}
