package kz.lib_mob_client.entity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.ramotion.foldingcell.FoldingCell;

import java.io.IOException;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import kz.lib_mob_client.MainActivity;
import kz.lib_mob_client.R;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.fragments.WebViewDialogFragment;
import kz.lib_mob_client.network.ServiceAuth;

public class TechnicalLessonPlan extends AbstractFlexibleItem<TechnicalLessonPlan.ViewHolder> {

	private int id;
	private String branch;
	private String subdivision;
	private String department;
	private int yearOf;
	private String link;
	
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
	public String getSubdivision() {
		return subdivision;
	}
	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof TechnicalLessonPlan) {
			TechnicalLessonPlan inItem = (TechnicalLessonPlan) o;
			return String.valueOf(this.id).equals(inItem.id);
		}
		return false;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_tech_plan;
	}

	@Override
	public ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
		return new TechnicalLessonPlan.ViewHolder(view, adapter);
	}

	@Override
	public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
		holder.branchTV.setText(branch+" "+subdivision);
		holder.departmentTV.setText(department);
		//holder.subdivisionTV.setText(subdivision);
		holder.yearOfTV.setText(String.valueOf(yearOf));
		holder.linkTV.setText("Скачать");
		holder.foldingCell.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctx = v.getContext();
				TokenManager tokenManager = TokenManager.getInstance(ctx.getSharedPreferences("prefs", ctx.MODE_PRIVATE));
				try {
					TechnicalLessonPlanInfo techPlanInfo = ServiceAuth.createService(ServiceApi.class, tokenManager).getTechPlanById(id).execute().body();
					holder.techLessonThemeTv.setText(techPlanInfo.getTechLessonTheme());
					holder.lessonTypeTv.setText(techPlanInfo.getLessonType());
					holder.positionTv.setText(techPlanInfo.getPosition());
					holder.fullNameTv.setText(techPlanInfo.getFullName());
					holder.foldingCell.toggle(false);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		/*holder.departmentTV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Context ctx = view.getContext();
				TokenManager tokenManager = TokenManager.getInstance(ctx.getSharedPreferences("prefs", ctx.MODE_PRIVATE));
				try {
					TechnicalLessonPlanInfo techPlanInfo = ServiceAuth.createService(ServiceApi.class, tokenManager).getTechPlanById(id).execute().body();
					Dialog dialog = new Dialog(techPlanInfo);
					FragmentManager manager = ((MainActivity) ctx).getSupportFragmentManager();
					dialog.show(manager, "dialog");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});*/
		/*holder.linkTV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.i("PLAN", "ON CLICK");
			}
		});*/
	}

	public class ViewHolder extends FlexibleViewHolder {
		public TextView branchTV;
		//public TextView subdivisionTV;
		public TextView yearOfTV;
		public TextView departmentTV;
		public TextView linkTV;
		public TextView titleTv;
		public TextView techLessonThemeTv;
		public TextView lessonTypeTv;
		public TextView positionTv;
		public TextView fullNameTv;
		public FoldingCell foldingCell;

		public ViewHolder(View view, FlexibleAdapter adapter) {
			super(view, adapter);
			branchTV = view.findViewById(R.id.branchTV);
			yearOfTV = view.findViewById(R.id.yearOfTV);
			departmentTV = view.findViewById(R.id.departmentTV);
			linkTV = view.findViewById(R.id.linkTV);
			titleTv = view.findViewById(R.id.titleTV);
			techLessonThemeTv = view.findViewById(R.id.techLessonThemeTV);
			lessonTypeTv = view.findViewById(R.id.lessonTypeTV);
			positionTv = view.findViewById(R.id.positionTV);
			fullNameTv = view.findViewById(R.id.fullNameTV);
			foldingCell = view.findViewById(R.id.folding_cell);
		}
	}

/*	public static class Dialog extends DialogFragment{

		private TextView lessonTypeTV;
		private TextView techLessonThemeTV;
		private TextView positionTV;
		private TextView fullNameTV;
		private TechnicalLessonPlanInfo technicalLessonPlanInfo;

		public Dialog(TechnicalLessonPlanInfo technicalLessonPlanInfo){
			this.technicalLessonPlanInfo = technicalLessonPlanInfo;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {
			return inflater.inflate(R.layout.item_tech_plan_info, container, false);
		}

		@Override
		public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			lessonTypeTV = view.findViewById(R.id.lessonTypeTV);
			techLessonThemeTV = view.findViewById(R.id.techLessonThemeTV);
			positionTV = view.findViewById(R.id.positionTV);
			fullNameTV = view.findViewById(R.id.fullNameTV);
			if (technicalLessonPlanInfo != null) {
				lessonTypeTV.setText(technicalLessonPlanInfo.getLessonType());
				techLessonThemeTV.setText(technicalLessonPlanInfo.getTechLessonTheme());
				positionTV.setText(technicalLessonPlanInfo.getPosition());
				fullNameTV.setText(technicalLessonPlanInfo.getFullName());
			}
		}
	}*/

}
