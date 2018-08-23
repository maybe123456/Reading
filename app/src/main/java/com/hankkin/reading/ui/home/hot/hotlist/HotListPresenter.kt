package com.hankkin.reading.ui.home.hot.hotlist

import com.hankkin.reading.http.HttpClient
import com.hankkin.library.mvp.presenter.RxLifePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by huanghaijie on 2018/7/8.
 */
class HotListPresenter : RxLifePresenter<HotListContact.IView>(), HotListContact.IPresenter{
    override fun queryKey(page: Int, key: String) {
        HttpClient.Builder.getCommonHttp()
                .query(page,key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNx ({
                    getMvpView().setData(it.data)
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

    override fun getBannerHttp() {
        HttpClient.Builder.getCommonHttp()
                .getHomeBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNx ({
                    getMvpView().setBanner(it.data)
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }


}