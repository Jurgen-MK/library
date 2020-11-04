package kz.lib_mob_client.entity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.R;
import kz.lib_mob_client.manager.FlexibleCarouselLayouManager;

public class ExhibitionRespond extends AbstractFlexibleItem<ExhibitionRespond.ViewHolder> {
	
	private int id;
	private String name;
	private String linkdate;
	private String place;
	private String description;
	private HashMap<String,String> fileslist;	

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
	public String getLinkdate() {
		return linkdate;
	}
	public void setLinkdate(String linkdate) {
		this.linkdate = linkdate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String,String> getFileslist() {
		return fileslist;
	}

	public void setFileslist(HashMap<String,String> fileslist) {
		this.fileslist = fileslist;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof ExhibitionRespond) {
			ExhibitionRespond inItem = (ExhibitionRespond) inObject;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_exhibition;
	}

	@Override
	public ExhibitionRespond.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new ExhibitionRespond.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ExhibitionRespond.ViewHolder holder, int position, List<Object> payloads) {
		holder.descriptionTv.setText(description);
		holder.placeTv.setText(place);
		holder.nameTv.setText(name);
		holder.dateTv.setText(linkdate);
		holder.foldingCell.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				holder.foldingCell.toggle(false);
			}
		});
		List<ExhibitionImage> exImgList = new ArrayList<>();
		fileslist.forEach((key,value) -> {ExhibitionImage exImg = new ExhibitionImage(); exImg.setId(key); exImg.setLink(value); exImgList.add(exImg);} );
		List<IFlexible> list = new ArrayList<>();
		list.addAll(exImgList);
		FlexibleAdapter<IFlexible> adapter1 = new FlexibleAdapter<>(list);
		holder.imagesRv.setAdapter(adapter1);
	}



	public class ViewHolder extends FlexibleViewHolder {
		public TextView nameTv;
		public TextView dateTv;
		public TextView placeTv;
		public TextView descriptionTv;
		public FoldingCell foldingCell;
		public RecyclerView imagesRv;
		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			nameTv = view.findViewById(R.id.nameTv);
			dateTv = view.findViewById(R.id.dateTv);
			placeTv = view.findViewById(R.id.placeTv);
			descriptionTv = view.findViewById(R.id.descriptionTv);
			foldingCell = view.findViewById(R.id.folding_cell);
			//RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
			imagesRv = view.findViewById(R.id.imagesRv);
			FlexibleCarouselLayouManager manager = new FlexibleCarouselLayouManager(CarouselLayoutManager.HORIZONTAL);
			manager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
			imagesRv.setLayoutManager(manager);
			imagesRv.addOnScrollListener(new CenterScrollListener());
			SnapHelper snapHelper = new PagerSnapHelper();
			snapHelper.attachToRecyclerView(imagesRv);
		}
	}
	

}
