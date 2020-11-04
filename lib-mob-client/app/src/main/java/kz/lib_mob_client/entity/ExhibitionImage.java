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

public class ExhibitionImage extends AbstractFlexibleItem<ExhibitionImage.ViewHolder> {
    
    private String id;
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof ExhibitionImage) {
            ExhibitionImage inItem = (ExhibitionImage) inObject;
            return String.valueOf(this.id).equals(inItem.id);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.sub_item_exhibition;
    }

    @Override
    public ExhibitionImage.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ExhibitionImage.ViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ExhibitionImage.ViewHolder holder, int position, List<Object> payloads) {
        Context ctx = holder.eximg.getContext();
        TokenManager tokenManager = TokenManager.getInstance(ctx.getSharedPreferences("prefs", ctx.MODE_PRIVATE));
        GlideUrl glideUrl = new GlideUrl(ServiceAuth.RES_API_BASE_URL+"/files/getfilebyfullpath?fullpath="+link,
                new LazyHeaders.Builder()
                        .addHeader("Authorization", "Bearer " + tokenManager.getToken().getAccess_token())
                        .build());
        Glide.with(ctx)
                .load(glideUrl).placeholder(R.drawable.imgplaceholder).error(R.drawable.arrow)
                .into(holder.eximg);
    }



    public class ViewHolder extends FlexibleViewHolder {
        public ImageView eximg;
        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            eximg = view.findViewById(R.id.eximage);
        }
    }

}
