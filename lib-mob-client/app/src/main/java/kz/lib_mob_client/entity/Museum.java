package kz.lib_mob_client.entity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class Museum extends AbstractFlexibleItem<Museum.ViewHolder> {

    private int id;
    private String name;
    private String description;
    private List<Exhibit> exhibitList = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exhibit> getExhibitList() {
        return exhibitList;
    }

    public void setExhibitList(List<Exhibit> exhibitList) {
        exhibitList = exhibitList;
    }


    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof Museum) {
            Museum inItem = (Museum) inObject;
            return String.valueOf(this.id).equals(inItem.id);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_museum;
    }

    @Override
    public Museum.ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new Museum.ViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, Museum.ViewHolder holder, int position, List<Object> payloads) {
        holder.descriptionTv.setText(description);
        holder.nameTv.setText(name);
        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if (exhibitList.isEmpty()) {
					Toast.makeText(v.getContext(), "Нет экспонатов", Toast.LENGTH_SHORT).show();
				} else {
					holder.foldingCell.toggle(false);
				}
            }
        });
        List<IFlexible> list = new ArrayList<>();
        list.addAll(exhibitList);
        FlexibleAdapter<IFlexible> adapter1 = new FlexibleAdapter<>(list);
        holder.exhibitRv.setAdapter(adapter1);
    }


    public class ViewHolder extends FlexibleViewHolder {
        public TextView nameTv;
        public TextView descriptionTv;
        public TextView titleTv;
        public FoldingCell foldingCell;
        public RecyclerView exhibitRv;

        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            nameTv = view.findViewById(R.id.nameTv);
            descriptionTv = view.findViewById(R.id.descriptionTv);
            titleTv = view.findViewById(R.id.titleTv);
            foldingCell = view.findViewById(R.id.folding_cell);
            exhibitRv = view.findViewById(R.id.exhibitRv);
            //RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
            FlexibleCarouselLayouManager manager = new FlexibleCarouselLayouManager(CarouselLayoutManager.HORIZONTAL);
            manager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
            exhibitRv.setLayoutManager(manager);
            exhibitRv.setHasFixedSize(true);
            exhibitRv.addOnScrollListener(new CenterScrollListener());
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(exhibitRv);
        }
    }

}
