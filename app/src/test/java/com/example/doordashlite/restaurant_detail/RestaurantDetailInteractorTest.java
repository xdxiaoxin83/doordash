package com.example.doordashlite.restaurant_detail;

import android.content.Context;

import com.example.doordashlite.model.RestaurantDetail;
import com.example.doordashlite.network.NetworkClient;
import com.google.common.base.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Response;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestaurantDetailInteractorTest {

    @Mock
    RestaurantDetailInteractor.RestaurantDetailPresenter presenter;
    @Mock
    Context context;
    @Mock RestaurantDetail detail;
    @Mock NetworkClient networkClient;
    private RestaurantDetailInteractor interactor;
    private BehaviorSubject<Optional<Response<RestaurantDetail>>> detailSubject = BehaviorSubject.create();

    @Before
    public void setup() {
        initMocks(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        interactor = new RestaurantDetailInteractor();
        interactor.presenter = presenter;
        interactor.networkClient = networkClient;
        interactor.context = context;
        when(networkClient.getRestaurantDetail(anyString())).thenReturn(detailSubject);
    }

    @Test
    public void onBind_whenNetworkReturnDetail_shouldLoad() {
        interactor.bind();
        detailSubject.onNext(Optional.of(Response.success(detail)));
        verify(presenter).update(detail, context);
    }
}
