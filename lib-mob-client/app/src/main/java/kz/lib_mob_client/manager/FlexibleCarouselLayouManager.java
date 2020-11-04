package kz.lib_mob_client.manager;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;

import eu.davidea.flexibleadapter.common.IFlexibleLayoutManager;

public class FlexibleCarouselLayouManager extends CarouselLayoutManager implements IFlexibleLayoutManager {

    public FlexibleCarouselLayouManager(int orientation) {
        super(orientation);
    }

    @Override
    public int getSpanCount() {
        return 0;
    }

    @Override
    public int findFirstCompletelyVisibleItemPosition() {
        return 0;
    }

    @Override
    public int findFirstVisibleItemPosition() {
        return 0;
    }

    @Override
    public int findLastCompletelyVisibleItemPosition() {
        return 0;
    }

    @Override
    public int findLastVisibleItemPosition() {
        return 0;
    }
}
