package edu.navalkishoreb.tvguide.listing;

import edu.navalkishoreb.tvguide.base.BaseRetry;

/**
 * Created by NavalB on 24-08-2016.
 */

interface ListCallback extends BaseFetching.Callback,
    FetchListingApi.Callback, BaseRetry.Callback,
    BaseListingAdapter.Callback {
}
