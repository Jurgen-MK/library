package kz.lib_mob_client.entity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
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
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;
import kz.lib_mob_client.network.ServiceAuth;
import kz.lib_mob_client.utils.FileUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllBook extends AbstractFlexibleItem<AllBook.ViewHolder> {
	private Integer id;
	private String author;	
	private String doc_name;	
	private String public_date;
	private String registration_date;
	private String doc_type;
	private String n_pages;
	private String udc;
	private String gasnti;
	private String short_desc;
	private String applic_area;
	private String public_house;
	private String filePath;
	private String user_add;
	private Integer status;
	private String filetype;
	private String fileName;

	
	public AllBook(String author, String doc_name, String public_date, String registration_date, String doc_types, String n_pages, String udc, String gasnti,
			String short_desc, String applic_area, String public_house, String filePath, String fileName, String user_add, Integer status, String filetype ) {
			
		this.author=author;	
		this.doc_name=doc_name;	
		this.public_date=public_date;
		this.registration_date=registration_date;
		this.doc_type=doc_types;
		this.n_pages=n_pages;
		this.udc=udc;
		this.gasnti=gasnti;
		this.short_desc=short_desc;
		this.applic_area=applic_area;
		this.public_house=public_house;
		this.filePath=filePath;
		this.fileName = fileName;
		this.user_add=user_add;
		this.status=status;	
		this.filetype=filetype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getPublic_date() {
		return public_date;
	}

	public void setPublic_date(String public_date) {
		this.public_date = public_date;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getN_pages() {
		return n_pages;
	}

	public void setN_pages(String n_pages) {
		this.n_pages = n_pages;
	}

	public String getUdc() {
		return udc;
	}

	public void setUdc(String udc) {
		this.udc = udc;
	}

	public String getGasnti() {
		return gasnti;
	}

	public void setGasnti(String gasnti) {
		this.gasnti = gasnti;
	}

	public String getShort_desc() {
		return short_desc;
	}

	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}

	public String getApplic_area() {
		return applic_area;
	}

	public void setApplic_area(String applic_area) {
		this.applic_area = applic_area;
	}

	public String getPublic_house() {
		return public_house;
	}

	public void setPublic_house(String public_house) {
		this.public_house = public_house;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUser_add() {
		return user_add;
	}

	public void setUser_add(String user_add) {
		this.user_add = user_add;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof AllBook) {
			AllBook inItem = (AllBook) inObject;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return String.valueOf(id).hashCode();
	}


	@Override
	public int getLayoutRes() {
		return R.layout.item_all_book;
	}

	@Override
	public AllBook.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new AllBook.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
		holder.idTv.setText(Integer.toString(id));
		holder.nameTv.setText(doc_name);
		holder.authorTv.setText(author);
		holder.dateTv.setText(public_date);
		holder.descriptionTV.setText(short_desc);
		holder.filenameTV.setText(fileName);
		holder.downloadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				Context ctx = v.getContext();
				TokenManager tokenManager = TokenManager.getInstance(ctx.getSharedPreferences("prefs", ctx.MODE_PRIVATE));
				ServiceAuth.createService(ServiceApi.class, tokenManager).
						getFile(filePath, fileName).
						enqueue(new Callback<ResponseBody>() {
							@Override
							public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
								if (FileUtils.writeResponseBodyToDisk(v.getContext(), response.body(), fileName)) {
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
//				NetworkServiceResource.getInstance().getJSONApi().getFile("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(), filePath, fileName).enqueue(new Callback<ResponseBody>() {
//					@Override
//					public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//						if (FileUtils.writeResponseBodyToDisk(v.getContext(), response.body(), fileName)) {
//							Toast.makeText(v.getContext(), fileName+" сохранен", Toast.LENGTH_LONG).show();
//						} else {
//							Toast.makeText(v.getContext(), "Файл не найден", Toast.LENGTH_LONG).show();
//						}
//					}
//					@Override
//					public void onFailure(Call<ResponseBody> call, Throwable t) {
//						Toast.makeText(v.getContext(), "Файл не найден", Toast.LENGTH_LONG).show();
//					}
//				});
			}
		});
	}


	/**
	 * The ViewHolder used by this item.
	 * Extending from FlexibleViewHolder is recommended especially when you will use
	 * more advanced features.
	 */
	public class ViewHolder extends FlexibleViewHolder {

		public TextView idTv;
		public TextView nameTv;
		public TextView authorTv;
		public TextView dateTv;
		public TextView descriptionTV;
		public TextView filenameTV;
		public ImageButton downloadButton;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			idTv =  view.findViewById(R.id.idTV);
			nameTv = view.findViewById(R.id.nameTV);
			authorTv = view.findViewById(R.id.authorTV);
			dateTv =  view.findViewById(R.id.datepubTV);
			descriptionTV = view.findViewById(R.id.descriptionTV);
			filenameTV = view.findViewById(R.id.filenameTV);
			downloadButton = view.findViewById(R.id.downloadButton);
		}
	}

}
