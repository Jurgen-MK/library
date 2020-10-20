package kz.lib_mob_client.entity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.R;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.network.ServiceAuth;

public class PublishingProducts extends AbstractFlexibleItem<PublishingProducts.ViewHolder> {

	private int id;
	private String name;
	private String link;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof PublishingProducts) {
			PublishingProducts inItem = (PublishingProducts) inObject;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_publishing_products;
	}

	@Override
	public PublishingProducts.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new PublishingProducts.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, PublishingProducts.ViewHolder holder, int position, List<Object> payloads) {
		holder.nameTv.setText(name);
		Context ctx = holder.prodimage.getContext();
		TokenManager tokenManager = TokenManager.getInstance(ctx.getSharedPreferences("prefs", ctx.MODE_PRIVATE));
		GlideUrl glideUrl = new GlideUrl(ServiceAuth.RES_API_BASE_URL+"/files/getfilebyfullpath?fullpath="+link,
				new LazyHeaders.Builder()
						.addHeader("Authorization", "Bearer " + tokenManager.getToken().getAccess_token())
						.build());
		Glide.with(ctx)
				.load(glideUrl).placeholder(R.drawable.imgplaceholder).error(R.drawable.noimage)
				.into(holder.prodimage);
	}


	public class ViewHolder extends FlexibleViewHolder {
		public TextView nameTv;
		public ImageView prodimage;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			nameTv = view.findViewById(R.id.nameTv);
			prodimage = view.findViewById(R.id.prodimage);
		}
	}

}
