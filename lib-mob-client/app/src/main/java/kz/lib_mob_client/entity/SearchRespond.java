package kz.lib_mob_client.entity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ServiceConfigurationError;

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

public class SearchRespond extends AbstractFlexibleItem<SearchRespond.ViewHolder> {

	String docName;
	String fileName;
	String filePath;

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof SearchRespond) {
			SearchRespond inItem = (SearchRespond) inObject;
			return String.valueOf(this.docName).equals(inItem.docName);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_search_respond;
	}

	@Override
	public SearchRespond.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new SearchRespond.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, SearchRespond.ViewHolder holder, int position, List<Object> payloads) {
		holder.descriptionTV.setText(docName);
		holder.filenameTV.setText(fileName);
		holder.downloadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				NetworkServiceResource.getInstance().getJSONApi().getFile("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(), filePath, fileName).enqueue(new Callback<ResponseBody>() {
					@Override
					public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

						if (FileUtils.writeResponseBodyToDisk(response.body(), fileName)) {
							Toast.makeText(v.getContext(), fileName+" сохранен", Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(v.getContext(), "Файл не найден", Toast.LENGTH_LONG).show();
						}
					}
					@Override
					public void onFailure(Call<ResponseBody> call, Throwable t) {
						Toast.makeText(v.getContext(), "Файл не найден", Toast.LENGTH_LONG).show();
					}
				});
			}
		});
	}

	public class ViewHolder extends FlexibleViewHolder {
		public TextView descriptionTV;
		public TextView filenameTV;
		public ImageButton downloadButton;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			descriptionTV = view.findViewById(R.id.descriptionTV);
			filenameTV = view.findViewById(R.id.filenameTV);
			downloadButton = view.findViewById(R.id.downloadButton);
		}
	}


}
