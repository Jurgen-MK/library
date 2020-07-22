package kz.lib_mob_client.entity;

import android.Manifest;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.R;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;
import kz.lib_mob_client.utils.FileUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegulatoryDocumentationFiles  extends AbstractFlexibleItem<RegulatoryDocumentationFiles.ViewHolder> {

	private int idfile;
	private int id;
	private String path;
	private String name;




	public RegulatoryDocumentationFiles(int idfile, String path, String name) {
		this.idfile = idfile;
		this.id = id;
		this.path = path;
		this.name = name;
	}

	public int getIdfile() {
		return idfile;
	}

	public void setIdfile(int idfile) {
		this.idfile = idfile;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof RegulatoryDocumentationFiles) {
			RegulatoryDocumentationFiles inItem = (RegulatoryDocumentationFiles) inObject;
			return String.valueOf(this.idfile).equals(inItem.idfile);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return String.valueOf(id).hashCode();
	}

	@Override
	public int getLayoutRes() {
		return R.layout.sub_item_study_guides;
	}

	@Override
	public ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder,
							   final int position,
							   List<Object> payloads) {
		holder.filenameTv.setText(name);
		holder.downloadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				NetworkServiceResource.getInstance().getJSONApi().getFile("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(), path, name).enqueue(new Callback<ResponseBody>() {
					@Override
					public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
						if (FileUtils.writeResponseBodyToDisk(v.getContext(), response.body(), name)) {
							Toast.makeText(v.getContext(), name+" сохранен", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(v.getContext(), "Ошибка сохранения", Toast.LENGTH_SHORT).show();
						}
					}
					@Override
					public void onFailure(Call<ResponseBody> call, Throwable t) {

					}
				});
			}
		});
	}

	/**
	 * The ViewHolder used by this item.
	 * Extending from FlexibleViewHolder is recommended especially when you will use
	 * more advanced features.
	 */
	public class ViewHolder extends FlexibleViewHolder {

		public TextView filenameTv;
		public ImageButton downloadButton;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			filenameTv =  view.findViewById(R.id.filenameTV);
			downloadButton = view.findViewById(R.id.downloadButton);
		}
	}

}
