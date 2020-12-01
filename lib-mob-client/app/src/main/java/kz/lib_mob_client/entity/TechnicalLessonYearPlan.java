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

public class TechnicalLessonYearPlan extends AbstractFlexibleItem<TechnicalLessonYearPlan.ViewHolder> {
	
	private int id;
	private String branch;
	private String subbranch;
	private String department;
	private String year;
	private String link_file;
	private List<TechnicalLessonYearPlanThemeInfo> info = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSubbranch() {
		return subbranch;
	}
	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLink_file() {
		return link_file;
	}
	public void setLink_file(String link_file) {
		this.link_file = link_file;
	}
	public List<TechnicalLessonYearPlanThemeInfo> getInfo() {
		return info;
	}
	public void setInfo(List<TechnicalLessonYearPlanThemeInfo> info) {
		this.info = info;
	}

	@Override
	public boolean equals(Object inObject) {
		if (inObject instanceof TechnicalLessonYearPlan) {
			TechnicalLessonYearPlan inItem = (TechnicalLessonYearPlan) inObject;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_year_plans;
	}

	@Override
	public TechnicalLessonYearPlan.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new TechnicalLessonYearPlan.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, TechnicalLessonYearPlan.ViewHolder holder, int position, List<Object> payloads) {
		holder.yearTv.setText(year);
		holder.branchTv.setText(branch+" "+subbranch);
		holder.departmentTv.setText(department);
		holder.foldingCell.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (info.isEmpty()) {
					Toast.makeText(v.getContext(), "Пусто", Toast.LENGTH_SHORT).show();
				} else {
					holder.foldingCell.toggle(false);
				}
			}
		});
		List<IFlexible> list = new ArrayList<>();
		list.addAll(info);
		FlexibleAdapter<IFlexible> adapter1 = new FlexibleAdapter<>(list);
		holder.planinfoRv.setAdapter(adapter1);
	}


	public class ViewHolder extends FlexibleViewHolder {
		public TextView yearTv;
		public TextView branchTv;
		public TextView departmentTv;
		public FoldingCell foldingCell;
		public RecyclerView planinfoRv;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			yearTv = view.findViewById(R.id.yearTv);
			branchTv = view.findViewById(R.id.branchTv);
			departmentTv = view.findViewById(R.id.departmentTv);
			foldingCell = view.findViewById(R.id.folding_cell);
			planinfoRv = view.findViewById(R.id.planinfoRv);
			//RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
			FlexibleCarouselLayouManager manager = new FlexibleCarouselLayouManager(CarouselLayoutManager.HORIZONTAL);
			manager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
			planinfoRv.setLayoutManager(manager);
			planinfoRv.setHasFixedSize(true);
			planinfoRv.addOnScrollListener(new CenterScrollListener());
			SnapHelper snapHelper = new PagerSnapHelper();
			snapHelper.attachToRecyclerView(planinfoRv);
		}
	}
	

}
