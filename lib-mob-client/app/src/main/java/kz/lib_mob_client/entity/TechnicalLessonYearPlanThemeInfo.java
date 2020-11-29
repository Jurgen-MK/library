package kz.lib_mob_client.entity;


import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.R;
import kz.lib_mob_client.manager.FlexibleCarouselLayouManager;
import kz.lib_mob_client.utils.DateUtils;

public class TechnicalLessonYearPlanThemeInfo extends AbstractFlexibleItem<TechnicalLessonYearPlanThemeInfo.ViewHolder> {
	
	private int id;
	private String month;
	private String themeName;
	private String place;
	private String position;
	private String note;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof TechnicalLessonYearPlanThemeInfo) {
			TechnicalLessonYearPlanThemeInfo inItem = (TechnicalLessonYearPlanThemeInfo) inObject;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.sub_item_plan_info;
	}

	@Override
	public TechnicalLessonYearPlanThemeInfo.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new TechnicalLessonYearPlanThemeInfo.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, TechnicalLessonYearPlanThemeInfo.ViewHolder holder, int position, List<Object> payloads) {
		holder.monthTv.setText(DateUtils.getMonth(Integer.parseInt(month)));
		holder.placeTv.setText(place);
		holder.themeTv.setText(themeName);
		holder.instructorTv.setText("Лектор: "+this.position);
		holder.noteTv.setText(note);
	}


	public class ViewHolder extends FlexibleViewHolder {
		public TextView monthTv;
		public TextView placeTv;
		public TextView themeTv;
		public TextView instructorTv;
		public TextView noteTv;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			monthTv = view.findViewById(R.id.Tv2);
			placeTv = view.findViewById(R.id.Tv3);
			themeTv = view.findViewById(R.id.Tv1);
			instructorTv = view.findViewById(R.id.Tv4);
			noteTv = view.findViewById(R.id.Tv5);

		}
	}

}
