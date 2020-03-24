package kz.lib_mob_client.entity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.R;


public class RegulatoryDocumentation extends AbstractFlexibleItem<RegulatoryDocumentation.ViewHolder> {

	private int id;
	private int category;
	private String user;
	private String text;
	private String date;
	private List<RegulatoryDocumentationFiles> fileslist;

	public RegulatoryDocumentation(int id, int category, String user, String text, String date) {
		this.id = id;
		this.category = category;
		this.user = user;
		this.text = text;
		this.date = date;		
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<RegulatoryDocumentationFiles> getFileslist() {
		return fileslist;
	}

	public void setFileslist(List<RegulatoryDocumentationFiles> fileslist) {
		this.fileslist = fileslist;
	}


	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof RegulatoryDocumentation) {
			RegulatoryDocumentation inItem = (RegulatoryDocumentation) inObject;
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
		return R.layout.item_study_guides;
	}

	@Override
	public ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder,
							   int position,
							   List<Object> payloads) {
		holder.idTv.setText(Integer.toString(id));
		holder.dateTv.setText(date);
		holder.textTv.setText(text);
		List<IFlexible> list = new ArrayList<>();
		list.addAll(fileslist);
		FlexibleAdapter<IFlexible> adapter1 = new FlexibleAdapter<>(list);
		holder.rv_files.setAdapter(adapter1);
	}

	/**
	 * The ViewHolder used by this item.
	 * Extending from FlexibleViewHolder is recommended especially when you will use
	 * more advanced features.
	 */
	public class ViewHolder extends FlexibleViewHolder {

		public TextView idTv;
		public TextView dateTv;
		public TextView textTv;
		public RecyclerView rv_files;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			rv_files = view.findViewById(R.id.rv_files);
			RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
			rv_files.setLayoutManager(manager);
			idTv =  view.findViewById(R.id.idTV);
			dateTv =  view.findViewById(R.id.dateTV);
			textTv = view.findViewById(R.id.textTV);
		}
	}

}
